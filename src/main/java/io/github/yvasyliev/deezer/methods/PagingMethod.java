package io.github.yvasyliev.deezer.methods;

import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class PagingMethod<T extends Pageable> extends AbstractPagingMethod<T, PagingMethod<T>, Page<T>> {
    public PagingMethod(
            Function<Map<String, Object>, Page<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker
    ) {
        super(invoker, asyncInvoker);
    }

    public PagingMethod(
            Function<Map<String, Object>, Page<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker,
            String accessToken
    ) {
        super(invoker, asyncInvoker, accessToken);
    }

    @Override
    public PagingMethod<T> index(Integer index) {
        super.index(index);
        return this;
    }

    @Override
    public PagingMethod<T> limit(Integer limit) {
        super.limit(limit);
        return this;
    }
}
