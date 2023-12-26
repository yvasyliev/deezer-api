package io.github.yvasyliev.deezer.http;

import io.github.yvasyliev.deezer.helpers.IOHelper;
import lombok.Cleanup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class DefaultHttpClient implements HttpClient {
    @Override
    public HttpResponse send(HttpRequest request) throws IOException {
        @Cleanup("disconnect") HttpURLConnection connection = (HttpURLConnection) request.getUrl().openConnection();
        connection.setRequestMethod(request.getMethod().name());
        InputStream content = connection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST
                ? connection.getInputStream()
                : connection.getErrorStream();
        return new HttpResponse(
                connection.getResponseCode(),
                new String(IOHelper.readAllBytes(content), StandardCharsets.UTF_8)
        );
    }
}
