package api.deezer.requests;

import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
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
    public PagingRequest<TrackData> getArtistTopFiveTracks(long artistId) {
        return new PagingRequest<>(property("artist.top", artistId), TrackData.class);
    }

    /**
     * Gets artist's albums.
     *
     * @param artistId artist ID.
     * @return artist's albums.
     */
    public PagingRequest<AlbumData> getAlbums(long artistId) {
        return new PagingRequest<>(property("artist.albums", artistId), AlbumData.class);
    }

    /**
     * Gets artist fans.
     *
     * @param artistId artist ID.
     * @return artist fans.
     */
    public PagingRequest<UserData> getFans(long artistId) {
        return new PagingRequest<>(property("artist.fans", artistId), UserData.class);
    }

    /**
     * Gets related artists.
     *
     * @param artistId artist ID.
     * @return related artists.
     */
    public PagingRequest<ArtistData> getRelatedArtists(long artistId) {
        return new PagingRequest<>(property("artist.related", artistId), ArtistData.class);
    }

    /**
     * Gets artist radio.
     *
     * @param artistId artist ID.
     * @return artist radio.
     */
    public PagingRequest<TrackData> getRadio(long artistId) {
        return new PagingRequest<>(property("artist.radio", artistId), TrackData.class);
    }

    /**
     * Gets artist's playlists.
     *
     * @param artistId artist ID.
     * @return artist's playlists.
     */
    public PagingRequest<PlaylistData> getPlaylists(long artistId) {
        return new PagingRequest<>(property("artist.playlists", artistId), PlaylistData.class);
    }
}
