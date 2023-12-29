package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Album/track contributor.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contributor extends Artist {
    /**
     * Contributor's role.
     */
    @JsonProperty("role")
    private Role role;
}
