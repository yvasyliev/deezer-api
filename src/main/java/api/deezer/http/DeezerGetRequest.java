package api.deezer.http;

import okhttp3.Request;

/**
 * Executes Deezer API GET request.
 *
 * @param <T> response POJO type.
 */
public class DeezerGetRequest<T> extends DeezerRequest<T> {
    public DeezerGetRequest(String url, Class<T> answerClass) {
        super(url, answerClass);
    }

    @Override
    public DeezerGetRequest<T> addParam(String name, String value) {
        return (DeezerGetRequest<T>) super.addParam(name, value);
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().get().build();
    }
}
