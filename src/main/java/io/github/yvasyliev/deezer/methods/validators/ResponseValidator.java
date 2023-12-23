package io.github.yvasyliev.deezer.methods.validators;

import com.fasterxml.jackson.databind.JsonNode;

public interface ResponseValidator {
    boolean validate(JsonNode rawResponse);
}
