package io.github.yvasyliev.deezer.http;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.HttpURLConnection;

@Data
@AllArgsConstructor
public class HttpResponse {
    private String content;
    private int statusCode;

    public boolean isOk() {
        return statusCode == HttpURLConnection.HTTP_OK;
    }
}
