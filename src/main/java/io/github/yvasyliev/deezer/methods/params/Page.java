package io.github.yvasyliev.deezer.methods.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Page {
    @JsonProperty("index")
    private Integer index;

    @JsonProperty("limit")
    private Integer limit;
}
