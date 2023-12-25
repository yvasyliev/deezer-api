package io.github.yvasyliev.deezer.exceptions;

import lombok.Getter;

import java.io.IOException;

@Getter
public class ConverterException extends RuntimeException {
    public ConverterException(IOException cause) {
        super(cause);
    }

    @Override
    public synchronized IOException getCause() {
        return (IOException) super.getCause();
    }
}
