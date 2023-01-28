package api.deezer.http;

import api.deezer.objects.SearchOrder;

/**
 * Executes Deezer API search request.
 *
 * @param <Answer> response POJO type.
 */
public class SearchRequest<Answer> extends PagingRequest<Answer> {
    public SearchRequest(String url, String q, Class<Answer> answerClass) {
        super(url, answerClass);
        addParam("q", q);
    }

    public SearchRequest(String url, Class<Answer> answerClass) {
        super(url, answerClass);
    }

    @Override
    public SearchRequest<Answer> addParam(String name, String value) {
        return (SearchRequest<Answer>) super.addParam(name, value);
    }

    @Override
    public SearchRequest<Answer> limit(int limit) {
        return (SearchRequest<Answer>) super.limit(limit);
    }

    @Override
    public SearchRequest<Answer> index(int index) {
        return (SearchRequest<Answer>) super.index(index);
    }

    /**
     * Adds <b>strict</b> parameter.
     *
     * @return current instance.
     */
    public SearchRequest<Answer> strict() {
        return addParam("strict", "on");
    }

    /**
     * Adds <b>order</b> parameter.
     *
     * @param searchOrder search order.
     * @return current instance.
     */
    public SearchRequest<Answer> order(SearchOrder searchOrder) {
        return addParam("order", searchOrder.name());
    }
}
