package api.deezer.requests;

import api.deezer.http.DeezerDeleteRequest;
import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerPostRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
import api.deezer.objects.DeezerId;
import api.deezer.objects.GetPermissionsResponse;
import api.deezer.objects.Options;
import api.deezer.objects.SendNotificationResponse;
import api.deezer.objects.User;
import api.deezer.objects.data.AlbumData;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.PlaylistData;
import api.deezer.objects.data.RadioData;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;
import api.deezer.utils.ParamUtils;

import java.util.Arrays;
import java.util.List;

// TODO: 01.11.2021 user / charts - what is the implementation?

/**
 * Requests related to user.
 */
public class UserRequests extends DeezerRequests {
    /**
     * {@code access_token} key.
     */
    public static final String ACCESS_TOKEN = "access_token";

    /**
     * {@code user.albums} key.
     */
    public static final String USER_ALBUMS = "user.albums";

    /**
     * {@code user.artists} key.
     */
    public static final String USER_ARTISTS = "user.artists";

    /**
     * {@code user.followings} key.
     */
    public static final String USER_FOLLOWINGS = "user.followings";

    /**
     * {@code user.playlists} key.
     */
    public static final String USER_PLAYLISTS = "user.playlists";

    /**
     * {@code user.radios} key.
     */
    public static final String USER_RADIOS = "user.radios";

    /**
     * {@code user.tracks} key.
     */
    public static final String USER_TRACKS = "user.tracks";

    public UserRequests(String accessToken) {
        super(accessToken);
    }

    /**
     * Gets user by ID.
     *
     * @param userId user ID.
     * @return user.
     */
    public DeezerRequest<User> getById(long userId) {
        return new DeezerGetRequest<>(
                property("user.get", userId),
                User.class
        );
    }

