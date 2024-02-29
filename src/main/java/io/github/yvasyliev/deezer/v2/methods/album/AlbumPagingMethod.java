package io.github.yvasyliev.deezer.v2.methods.album;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AlbumPagingMethod<T extends Pageable> extends PagingMethod<T> {
    protected final AlbumService albumService;

    @Expose(serialize = false)
    @SerializedName(value = ALBUM_ID, alternate = OBJECT_ID)
    protected final long albumId;

    public AlbumPagingMethod(Gson gson, AlbumService albumService, long albumId) {
        super(gson);
        this.albumService = albumService;
        this.albumId = albumId;
    }
}
