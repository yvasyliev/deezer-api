package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.github.yvasyliev.deezer.methods.params.AdvancedSearchQuery;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class AdvancedSearchQuerySerializer extends StdSerializer<AdvancedSearchQuery> {
    private final TypeReference<Map<String, Object>> mapTypeReference;

    private final JsonMapper jsonMapper;

    public AdvancedSearchQuerySerializer() {
        super(AdvancedSearchQuery.class);
        this.mapTypeReference = new TypeReference<Map<String, Object>>() {
        };
        this.jsonMapper = JsonMapper
                .builder()
                .annotationIntrospector(new JacksonAnnotationIntrospector() {
                    @Override
                    public Object findSerializer(Annotated a) {
                        return null;
                    }
                })
                .build();
    }

    @Override
    public void serialize(AdvancedSearchQuery advancedSearchQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String query = jsonMapper
                .convertValue(advancedSearchQuery, mapTypeReference)
                .entrySet()
                .stream()
                .map(entry -> {
                    Object value = entry.getValue();
                    return entry.getKey() + ":" + (value instanceof String ? "\"" + value + "\"" : value);
                })
                .collect(Collectors.joining(" "));
        jsonGenerator.writeString(query);
    }
}
