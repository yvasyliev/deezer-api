package io.github.yvasyliev.deezer.exceptions;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class DeezerResponseException extends IOException {
    public DeezerResponseException(JsonNode jsonResponse) {
        super(jsonResponse.toString());
    }
}
