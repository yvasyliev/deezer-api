package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.Method;
import lombok.AllArgsConstructor;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class GetArtist implements Method<Artist> {
    private final ArtistService artistService;

    @Expose
    @SerializedName(value = ARTIST_ID, alternate = OBJECT_ID)
    private final int artistId;

    @Override
    public Artist execute() {
        return artistService.getArtist(artistId);
    }

    @Override
    public CompletableFuture<Artist> executeAsync() {
        return artistService.getArtistAsync(artistId);
    }
}
