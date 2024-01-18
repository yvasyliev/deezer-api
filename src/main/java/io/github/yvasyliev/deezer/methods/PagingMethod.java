package io.github.yvasyliev.deezer.methods;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class PagingMethod<T extends Pageable> extends GetMethod<Page<T>> {
    @JsonProperty("index")
    private Integer index;

    @JsonProperty("limit")
    private Integer limit;

    @JsonCreator
    public PagingMethod(@JacksonInject DeezerContext context, @JsonProperty(value = "endpoint", access = JsonProperty.Access.WRITE_ONLY) String endpoint, @JacksonInject TypeReference<Page<T>> responseType) {
        super(context, endpoint, responseType);
    }
}
