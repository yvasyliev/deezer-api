package io.github.yvasyliev.deezer.http;

import io.github.yvasyliev.deezer.helpers.IOHelper;
import io.github.yvasyliev.deezer.helpers.QueryParams;
import io.github.yvasyliev.deezer.helpers.URLHelper;
import lombok.Cleanup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class DefaultHttpClient implements HttpClient {
    @Override
    public HttpResponse get(String url, QueryParams queryParams) throws IOException {
        @Cleanup("disconnect") HttpURLConnection connection = (HttpURLConnection) URLHelper.newUrl(url, queryParams).openConnection();
        InputStream content = connection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST
                ? connection.getInputStream()
                : connection.getErrorStream();
        return new HttpResponse(
                new String(IOHelper.readAllBytes(content), StandardCharsets.UTF_8),
                connection.getResponseCode()
        );
    }
}
