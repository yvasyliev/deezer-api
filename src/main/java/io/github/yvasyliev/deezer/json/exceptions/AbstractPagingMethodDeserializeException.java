package io.github.yvasyliev.deezer.json.exceptions;

import com.google.gson.JsonParseException;

import java.util.Map;

public class AbstractPagingMethodDeserializeException extends JsonParseException {
    private static final String METHOD_FACTORY_NOT_FOUND = "No %s method factory found for path=%s, queryParams=%s";

    public AbstractPagingMethodDeserializeException(String methodType, String path, Map<String, String> queryParams) {
        super(String.format(METHOD_FACTORY_NOT_FOUND, methodType, path, queryParams));
    }
}
