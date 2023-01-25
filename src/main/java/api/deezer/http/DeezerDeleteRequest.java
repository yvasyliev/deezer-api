package api.deezer.http;

import okhttp3.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Executes Deezer API DELETE request.
 *
 * @param <Answer> response POJO type.
 */
public class DeezerDeleteRequest<Answer> extends DeezerRequest<Answer> {
    public DeezerDeleteRequest(String url, Class<Answer> responseClass) {
        this(url, new HashMap<>(), responseClass);
    }

    public DeezerDeleteRequest(String url, Map<String, String> params, Class<Answer> responseClass) {
        super(url, params, responseClass);
        params.put("request_method", "delete");
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().delete().build();
    }
}
