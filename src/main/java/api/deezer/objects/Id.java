package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Object ID.
 */
public class Id {
    /**
     * Object ID.
     */
    @SerializedName("id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id{" +
                "id=" + id +
                "} " + super.toString();
    }
}
