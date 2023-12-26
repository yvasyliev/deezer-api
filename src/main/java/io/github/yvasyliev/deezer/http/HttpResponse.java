package io.github.yvasyliev.deezer.http;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.HttpURLConnection;

@Data
@AllArgsConstructor
public class HttpResponse {
    private int statusCode;
    private String content;

    public boolean isOk() {
        return statusCode == HttpURLConnection.HTTP_OK;
    }

    @Override
    public String toString() {
        return statusCode + ": " + content;
    }
}
