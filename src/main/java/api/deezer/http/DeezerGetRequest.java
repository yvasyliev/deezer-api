package api.deezer.http;

import okhttp3.Request;

/**
 * Executes Deezer API GET request.
 *
 * @param <Answer> response POJO type.
 */
public class DeezerGetRequest<Answer> extends DeezerRequest<Answer> {
    public DeezerGetRequest(String url, Class<Answer> answerClass) {
        super(url, answerClass);
    }

    @Override
    public DeezerGetRequest<Answer> addParam(String name, String value) {
        return (DeezerGetRequest<Answer>) super.addParam(name, value);
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().get().build();
    }
}
