package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * An ID object
 */
public class Id {
    /**
     * A Deezer ID
     */
    @SerializedName("id")
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Id{" +
                "id=" + value +
                "} " + super.toString();
    }
}
