package io.github.yvasyliev.deezer.json.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.github.yvasyliev.deezer.methods.AbstractPagingMethod;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.AbstractPage;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.util.UrlUtil;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;

public abstract class AbstractPagingMethodDeserializer<T extends Pageable, M extends AbstractPagingMethod<T, M, P>, P extends AbstractPage<T, M, P>> implements JsonDeserializer<M> {
    @Override
    public M deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        URL url = context.deserialize(json, URL.class);
        Map<String, String> queryParams = UrlUtil.getQueryParams(url);
        M pagingMethod = createPagingMethod(url.getPath(), queryParams);
        setQueryParams(pagingMethod, queryParams);
        return pagingMethod;
    }

    protected abstract M createPagingMethod(String path, Map<String, String> queryParams);

    protected void setQueryParams(M pagingMethod, Map<String, String> queryParams) {
        pagingMethod
                .index(integer(queryParams.get(PagingMethod.INDEX)))
                .limit(integer(queryParams.get(PagingMethod.LIMIT)));
    }

    protected Integer integer(String value) {
        return value != null ? Integer.parseInt(value) : null;
    }
}
