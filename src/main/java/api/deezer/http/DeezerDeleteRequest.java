package api.deezer.http;

import okhttp3.Request;

import java.util.Map;

/**
 * Executes Deezer API DELETE request.
 *
 * @param <Answer> response POJO type.
 */
public class DeezerDeleteRequest<Answer> extends DeezerRequest<Answer> {
    public DeezerDeleteRequest(String url, Map<String, String> params, Class<Answer> answerClass) {
        super(url, params, answerClass);
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().delete().build();
    }
}
