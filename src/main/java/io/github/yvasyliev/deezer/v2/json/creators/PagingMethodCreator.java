package io.github.yvasyliev.deezer.v2.json.creators;

import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import lombok.Setter;

@Setter
public abstract class PagingMethodCreator<T extends Pageable, S> implements InstanceCreator<PagingMethod<T>> {
    protected static final long DEFAULT_OBJECT_ID = 0;
    protected Gson gson;
    protected S service;
}
