package io.github.yvasyliev.deezer.http;

import io.github.yvasyliev.deezer.helpers.QueryParams;

import java.io.IOException;

public interface HttpClient {
    HttpResponse get(String url, QueryParams queryParams) throws IOException;
}
