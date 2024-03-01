package io.github.yvasyliev.deezer.v2.json.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.yvasyliev.deezer.v2.methods.Method;

import java.lang.reflect.Type;
import java.net.URI;

public abstract class AbstractMethodDeserializer implements JsonDeserializer<Method<?>> {
    @Override
    public Method<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        URI uri = URI.create(json.getAsString());
        String path = uri.getPath();
        JsonObject queryParams = getQueryParams(uri.getQuery());
        Method<?> deserialized = deserialize(context, path, queryParams);
        if (deserialized == null) {
            throw new JsonParseException("No deserializer was found found for " + uri);
        }
        return deserialized;
    }

    public abstract Method<?> deserialize(JsonDeserializationContext context, String path, JsonObject queryParams) throws JsonParseException;

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
