package api.deezer.http.impl;

import api.deezer.converters.Converter;

import java.util.HashMap;
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

    public PaginationRequest(String url, Converter<String, Response> responseConverter) {
        super(url, new HashMap<>(), responseConverter);
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
     * Adds <b>index</b> parameter to the request.
     *
     * @param index index value.
     * @return current instance.
     */
    public PaginationRequest<Response> index(int index) {
        getParams().put("index", String.valueOf(index));
        return this;
    }
}
