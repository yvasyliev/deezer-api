package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("F") FEMALE,
    @JsonProperty("M") MALE
}
