package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Marks that this object can be returned in {@link Chart}
 */
public abstract class ChartMember {
    /**
     * The position of the objects in the charts
     */
    @SerializedName("position")
    private Integer position;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ChartMember{" +
                "position=" + position +
                '}';
    }
}
