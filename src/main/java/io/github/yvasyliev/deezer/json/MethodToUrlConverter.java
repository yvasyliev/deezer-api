package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import io.github.yvasyliev.deezer.methods.Method;

import java.net.URL;

public class MethodToUrlConverter<T> extends StdConverter<Method<T>, URL> {
    @Override
    public URL convert(Method<T> value) {
        return value.getUrl();
    }
}
