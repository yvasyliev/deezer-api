package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.ServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetArtistPlaylists extends ServicePagingMethod<Playlist, ArtistService> {
    public GetArtistPlaylists(Gson gson, ArtistService deezerService, long artistId) {
        super(gson, deezerService, artistId);
    }

    @Override
    public Page<Playlist, PagingMethod<Playlist>> execute() {
        return deezerService.getArtistPlaylists(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Playlist, PagingMethod<Playlist>>> executeAsync() {
        return deezerService.getArtistPlaylistsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/artist/" + objectId + "/playlists" + getQueryParams();
    }
}
