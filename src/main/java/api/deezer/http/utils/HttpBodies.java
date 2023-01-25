package api.deezer.http.utils;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Static HTTP bodies.
 */
public class HttpBodies {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     * Empty request body.
     */
    public static final RequestBody EMPTY_REQUEST_BODY = RequestBody.create(EMPTY_BYTE_ARRAY, null);

    /**
     * Empty response body.
     */
    public static final ResponseBody EMPTY_RESPONSE_BODY = ResponseBody.create(EMPTY_BYTE_ARRAY, null);
}
