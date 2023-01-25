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
        this.urlBuilder.addQueryParameter("q", q);
    }

    public SearchRequest(String url, Class<Answer> answerClass) {
        super(url, answerClass);
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
        this.urlBuilder.addQueryParameter("strict", "on");
        return this;
    }

    /**
     * Adds <b>order</b> parameter.
     *
     * @param searchOrder search order.
     * @return current instance.
     */
    public SearchRequest<Answer> order(SearchOrder searchOrder) {
        this.urlBuilder.addQueryParameter("order", searchOrder.name());
        return this;
    }
}
