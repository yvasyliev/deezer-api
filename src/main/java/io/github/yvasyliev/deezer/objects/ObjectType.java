package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ObjectType {
    @JsonProperty("artist") ARTIST,
    @JsonProperty("track") TRACK
}
