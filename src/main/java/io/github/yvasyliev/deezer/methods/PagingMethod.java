package io.github.yvasyliev.deezer.methods;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;

import java.util.Map;

//@Getter
public class PagingMethod<T extends Pageable> extends GetMethod<Page<T>> {
    @JsonProperty("index")
    private Integer index;

    @JsonProperty("limit")
    private Integer limit;

//    @JsonCreator
//    @ConstructorProperties({"context", "path", "responseType"})
//    public PagingMethod(@JacksonInject(useInput = OptBoolean.FALSE) @JsonProperty("io.github.yvasyliev.deezer.DeezerContext") @NonNull DeezerContext context, @JsonProperty("path") @NonNull String path, @JacksonInject(useInput = OptBoolean.FALSE) @NonNull TypeReference<Page<T>> responseType) {
//        super(context, path, responseType);
//    }

    @JsonCreator
    public PagingMethod(@JacksonInject(useInput = OptBoolean.FALSE) DeezerContext context, @JsonProperty("path") String path, @JacksonInject(useInput = OptBoolean.FALSE) TypeReference<Page<T>> responseType) {
        super(context, path, responseType);
    }

    @Override
    protected Map<String, String> getQueryParams() {
        Map<String, String> queryParams = super.getQueryParams();
        if (index != null) {
            queryParams.put("index", index.toString());
        }
        if (limit != null) {
            queryParams.put("limit", limit.toString());
        }
        return queryParams;
    }

    public PagingMethod<T> setIndex(Integer index) {
        this.index = index;
        return this;
    }

    public PagingMethod<T> setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
