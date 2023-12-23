package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CompositeValuesComposite extends InjectableValues {
    @NonNull
    private final InjectableValues first;

    @NonNull
    private final InjectableValues second;

    @Override
    public Object findInjectableValue(Object valueId, DeserializationContext ctxt, BeanProperty forProperty, Object beanInstance) throws JsonMappingException {
        try {
            return first.findInjectableValue(valueId, ctxt, forProperty, beanInstance);
        } catch (Exception e) {
            return second.findInjectableValue(valueId, ctxt, forProperty, beanInstance);
        }
    }
}
