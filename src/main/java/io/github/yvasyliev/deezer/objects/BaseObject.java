package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

@FunctionalInterface
public interface BaseObject {
    @JsonProperty("type")
    ObjectType getType();
}
