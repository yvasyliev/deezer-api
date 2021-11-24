package api.deezer.objects;

import api.deezer.objects.data.Data;
import com.google.gson.annotations.SerializedName;

/**
 * Charts of a specified genre
 */
public class Chart {
    /**
     * list of {@link Track}
     */
    @SerializedName("tracks")
    private Data<Track> tracks;

    /**
     * list of {@link Album}
     */
    @SerializedName("albums")
    private Data<Album> albums;

    /**
     * list of {@link Artist}
     */
    @SerializedName("artists")
    private Data<Artist> artists;

    /**
     * list of {@link Playlist}
     */
    @SerializedName("playlists")
    private Data<Playlist> playlists;

    /**
     * list of {@link Podcast}
     */
    @SerializedName("podcasts")
    private Data<Playlist> podcasts;

    public Data<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Data<Track> tracks) {
        this.tracks = tracks;
    }

    public Data<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Data<Album> albums) {
        this.albums = albums;
    }

    public Data<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Data<Artist> artists) {
        this.artists = artists;
    }

    public Data<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Data<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Data<Playlist> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(Data<Playlist> podcasts) {
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
