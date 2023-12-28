package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ExplicitContent {
    @JsonProperty("0") NOT_EXPLICIT,
    @JsonProperty("1") EXPLICIT,
    @JsonProperty("2") UNKNOWN,
    @JsonProperty("3") EDITED,
    @JsonProperty("6") NO_ADVICE_AVAILABLE
}
