package io.github.yvasyliev.deezer.http;

import lombok.NonNull;

public interface HttpRequest {
    static HttpRequest of(@NonNull String url) {
        return () -> url;
    }

    String getUrl();
}
