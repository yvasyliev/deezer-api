package api.deezer.requests;

import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
import api.deezer.objects.Album;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;

/**
 * Requests related to albums.
 */
public class AlbumRequests extends DeezerRequests {
    /**
     * Gets album by ID.
     *
     * @param albumId album ID.
     * @return {@link Album} object.
     */
    public DeezerRequest<Album> getById(long albumId) {
        return new DeezerGetRequest<>(property("album.get", albumId), Album.class);
    }

    /**
     * Gets album fans.
     *
     * @param albumId album ID.
     * @return album fans.
     */
    public PagingRequest<UserData> getFans(long albumId) {
        return new PagingRequest<>(property("album.fans", albumId), UserData.class);
    }

    /**
     * Gets album tracks.
     *
     * @param albumId album ID.
     * @return album tracks.
     */
    public PagingRequest<TrackData> getTracks(long albumId) {
        return new PagingRequest<>(property("album.tracks", albumId), TrackData.class);
    }
}
