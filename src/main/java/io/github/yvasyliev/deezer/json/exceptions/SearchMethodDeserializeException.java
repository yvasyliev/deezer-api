package io.github.yvasyliev.deezer.json.exceptions;

import java.util.Map;

public class SearchMethodDeserializeException extends AbstractPagingMethodDeserializeException {
    private static final String SEARCH = "search";

    public SearchMethodDeserializeException(String path, Map<String, String> queryParams) {
        super(SEARCH, path, queryParams);
    }
}
