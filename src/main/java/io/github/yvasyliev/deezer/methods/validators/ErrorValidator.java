package io.github.yvasyliev.deezer.methods.validators;

import com.fasterxml.jackson.databind.JsonNode;

public class ErrorValidator extends AbstractResponseValidator {
    private static final String ERROR = "error";

    @Override
    public boolean validate(JsonNode rawResponse) {
        return !rawResponse.has(ERROR) && validateNext(rawResponse);
    }
}
