package api.deezer.http;

import okhttp3.Request;

/**
 * Executes Deezer API DELETE request.
 *
 * @param <T> response POJO type.
 */
public class DeezerDeleteRequest<T> extends DeezerRequest<T> {
    public DeezerDeleteRequest(String url, Class<T> answerClass) {
        super(url, answerClass);
    }

    @Override
    public DeezerDeleteRequest<T> addParam(String name, String value) {
        return (DeezerDeleteRequest<T>) super.addParam(name, value);
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().delete().build();
    }
}