    /**
     * Gets current user.
     *
     * @return current user.
     */
    public DeezerRequest<User> getMe() {
        return new DeezerGetRequest<>(property("user.get", "me"), User.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's favourite albums.
     *
     * @param userId user ID.
     * @return user's favourite albums.
     */
    public PagingRequest<AlbumData> getFavouriteAlbums(long userId) {
        return new PagingRequest<>(property(USER_ALBUMS, userId), AlbumData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's favourite albums.
     *
     * @param userId user ID.
     * @return user's favourite albums.
     */
    public PagingRequest<ArtistData> getFavouriteArtists(long userId) {
        return new PagingRequest<>(property(USER_ARTISTS, userId), ArtistData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's flow.
     *
     * @return user's flow.
     */
    public PagingRequest<TrackData> getFlow() {
        return new PagingRequest<>(property("user.flow", "me"), TrackData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's followings.
     *
     * @param userId user ID.
     * @return user's followings.
     */
    public PagingRequest<UserData> getFollowings(long userId) {
        return new PagingRequest<>(property(USER_FOLLOWINGS, userId), UserData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's followers.
     *
     * @param userId user ID.
     * @return user's followers.
     */
    public PagingRequest<UserData> getFollowers(long userId) {
        return new PagingRequest<>(property("user.followers", userId), UserData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets current user's history.
     *
     * @return current user's history.
     */
    public PagingRequest<TrackData> getMyHistory() {
        return new PagingRequest<>(property("user.history", "me"), TrackData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    // TODO: 03.11.2021 is it working?

    /**
     * Sends notification to user.
     *
     * @param message notification message.
     * @return request status.
     */
    public DeezerRequest<SendNotificationResponse> sendNotification(String message) {
        return new DeezerPostRequest<>(property("user.notifications"), SendNotificationResponse.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's permissions.
     *
     * @return user's permissions.
     */
    public DeezerRequest<GetPermissionsResponse> getPermissions() {
        return new DeezerGetRequest<>(property("user.permissions"), GetPermissionsResponse.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's options.
     *
     * @return user's options.
     */
    public DeezerRequest<Options> getOptions() {
        return new DeezerGetRequest<>(property("user.options"), Options.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's songs.
     *
     * @return user's songs.
     */
    public PagingRequest<TrackData> getPersonalSongs() {
        return new PagingRequest<>(property("user.personal"), TrackData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's playlists.
     *
     * @param userId user ID.
     * @return user's playlists.
     */
    public PagingRequest<PlaylistData> getPlaylists(long userId) {
        return new PagingRequest<>(property(USER_PLAYLISTS, userId), PlaylistData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's favourite radios.
     *
     * @param userId user ID.
     * @return user's favourite radios.
     */
    public PagingRequest<RadioData> getFavouriteRadios(long userId) {
        return new PagingRequest<>(property(USER_RADIOS, userId), RadioData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's recommended albums.
     *
     * @return user's recommended albums.
     */
    public PagingRequest<AlbumData> getRecommendedAlbums() {
        return new PagingRequest<>(property("recommendations.albums"), AlbumData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's recommended releases.
     *
     * @return user's recommended releases.
     */
    public PagingRequest<AlbumData> getRecommendedReleases() {
        return new PagingRequest<>(property("recommendations.releases"), AlbumData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's recommended releases.
     *
     * @return user's recommended releases.
     */
    public PagingRequest<ArtistData> getRecommendedArtists() {
        return new PagingRequest<>(property("recommendations.artists"), ArtistData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's recommended playlists.
     *
     * @return user's recommended playlists.
     */
    public PagingRequest<PlaylistData> getRecommendedPlaylists() {
        return new PagingRequest<>(property("recommendations.playlists"), PlaylistData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's recommended tracks.
     *
     * @return user's recommended tracks.
     */
    public PagingRequest<TrackData> getRecommendedTracks() {
        return new PagingRequest<>(property("recommendations.tracks"), TrackData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's recommended radios.
     *
     * @return user's recommended radios.
     */
    public PagingRequest<RadioData> getRecommendedRadios() {
        return new PagingRequest<>(property("recommendations.radios"), RadioData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Gets user's favourite tracks.
     *
     * @param userId user ID.
     * @return user's favourite tracks.
     */
    public PagingRequest<TrackData> getFavouriteTracks(long userId) {
        return new PagingRequest<>(property(USER_TRACKS, userId), TrackData.class)
                .addParam(ACCESS_TOKEN, getAccessToken());
    }

    /**
     * Adds album to user library.
     *
     * @param albumId album ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addAlbumToLibrary(long albumId) {
        return new DeezerPostRequest<>(property(USER_ALBUMS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("album_id", String.valueOf(albumId));
    }

    /**
     * Adds artist to user favourites.
     *
     * @param artistId artist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addArtistToFavourites(long artistId) {
        return new DeezerPostRequest<>(property(USER_ARTISTS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("artist_id", String.valueOf(artistId));
    }

    /**
     * Follows a user.
     *
     * @param userId user ID to follow.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> follow(long userId) {
        return new DeezerPostRequest<>(property(USER_FOLLOWINGS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("user_id", String.valueOf(userId));
    }

    /**
     * Creates a playlist.
     *
     * @param playlistTitle playlist title.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<DeezerId> createPlaylist(String playlistTitle) {
        return new DeezerPostRequest<>(property(USER_PLAYLISTS, "me"), DeezerId.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("title", String.valueOf(playlistTitle));
    }

    /**
     * Adds playlist to user favourites.
     *
     * @param playlistId playlist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addPlaylistToFavourites(long playlistId) {
        return new DeezerPostRequest<>(property(USER_PLAYLISTS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("playlist_id", String.valueOf(playlistId));
    }

    /**
     * Adds podcast to user favourites.
     *
     * @param podcastId podcast ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addPodcastToFavourites(long podcastId) {
        return new DeezerPostRequest<>(property("user.podcasts", "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("podcast_id", String.valueOf(podcastId));
    }

    /**
     * Adds radio to user favourites.
     *
     * @param radioId radio ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addRadioToFavourites(long radioId) {
        return new DeezerPostRequest<>(property(USER_RADIOS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("radio_id", String.valueOf(radioId));
    }

    /**
     * Adds tracks to user favourites.
     *
     * @param trackIds tracks IDs.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addTracksToFavourites(Long... trackIds) {
        return addTracksToFavourites(Arrays.asList(trackIds));
    }

    /**
     * Adds tracks to user favourites.
     *
     * @param trackIds tracks IDs.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addTracksToFavourites(List<Long> trackIds) {
        return new DeezerPostRequest<>(property(USER_TRACKS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("track_id", ParamUtils.encode(trackIds));
    }

    /**
     * Removes an album from the user's library.
     *
     * @param albumId album ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeAlbum(long albumId) {
        return new DeezerDeleteRequest<>(property(USER_ALBUMS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("album_id", String.valueOf(albumId));
    }

    /**
     * Removes an artist from the user's favorites.
     *
     * @param artistId artist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeArtist(long artistId) {
        return new DeezerDeleteRequest<>(property(USER_ARTISTS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("artist_id", String.valueOf(artistId));
    }

    /**
     * Unfollows user.
     *
     * @param followingId following ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> unfollow(long followingId) {
        return new DeezerDeleteRequest<>(property(USER_FOLLOWINGS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("user_id", String.valueOf(followingId));
    }

    /**
     * Removes a playlist from the user's favorites.
     *
     * @param playlistId playlist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removePlaylist(long playlistId) {
        return new DeezerDeleteRequest<>(property(USER_PLAYLISTS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("playlist_id", String.valueOf(playlistId));
    }

    /**
     * Removes a podcast from the user's favorites.
     *
     * @param podcastId podcast ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removePodcast(long podcastId) {
        return new DeezerDeleteRequest<>(property("user.podcasts", "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("podcast_id", String.valueOf(podcastId));
    }

    /**
     * Removes a radio from the user's favorites.
     *
     * @param radio radio ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeRadio(long radio) {
        return new DeezerDeleteRequest<>(property(USER_RADIOS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("radio_id", String.valueOf(radio));
    }

    /**
     * Removes a track from the user's favorites.
     *
     * @param trackId track ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeTrack(long trackId) {
        return new DeezerDeleteRequest<>(property(USER_TRACKS, "me"), Boolean.class)
                .addParam(ACCESS_TOKEN, getAccessToken())
                .addParam("track_id", String.valueOf(trackId));
    }
}
