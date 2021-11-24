package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.objects.Track;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void getById() {
        DeezerRequest<Track> request = deezerApi.track().getById(3135556);
        assertEquals("https://api.deezer.com/track/3135556", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }

    @Test
    void delete() {
        DeezerRequest<Boolean> request = deezerApi.track().delete(3135556);
        assertEquals("https://api.deezer.com/track/3135556", request.getUrl());
        assertEquals("delete", request.getParams().get("request_method"));
        assertEquals("accessToken", request.getParams().get("access_token"));
    }
}
