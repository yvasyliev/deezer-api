package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.util.Converter;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Pageable;

import java.lang.reflect.Type;

public class PagingMethodDeserializer<T extends Pageable> extends StdDelegatingDeserializer<PagingMethod<T>> {
    private final UrlToPagingMethodConverter<T> converter;

    public PagingMethodDeserializer() {
        this(new UrlToPagingMethodConverter<>());
    }

    public PagingMethodDeserializer(UrlToPagingMethodConverter<T> converter) {
        super(converter);
        this.converter = converter;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        DeezerContext context = getDeezerContext(ctxt);
        ObjectMapper mapper = context != null ? context.getObjectMapper() : new ObjectMapper();
        InjectableValues.Std injectableValues = (InjectableValues.Std) mapper.getInjectableValues();
        injectableValues.addValue(TypeReference.class, new TypeReference<T>() {
            @Override
            public Type getType() {
                return property.getType().getSuperClass().containedType(0);
            }
        });
        converter.setObjectMapper(mapper);
        return super.createContextual(ctxt, property);
    }

    @Override
    protected StdDelegatingDeserializer<PagingMethod<T>> withDelegate(Converter<Object, PagingMethod<T>> converter, JavaType delegateType, JsonDeserializer<?> delegateDeserializer) {
        return new StdDelegatingDeserializer<>(converter, delegateType, delegateDeserializer);
    }

    public DeezerContext getDeezerContext(DeserializationContext ctxt) {
        try {
            return (DeezerContext) ctxt.findInjectableValue(DeezerContext.class.getName(), null, null);
        } catch (Exception e) {
            return null;
        }
    }
}
