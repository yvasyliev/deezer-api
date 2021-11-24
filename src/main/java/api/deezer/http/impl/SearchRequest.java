package api.deezer.http.impl;

import api.deezer.objects.SearchOrder;

/**
 * Executes Deezer API search request.
 *
 * @param <Response> response POJO type.
 */
public class SearchRequest<Response> extends PaginationRequest<Response> {
    public SearchRequest(String url, String q, Class<Response> responseClass) {
        super(url, responseClass);
        getParams().put("q", q);
    }

    public SearchRequest(String url, Class<Response> responseClass) {
        super(url, responseClass);
    }

    @Override
    public SearchRequest<Response> limit(int limit) {
        return (SearchRequest<Response>) super.limit(limit);
    }

    @Override
    public SearchRequest<Response> offset(int offset) {
        return (SearchRequest<Response>) super.offset(offset);
    }

    /**
     * Adds <b>strict</b> parameter.
     *
     * @return current instance.
     */
    public SearchRequest<Response> strict() {
        getParams().put("strict", "on");
        return this;
    }

    /**
     * Adds <b>order</b> parameter.
     *
     * @param searchOrder search order.
     * @return current instance.
     */
    public SearchRequest<Response> order(SearchOrder searchOrder) {
        getParams().put("order", searchOrder.name());
        return this;
    }
}
