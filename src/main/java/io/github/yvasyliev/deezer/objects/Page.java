package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.yvasyliev.deezer.json.PagingMethodDeserializerV2;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A page containing list of objects.
 *
 * @param <T> type of objects.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page<T extends Pageable> {
    /**
     * List of objects.
     */
    @JsonProperty("data")
    private List<T> data;

    /**
     * Total amount of objects.
     */
    @JsonProperty("total")
    private Integer total;

    /**
     * Next page.
     */
    @JsonProperty("next")
    @JsonDeserialize(using = PagingMethodDeserializerV2.class)
    private PagingMethod<T> next;

    /**
     * Previous page.
     */
    @JsonProperty("prev")
    @JsonDeserialize(using = PagingMethodDeserializerV2.class)
    private PagingMethod<T> prev;

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrev() {
        return prev != null;
    }
}
