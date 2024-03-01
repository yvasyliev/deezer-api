package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.ServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetArtistFans extends ServicePagingMethod<User, ArtistService> {
    public GetArtistFans(Gson gson, ArtistService deezerService, long artistId) {
        super(gson, deezerService, artistId);
    }

    @Override
    public Page<User, PagingMethod<User>> execute() {
        return deezerService.getArtistFans(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<User, PagingMethod<User>>> executeAsync() {
        return deezerService.getArtistFansAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/artist/" + objectId + "/fans" + getQueryParams();
    }
}
