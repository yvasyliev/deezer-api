package io.github.yvasyliev.deezer.v2.methods.playlist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.v2.methods.ObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetPlaylistRadio extends ObjectServicePagingMethod<Track, PlaylistService> {
    public GetPlaylistRadio(Gson gson, PlaylistService playlistService, long playlistId) {
        super(gson, playlistService, playlistId);
    }

    @Override
    public Page<Track, PagingMethod<Track>> execute() {
        return deezerService.getPlaylistRadio(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Track, PagingMethod<Track>>> executeAsync() {
        return deezerService.getPlaylistRadioAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/playlist/" + objectId + "/radio" + getQueryParams();
    }
}
