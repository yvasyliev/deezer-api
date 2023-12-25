package io.github.yvasyliev.deezer.http;

import io.github.yvasyliev.deezer.helpers.QueryParams;

import java.io.IOException;

public interface DeezerHttpClient {
    DeezerHttpResponse get(String url, QueryParams queryParams) throws IOException;
}
