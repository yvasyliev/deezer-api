package io.github.yvasyliev.deezer.methods;

import io.github.yvasyliev.deezer.objects.AbstractPage;
import io.github.yvasyliev.deezer.objects.Pageable;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Setter
@Accessors(fluent = true)
public abstract class AbstractPagingMethod<T extends Pageable, M extends AbstractPagingMethod<T, M, P>, P extends AbstractPage<T, M, P>> extends Method<P> {
    public static final String INDEX = "index";

    public static final String LIMIT = "limit";

    private Integer index;

    private Integer limit;

    public AbstractPagingMethod(
            Function<Map<String, Object>, P> invoker,
            Function<Map<String, Object>, CompletableFuture<P>> asyncInvoker
    ) {
        super(invoker, asyncInvoker);
    }

    public AbstractPagingMethod(
            Function<Map<String, Object>, P> invoker,
            Function<Map<String, Object>, CompletableFuture<P>> asyncInvoker,
            String accessToken
    ) {
        super(invoker, asyncInvoker, accessToken);
    }

    @Override
    protected Map<String, Object> getQueryParams() {
        Map<String, Object> queryParams = super.getQueryParams();
        queryParams.put(INDEX, index);
        queryParams.put(LIMIT, limit);
        return queryParams;
    }
}
