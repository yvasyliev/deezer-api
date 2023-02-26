package api.deezer.utils;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Static HTTP bodies.
 */
public final class HttpBodies {
    /**
     * Empty byte array.
     */
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private HttpBodies() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    /**
     * Empty request body.
     */
    public static final RequestBody EMPTY_REQUEST_BODY = RequestBody.create(EMPTY_BYTE_ARRAY, null);

    /**
     * Empty response body.
     */
    public static final ResponseBody EMPTY_RESPONSE_BODY = ResponseBody.create(EMPTY_BYTE_ARRAY, null);
}
