package io.github.yvasyliev.deezer.helpers;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public final class URLHelper {
    private URLHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String decode(String s, Charset charset) {
        try {
            return s != null && !s.isEmpty()
                    ? URLDecoder.decode(s, charset.name())
                    : null;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String encode(String s, Charset charset) {
        try {
            return s != null
                    ? URLEncoder.encode(s, charset.name())
                    : "";
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static QueryParams getQueryParams(URL url) {
        return QueryParams.fromQuery(url.getQuery());
    }

    public static URL newUrl(String url, QueryParams queryParams) throws MalformedURLException {
        if (!queryParams.isEmpty()) {
            url += "?" + queryParams.toQuery();
        }
        return new URL(url);
    }

    public static URL setQueryParams(URL url, QueryParams queryParams) throws MalformedURLException {
        return newUrl(url.getProtocol() + "://" + url.getAuthority() + url.getPath(), queryParams);
    }
}
