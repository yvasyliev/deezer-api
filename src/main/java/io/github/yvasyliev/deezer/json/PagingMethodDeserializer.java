package io.github.yvasyliev.deezer.json;

import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Pageable;

public class PagingMethodDeserializer<R extends Pageable> extends MethodDeserializer<PagingMethod<R>, R> {
    public PagingMethodDeserializer() {
        super(new UrlToPagingMethodConverter<>());
    }
}
