package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Charts of a specified genre.
 */
@Data
public class Chart {
    /**
     * List of {@link Track}.
     */
    @SerializedName("tracks")
    private Page<Track> tracks;

    /**
     * List of {@link Album}.
     */
    @SerializedName("albums")
    private Page<Album> albums;

    /**
     * List of {@link Artist}.
     */
    @SerializedName("artists")
    private Page<Artist> artists;

    /**
     * List of {@link Playlist}.
     */
    @SerializedName("playlists")
    private Page<Playlist> playlists;

    /**
     * List of {@link Podcast}.
     */
    @SerializedName("podcasts")
    private Page<Playlist> podcasts;
}
