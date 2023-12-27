package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.helpers.URLHelper;
import io.github.yvasyliev.deezer.methods.Method;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;

public class MethodDeserializer<T> extends StdDeserializer<Method<T>> implements ContextualDeserializer {
    public MethodDeserializer() {
        this(null);
    }

    protected MethodDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public Method<T> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JavaType valueType = getValueType();
        DeezerContext context = (DeezerContext) ctxt.findInjectableValue(DeezerContext.class.getName(), null, null);
        ObjectMapper objectMapper = context.getObjectMapper();
        InjectableValues.Std injectableValues = (InjectableValues.Std) objectMapper.getInjectableValues();
        injectableValues.addValue(TypeReference.class, new TypeReference<T>() {
            @Override
            public Type getType() {
                return valueType.findSuperType(Method.class).containedType(0);
            }
        });
        URL url = p.readValueAs(URL.class);
        return objectMapper.convertValue(
                objectMapper
                        .convertValue(URLHelper.getQueryParams(url), ObjectNode.class)
                        .put("path", url.getPath()),
                valueType
        );
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        return new MethodDeserializer<>(property.getType());
    }
}
