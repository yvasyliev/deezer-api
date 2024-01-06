package io.github.yvasyliev.deezer.objects;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Charts of a specified genre
 */
@Data
public class Chart {
    /**
     * list of {@link Track}.
     */
    @JsonProperty("tracks")
    private Page<Track> tracks;

    /**
     * list of {@link Album}.
     */
    @JsonProperty("albums")
    private Page<Album> albums;

    /**
     * list of {@link Artist}.
     */
    @JsonProperty("artists")
    private Page<Artist> artists;

    /**
     * list of {@link Playlist}.
     */
    @JsonProperty("playlists")
    private Page<Playlist> playlists;

    /**
     * list of {@link Podcast}.
     */
    @JsonProperty("podcasts")
    private Page<Playlist> podcasts;
}
