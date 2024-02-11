package io.github.yvasyliev.deezer.json.deserializers;

import io.github.yvasyliev.deezer.methods.AbstractSearchMethod;
import io.github.yvasyliev.deezer.objects.AbstractPage;
import io.github.yvasyliev.deezer.objects.Order;
import io.github.yvasyliev.deezer.objects.Pageable;

import java.util.Map;

public abstract class AbstractSearchMethodDeserializer<T extends Pageable, M extends AbstractSearchMethod<T, M, P>, P extends AbstractPage<T, M, P>> extends AbstractPagingMethodDeserializer<T, M, P> {
    @Override
    protected void setQueryParams(M searchMethod, Map<String, String> queryParams) {
        searchMethod
                .strict(queryParams.containsKey(AbstractSearchMethod.STRICT) ? Boolean.TRUE : Boolean.FALSE)
                .order(queryParams.containsKey(AbstractSearchMethod.ORDER) ? Order.valueOf(queryParams.get(AbstractSearchMethod.ORDER)) : null);
        super.setQueryParams(searchMethod, queryParams);
    }
}
