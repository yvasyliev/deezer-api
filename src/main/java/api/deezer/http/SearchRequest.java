package api.deezer.http;

import api.deezer.objects.SearchOrder;

/**
 * Executes Deezer API search request.
 *
 * @param <T> response POJO type.
 */
public class SearchRequest<T> extends PagingRequest<T> {
    public SearchRequest(String url, String q, Class<T> answerClass) {
        super(url, answerClass);
        addParam("q", q);
    }

    public SearchRequest(String url, Class<T> answerClass) {
        super(url, answerClass);
    }

    @Override
    public SearchRequest<T> addParam(String name, String value) {
        return (SearchRequest<T>) super.addParam(name, value);
    }

    @Override
    public SearchRequest<T> limit(int limit) {
        return (SearchRequest<T>) super.limit(limit);
    }

    @Override
    public SearchRequest<T> index(int index) {
        return (SearchRequest<T>) super.index(index);
    }

    /**
     * Adds <b>strict</b> parameter.
     *
     * @return current instance.
     */
    public SearchRequest<T> strict() {
        return addParam("strict", "on");
    }

    /**
     * Adds <b>order</b> parameter.
     *
     * @param searchOrder search order.
     * @return current instance.
     */
    public SearchRequest<T> order(SearchOrder searchOrder) {
        return addParam("order", searchOrder.name());
    }
}
