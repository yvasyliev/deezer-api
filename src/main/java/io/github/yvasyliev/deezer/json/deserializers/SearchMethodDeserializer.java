package io.github.yvasyliev.deezer.json.deserializers;

import io.github.yvasyliev.deezer.json.exceptions.SearchMethodDeserializeException;
import io.github.yvasyliev.deezer.methods.AbstractSearchMethod;
import io.github.yvasyliev.deezer.methods.SearchMethod;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.objects.SearchPage;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.function.Function;

@AllArgsConstructor
public class SearchMethodDeserializer<T extends Pageable> extends AbstractSearchMethodDeserializer<T, SearchMethod<T>, SearchPage<T>> {
    private final Map<String, Function<String, ? extends SearchMethod<? extends Pageable>>> searchMethodFactories;

    @SuppressWarnings("unchecked")
    @Override
    protected SearchMethod<T> createPagingMethod(String path, Map<String, String> queryParams) {
        for (Map.Entry<String, Function<String, ? extends SearchMethod<? extends Pageable>>> searchMethodFactory : searchMethodFactories.entrySet()) {
            if (searchMethodFactory.getKey().equals(path)) {
                return (SearchMethod<T>) searchMethodFactory.getValue().apply(queryParams.get(AbstractSearchMethod.Q));
            }
        }
        throw new SearchMethodDeserializeException(path, queryParams);
    }
}
