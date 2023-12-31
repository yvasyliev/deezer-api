package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ObjectType {
    @JsonProperty("album") ALBUM,
    @JsonProperty("artist") ARTIST,
    @JsonProperty("track") TRACK,
    @JsonProperty("genre") GENRE,
    @JsonProperty("user") USER;
}
