package io.github.yvasyliev.deezer.helpers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;

public class QueryParams extends HashMap<String, String> {
    public static QueryParams fromQuery(String s) {
        return fromQuery(s, StandardCharsets.UTF_8);
    }

    public static QueryParams fromQuery(String s, Charset charset) {
        QueryParams queryParams = new QueryParams();
        if (s != null) {
            for (String params : s.split("&")) {
                String[] keyVal = params.split("=");
                queryParams.put(
                        URLHelper.decode(keyVal[0], charset),
                        keyVal.length > 1 ? URLHelper.decode(keyVal[1], charset) : null
                );
            }
        }
        return queryParams;
    }

    public String toQuery() {
        return toQuery(StandardCharsets.UTF_8);
    }

    public String toQuery(Charset charset) {
        return entrySet()
                .stream()
                .map(param -> URLHelper.encode(param.getKey(), charset) + "=" + URLHelper.encode(param.getValue(), charset))
                .collect(Collectors.joining("&"));
    }
}
