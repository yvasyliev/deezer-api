package api.deezer.http.utils;

import okhttp3.RequestBody;

/**
 * Static request bodies.
 */
public class RequestBodies {
    /**
     * Empty request body.
     */
    public static final RequestBody EMPTY_REQUEST_BODY = RequestBody.create(new byte[0], null);
}
