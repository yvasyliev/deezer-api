package api.deezer.objects.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Stores list of data
 *
 * @param <T> data type
 */
public abstract class Data<T> {
    /**
     * List of data
     */
    @SerializedName("data")
    private List<T> data;

    /**
     * Total amount of objects.
     */
    @SerializedName("total")
    private Integer total;

    /**
     * Next page.
     */
    @SerializedName("next")
    private String next;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                ", total=" + total +
                ", next=" + next +
                '}';
    }
}
