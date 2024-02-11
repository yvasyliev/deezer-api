package io.github.yvasyliev.deezer.methods;

import io.github.yvasyliev.deezer.objects.SearchHistory;
import io.github.yvasyliev.deezer.objects.SearchHistoryPage;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class SearchHistoryMethod extends AbstractPagingMethod<SearchHistory, SearchHistoryMethod, SearchHistoryPage> {
    public SearchHistoryMethod(Function<Map<String, Object>, SearchHistoryPage> invoker, Function<Map<String, Object>, CompletableFuture<SearchHistoryPage>> asyncInvoker, String accessToken) {
        super(invoker, asyncInvoker, accessToken);
    }

    @Override
    public SearchHistoryMethod index(Integer index) {
        super.index(index);
        return this;
    }

    @Override
    public SearchHistoryMethod limit(Integer limit) {
        super.limit(limit);
        return this;
    }
}
