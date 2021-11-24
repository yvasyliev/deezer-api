package api.deezer.http;

import java.io.IOException;

/**
 * Executes HTTP requests.
 */
public interface HttpClient {
    /**
     * Executes a HTTP request.
     *
     * @param httpRequest {@link HttpRequest} object.
     * @return response body.
     * @throws IOException if errors occur.
     */
    String execute(HttpRequest httpRequest) throws IOException;
}
