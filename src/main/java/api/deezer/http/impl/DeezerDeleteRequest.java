package api.deezer.http.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Executes Deezer API DELETE request.
 *
 * @param <Response> response POJO type.
 */
public class DeezerDeleteRequest<Response> extends DeezerRequest<Response> {
    public DeezerDeleteRequest(String url, Class<Response> responseClass) {
        this(url, new HashMap<>(), responseClass);
    }

    public DeezerDeleteRequest(String url, Map<String, String> params, Class<Response> responseClass) {
        super(url, params, responseClass);
        params.put("request_method", "delete");
    }
}
