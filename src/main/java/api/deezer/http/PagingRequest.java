package api.deezer.http;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Executes Deezer API request with pagination.
 *
 * @param <Answer> response POJO type.
 */
public class PagingRequest<Answer> extends DeezerGetRequest<Answer> {
    public PagingRequest(String url, Class<Answer> responseClass) {
        super(url, responseClass);
    }

    public PagingRequest(String url, Map<String, String> params, Class<Answer> responseClass) {
        super(url, params, responseClass);
    }

    public PagingRequest(String url, Function<String, Answer> responseConverter) {
        super(url, new HashMap<>(), responseConverter);
    }

    /**
     * Adds <b>limit</b> parameter to the request.
     *
     * @param limit limit value.
     * @return current instance.
     */
    public PagingRequest<Answer> limit(int limit) {
        this.urlBuilder.addQueryParameter("limit", String.valueOf(limit));
        return this;
    }

    /**
     * Adds <b>index</b> parameter to the request.
     *
     * @param index index value.
     * @return current instance.
     */
    public PagingRequest<Answer> index(int index) {
        this.urlBuilder.addQueryParameter("index", String.valueOf(index));
        return this;
    }
}
