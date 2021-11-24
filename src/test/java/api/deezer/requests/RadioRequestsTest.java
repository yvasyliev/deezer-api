package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.objects.data.RadioData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RadioRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void get() {
        DeezerRequest<RadioData> request = deezerApi.radio().get();
        assertEquals("https://api.deezer.com/radio", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }
}
