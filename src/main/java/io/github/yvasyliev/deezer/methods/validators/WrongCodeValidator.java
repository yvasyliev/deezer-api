package io.github.yvasyliev.deezer.methods.validators;

import com.fasterxml.jackson.databind.JsonNode;

public class WrongCodeValidator extends AbstractResponseValidator {
    private static final String WRONG_CODE = "wrong code";

    @Override
    public boolean validate(JsonNode rawResponse) {
        return !WRONG_CODE.equals(rawResponse.asText()) && validateNext(rawResponse);
    }
}
