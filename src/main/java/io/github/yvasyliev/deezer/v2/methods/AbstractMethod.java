package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractMethod<T, S extends DeezerService> implements Method<T> {
    protected final S deezerService;

    @Expose
    @SerializedName(value = OBJECT_ID)
    protected final long objectId;
}
