package api.deezer.http;

import java.util.Map;

/**
 * Represents HTTP request.
 */
public interface HttpRequest {
    /**
     * Gets request URL.
     *
     * @return request URL.
     */
    String getUrl();

    /**
     * Gets request method. (E.g. GET or POST)
     *
     * @return request method.
     */
    String getRequestMethod();

    /**
     * Gets request URL params.
     *
     * @return request URL params.
     */
    Map<String, String> getParams();
}
