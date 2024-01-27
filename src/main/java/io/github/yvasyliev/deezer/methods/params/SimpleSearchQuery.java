package io.github.yvasyliev.deezer.methods.params;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//@JsonSerialize(using = SimpleSearchQuerySerializer.class)
public class SimpleSearchQuery implements SearchQuery {
    /**
     * Query string.
     */
    private String query;
}
