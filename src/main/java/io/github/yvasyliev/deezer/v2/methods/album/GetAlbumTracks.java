package io.github.yvasyliev.deezer.v2.methods.album;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetAlbumTracks extends AlbumPagingMethod<Track> {
    public GetAlbumTracks(Gson gson, AlbumService albumService, long albumId) {
        super(gson, albumService, albumId);
    }

    @Override
    public Page<Track, PagingMethod<Track>> execute() {
        return albumService.getAlbumTracks(albumId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Track, PagingMethod<Track>>> executeAsync() {
        return albumService.getAlbumTracksAsync(albumId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/album/" + albumId + "/tracks" + getQueryParams();
    }
}
