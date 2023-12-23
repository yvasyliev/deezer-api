package io.github.yvasyliev.deezer.http;

import java.net.HttpURLConnection;

public interface DeezerHttpResponse {
    String getContent();

    int getStatusCode();

    default boolean isOk() {
        return getStatusCode() == HttpURLConnection.HTTP_OK;
    }
}
