package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.objects.Infos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InfosRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void get() {
        DeezerRequest<Infos> request = deezerApi.infos().get();
        assertEquals("https://api.deezer.com/infos", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }
}
