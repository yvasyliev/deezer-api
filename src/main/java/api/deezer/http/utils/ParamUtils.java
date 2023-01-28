package api.deezer.http.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parameter utilities.
 */
public class ParamUtils {
    /**
     * Encodes a string into URL-encoded string.
     *
     * @param s source string.
     * @return URL-encoded string.
     */
    public static String encode(String s) {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Encodes a list of objects into URL-encoded CSV.
     *
     * @param list list of objects.
     * @param <T>  objects' type.
     * @return URL-encoded string.
     */
    public static <T> String encode(List<T> list) {
        return list.stream()
                .map(String::valueOf)
                .map(ParamUtils::encode)
                .collect(Collectors.joining(","));

    }
}
