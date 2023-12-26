package io.github.yvasyliev.deezer.methods.validators;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class AbstractResponseValidator implements ResponseValidator {
    private AbstractResponseValidator nextValidator;

    public static AbstractResponseValidator createChain(AbstractResponseValidator firstValidator, AbstractResponseValidator... chain) {
        AbstractResponseValidator head = firstValidator;
        for (AbstractResponseValidator nextInChain : chain) {
            head.nextValidator = nextInChain;
            head = nextInChain;
        }
        return firstValidator;
    }

    protected boolean validateNext(JsonNode rawResponse) {
        return nextValidator == null || nextValidator.validate(rawResponse);
    }
}
