package io.github.yvasyliev.deezer.http;

import lombok.NonNull;

import java.io.IOException;
import java.util.Map;

public interface DeezerHttpClient {
    DeezerHttpResponse get(@NonNull String url, @NonNull Map<String, String> queryParams) throws IOException;
}
