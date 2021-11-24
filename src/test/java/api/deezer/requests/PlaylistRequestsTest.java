package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
import api.deezer.objects.Playlist;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaylistRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void getById() {
        DeezerRequest<Playlist> request = deezerApi.playlist().getById(908622995);
        assertEquals("https://api.deezer.com/playlist/908622995", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }

    @Test
    void markAsSeen() {
        DeezerRequest<Boolean> request = deezerApi.playlist().markAsSeen(908622995);
        assertEquals("https://api.deezer.com/playlist/908622995/seen", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }

    @Test
    void getFans() {
        PaginationRequest<UserData> request = deezerApi.playlist().getFans(908622995).limit(5).offset(1);
        assertEquals("https://api.deezer.com/playlist/908622995/fans", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getTracks() {
        PaginationRequest<TrackData> request = deezerApi.playlist().getTracks(908622995).limit(5).offset(1);
        assertEquals("https://api.deezer.com/playlist/908622995/tracks", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getRadio() {
        PaginationRequest<TrackData> request = deezerApi.playlist().getRadio(908622995).limit(5).offset(1);
        assertEquals("https://api.deezer.com/playlist/908622995/radio", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void addTracks() {
        DeezerRequest<Boolean> request = deezerApi.playlist().addTracks(908622995, 111, 222, 333);
        assertEquals("https://api.deezer.com/playlist/908622995/tracks", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("111,222,333", request.getParams().get("songs"));
    }

    @Test
    void orderTracks() {
        DeezerRequest<Boolean> request = deezerApi.playlist().orderTracks(908622995, 333, 222, 111);
        assertEquals("https://api.deezer.com/playlist/908622995/tracks", request.getUrl());
        assertEquals("post", request.getParams().get("request_method"));
        assertEquals("333,222,111", request.getParams().get("order"));
    }

    @Test
    void delete() {
        DeezerRequest<Boolean> request = deezerApi.playlist().delete(908622995);
        assertEquals("https://api.deezer.com/playlist/908622995", request.getUrl());
        assertEquals("delete", request.getParams().get("request_method"));
    }

    @Test
    void removeTracks() {
        DeezerRequest<Boolean> request = deezerApi.playlist().removeTracks(908622995, 111, 222);
        assertEquals("https://api.deezer.com/playlist/908622995", request.getUrl());
        assertEquals("delete", request.getParams().get("request_method"));
        assertEquals("111,222", request.getParams().get("songs"));
    }
}
