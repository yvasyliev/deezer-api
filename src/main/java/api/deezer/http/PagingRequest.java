package api.deezer.http;

/**
 * Executes Deezer API request with pagination.
 *
 * @param <Answer> response POJO type.
 */
public class PagingRequest<Answer> extends DeezerGetRequest<Answer> {
    public PagingRequest(String url, Class<Answer> answerClass) {
        super(url, answerClass);
    }

    @Override
    public PagingRequest<Answer> addParam(String name, String value) {
        return (PagingRequest<Answer>) super.addParam(name, value);
    }

    /**
     * Adds <b>limit</b> parameter to the request.
     *
     * @param limit limit value.
     * @return current instance.
     */
    public PagingRequest<Answer> limit(int limit) {
        return addParam("limit", String.valueOf(limit));
    }

    /**
     * Adds <b>index</b> parameter to the request.
     *
     * @param index index value.
     * @return current instance.
     */
    public PagingRequest<Answer> index(int index) {
        return addParam("index", String.valueOf(index));
    }
}
