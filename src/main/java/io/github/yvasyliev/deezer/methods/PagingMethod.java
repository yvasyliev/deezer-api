package io.github.yvasyliev.deezer.methods;

import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

@Setter
@Accessors(fluent = true)
public class PagingMethod<T extends Pageable> extends Method<Page<T>> {
    public static final String INDEX = "index";

    public static final String LIMIT = "limit";

    private Integer index;

    private Integer limit;

    public PagingMethod(
            Function<Map<String, Object>, Page<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker
    ) {
        super(invoker, asyncInvoker);
    }

    public static <T extends Pageable> PagingMethod<T> of(
            BiFunction<Long, Map<String, Object>, Page<T>> invoker,
            BiFunction<Long, Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker,
            long objectId
    ) {
        return new PagingMethod<>(
                queryParams -> invoker.apply(objectId, queryParams),
                queryParams -> asyncInvoker.apply(objectId, queryParams)
        );
    }

    @Override
    protected Map<String, Object> getQueryParams() {
        Map<String, Object> queryParams = super.getQueryParams();
        queryParams.put(INDEX, index);
        queryParams.put(LIMIT, limit);
        return queryParams;
    }
}
