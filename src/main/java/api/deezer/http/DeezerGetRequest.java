package api.deezer.http;

import okhttp3.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Executes Deezer API GET request.
 *
 * @param <Answer> response POJO type.
 */
public class DeezerGetRequest<Answer> extends DeezerRequest<Answer> {
    public DeezerGetRequest(String url, Class<Answer> responseClass) {
        this(url, new HashMap<>(), responseClass);
    }

    public DeezerGetRequest(String url, Map<String, String> params, Class<Answer> responseClass) {
        super(url, params, responseClass);
        params.put("request_method", "get");
    }

    public DeezerGetRequest(String url, Map<String, String> params, Function<String, Answer> responseConverter) {
        super(url, params, responseConverter);
        params.put("request_method", "get");
    }

    public DeezerGetRequest(String url, Map<String, String> params, Predicate<String> responseValidator, Function<String, Answer> responseConverter) {
        super(url, params, responseValidator, responseConverter);
        params.put("request_method", "get");
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().get().build();
    }
}
