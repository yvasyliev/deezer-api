package api.deezer.http.impl;

import api.deezer.http.HttpResponse;

/**
 * A default implementation of {@link HttpResponse}.
 */
public class HttpResponseImpl implements HttpResponse {
    /**
     * HTTP response code.
     */
    private final int responseCode;

    /**
     * Response body.
     */
    private final String body;

    public HttpResponseImpl(int responseCode, String body) {
        this.responseCode = responseCode;
        this.body = body;
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getBody() {
        return body;
    }
}
