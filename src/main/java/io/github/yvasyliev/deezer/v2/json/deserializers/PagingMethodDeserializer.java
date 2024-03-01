package io.github.yvasyliev.deezer.v2.json.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.v2.methods.Method;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class PagingMethodDeserializer extends AbstractMethodDeserializer {
    @Delegate
    private final Map<Pattern, Class<? extends PagingMethod<? extends Pageable>>> commonPagingMethodClassMap;

    public PagingMethodDeserializer() {
        this(new HashMap<>());
    }

    @Override
    public Method<?> deserialize(JsonDeserializationContext context, String path, JsonObject queryParams) throws JsonParseException {
        for (Map.Entry<Pattern, Class<? extends PagingMethod<? extends Pageable>>> commonPagingMethodClassEntry : commonPagingMethodClassMap.entrySet()) {
            Matcher matcher = commonPagingMethodClassEntry.getKey().matcher(path);
            if (matcher.matches()) {
                if (matcher.groupCount() > 0) {
                    queryParams.addProperty(Method.OBJECT_ID, matcher.group(1));
                }
                return context.deserialize(queryParams, commonPagingMethodClassEntry.getValue());
            }
        }

        return null;
    }
}
