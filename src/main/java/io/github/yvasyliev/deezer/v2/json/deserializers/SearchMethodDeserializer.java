package io.github.yvasyliev.deezer.v2.json.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.yvasyliev.deezer.v2.methods.AbstractSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.Method;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class SearchMethodDeserializer extends AbstractMethodDeserializer {
    @Delegate
    private final Map<String, Class<? extends AbstractSearchMethod<?, ?>>> searchMethodClassMap;

    public SearchMethodDeserializer() {
        this(new HashMap<>());
    }

    @Override
    public Method<?> deserialize(JsonDeserializationContext context, String path, JsonObject queryParams) throws JsonParseException {
        for (Map.Entry<String, Class<? extends AbstractSearchMethod<?, ?>>> searchMethodClassEntry : searchMethodClassMap.entrySet()) {
            if (searchMethodClassEntry.getKey().equals(path)) {
                return context.deserialize(queryParams, searchMethodClassEntry.getValue());
            }
        }

        return null;
    }
}
