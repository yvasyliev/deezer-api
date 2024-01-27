package io.github.yvasyliev.deezer.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.util.UrlUtil;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import lombok.AllArgsConstructor;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class PagingMethodDeserializer implements JsonDeserializer<PagingMethod<? extends Pageable>> {
    private final Map<Pattern, Function<Long, ? extends PagingMethod<? extends Pageable>>> pagingMethodFactories;

    @Override
    public PagingMethod<? extends Pageable> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        URL url = context.deserialize(json, URL.class);
        return setMethodParams(
                createPagingMethod(url.getPath()),
                UrlUtil.getQueryParams(url)
        );
    }

    protected PagingMethod<? extends Pageable> createPagingMethod(String path) {
        for (Map.Entry<Pattern, Function<Long, ? extends PagingMethod<? extends Pageable>>> pagingMethodFactory : pagingMethodFactories.entrySet()) {
            Matcher matcher = pagingMethodFactory.getKey().matcher(path);
            if (matcher.matches()) {
                long objectId = Long.parseLong(matcher.group(1));
                return pagingMethodFactory.getValue().apply(objectId);
            }
        }
        throw new JsonParseException("Unhandled paging method: " + path);
    }

    protected Integer integer(String value) {
        return value != null ? Integer.parseInt(value) : null;
    }

    protected PagingMethod<? extends Pageable> setMethodParams(PagingMethod<? extends Pageable> pagingMethod, Map<String, String> queryParams) {
        return pagingMethod
                .index(integer(queryParams.get(PagingMethod.INDEX)))
                .limit(integer(queryParams.get(PagingMethod.LIMIT)));
    }
}
