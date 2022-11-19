package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.AdvancedSearchRequest;
import api.deezer.http.impl.SearchRequest;
import api.deezer.objects.SearchOrder;
import api.deezer.objects.data.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void searchAlbum() {
        SearchRequest<AlbumData> searchRequest = deezerApi.search().searchAlbum("nevermind").strict().order(SearchOrder.ALBUM_ASC).limit(5).index(1);
        assertEquals("https://api.deezer.com/search/album", searchRequest.getUrl());
        assertEquals("get", searchRequest.getParams().get("request_method"));
        assertEquals("nevermind", searchRequest.getParams().get("q"));
        assertEquals("on", searchRequest.getParams().get("strict"));
        assertEquals("ALBUM_ASC", searchRequest.getParams().get("order"));
        assertEquals("5", searchRequest.getParams().get("limit"));
        assertEquals("1", searchRequest.getParams().get("index"));

        AdvancedSearchRequest<AlbumData> advancedSearchRequest = deezerApi.search().searchAlbum().artist("nirvana").album("nevermind");
        assertEquals("https://api.deezer.com/search/album", advancedSearchRequest.getUrl());
        assertEquals("get", advancedSearchRequest.getParams().get("request_method"));
        assertEquals("artist:\"nirvana\" album:\"nevermind\"", advancedSearchRequest.getParams().get("q"));
    }

    @Test
    void searchArtist() {
        SearchRequest<ArtistData> searchRequest = deezerApi.search().searchArtist("eminem").strict().order(SearchOrder.ARTIST_DESC).limit(5).index(1);
        assertEquals("https://api.deezer.com/search/artist", searchRequest.getUrl());
        assertEquals("get", searchRequest.getParams().get("request_method"));
        assertEquals("eminem", searchRequest.getParams().get("q"));
        assertEquals("on", searchRequest.getParams().get("strict"));
        assertEquals("ARTIST_DESC", searchRequest.getParams().get("order"));
        assertEquals("5", searchRequest.getParams().get("limit"));
        assertEquals("1", searchRequest.getParams().get("index"));

        AdvancedSearchRequest<ArtistData> advancedSearchRequest = deezerApi.search().searchArtist().artist("eminem").bpmMax(1000).bpmMin(100);
        assertEquals("https://api.deezer.com/search/artist", advancedSearchRequest.getUrl());
        assertEquals("get", advancedSearchRequest.getParams().get("request_method"));
        assertEquals("artist:\"eminem\" bpm_max:1000 bpm_min:100", advancedSearchRequest.getParams().get("q"));
    }

    @Test
    void searchPlaylist() {
        SearchRequest<PlaylistData> searchRequest = deezerApi.search().searchPlaylist("2020").strict().order(SearchOrder.DURATION_ASC).limit(5).index(1);
        assertEquals("https://api.deezer.com/search/playlist", searchRequest.getUrl());
        assertEquals("get", searchRequest.getParams().get("request_method"));
        assertEquals("2020", searchRequest.getParams().get("q"));
        assertEquals("on", searchRequest.getParams().get("strict"));
        assertEquals("DURATION_ASC", searchRequest.getParams().get("order"));
        assertEquals("5", searchRequest.getParams().get("limit"));
        assertEquals("1", searchRequest.getParams().get("index"));

        AdvancedSearchRequest<PlaylistData> advancedSearchRequest = deezerApi.search().searchPlaylist().durMax(60).durMin(10);
        assertEquals("https://api.deezer.com/search/playlist", advancedSearchRequest.getUrl());
        assertEquals("get", advancedSearchRequest.getParams().get("request_method"));
        assertEquals("dur_max:60 dur_min:10", advancedSearchRequest.getParams().get("q"));
    }

    @Test
    void searchRadio() {
        SearchRequest<RadioData> searchRequest = deezerApi.search().searchRadio("indie").strict().order(SearchOrder.RATING_DESC).limit(5).index(1);
        assertEquals("https://api.deezer.com/search/radio", searchRequest.getUrl());
        assertEquals("get", searchRequest.getParams().get("request_method"));
        assertEquals("indie", searchRequest.getParams().get("q"));
        assertEquals("on", searchRequest.getParams().get("strict"));
        assertEquals("RATING_DESC", searchRequest.getParams().get("order"));
        assertEquals("5", searchRequest.getParams().get("limit"));
        assertEquals("1", searchRequest.getParams().get("index"));

        AdvancedSearchRequest<RadioData> advancedSearchRequest = deezerApi.search().searchRadio().label("indie");
        assertEquals("https://api.deezer.com/search/radio", advancedSearchRequest.getUrl());
        assertEquals("get", advancedSearchRequest.getParams().get("request_method"));
        assertEquals("label:\"indie\"", advancedSearchRequest.getParams().get("q"));
    }

    @Test
    void searchTrack() {
        SearchRequest<TrackData> searchRequest = deezerApi.search().searchTrack("star").strict().order(SearchOrder.TRACK_ASC).limit(5).index(1);
        assertEquals("https://api.deezer.com/search/track", searchRequest.getUrl());
        assertEquals("get", searchRequest.getParams().get("request_method"));
        assertEquals("star", searchRequest.getParams().get("q"));
        assertEquals("on", searchRequest.getParams().get("strict"));
        assertEquals("TRACK_ASC", searchRequest.getParams().get("order"));
        assertEquals("5", searchRequest.getParams().get("limit"));
        assertEquals("1", searchRequest.getParams().get("index"));

        AdvancedSearchRequest<TrackData> advancedSearchRequest = deezerApi.search().searchTrack().track("star");
        assertEquals("https://api.deezer.com/search/track", advancedSearchRequest.getUrl());
        assertEquals("get", advancedSearchRequest.getParams().get("request_method"));
        assertEquals("track:\"star\"", advancedSearchRequest.getParams().get("q"));
    }

    @Test
    void searchUser() {
        SearchRequest<UserData> searchRequest = deezerApi.search().searchUser("arthur").strict().order(SearchOrder.RANKING).limit(5).index(1);
        assertEquals("https://api.deezer.com/search/user", searchRequest.getUrl());
        assertEquals("get", searchRequest.getParams().get("request_method"));
        assertEquals("arthur", searchRequest.getParams().get("q"));
        assertEquals("on", searchRequest.getParams().get("strict"));
        assertEquals("RANKING", searchRequest.getParams().get("order"));
        assertEquals("5", searchRequest.getParams().get("limit"));
        assertEquals("1", searchRequest.getParams().get("index"));

        AdvancedSearchRequest<UserData> advancedSearchRequest = deezerApi.search().searchUser().track("sunset");
        assertEquals("https://api.deezer.com/search/user", advancedSearchRequest.getUrl());
        assertEquals("get", advancedSearchRequest.getParams().get("request_method"));
        assertEquals("track:\"sunset\"", advancedSearchRequest.getParams().get("q"));
    }
}
