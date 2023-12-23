package io.github.yvasyliev.deezer.helpers;

import lombok.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public final class URLHelper {
    private URLHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @NonNull
    public static String decode(@NonNull String s) {
        try {
            return URLDecoder.decode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @NonNull
    public static String encode(@NonNull String s) {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @NonNull
    public static Map<String, String> getQueryParams(@NonNull URL url) {
        String query = url.getQuery();
        if (query != null && !query.isEmpty()) {
            return Arrays
                    .stream(query.split("&"))
                    .map(param -> param.split("="))
                    .collect(Collectors.toMap(
                            param -> decode(param[0]),
                            param -> param.length > 1 ? decode(param[1]) : ""
                    ));
        }
        return Collections.emptyMap();
    }

    @NonNull
    public static URL newUrl(@NonNull String url, @NonNull Map<String, String> queryParams) throws MalformedURLException {
        String query = queryParams
                .entrySet()
                .stream()
                .map(param -> encode(param.getKey()) + "=" + encode(param.getValue()))
                .collect(Collectors.joining());
        if (!query.isEmpty()) {
            url += "?" + query;
        }
        return new URL(url);
    }
}
