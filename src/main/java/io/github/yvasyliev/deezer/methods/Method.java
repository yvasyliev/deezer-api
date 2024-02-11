package io.github.yvasyliev.deezer.methods;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@AllArgsConstructor
public class Method<T> {
    public static final String ACCESS_TOKEN = "access_token";
    private transient final Function<Map<String, Object>, T> invoker;
    private transient final Function<Map<String, Object>, CompletableFuture<T>> asyncInvoker;
    private final String accessToken;

    public Method(Function<Map<String, Object>, T> invoker, Function<Map<String, Object>, CompletableFuture<T>> asyncInvoker) {
        this(invoker, asyncInvoker, null);
    }

    public T execute() {
        return invoker.apply(getQueryParams());
    }

    public CompletableFuture<T> executeAsync() {
        return asyncInvoker.apply(getQueryParams());
    }

    protected Map<String, Object> getQueryParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put(ACCESS_TOKEN, accessToken);
        return queryParams;
    }
}
