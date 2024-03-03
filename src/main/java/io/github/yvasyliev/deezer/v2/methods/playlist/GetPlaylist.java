package io.github.yvasyliev.deezer.v2.methods.playlist;

import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.v2.methods.ObjectServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetPlaylist extends ObjectServiceMethod<Playlist, PlaylistService> {
    public GetPlaylist(PlaylistService playlistService, long playlistId) {
        super(playlistService, playlistId);
    }

    @Override
    public Playlist execute() {
        return deezerService.getPlaylist(objectId);
    }

    @Override
    public CompletableFuture<Playlist> executeAsync() {
        return deezerService.getPlaylistAsync(objectId);
    }

    @Override
    public String toString() {
        return "/playlist/" + objectId;
    }
}
