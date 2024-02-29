package io.github.yvasyliev.deezer.v2.methods.album;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.Method;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@EqualsAndHashCode
public class GetAlbum implements Method<Album> {
    protected final AlbumService albumService;

    @Expose
    @SerializedName(value = ALBUM_ID, alternate = OBJECT_ID)
    protected final long albumId;

    @Override
    public Album execute() {
        return albumService.getAlbum(albumId);
    }

    @Override
    public CompletableFuture<Album> executeAsync() {
        return albumService.getAlbumAsync(albumId);
    }

    @Override
    public String toString() {
        return "/album/" + albumId;
    }
}
