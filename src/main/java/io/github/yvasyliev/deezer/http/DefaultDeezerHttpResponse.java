package io.github.yvasyliev.deezer.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class DefaultDeezerHttpResponse implements DeezerHttpResponse {
    @NonNull
    private String content;

    private int statusCode;
}
