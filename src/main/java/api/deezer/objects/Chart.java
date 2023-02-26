package api.deezer.objects;

import api.deezer.objects.data.DeezerData;
import com.google.gson.annotations.SerializedName;

/**
 * Charts of a specified genre
 */
public class Chart {
    /**
     * list of {@link Track}
     */
    @SerializedName("tracks")
    private DeezerData<Track> tracks;

    /**
     * list of {@link Album}
     */
    @SerializedName("albums")
    private DeezerData<Album> albums;

    /**
     * list of {@link Artist}
     */
    @SerializedName("artists")
    private DeezerData<Artist> artists;

    /**
     * list of {@link Playlist}
     */
    @SerializedName("playlists")
    private DeezerData<Playlist> playlists;

    /**
     * list of {@link Podcast}
     */
    @SerializedName("podcasts")
    private DeezerData<Playlist> podcasts;

    public DeezerData<Track> getTracks() {
        return tracks;
    }

    public void setTracks(DeezerData<Track> tracks) {
        this.tracks = tracks;
    }

    public DeezerData<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(DeezerData<Album> albums) {
        this.albums = albums;
    }

    public DeezerData<Artist> getArtists() {
        return artists;
    }

    public void setArtists(DeezerData<Artist> artists) {
        this.artists = artists;
    }

    public DeezerData<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(DeezerData<Playlist> playlists) {
        this.playlists = playlists;
    }

    public DeezerData<Playlist> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(DeezerData<Playlist> podcasts) {
        this.podcasts = podcasts;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "tracks=" + tracks +
                ", albums=" + albums +
                ", artists=" + artists +
                ", playlists=" + playlists +
                ", podcasts=" + podcasts +
                '}';
    }
}
