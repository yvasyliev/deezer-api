package api.deezer.requests;

import api.deezer.http.impl.DeezerGetRequest;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
import api.deezer.objects.Artist;
import api.deezer.objects.data.AlbumData;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.PlaylistData;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;

/**
 * Requests related to artists.
 */
public class ArtistRequests extends DeezerRequests {
    /**
     * Get artists by ID.
     *
     * @param artistId artist ID.
     * @return {@link Artist} object.
     */
    public DeezerRequest<Artist> getById(long artistId) {
        return new DeezerGetRequest<>(property("artist.get", artistId), Artist.class);
    }

    /**
     * Gets artist's top five favourite tracks.
     *
     * @param artistId artist ID.
     * @return artist's top five favourite tracks.
     */
    public PaginationRequest<TrackData> getArtistTopFiveTracks(long artistId) {
        return new PaginationRequest<>(property("artist.top", artistId), TrackData.class);
    }

    /**
     * Gets artist's albums.
     *
     * @param artistId artist ID.
     * @return artist's albums.
     */
    public PaginationRequest<AlbumData> getAlbums(long artistId) {
        return new PaginationRequest<>(property("artist.albums", artistId), AlbumData.class);
    }

    /**
     * Gets artist fans.
     *
     * @param artistId artist ID.
     * @return artist fans.
     */
    public PaginationRequest<UserData> getFans(long artistId) {
        return new PaginationRequest<>(property("artist.fans", artistId), UserData.class);
    }

    /**
     * Gets related artists.
     *
     * @param artistId artist ID.
     * @return related artists.
     */
    public PaginationRequest<ArtistData> getRelatedArtists(long artistId) {
        return new PaginationRequest<>(property("artist.related", artistId), ArtistData.class);
    }

    /**
     * Gets artist radio.
     *
     * @param artistId artist ID.
     * @return artist radio.
     */
    public PaginationRequest<TrackData> getRadio(long artistId) {
        return new PaginationRequest<>(property("artist.radio", artistId), TrackData.class);
    }

    /**
     * Gets artist's playlists.
     *
     * @param artistId artist ID.
     * @return artist's playlists.
     */
    public PaginationRequest<PlaylistData> getPlaylists(long artistId) {
        return new PaginationRequest<>(property("artist.playlists", artistId), PlaylistData.class);
    }
}
