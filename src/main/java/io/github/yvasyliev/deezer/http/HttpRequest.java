package io.github.yvasyliev.deezer.http;

import java.net.URL;

public interface HttpRequest {
    HttpMethod getMethod();
    URL getUrl();
}
