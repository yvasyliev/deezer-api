package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
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
    @SerializedName("role")
    private Role role;
}
