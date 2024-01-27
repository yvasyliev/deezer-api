package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import lombok.Data;

import java.util.List;

/**
 * A page containing list of objects.
 *
 * @param <T> type of objects.
 */
@Data
public class Page<T extends Pageable> {
    /**
     * List of objects.
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
    private PagingMethod<T> next;

    /**
     * Previous page.
     */
    @SerializedName("prev")
    private PagingMethod<T> prev;

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrev() {
        return prev != null;
    }
}
