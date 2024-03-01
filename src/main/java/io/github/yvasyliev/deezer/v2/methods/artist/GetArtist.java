package io.github.yvasyliev.deezer.v2.methods.artist;

import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractMethod;

import java.util.concurrent.CompletableFuture;

public class GetArtist extends AbstractMethod<Artist, ArtistService> {
    public GetArtist(ArtistService deezerService, long artistId) {
        super(deezerService, artistId);
    }

    @Override
    public Artist execute() {
        return deezerService.getArtist(objectId);
    }

    @Override
    public CompletableFuture<Artist> executeAsync() {
        return deezerService.getArtistAsync(objectId);
    }

    @Override
    public String toString() {
        return "/artist/" + objectId;
    }
}
