package api.deezer.requests;

import api.deezer.http.DeezerDeleteRequest;
import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerPostRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
import api.deezer.http.utils.ParamUtils;
import api.deezer.objects.Playlist;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Requests related to playlists.
 */
public class PlaylistRequests extends DeezerRequests {

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
     * Update an existing playlist.
     *
     * @param playlist the playlist object {@link Playlist}
     * @return {@link Playlist} object.
     */
    public DeezerRequest<Boolean> update(Playlist playlist) {
        Map<String, String> params = accessTokenParam();
        if (!"".equals(playlist.getTitle())) {
            params.put("title", playlist.getTitle());
        }
        if (!"".equals(playlist.getDescription())) {
            params.put("description", playlist.getDescription());
        }
        if (playlist.getIsCollaborative() != null) {
            params.put("collaborative", playlist.getIsCollaborative().toString());
        }
        return new DeezerPostRequest<>(property("playlist.get", playlist.getId()), params, Boolean.class);
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
    public PagingRequest<UserData> getFans(long playlistId) {
        return new PagingRequest<>(property("playlist.fans", playlistId), UserData.class);
    }

    /**
     * Gets playlist tracks.
     *
     * @param playlistId playlist ID.
     * @return playlist tracks.
     */
    public PagingRequest<TrackData> getTracks(long playlistId) {
        return new PagingRequest<>(property("playlist.tracks", playlistId), TrackData.class);
    }

    /**
     * Gets playlist radio.
     *
     * @param playlistId playlist ID.
     * @return playlist radio.
     */
    public PagingRequest<TrackData> getRadio(long playlistId) {
        return new PagingRequest<>(
                property("playlist.radio", playlistId),
                TrackData.class
        );
    }

    /**
     * Sets playlist cover.
     *
     * @param playlistId  playlist ID.
     * @param uploadToken the upload token provided by {@link InfosRequests#get()}.
     * @param imageName   image name.
     * @param image       the image.
     * @return {@code true} if successful.
     */
    public DeezerRequest<Boolean> setPlaylistCover(long playlistId, final String uploadToken, String imageName, byte[] image) {
        Map<String, String> params = accessTokenParam();
        params.put("upload_token", uploadToken);
        return new DeezerPostRequest<>(
                property("playlist.picture", playlistId),
                params,
                Boolean.class,
                imageName,
                image
        );
    }

    /**
     * Sets playlist cover.
     *
     * @param playlistId  playlist ID.
     * @param uploadToken the upload token provided by {@link InfosRequests#get()}.
     * @param image       the image.
     * @return {@code true} if successful.
     */
    public DeezerRequest<Boolean> setPlaylistCover(long playlistId, final String uploadToken, File image) {
        Map<String, String> params = accessTokenParam();
        params.put("upload_token", uploadToken);
        return new DeezerPostRequest<>(
                property("playlist.picture", playlistId),
                params,
                Boolean.class,
                image
        );
    }

    /**
     * Sets playlist cover.
     *
     * @param playlistId  playlist ID.
     * @param uploadToken the upload token provided by {@link InfosRequests#get()}.
     * @param imageName   image name.
     * @param image       the image.
     * @return {@code true} if successful.
     */
    public DeezerRequest<Boolean> setPlaylistCover(long playlistId, final String uploadToken, String imageName, InputStream image) {
        Map<String, String> params = accessTokenParam();
        params.put("upload_token", uploadToken);
        return new DeezerPostRequest<>(
                property("playlist.picture", playlistId),
                params,
                Boolean.class,
                imageName,
                image
        );
    }

    /**
     * Adds tracks to playlist
     *
     * @param playlistId playlist ID.
     * @param trackIds   tracks IDs.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> addTracks(long playlistId, Long... trackIds) {
        return addTracks(playlistId, Arrays.asList(trackIds));
    }

    /**
     * Adds tracks to playlist.
     *
     * @param playlistId playlist ID.
     * @param trackIds   tracks IDs.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> addTracks(long playlistId, List<Long> trackIds) {
        Map<String, String> params = accessTokenParam();
        params.put("songs", ParamUtils.encode(trackIds));
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
    public DeezerRequest<Boolean> orderTracks(long playlistId, Long... trackIds) {
        return orderTracks(playlistId, Arrays.asList(trackIds));
    }

    /**
     * Orders tracks in playlist.
     *
     * @param playlistId playlist ID.
     * @param trackIds   tracks IDs.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> orderTracks(long playlistId, List<Long> trackIds) {
        Map<String, String> params = accessTokenParam();
        params.put("order", ParamUtils.encode(trackIds));
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
    public DeezerRequest<Boolean> removeTracks(long playlistId, Long... trackIds) {
        return removeTracks(playlistId, Arrays.asList(trackIds));
    }

    /**
     * Removes tracks from playlist.
     *
     * @param playlistId playlist ID.
     * @param trackIds   track IDs.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> removeTracks(long playlistId, List<Long> trackIds) {
        Map<String, String> params = accessTokenParam();
        params.put("songs", ParamUtils.encode(trackIds));
        return new DeezerDeleteRequest<>(
                property("playlist.tracks", playlistId),
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
