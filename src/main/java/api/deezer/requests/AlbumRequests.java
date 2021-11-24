package api.deezer.requests;

import api.deezer.http.impl.DeezerGetRequest;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
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
    public DeezerRequest<Album> getById(int albumId) {
        return new DeezerGetRequest<>(property("album.get", albumId), Album.class);
    }

    /**
     * Gets album fans.
     *
     * @param albumId album ID.
     * @return album fans.
     */
    public PaginationRequest<UserData> getFans(int albumId) {
        return new PaginationRequest<>(property("album.fans", albumId), UserData.class);
    }

    /**
     * Gets album tracks.
     *
     * @param albumId album ID.
     * @return album tracks.
     */
    public PaginationRequest<TrackData> getTracks(int albumId) {
        return new PaginationRequest<>(property("album.tracks", albumId), TrackData.class);
    }
}
