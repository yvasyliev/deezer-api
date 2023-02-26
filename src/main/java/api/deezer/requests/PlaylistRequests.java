package api.deezer.requests;

import api.deezer.http.DeezerDeleteRequest;
import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerPostRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
import api.deezer.objects.Playlist;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;
import api.deezer.utils.ParamUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Requests related to playlists.
 */
public class PlaylistRequests extends DeezerRequests {
    /**
     * {@code playlist.get} key.
     */
    private static final String PLAYLIST_GET = "playlist.get";

    /**
     * {@code access_token} key.
     */
    private static final String ACCESS_TOKEN = "access_token";

    /**
     * {@code playlist.tracks} key.
     */
    private static final String PLAYLIST_TRACKS = "playlist.tracks";

    /**
     * {@code playlist.picture} key.
     */
    private static final String PLAYLIST_PICTURE = "playlist.picture";

    /**
     * {@code upload_token} key.
     */
    private static final String UPLOAD_TOKEN = "upload_token";

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
        return new DeezerGetRequest<>(property(PLAYLIST_GET, playlistId), Playlist.class);
    }

    /**
     * Update an existing playlist.
     *
     * @param playlist the playlist object {@link Playlist}
     * @return {@link Playlist} object.
     */
    public DeezerRequest<Boolean> update(Playlist playlist) {
        DeezerPostRequest<Boolean> request = new DeezerPostRequest<>(property(PLAYLIST_GET, playlist.getId()), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken());

        if (playlist.getTitle() != null && !playlist.getTitle().isEmpty()) {
            request.addParam("title", playlist.getTitle());
        }
        if (playlist.getDescription() != null && !playlist.getDescription().isEmpty()) {
            request.addParam("description", playlist.getDescription());
        }
        if (playlist.getIsCollaborative() != null) {
            request.addParam("collaborative", playlist.getIsCollaborative().toString());
        }

        return request;
    }

    /**
     * Marks playlist as seen.
     *
     * @param playlistId playlist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> markAsSeen(long playlistId) {
        return new DeezerPostRequest<>(property("playlist.seen", playlistId), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
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
        return new PagingRequest<>(property(PLAYLIST_TRACKS, playlistId), TrackData.class);
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
        return new DeezerPostRequest<>(property(PLAYLIST_PICTURE, playlistId), Boolean.class, imageName, image)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam(UPLOAD_TOKEN, uploadToken);
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
        return new DeezerPostRequest<>(property(PLAYLIST_PICTURE, playlistId), Boolean.class, image)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam(UPLOAD_TOKEN, uploadToken);
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
        return new DeezerPostRequest<>(property(PLAYLIST_PICTURE, playlistId), Boolean.class, imageName, image)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam(UPLOAD_TOKEN, uploadToken);
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
        return new DeezerPostRequest<>(property(PLAYLIST_TRACKS, playlistId), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("songs", ParamUtils.encode(trackIds));
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
        return new DeezerPostRequest<>(property(PLAYLIST_TRACKS, playlistId), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("order", ParamUtils.encode(trackIds));
    }

    /**
     * Deletes playlist.
     *
     * @param playlistId playlist ID.
     * @return <i>true</i> if successful.
     */
    public DeezerRequest<Boolean> delete(long playlistId) {
        return new DeezerDeleteRequest<>(property(PLAYLIST_GET, playlistId), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
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
        return new DeezerDeleteRequest<>(property(PLAYLIST_TRACKS, playlistId), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("songs", ParamUtils.encode(trackIds));
    }
}
