package io.github.yvasyliev.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.util.List;

public class WrappedListDeserializer<T extends Wrappable> extends JsonDeserializer<List<T>> implements ContextualDeserializer {
    private JavaType type;

    @Override
    public List<T> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return ctxt.readValue(p, type);
    }


    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        WrappedListDeserializer<T> deserializer = new WrappedListDeserializer<>();
        deserializer.type = property.getType();
        return deserializer;
    }
}
