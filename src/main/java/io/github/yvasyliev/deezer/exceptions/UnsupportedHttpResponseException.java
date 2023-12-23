package io.github.yvasyliev.deezer.exceptions;

import io.github.yvasyliev.deezer.http.DeezerHttpResponse;

import java.io.IOException;

public class UnsupportedHttpResponseException extends IOException {
    private static final String MSG_TEMPLATE = "%d: %s";
    public UnsupportedHttpResponseException(DeezerHttpResponse response) {
        super(String.format(MSG_TEMPLATE, response.getStatusCode(), response.getContent()));
    }
}
