package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.objects.Options;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void get() {
        DeezerRequest<Options> request = deezerApi.options().get();
        assertEquals("https://api.deezer.com/options", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }
}
