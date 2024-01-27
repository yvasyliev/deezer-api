package io.github.yvasyliev.deezer.methods;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@AllArgsConstructor
public class Method<T> {
    private transient final Function<Map<String, Object>, T> invoker;
    private transient final Function<Map<String, Object>, CompletableFuture<T>> asyncInvoker;

    public T execute() {
        return invoker.apply(getQueryParams());
    }

    public CompletableFuture<T> executeAsync() {
        return asyncInvoker.apply(getQueryParams());
    }

    protected Map<String, Object> getQueryParams() {
        return new HashMap<>();
    }
}
