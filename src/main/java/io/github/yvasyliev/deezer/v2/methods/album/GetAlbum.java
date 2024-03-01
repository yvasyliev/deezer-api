package io.github.yvasyliev.deezer.v2.methods.album;

import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.AbstractMethod;

import java.util.concurrent.CompletableFuture;

public class GetAlbum extends AbstractMethod<Album, AlbumService> {
    public GetAlbum(AlbumService deezerService, long albumId) {
        super(deezerService, albumId);
    }

    @Override
    public Album execute() {
        return deezerService.getAlbum(objectId);
    }

    @Override
    public CompletableFuture<Album> executeAsync() {
        return deezerService.getAlbumAsync(objectId);
    }

    @Override
    public String toString() {
        return "/album/" + objectId;
    }
}
