package api.deezer.http;

import okhttp3.Request;

/**
 * Executes Deezer API DELETE request.
 *
 * @param <Answer> response POJO type.
 */
public class DeezerDeleteRequest<Answer> extends DeezerRequest<Answer> {
    public DeezerDeleteRequest(String url, Class<Answer> answerClass) {
        super(url, answerClass);
    }

    @Override
    public DeezerDeleteRequest<Answer> addParam(String name, String value) {
        return (DeezerDeleteRequest<Answer>) super.addParam(name, value);
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().delete().build();
    }
}
