package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("Main") MAIN,
    @JsonProperty("Featured") FEATURED,
    @JsonProperty("Remixer") REMIXER,
    @JsonProperty("Composer") COMPOSER,
    @JsonProperty("Author") AUTHOR,
    @JsonProperty("Producer") PRODUCER,
    @JsonProperty("Guest") GUEST
}
