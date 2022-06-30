package api.deezer.requests;

import api.deezer.converters.Converter;
import api.deezer.converters.ListConverter;
import api.deezer.converters.TracksDataConverter;
import api.deezer.http.impl.*;
import api.deezer.objects.Playlist;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Requests related to playlists.
 */
public class PlaylistRequests extends DeezerRequests {
    /**
     * Converts list of integers into comma separated values.
     */
    private final Converter<List<Integer>, String> listConverter = new ListConverter<>();

    public PlaylistRequests(String accessToken) {
        super(accessToken);
    }

    /**
     * Gets playlist.
     *
     * @param playlistId playlist ID.
     * @return {@link Playlist} object.
     */
    public DeezerRequest<Playlist> getById(long playlistId) {
        return new DeezerGetRequest<>(property("playlist.get", playlistId), Playlist.class);
    }

    /**
     * Marks playlist as seen.
     *
     * @param playlistId playlist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> markAsSeen(long playlistId) {
        return new DeezerPostRequest<>(property("playlist.seen", playlistId), accessTokenParam(), Boolean.class);
    }

    /**
     * Gets playlist fans.
     *
     * @param playlistId playlist ID.
     * @return playlist fans.
     */
    public PaginationRequest<UserData> getFans(long playlistId) {
        return new PaginationRequest<>(property("playlist.fans", playlistId), UserData.class);
    }

    /**
     * Gets playlist tracks.
     *
     * @param playlistId playlist ID.
     * @return playlist tracks.
     */
    public PaginationRequest<TrackData> getTracks(long playlistId) {
        return new PaginationRequest<>(property("playlist.tracks", playlistId), TrackData.class);
    }

    /**
     * Gets playlist radio.
     *
     * @param playlistId playlist ID.
     * @return playlist radio.
     */
    public PaginationRequest<TrackData> getRadio(long playlistId) {
        PaginationRequest<TrackData> paginationRequest = new PaginationRequest<>(
                property("playlist.radio", playlistId),
                TrackData.class
        );
        paginationRequest.setResponseConverter(new TracksDataConverter());
        return paginationRequest;
    }

    /**
     * Adds tracks to playlist
     *
     * @param playlistId playlist ID.
     * @param trackIds   tracks IDs.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> addTracks(int playlistId, Integer... trackIds) {
        return addTracks(playlistId, Arrays.asList(trackIds));
    }

    /**
     * Adds tracks to playlist.
     *
     * @param playlistId playlist ID.
     * @param trackIds   tracks IDs.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> addTracks(int playlistId, List<Integer> trackIds) {
        Map<String, String> params = accessTokenParam();
        params.put("songs", listConverter.convert(trackIds));
        return new DeezerPostRequest<>(
                property("playlist.tracks", playlistId),
                params,
                Boolean.class
        );
    }

    /**
     * Orders tracks in playlist.
     *
     * @param playlistId playlist ID.
     * @param trackIds   tracks IDs.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> orderTracks(int playlistId, Integer... trackIds) {
        return orderTracks(playlistId, Arrays.asList(trackIds));
    }

    /**
     * Orders tracks in playlist.
     *
     * @param playlistId playlist ID.
     * @param trackIds   tracks IDs.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> orderTracks(int playlistId, List<Integer> trackIds) {
        Map<String, String> params = accessTokenParam();
        params.put("order", listConverter.convert(trackIds));
        return new DeezerPostRequest<>(
                property("playlist.tracks", playlistId),
                params,
                Boolean.class
        );
    }

    /**
     * Deletes playlist.
     *
     * @param playlistId playlist ID.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> delete(long playlistId) {
        return new DeezerDeleteRequest<>(
                property("playlist.get", playlistId),
                accessTokenParam(),
                Boolean.class
        );
    }

    /**
     * Removes tracks from playlist.
     *
     * @param playlistId playlist ID.
     * @param trackIds   track IDs.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> removeTracks(long playlistId, Integer... trackIds) {
        return removeTracks(playlistId, Arrays.asList(trackIds));
    }

    /**
     * Removes tracks from playlist.
     *
     * @param playlistId playlist ID.
     * @param trackIds   track IDs.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> removeTracks(long playlistId, List<Integer> trackIds) {
        Map<String, String> params = accessTokenParam();
        params.put("songs", listConverter.convert(trackIds));
        return new DeezerDeleteRequest<>(
                property("playlist.get", playlistId),
                params,
                Boolean.class
        );
    }

    /**
     * Creates <i>access_token</i> param.
     *
     * @return <i>access_token</i> param.
     */
    private Map<String, String> accessTokenParam() {
        return params(entry("access_token", getAccessToken()));
    }
}
