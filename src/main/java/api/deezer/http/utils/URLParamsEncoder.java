package api.deezer.http.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Encodes string values to URL-encoded values.
 */
public class URLParamsEncoder {
    /**
     * Encodes request params into URL-encoded params.
     *
     * @param params request params.
     * @return a string format <i>key1=value1&key2=value2...</i>
     * @throws UnsupportedEncodingException if errors occur.
     */
    public static String encode(Map<String, String> params) throws UnsupportedEncodingException {
        List<String> paramsList = new ArrayList<>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsList.add(encode(entry.getKey()) + "=" + encode(entry.getValue()));
        }
        return String.join("&", paramsList);
    }

    /**
     * Encodes a string into URL-encoded string.
     *
     * @param s source string.
     * @return URL-encoded string.
     * @throws UnsupportedEncodingException if errors occur.
     */
    public static String encode(String s) throws UnsupportedEncodingException {
        return URLEncoder.encode(s, "UTF-8");
    }
}
