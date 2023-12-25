package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import io.github.yvasyliev.deezer.exceptions.ConverterException;

import java.io.IOException;

public abstract class ThrowingStdConverter<T, R> extends StdConverter<T, R> {
    public abstract R tryConvert(T value) throws IOException;

    @Override
    public R convert(T value) {
        try {
            return tryConvert(value);
        } catch (IOException e) {
            throw new ConverterException(e);
        }
    }
}
