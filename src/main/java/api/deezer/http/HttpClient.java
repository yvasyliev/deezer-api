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
     * @return HTTP response.
     * @throws IOException if errors occur.
     */
    HttpResponse execute(HttpRequest httpRequest) throws IOException;
}
