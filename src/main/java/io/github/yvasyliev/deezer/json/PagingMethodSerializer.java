package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.util.Converter;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.exceptions.ConverterException;
import io.github.yvasyliev.deezer.objects.Pageable;

import java.io.IOException;

public class PagingMethodSerializer<T extends Pageable> extends StdDelegatingSerializer {
    private final PagingMethodToUrlConverter<T> converter;

    public PagingMethodSerializer() {
        this(new PagingMethodToUrlConverter<>());
    }

    public PagingMethodSerializer(PagingMethodToUrlConverter<T> converter) {
        super(converter);
        this.converter = converter;
    }

    @Override
    protected StdDelegatingSerializer withDelegate(Converter<Object, ?> converter, JavaType delegateType, JsonSerializer<?> delegateSerializer) {
        return new StdDelegatingSerializer(converter, delegateType, delegateSerializer);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        DeezerContext context = (DeezerContext) provider.getAttribute(DeezerContext.class);
        converter.setObjectMapper(context != null ? context.getObjectMapper() : new ObjectMapper());
        return super.createContextual(provider, property);
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        try {
            super.serialize(value, gen, provider);
        } catch (ConverterException e) {
            throw e.getCause();
        }
    }
}
