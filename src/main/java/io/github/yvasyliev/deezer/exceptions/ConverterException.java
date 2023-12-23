package io.github.yvasyliev.deezer.exceptions;

import lombok.Getter;

@Getter
public class ConverterException extends RuntimeException {
    public ConverterException(Throwable cause) {
        super(cause);
    }
}
