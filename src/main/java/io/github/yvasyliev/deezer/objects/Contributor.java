package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Album/track contributor.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Contributor extends Artist {
    /**
     * Contributor's role.
     */
    @JsonProperty("role")
    private Role role;
}
