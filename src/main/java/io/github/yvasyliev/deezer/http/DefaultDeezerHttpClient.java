package io.github.yvasyliev.deezer.http;

import io.github.yvasyliev.deezer.helpers.DeezerUtils;
import io.github.yvasyliev.deezer.helpers.QueryParams;
import io.github.yvasyliev.deezer.helpers.URLHelper;
import lombok.Cleanup;
import lombok.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class DefaultDeezerHttpClient implements DeezerHttpClient {
    @Override
    public DeezerHttpResponse get(String url, QueryParams queryParams) throws IOException {
        @Cleanup("disconnect") HttpURLConnection connection = (HttpURLConnection) URLHelper.newUrl(url, queryParams).openConnection();
        InputStream content = connection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST
                ? connection.getInputStream()
                : connection.getErrorStream();
        return new DefaultDeezerHttpResponse(
                new String(DeezerUtils.readAllBytes(content), StandardCharsets.UTF_8),
                connection.getResponseCode()
        );
    }
}
