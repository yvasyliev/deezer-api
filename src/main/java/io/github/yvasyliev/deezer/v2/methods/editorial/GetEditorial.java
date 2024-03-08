package io.github.yvasyliev.deezer.v2.methods.editorial;

import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.v2.methods.ObjectServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetEditorial extends ObjectServiceMethod<Editorial, EditorialService> {
    public GetEditorial(EditorialService editorialService, long editorialId) {
        super(editorialService, editorialId);
    }

    @Override
    public Editorial execute() {
        return deezerService.getEditorial(objectId);
    }

    @Override
    public CompletableFuture<Editorial> executeAsync() {
        return deezerService.getEditorialAsync(objectId);
    }

    @Override
    public String toString() {
        return "/editorial/" + objectId;
    }
}
