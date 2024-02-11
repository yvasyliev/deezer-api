package io.github.yvasyliev.deezer.json.exceptions;

import java.util.Map;

public class PagingMethodDeserializeException extends AbstractPagingMethodDeserializeException {
    private static final String PAGING = "paging";

    public PagingMethodDeserializeException(String path, Map<String, String> queryParams) {
        super(PAGING, path, queryParams);
    }
}
