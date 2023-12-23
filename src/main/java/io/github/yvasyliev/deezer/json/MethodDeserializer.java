package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.util.Converter;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.exceptions.ConverterException;
import io.github.yvasyliev.deezer.methods.Method;
import io.github.yvasyliev.deezer.objects.Page;

import java.io.IOException;
import java.lang.reflect.Type;

public class MethodDeserializer<T extends Method<?>, R> extends StdDelegatingDeserializer<T> {
    private final UrlToMethodConverter<T, R> converter;

    public MethodDeserializer() {
        this(new UrlToMethodConverter<>());
    }

    public MethodDeserializer(UrlToMethodConverter<T, R> converter) {
        super(converter);
        this.converter = converter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        DeezerContext deezerContext = getDeezerContext(ctxt);
        this.converter.setDeezerContext(deezerContext);
        this.converter.setResponseType(new TypeReference<R>() {
            @Override
            public Type getType() {
                return deezerContext
                        .getObjectMapper()
                        .getTypeFactory()
                        .constructParametricType(
                                Page.class,
                                property.getType().containedType(0)
                        );
            }
        });
        this.converter.setMethodClass((Class<T>) property.getType().getRawClass());
        return super.createContextual(ctxt, property);
    }

    @Override
    protected StdDelegatingDeserializer<T> withDelegate(Converter<Object, T> converter, JavaType delegateType, JsonDeserializer<?> delegateDeserializer) {
        return new StdDelegatingDeserializer<>(converter, delegateType, delegateDeserializer);
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return super.deserialize(p, ctxt);
        } catch (ConverterException e) {
            throw new IOException(e.getCause());
        }
    }

    private DeezerContext getDeezerContext(DeserializationContext ctxt) {
        try {
            return (DeezerContext) ctxt.findInjectableValue(DeezerContext.class.getName(), null, null);
        } catch (Exception e) {
            return new DeezerContext();
        }
    }
}
