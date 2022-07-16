package api.deezer.http;

/**
 * A HTTP response.
 */
public interface HttpResponse {
    /**
     * Gets HTTP response code.
     *
     * @return response code.
     */
    int getResponseCode();

    /**
     * Gets response body.
     *
     * @return response body.
     */
    String getBody();
}
