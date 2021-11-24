package api.deezer.http.impl;

import java.util.Map;

/**
 * Executes Deezer API request with pagination.
 *
 * @param <Response> response POJO type.
 */
public class PaginationRequest<Response> extends DeezerGetRequest<Response> {
    public PaginationRequest(String url, Class<Response> responseClass) {
        super(url, responseClass);
    }

    public PaginationRequest(String url, Map<String, String> params, Class<Response> responseClass) {
        super(url, params, responseClass);
    }

    /**
     * Adds <b>limit</b> parameter to the request.
     *
     * @param limit limit value.
     * @return current instance.
     */
    public PaginationRequest<Response> limit(int limit) {
        getParams().put("limit", String.valueOf(limit));
        return this;
    }

    /**
     * Adds <b>offset</b> parameter to the request.
     *
     * @param offset offset value.
     * @return current instance.
     */
    public PaginationRequest<Response> offset(int offset) {
        getParams().put("offset", String.valueOf(offset));
        return this;
    }
}
