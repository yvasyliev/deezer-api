package api.deezer.http.impl;

import api.deezer.converters.PojoConverter;
import api.deezer.http.HttpRequestFilePart;

import java.util.HashMap;
import java.util.Map;

/**
 * Executes Deezer API POST request.
 *
 * @param <Response> response POJO type.
 */
public class DeezerPostRequest<Response> extends DeezerRequest<Response> {
    public DeezerPostRequest(String url, Class<Response> responseClass) {
        this(url, new HashMap<>(), responseClass);
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Response> responseClass) {
        super(url, params, responseClass);
        params.put("request_method", "post");
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Response> responseClass, HttpRequestFilePart[] parts) {
        super(url, params, new PojoConverter<>(responseClass), parts);
        params.put("request_method", "post");
    }

}
