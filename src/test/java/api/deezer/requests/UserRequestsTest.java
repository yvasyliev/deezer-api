package api.deezer.requests;

import api.deezer.DeezerApi;
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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void getById() {
        DeezerRequest<User> request = deezerApi.user().getById(123123123123L);
        assertEquals("https://api.deezer.com/user/123123123123", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }

    @Test
    void getMe() {
        DeezerRequest<User> request = deezerApi.user().getMe();
        assertEquals("https://api.deezer.com/user/me", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getFavouriteAlbums() {
        PaginationRequest<AlbumData> request = deezerApi.user().getFavouriteAlbums(123123123123L).limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/123123123123/albums", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getFavouriteArtists() {
        PaginationRequest<ArtistData> request = deezerApi.user().getFavouriteArtists(123123123123L).limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/123123123123/artists", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getFlow() {
        PaginationRequest<TrackData> request = deezerApi.user().getFlow().limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/me/flow", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getFollowings() {
        PaginationRequest<UserData> request = deezerApi.user().getFollowings(123123123123L).limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/123123123123/followings", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getFollowers() {
        PaginationRequest<UserData> request = deezerApi.user().getFollowers(123123123123L).limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/123123123123/followers", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getMyHistory() {
        PaginationRequest<TrackData> request = deezerApi.user().getMyHistory().limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/me/history", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void sendNotification() {
        DeezerRequest<SendNotificationResponse> request = deezerApi.user().sendNotification("hello");
        assertEquals("https://api.deezer.com/user/me/notifications", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getPermissions() {
        DeezerRequest<GetPermissionsResponse> request = deezerApi.user().getPermissions();
        assertEquals("https://api.deezer.com/user/me/permissions", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getOptions() {
        DeezerRequest<Options> request = deezerApi.user().getOptions();
        assertEquals("https://api.deezer.com/user/me/options", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getPersonalSongs() {
        PaginationRequest<TrackData> request = deezerApi.user().getPersonalSongs().limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/me/personal_songs", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getPlaylists() {
        PaginationRequest<PlaylistData> request = deezerApi.user().getPlaylists(123123123123L).limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/123123123123/playlists", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getFavouriteRadios() {
        PaginationRequest<RadioData> request = deezerApi.user().getFavouriteRadios(123123123123L).limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/123123123123/radios", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getRecommendedAlbums() {
        PaginationRequest<AlbumData> request = deezerApi.user().getRecommendedAlbums().limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/me/recommendations/albums", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getRecommendedReleases() {
        PaginationRequest<AlbumData> request = deezerApi.user().getRecommendedReleases().limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/me/recommendations/releases", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getRecommendedArtists() {
        PaginationRequest<ArtistData> request = deezerApi.user().getRecommendedArtists().limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/me/recommendations/artists", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getRecommendedPlaylists() {
        PaginationRequest<PlaylistData> request = deezerApi.user().getRecommendedPlaylists().limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/me/recommendations/playlists", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getRecommendedTracks() {
        PaginationRequest<TrackData> request = deezerApi.user().getRecommendedTracks().limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/me/recommendations/tracks", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getRecommendedRadios() {
        PaginationRequest<RadioData> request = deezerApi.user().getRecommendedRadios().limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/me/recommendations/radios", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getFavouriteTracks() {
        PaginationRequest<TrackData> request = deezerApi.user().getFavouriteTracks(123123123123123L).limit(5).offset(1);
        assertEquals("https://api.deezer.com/user/123123123123123/tracks", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void addAlbumToLibrary() {
        DeezerRequest<Boolean> request = deezerApi.user().addAlbumToLibrary(456456456456456L);
        assertEquals("https://api.deezer.com/user/me/albums", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("456456456456456", request.getParams().get("album_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void addArtistToFavourites() {
        DeezerRequest<Boolean> request = deezerApi.user().addArtistToFavourites(789789789789L);
        assertEquals("https://api.deezer.com/user/me/artists", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("789789789789", request.getParams().get("artist_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void follow() {
        DeezerRequest<Boolean> request = deezerApi.user().follow(123123123123L);
        assertEquals("https://api.deezer.com/user/me/followings", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("123123123123", request.getParams().get("user_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void createPlaylist() {
        DeezerRequest<Id> request = deezerApi.user().createPlaylist("my top tracks");
        assertEquals("https://api.deezer.com/user/me/playlists", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("my top tracks", request.getParams().get("title"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void addPlaylistToFavourites() {
        DeezerRequest<Boolean> request = deezerApi.user().addPlaylistToFavourites(345345345345L);
        assertEquals("https://api.deezer.com/user/me/playlists", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("345345345345", request.getParams().get("playlist_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void addPodcastToFavourites() {
        DeezerRequest<Boolean> request = deezerApi.user().addPodcastToFavourites(345345345345L);
        assertEquals("https://api.deezer.com/user/me/podcasts", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("345345345345", request.getParams().get("podcast_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void addRadioToFavourites() {
        DeezerRequest<Boolean> request = deezerApi.user().addRadioToFavourites(345345345345L);
        assertEquals("https://api.deezer.com/user/me/radios", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("345345345345", request.getParams().get("radio_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void addTracksToFavourites() {
        DeezerRequest<Boolean> request = deezerApi.user().addTracksToFavourites(345345345345345L, 678L);
        assertEquals("https://api.deezer.com/user/me/tracks", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("345345345345345,678", request.getParams().get("songs"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void removeAlbum() {
        DeezerRequest<Boolean> request = deezerApi.user().removeAlbum(123123123123L);
        assertEquals("https://api.deezer.com/user/me/albums", request.getUrl());
        assertEquals("delete", request.getParams().get("request_method"));
        assertEquals("123123123123", request.getParams().get("album_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void removeArtist() {
        DeezerRequest<Boolean> request = deezerApi.user().removeArtist(123123123123L);
        assertEquals("https://api.deezer.com/user/me/artists", request.getUrl());
        assertEquals("delete", request.getParams().get("request_method"));
        assertEquals("123123123123", request.getParams().get("artist_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void unfollow() {
        DeezerRequest<Boolean> request = deezerApi.user().unfollow(123123123123L);
        assertEquals("https://api.deezer.com/user/me/followings", request.getUrl());
        assertEquals("delete", request.getParams().get("request_method"));
        assertEquals("123123123123", request.getParams().get("user_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void removePlaylist() {
        DeezerRequest<Boolean> request = deezerApi.user().removePlaylist(123123123123L);
        assertEquals("https://api.deezer.com/user/me/playlists", request.getUrl());
        assertEquals("delete", request.getParams().get("request_method"));
        assertEquals("123123123123", request.getParams().get("playlist_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void removePodcast() {
        DeezerRequest<Boolean> request = deezerApi.user().removePodcast(123123123123L);
        assertEquals("https://api.deezer.com/user/me/podcasts", request.getUrl());
        assertEquals("delete", request.getParams().get("request_method"));
        assertEquals("123123123123", request.getParams().get("podcast_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void removeRadio() {
        DeezerRequest<Boolean> request = deezerApi.user().removeRadio(123123123123L);
        assertEquals("https://api.deezer.com/user/me/radios", request.getUrl());
        assertEquals("delete", request.getParams().get("request_method"));
        assertEquals("123123123123", request.getParams().get("radio_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void removeTrack() {
        DeezerRequest<Boolean> request = deezerApi.user().removeTrack(123123123123L);
        assertEquals("https://api.deezer.com/user/me/tracks", request.getUrl());
        assertEquals("delete", request.getParams().get("request_method"));
        assertEquals("123123123123", request.getParams().get("track_id"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }
}
