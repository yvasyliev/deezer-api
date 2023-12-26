package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.yvasyliev.deezer.json.PagingMethodDeserializer;
import io.github.yvasyliev.deezer.json.PagingMethodSerializer;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import lombok.Data;

import java.util.List;

/**
 * A page containing list of objects.
 *
 * @param <T> type of objects.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonDeserialize(using = PagingMethodDeserializer.class)
    @JsonSerialize(using = PagingMethodSerializer.class)
    private PagingMethod<T> next;

    /**
     * Previous page.
     */
    @JsonProperty("prev")
    @JsonDeserialize(using = PagingMethodDeserializer.class)
    @JsonSerialize(using = PagingMethodSerializer.class)
    private PagingMethod<T> prev;

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrev() {
        return prev != null;
    }
}
