package api.deezer.requests;

import api.deezer.http.impl.DeezerDeleteRequest;
import api.deezer.http.impl.DeezerGetRequest;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.objects.Track;

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
    public DeezerRequest<Track> getById(int id) {
        return new DeezerGetRequest<>(property("track.get", id), Track.class);
    }

    /**
     * Deletes user personal track.
     *
     * @param trackId track ID.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> delete(int trackId) {
        return new DeezerDeleteRequest<>(
                property("track.get", trackId),
                params(entry("access_token", getAccessToken())),
                Boolean.class
        );
    }
}
