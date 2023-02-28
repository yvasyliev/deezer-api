package api.deezer.requests;

import api.deezer.http.DeezerDeleteRequest;
import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.objects.Track;
import api.deezer.utils.DeezerPropertyKeys;
import api.deezer.utils.ParamUtils;

/**
 * Requests related to tracks.
 */
public class TrackRequests extends DeezerRequests {
    public TrackRequests(String accessToken) {
        super(accessToken);
    }

    /**
     * Gets track by ID.
     *
     * @param id track ID.
     * @return track.
     */
    public DeezerRequest<Track> getById(long id) {
        return new DeezerGetRequest<>(property(DeezerPropertyKeys.TRACK_GET, id), Track.class);
    }

    /**
     * Deletes user personal track.
     *
     * @param trackId track ID.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> delete(long trackId) {
        return new DeezerDeleteRequest<>(property(DeezerPropertyKeys.TRACK_GET, trackId), Boolean.class)
                .addParam(ParamUtils.ACCESS_TOKEN, getAccessToken());
    }
}
