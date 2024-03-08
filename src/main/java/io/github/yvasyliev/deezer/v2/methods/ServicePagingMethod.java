package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.DeezerService;

public abstract class ServicePagingMethod<T extends Pageable, S extends DeezerService> extends PagingMethod<T> {
    protected final S deezerService;

    public ServicePagingMethod(Gson gson, S deezerService) {
        super(gson);
        this.deezerService = deezerService;
    }
}
