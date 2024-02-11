package io.github.yvasyliev.deezer.json.exceptions;

import java.util.Map;

public class AdvancedSearchMethodDeserializeException extends AbstractPagingMethodDeserializeException {
    private static final String ADVANCED_SEARCH = "advanced search";

    public AdvancedSearchMethodDeserializeException(String path, Map<String, String> queryParams) {
        super(ADVANCED_SEARCH, path, queryParams);
    }
}
