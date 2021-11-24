package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Album contributor.
 */
public class Contributor extends Artist {
    /**
     * Contributor's role.
     */
    @SerializedName("role")
    private String role;

    public String getRole() {
        return role;
    }

    public Contributor setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "role='" + role + '\'' +
                "} " + super.toString();
    }
}
