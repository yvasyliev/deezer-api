package api.deezer.http;

import okhttp3.Request;

import java.util.Collections;
import java.util.Map;

/**
 * Executes Deezer API GET request.
 *
 * @param <Answer> response POJO type.
 */
public class DeezerGetRequest<Answer> extends DeezerRequest<Answer> {
    public DeezerGetRequest(String url, Class<Answer> answerClass) {
        this(url, Collections.emptyMap(), answerClass);
    }

    public DeezerGetRequest(String url, Map<String, String> params, Class<Answer> answerClass) {
        super(url, params, answerClass);
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().get().build();
    }
}
