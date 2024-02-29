package io.github.yvasyliev.deezer.v2.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.v2.methods.AbstractPagingMethod;
import io.github.yvasyliev.deezer.v2.methods.Method;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class AbstractPagingMethodDeserializer implements JsonDeserializer<AbstractPagingMethod<? extends Pageable, ?>> {
    @Delegate
    private final Map<Pattern, Class<? extends AbstractPagingMethod<? extends Pageable, ?>>> abstractPagingMethodTypes;

    public AbstractPagingMethodDeserializer() {
        this(new HashMap<>());
    }

    @Override
    public AbstractPagingMethod<? extends Pageable, ?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        URI uri = URI.create(json.getAsString());
        String path = uri.getPath();
        JsonObject queryParams = getQueryParams(uri.getQuery());

        for (Map.Entry<Pattern, Class<? extends AbstractPagingMethod<? extends Pageable, ?>>> abstractPagingMethodType : abstractPagingMethodTypes.entrySet()) {
            Matcher matcher = abstractPagingMethodType.getKey().matcher(path);
            if (matcher.matches()) {
                if (matcher.groupCount() > 0) {
                    queryParams.addProperty(Method.OBJECT_ID, matcher.group(1));
                }
                return context.deserialize(queryParams, abstractPagingMethodType.getValue());
            }
        }

        throw new JsonParseException("No deserializer was found found for " + uri);
    }

    protected JsonObject getQueryParams(String query) {
        JsonObject queryParams = new JsonObject();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] keyVal = param.split("=");
                queryParams.addProperty(keyVal[0], keyVal[1]);
            }
        }
        return queryParams;
    }
}
