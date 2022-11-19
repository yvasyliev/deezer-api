package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
import api.deezer.objects.Album;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void getById() {
        DeezerRequest<Album> request = deezerApi.album().getById(302127302127302127L);
        assertEquals("https://api.deezer.com/album/302127302127302127", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }

    @Test
    void getFans() {
        PaginationRequest<UserData> request = deezerApi.album().getFans(302127302127302127L).limit(5).index(2);
        assertEquals("https://api.deezer.com/album/302127302127302127/fans", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("2", request.getParams().get("index"));
    }

    @Test
    void getTracks() {
        PaginationRequest<TrackData> request = deezerApi.album().getTracks(302127302127302127L).limit(5).index(2);
        assertEquals("https://api.deezer.com/album/302127302127302127/tracks", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("2", request.getParams().get("index"));
    }
}
