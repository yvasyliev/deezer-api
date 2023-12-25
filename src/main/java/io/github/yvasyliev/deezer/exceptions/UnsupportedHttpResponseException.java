package io.github.yvasyliev.deezer.exceptions;

import io.github.yvasyliev.deezer.http.HttpResponse;

import java.io.IOException;

public class UnsupportedHttpResponseException extends IOException {
    private static final String MSG_TEMPLATE = "%d: %s";
    public UnsupportedHttpResponseException(HttpResponse response) {
        super(String.format(MSG_TEMPLATE, response.getStatusCode(), response.getContent()));
    }
}
