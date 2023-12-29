package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@FunctionalInterface
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "type", allowGetters = true)
public interface BaseObject {
    @JsonProperty("type")
    ObjectType getType();
}
