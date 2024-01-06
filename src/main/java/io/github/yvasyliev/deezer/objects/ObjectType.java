package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ObjectType {
    @JsonProperty("album") ALBUM,
    @JsonProperty("artist") ARTIST,
    @JsonProperty("genre") GENRE,
    @JsonProperty("playlist") PLAYLIST,
    @JsonProperty("track") TRACK,
    @JsonProperty("user") USER;
}
