package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.methods.AbstractPagingMethod;
import lombok.Data;

import java.util.List;

/**
 * Abstract page containing list of objects.
 *
 * @param <T> type of objects.
 * @param <M> navigation method.
 */
@Data
public class AbstractPage<T extends Pageable, M extends AbstractPagingMethod<T, M, P>, P extends AbstractPage<T, M, P>> {
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
    private M next;

    /**
     * Previous page.
     */
    @SerializedName("prev")
    private M prev;

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrev() {
        return prev != null;
    }
}
