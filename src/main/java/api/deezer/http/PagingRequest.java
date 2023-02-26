package api.deezer.http;

/**
 * Executes Deezer API request with pagination.
 *
 * @param <T> response POJO type.
 */
public class PagingRequest<T> extends DeezerGetRequest<T> {
    public PagingRequest(String url, Class<T> answerClass) {
        super(url, answerClass);
    }

    @Override
    public PagingRequest<T> addParam(String name, String value) {
        return (PagingRequest<T>) super.addParam(name, value);
    }

    /**
     * Adds <b>limit</b> parameter to the request.
     *
     * @param limit limit value.
     * @return current instance.
     */
    public PagingRequest<T> limit(int limit) {
        return addParam("limit", String.valueOf(limit));
    }

    /**
     * Adds <b>index</b> parameter to the request.
     *
     * @param index index value.
     * @return current instance.
     */
    public PagingRequest<T> index(int index) {
        return addParam("index", String.valueOf(index));
    }
}
