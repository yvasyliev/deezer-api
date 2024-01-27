package io.github.yvasyliev.deezer.util;

import lombok.experimental.UtilityClass;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class UrlUtil {
    public static Map<String, String> getQueryParams(URL url) {
        return Optional
                .ofNullable(url)
                .map(URL::getQuery)
                .map(query -> Arrays
                        .stream(query.split("&"))
                        .map(queryParam -> queryParam.split("="))
                        .collect(Collectors.toMap(
                                queryParam -> queryParam[0],
                                queryParam -> queryParam[1]
                        ))
                )
                .orElse(Collections.emptyMap());
    }
}
