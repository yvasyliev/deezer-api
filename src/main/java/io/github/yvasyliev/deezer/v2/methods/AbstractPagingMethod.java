package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.v2.objects.Page;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Map;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class AbstractPagingMethod<T extends Pageable, M extends AbstractPagingMethod<T, M>> implements Method<Page<T, M>> {
    private static final TypeToken<Map<String, Object>> QUERY_PARAMS_TYPE_TOKEN = new TypeToken<Map<String, Object>>() {
    };

    private final Gson gson;

    @Expose
    @SerializedName("index")
    private Integer index;

    @Expose
    @SerializedName("limit")
    private Integer limit;

    public M index(int index) {
        this.index = index;
        return getThis();
    }

    public M limit(int limit) {
        this.limit = limit;
        return getThis();
    }

    protected abstract M getThis();

    protected Map<String, Object> getQueryParams() {
        return gson.fromJson(
                gson.toJsonTree(this),
                QUERY_PARAMS_TYPE_TOKEN.getType()
        );
    }
}
