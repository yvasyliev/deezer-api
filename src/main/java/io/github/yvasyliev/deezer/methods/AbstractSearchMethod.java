package io.github.yvasyliev.deezer.methods;

import io.github.yvasyliev.deezer.objects.AbstractPage;
import io.github.yvasyliev.deezer.objects.Order;
import io.github.yvasyliev.deezer.objects.Pageable;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Setter
@Accessors(fluent = true)
public abstract class AbstractSearchMethod<T extends Pageable, M extends AbstractSearchMethod<T, M, P>, P extends AbstractPage<T, M, P>> extends AbstractPagingMethod<T, M, P> {
    public static final String Q = "Q";
    public static final String STRICT = "strict";
    public static final String ORDER = "order";
    private static final String ON = "on";

    private boolean strict;
    private Order order;

    public AbstractSearchMethod(Function<Map<String, Object>, P> invoker, Function<Map<String, Object>, CompletableFuture<P>> asyncInvoker) {
        super(invoker, asyncInvoker);
    }

    protected abstract String getQ();

    @Override
    protected Map<String, Object> getQueryParams() {
        Map<String, Object> queryParams = super.getQueryParams();
        queryParams.put(Q, getQ());
        if (strict) {
            queryParams.put(STRICT, ON);
        }
        if (order != null) {
            queryParams.put(ORDER, order.name());
        }
        return queryParams;
    }
}
