package api.deezer.http.impl;

import api.deezer.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Executes Deezer API GET request.
 *
 * @param <Response> response POJO type.
 */
public class DeezerGetRequest<Response> extends DeezerRequest<Response> {
    public DeezerGetRequest(String url, Class<Response> responseClass) {
        this(url, new HashMap<>(), responseClass);
    }

    public DeezerGetRequest(String url, Map<String, String> params, Class<Response> responseClass) {
        super(url, params, responseClass);
        params.put("request_method", "get");
    }

    public DeezerGetRequest(String url, Map<String, String> params, Function<String, Response> responseConverter) {
        super(url, params, responseConverter);
        params.put("request_method", "get");
    }

    public DeezerGetRequest(String url, Map<String, String> params, Predicate<HttpResponse> responseValidator, Function<String, Response> responseConverter) {
        super(url, params, responseValidator, responseConverter);
        params.put("request_method", "get");
    }
}
