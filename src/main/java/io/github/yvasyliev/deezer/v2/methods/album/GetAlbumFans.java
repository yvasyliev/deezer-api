package io.github.yvasyliev.deezer.v2.methods.album;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetAlbumFans extends AlbumPagingMethod<User> {
    public GetAlbumFans(Gson gson, AlbumService albumService, long albumId) {
        super(gson, albumService, albumId);
    }

    @Override
    public Page<User, PagingMethod<User>> execute() {
        return albumService.getAlbumFans(albumId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<User, PagingMethod<User>>> executeAsync() {
        return albumService.getAlbumFansAsync(albumId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/album/" + albumId + "/fans" + getQueryParams();
    }
}
