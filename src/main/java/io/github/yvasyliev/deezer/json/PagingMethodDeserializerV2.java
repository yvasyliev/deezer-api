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
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Pageable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.function.Supplier;

public class PagingMethodDeserializerV2<T extends Pageable> extends StdDeserializer<PagingMethod<T>> implements ContextualDeserializer {
    private final ObjectMapper objectMapper;

    public PagingMethodDeserializerV2() {
        this(null, null);
    }

    protected PagingMethodDeserializerV2(JavaType valueType, ObjectMapper objectMapper) {
        super(valueType);
        this.objectMapper = objectMapper;
    }

    @Override
    public PagingMethod<T> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        URL url = p.readValueAs(URL.class);
        return objectMapper.convertValue(
                objectMapper
                        .convertValue(URLHelper.getQueryParams(url), ObjectNode.class)
                        .put("path", url.getPath()),
                getValueType()
        );
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        ObjectMapper mapper = getDeezerContext(ctxt).getObjectMapper().copy();
        return new PagingMethodDeserializerV2<>(
                property.getType(),
                mapper.setInjectableValues(new CompositeValuesComposite(
                        mapper.getInjectableValues(),
                        new InjectableValues.Std().addValue(TypeReference.class, new TypeReference<T>() {
                            @Override
                            public Type getType() {
                                return getValueType().getSuperClass().containedType(0);
                            }
                        })
                ))
        );
    }

    public DeezerContext getDeezerContext(DeserializationContext ctxt) {
        return getDeezerContext(ctxt, DeezerContext::new);
    }

    public DeezerContext getDeezerContext(DeserializationContext ctxt, Supplier<DeezerContext> defaultDeezerContextSupplier) {
        try {
            return (DeezerContext) ctxt.findInjectableValue(DeezerContext.class.getName(), null, null);
        } catch (Exception e) {
            return defaultDeezerContextSupplier.get();
        }
    }
}
