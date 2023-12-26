package io.github.yvasyliev.deezer.http;

import java.io.IOException;

@FunctionalInterface
public interface Interceptor<T> {
    T intercept(T t) throws IOException;
}
