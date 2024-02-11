package io.github.yvasyliev.deezer.json.deserializers;

import io.github.yvasyliev.deezer.methods.Method;
import io.github.yvasyliev.deezer.methods.SearchHistoryMethod;
import io.github.yvasyliev.deezer.objects.SearchHistory;
import io.github.yvasyliev.deezer.objects.SearchHistoryPage;
import lombok.Setter;

import java.util.Map;
import java.util.function.Function;

@Setter
public class SearchHistoryMethodDeserializer extends AbstractPagingMethodDeserializer<SearchHistory, SearchHistoryMethod, SearchHistoryPage> {
    private Function<String, SearchHistoryMethod> searchHistoryMethodFactory;

    @Override
    protected SearchHistoryMethod createPagingMethod(String path, Map<String, String> queryParams) {
        return searchHistoryMethodFactory.apply(queryParams.get(Method.ACCESS_TOKEN));
    }
}
