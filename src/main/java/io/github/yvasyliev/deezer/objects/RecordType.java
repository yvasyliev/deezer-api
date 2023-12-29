package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RecordType {
    @JsonProperty("album") ALBUM,
    @JsonProperty("compilation") COMPILATION,
    @JsonProperty("ep") EP,
    @JsonProperty("single") SINGLE
}
