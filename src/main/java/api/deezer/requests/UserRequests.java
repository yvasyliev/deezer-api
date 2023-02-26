package api.deezer.requests;

import api.deezer.http.DeezerDeleteRequest;
import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerPostRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
import api.deezer.objects.GetPermissionsResponse;
import api.deezer.objects.Options;
import api.deezer.objects.PlaylistId;
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
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's favourite albums.
     *
     * @param userId user ID.
     * @return user's favourite albums.
     */
    public PagingRequest<AlbumData> getFavouriteAlbums(long userId) {
        return new PagingRequest<>(property("user.albums", userId), AlbumData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's favourite albums.
     *
     * @param userId user ID.
     * @return user's favourite albums.
     */
    public PagingRequest<ArtistData> getFavouriteArtists(long userId) {
        return new PagingRequest<>(property("user.artists", userId), ArtistData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's flow.
     *
     * @return user's flow.
     */
    public PagingRequest<TrackData> getFlow() {
        return new PagingRequest<>(property("user.flow", "me"), TrackData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's followings.
     *
     * @param userId user ID.
     * @return user's followings.
     */
    public PagingRequest<UserData> getFollowings(long userId) {
        return new PagingRequest<>(property("user.followings", userId), UserData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's followers.
     *
     * @param userId user ID.
     * @return user's followers.
     */
    public PagingRequest<UserData> getFollowers(long userId) {
        return new PagingRequest<>(property("user.followers", userId), UserData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets current user's history.
     *
     * @return current user's history.
     */
    public PagingRequest<TrackData> getMyHistory() {
        return new PagingRequest<>(property("user.history", "me"), TrackData.class)
                .addParam("access_token", getAccessToken());
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
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's permissions.
     *
     * @return user's permissions.
     */
    public DeezerRequest<GetPermissionsResponse> getPermissions() {
        return new DeezerGetRequest<>(property("user.permissions"), GetPermissionsResponse.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's options.
     *
     * @return user's options.
     */
    public DeezerRequest<Options> getOptions() {
        return new DeezerGetRequest<>(property("user.options"), Options.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's songs.
     *
     * @return user's songs.
     */
    public PagingRequest<TrackData> getPersonalSongs() {
        return new PagingRequest<>(property("user.personal"), TrackData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's playlists.
     *
     * @param userId user ID.
     * @return user's playlists.
     */
    public PagingRequest<PlaylistData> getPlaylists(long userId) {
        return new PagingRequest<>(property("user.playlists", userId), PlaylistData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's favourite radios.
     *
     * @param userId user ID.
     * @return user's favourite radios.
     */
    public PagingRequest<RadioData> getFavouriteRadios(long userId) {
        return new PagingRequest<>(property("user.radios", userId), RadioData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's recommended albums.
     *
     * @return user's recommended albums.
     */
    public PagingRequest<AlbumData> getRecommendedAlbums() {
        return new PagingRequest<>(property("recommendations.albums"), AlbumData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's recommended releases.
     *
     * @return user's recommended releases.
     */
    public PagingRequest<AlbumData> getRecommendedReleases() {
        return new PagingRequest<>(property("recommendations.releases"), AlbumData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's recommended releases.
     *
     * @return user's recommended releases.
     */
    public PagingRequest<ArtistData> getRecommendedArtists() {
        return new PagingRequest<>(property("recommendations.artists"), ArtistData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's recommended playlists.
     *
     * @return user's recommended playlists.
     */
    public PagingRequest<PlaylistData> getRecommendedPlaylists() {
        return new PagingRequest<>(property("recommendations.playlists"), PlaylistData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's recommended tracks.
     *
     * @return user's recommended tracks.
     */
    public PagingRequest<TrackData> getRecommendedTracks() {
        return new PagingRequest<>(property("recommendations.tracks"), TrackData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's recommended radios.
     *
     * @return user's recommended radios.
     */
    public PagingRequest<RadioData> getRecommendedRadios() {
        return new PagingRequest<>(property("recommendations.radios"), RadioData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Gets user's favourite tracks.
     *
     * @param userId user ID.
     * @return user's favourite tracks.
     */
    public PagingRequest<TrackData> getFavouriteTracks(long userId) {
        return new PagingRequest<>(property("user.tracks", userId), TrackData.class)
                .addParam("access_token", getAccessToken());
    }

    /**
     * Adds album to user library.
     *
     * @param albumId album ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addAlbumToLibrary(long albumId) {
        return new DeezerPostRequest<>(property("user.albums", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
                .addParam("album_id", String.valueOf(albumId));
    }

    /**
     * Adds artist to user favourites.
     *
     * @param artistId artist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addArtistToFavourites(long artistId) {
        return new DeezerPostRequest<>(property("user.artists", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
                .addParam("artist_id", String.valueOf(artistId));
    }

    /**
     * Follows a user.
     *
     * @param userId user ID to follow.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> follow(long userId) {
        return new DeezerPostRequest<>(property("user.followings", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
                .addParam("user_id", String.valueOf(userId));
    }

    /**
     * Creates a playlist.
     *
     * @param playlistTitle playlist title.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<PlaylistId> createPlaylist(String playlistTitle) {
        return new DeezerPostRequest<>(property("user.playlists", "me"), PlaylistId.class)
                .addParam("access_token", getAccessToken())
                .addParam("title", String.valueOf(playlistTitle));
    }

    /**
     * Adds playlist to user favourites.
     *
     * @param playlistId playlist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addPlaylistToFavourites(long playlistId) {
        return new DeezerPostRequest<>(property("user.playlists", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
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
                .addParam("access_token", getAccessToken())
                .addParam("podcast_id", String.valueOf(podcastId));
    }

    /**
     * Adds radio to user favourites.
     *
     * @param radioId radio ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addRadioToFavourites(long radioId) {
        return new DeezerPostRequest<>(property("user.radios", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
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
    public DeezerRequest<Boolean> addTracksToFavourites(List<Long> trackIds) { // TODO: 21.11.2021 check if it's working
        return new DeezerPostRequest<>(property("user.tracks", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
                .addParam("track_id", ParamUtils.encode(trackIds));
    }

    /**
     * Removes an album from the user's library.
     *
     * @param albumId album ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeAlbum(long albumId) {
        return new DeezerDeleteRequest<>(property("user.albums", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
                .addParam("album_id", String.valueOf(albumId));
    }

    /**
     * Removes an artist from the user's favorites.
     *
     * @param artistId artist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeArtist(long artistId) {
        return new DeezerDeleteRequest<>(property("user.artists", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
                .addParam("artist_id", String.valueOf(artistId));
    }

    /**
     * Unfollows user.
     *
     * @param followingId following ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> unfollow(long followingId) {
        return new DeezerDeleteRequest<>(property("user.followings", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
                .addParam("user_id", String.valueOf(followingId));
    }

    /**
     * Removes a playlist from the user's favorites.
     *
     * @param playlistId playlist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removePlaylist(long playlistId) {
        return new DeezerDeleteRequest<>(property("user.playlists", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
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
                .addParam("access_token", getAccessToken())
                .addParam("podcast_id", String.valueOf(podcastId));
    }

    /**
     * Removes a radio from the user's favorites.
     *
     * @param radio radio ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeRadio(long radio) {
        return new DeezerDeleteRequest<>(property("user.radios", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
                .addParam("radio_id", String.valueOf(radio));
    }

    /**
     * Removes a track from the user's favorites.
     *
     * @param trackId track ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeTrack(long trackId) {
        return new DeezerDeleteRequest<>(property("user.tracks", "me"), Boolean.class)
                .addParam("access_token", getAccessToken())
                .addParam("track_id", String.valueOf(trackId));
    }
}
