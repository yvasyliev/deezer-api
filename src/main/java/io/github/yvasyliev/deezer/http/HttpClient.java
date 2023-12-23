package io.github.yvasyliev.deezer.http;

import lombok.NonNull;

import java.util.concurrent.CompletableFuture;

public interface HttpClient {

    CompletableFuture<DeezerHttpResponse> executeAsync(@NonNull HttpRequest request);
}
