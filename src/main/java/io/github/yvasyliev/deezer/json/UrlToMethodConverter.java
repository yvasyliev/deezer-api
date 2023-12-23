package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.exceptions.ConverterException;
import io.github.yvasyliev.deezer.methods.Method;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

@Setter
public class UrlToMethodConverter<T extends Method<?>, R> extends StdConverter<URL, T> {
    private DeezerContext deezerContext;
    private TypeReference<R> responseType;
    private Class<T> methodClass;

    @Override
    public T convert(URL url) throws ConverterException {
        try {
            return methodClass
                    .getConstructor(DeezerContext.class, String.class, TypeReference.class)
                    .newInstance(deezerContext, url.getPath(), responseType);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException e) {
            throw new ConverterException(e);
        }
    }
}
