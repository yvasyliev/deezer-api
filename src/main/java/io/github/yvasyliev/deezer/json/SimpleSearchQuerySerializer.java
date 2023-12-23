package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.github.yvasyliev.deezer.methods.params.SimpleSearchQuery;

import java.io.IOException;

public class SimpleSearchQuerySerializer extends StdSerializer<SimpleSearchQuery> {
    public SimpleSearchQuerySerializer() {
        super(SimpleSearchQuery.class);
    }

    @Override
    public void serialize(SimpleSearchQuery simpleSearchQuery, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String query = simpleSearchQuery.getQuery();
        jsonGenerator.writeString(query != null ? query : "");
    }
}
