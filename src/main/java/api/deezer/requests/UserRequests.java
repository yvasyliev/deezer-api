package api.deezer.requests;

import api.deezer.converters.ListConverter;
import api.deezer.http.impl.DeezerDeleteRequest;
import api.deezer.http.impl.DeezerGetRequest;
import api.deezer.http.impl.DeezerPostRequest;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
import api.deezer.objects.GetPermissionsResponse;
import api.deezer.objects.Id;
import api.deezer.objects.Options;
import api.deezer.objects.SendNotificationResponse;
import api.deezer.objects.User;
import api.deezer.objects.data.AlbumData;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.PlaylistData;
import api.deezer.objects.data.RadioData;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// TODO: 01.11.2021 user / charts - what is the implementation?

/**
 * Requests related to user.
 */
public class UserRequests extends DeezerRequests {
    /**
     * Converts list of integers to comma separated values.
     */
    private final Function<List<Long>, String> listConverter = new ListConverter<>();

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
        return new DeezerGetRequest<>(
                property("user.get", "me"),
                accessTokenParam(),
                User.class
        );
    }

    /**
     * Gets user's favourite albums.
     *
     * @param userId user ID.
     * @return user's favourite albums.
     */
    public PaginationRequest<AlbumData> getFavouriteAlbums(long userId) {
        return new PaginationRequest<>(
                property("user.albums", userId),
                accessTokenParam(),
                AlbumData.class
        );
    }

    /**
     * Gets user's favourite albums.
     *
     * @param userId user ID.
     * @return user's favourite albums.
     */
    public PaginationRequest<ArtistData> getFavouriteArtists(long userId) {
        return new PaginationRequest<>(
                property("user.artists", userId),
                accessTokenParam(),
                ArtistData.class
        );
    }

    /**
     * Gets user's flow.
     *
     * @return user's flow.
     */
    public PaginationRequest<TrackData> getFlow() {
        return new PaginationRequest<>(
                property("user.flow", "me"),
                accessTokenParam(),
                TrackData.class
        );
    }

    /**
     * Gets user's followings.
     *
     * @param userId user ID.
     * @return user's followings.
     */
    public PaginationRequest<UserData> getFollowings(long userId) {
        return new PaginationRequest<>(
                property("user.followings", userId),
                accessTokenParam(),
                UserData.class
        );
    }

    /**
     * Gets user's followers.
     *
     * @param userId user ID.
     * @return user's followers.
     */
    public PaginationRequest<UserData> getFollowers(long userId) {
        return new PaginationRequest<>(
                property("user.followers", userId),
                accessTokenParam(),
                UserData.class
        );
    }

    /**
     * Gets current user's history.
     *
     * @return current user's history.
     */
    public PaginationRequest<TrackData> getMyHistory() {
        return new PaginationRequest<>(
                property("user.history", "me"),
                accessTokenParam(),
                TrackData.class
        );
    }

    // TODO: 03.11.2021 is it working?

    /**
     * Sends notification to user.
     *
     * @param message notification message.
     * @return request status.
     */
    public DeezerRequest<SendNotificationResponse> sendNotification(String message) {
        return new DeezerPostRequest<>(
                property("user.notifications"),
                accessTokenParam(),
                SendNotificationResponse.class
        );
    }

    /**
     * Gets user's permissions.
     *
     * @return user's permissions.
     */
    public DeezerRequest<GetPermissionsResponse> getPermissions() {
        return new DeezerGetRequest<>(
                property("user.permissions"),
                accessTokenParam(),
                GetPermissionsResponse.class
        );
    }

    /**
     * Gets user's options.
     *
     * @return user's options.
     */
    public DeezerRequest<Options> getOptions() {
        return new DeezerGetRequest<>(
                property("user.options"),
                accessTokenParam(),
                Options.class
        );
    }

    /**
     * Gets user's songs.
     *
     * @return user's songs.
     */
    public PaginationRequest<TrackData> getPersonalSongs() {
        return new PaginationRequest<>(
                property("user.personal"),
                accessTokenParam(),
                TrackData.class
        );
    }

    /**
     * Gets user's playlists.
     *
     * @param userId user ID.
     * @return user's playlists.
     */
    public PaginationRequest<PlaylistData> getPlaylists(long userId) {
        return new PaginationRequest<>(
                property("user.playlists", userId),
                accessTokenParam(),
                PlaylistData.class
        );
    }

    /**
     * Gets user's favourite radios.
     *
     * @param userId user ID.
     * @return user's favourite radios.
     */
    public PaginationRequest<RadioData> getFavouriteRadios(long userId) {
        return new PaginationRequest<>(
                property("user.radios", userId),
                accessTokenParam(),
                RadioData.class
        );
    }

    /**
     * Gets user's recommended albums.
     *
     * @return user's recommended albums.
     */
    public PaginationRequest<AlbumData> getRecommendedAlbums() {
        return new PaginationRequest<>(
                property("recommendations.albums"),
                accessTokenParam(),
                AlbumData.class
        );
    }

    /**
     * Gets user's recommended releases.
     *
     * @return user's recommended releases.
     */
    public PaginationRequest<AlbumData> getRecommendedReleases() {
        return new PaginationRequest<>(
                property("recommendations.releases"),
                accessTokenParam(),
                AlbumData.class
        );
    }

    /**
     * Gets user's recommended releases.
     *
     * @return user's recommended releases.
     */
    public PaginationRequest<ArtistData> getRecommendedArtists() {
        return new PaginationRequest<>(
                property("recommendations.artists"),
                accessTokenParam(),
                ArtistData.class
        );
    }

    /**
     * Gets user's recommended playlists.
     *
     * @return user's recommended playlists.
     */
    public PaginationRequest<PlaylistData> getRecommendedPlaylists() {
        return new PaginationRequest<>(
                property("recommendations.playlists"),
                accessTokenParam(),
                PlaylistData.class
        );
    }

    /**
     * Gets user's recommended tracks.
     *
     * @return user's recommended tracks.
     */
    public PaginationRequest<TrackData> getRecommendedTracks() {
        return new PaginationRequest<>(
                property("recommendations.tracks"),
                accessTokenParam(),
                TrackData.class
        );
    }

    /**
     * Gets user's recommended radios.
     *
     * @return user's recommended radios.
     */
    public PaginationRequest<RadioData> getRecommendedRadios() {
        return new PaginationRequest<>(
                property("recommendations.radios"),
                accessTokenParam(),
                RadioData.class
        );
    }

    /**
     * Gets user's favourite tracks.
     *
     * @param userId user ID.
     * @return user's favourite tracks.
     */
    public PaginationRequest<TrackData> getFavouriteTracks(long userId) {
        return new PaginationRequest<>(
                property("user.tracks", userId),
                accessTokenParam(),
                TrackData.class
        );
    }

    /**
     * Adds album to user library.
     *
     * @param albumId album ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addAlbumToLibrary(long albumId) {
        Map<String, String> params = accessTokenParam();
        params.put("album_id", String.valueOf(albumId));
        return new DeezerPostRequest<>(
                property("user.albums", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Adds artist to user favourites.
     *
     * @param artistId artist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addArtistToFavourites(long artistId) {
        Map<String, String> params = accessTokenParam();
        params.put("artist_id", String.valueOf(artistId));
        return new DeezerPostRequest<>(
                property("user.artists", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Follows a user.
     *
     * @param userId user ID to follow.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> follow(long userId) {
        Map<String, String> params = accessTokenParam();
        params.put("user_id", String.valueOf(userId));
        return new DeezerPostRequest<>(
                property("user.followings", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Creates a playlist.
     *
     * @param playlistTitle playlist title.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Id> createPlaylist(String playlistTitle) {
        Map<String, String> params = accessTokenParam();
        params.put("title", String.valueOf(playlistTitle));
        return new DeezerPostRequest<>(
                property("user.playlists", "me"),
                params,
                Id.class
        );
    }

    /**
     * Adds playlist to user favourites.
     *
     * @param playlistId playlist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addPlaylistToFavourites(long playlistId) {
        Map<String, String> params = accessTokenParam();
        params.put("playlist_id", String.valueOf(playlistId));
        return new DeezerPostRequest<>(
                property("user.playlists", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Adds podcast to user favourites.
     *
     * @param podcastId podcast ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addPodcastToFavourites(long podcastId) {
        Map<String, String> params = accessTokenParam();
        params.put("podcast_id", String.valueOf(podcastId));
        return new DeezerPostRequest<>(
                property("user.podcasts", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Adds radio to user favourites.
     *
     * @param radioId radio ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> addRadioToFavourites(long radioId) {
        Map<String, String> params = accessTokenParam();
        params.put("radio_id", String.valueOf(radioId));
        return new DeezerPostRequest<>(
                property("user.radios", "me"),
                params,
                Boolean.class
        );
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
        Map<String, String> params = accessTokenParam();
        params.put("track_id", String.valueOf(listConverter.apply(trackIds)));
        return new DeezerPostRequest<>(
                property("user.tracks", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Removes an album from the user's library.
     *
     * @param albumId album ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeAlbum(long albumId) {
        Map<String, String> params = accessTokenParam();
        params.put("album_id", String.valueOf(albumId));
        return new DeezerDeleteRequest<>(
                property("user.albums", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Removes an artist from the user's favorites.
     *
     * @param artistId artist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeArtist(long artistId) {
        Map<String, String> params = accessTokenParam();
        params.put("artist_id", String.valueOf(artistId));
        return new DeezerDeleteRequest<>(
                property("user.artists", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Unfollows user.
     *
     * @param followingId following ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> unfollow(long followingId) {
        Map<String, String> params = accessTokenParam();
        params.put("user_id", String.valueOf(followingId));
        return new DeezerDeleteRequest<>(
                property("user.followings", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Removes a playlist from the user's favorites.
     *
     * @param playlistId playlist ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removePlaylist(long playlistId) {
        Map<String, String> params = accessTokenParam();
        params.put("playlist_id", String.valueOf(playlistId));
        return new DeezerDeleteRequest<>(
                property("user.playlists", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Removes a podcast from the user's favorites.
     *
     * @param podcastId podcast ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removePodcast(long podcastId) {
        Map<String, String> params = accessTokenParam();
        params.put("podcast_id", String.valueOf(podcastId));
        return new DeezerDeleteRequest<>(
                property("user.podcasts", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Removes a radio from the user's favorites.
     *
     * @param radio radio ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeRadio(long radio) {
        Map<String, String> params = accessTokenParam();
        params.put("radio_id", String.valueOf(radio));
        return new DeezerDeleteRequest<>(
                property("user.radios", "me"),
                params,
                Boolean.class
        );
    }

    /**
     * Removes a track from the user's favorites.
     *
     * @param trackId track ID.
     * @return <i>true</i> if was successful.
     */
    public DeezerRequest<Boolean> removeTrack(long trackId) {
        Map<String, String> params = accessTokenParam();
        params.put("track_id", String.valueOf(trackId));
        return new DeezerDeleteRequest<>(
                property("user.tracks", "me"),
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
