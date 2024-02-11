package io.github.yvasyliev.deezer.methods;

import io.github.yvasyliev.deezer.objects.Order;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.objects.SearchPage;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class SearchMethod<T extends Pageable> extends AbstractSearchMethod<T, SearchMethod<T>, SearchPage<T>> {
    private final String q;

    public SearchMethod(Function<Map<String, Object>, SearchPage<T>> invoker, Function<Map<String, Object>, CompletableFuture<SearchPage<T>>> asyncInvoker, String q) {
        super(invoker, asyncInvoker);
        this.q = q;
    }

    @Override
    public SearchMethod<T> strict(boolean strict) {
        super.strict(strict);
        return this;
    }

    @Override
    public SearchMethod<T> order(Order order) {
        super.order(order);
        return this;
    }

    @Override
    protected String getQ() {
        return q;
    }
}
