package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.GenreData;
import api.deezer.objects.data.RadioData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void getAll() {
        DeezerRequest<GenreData> request = deezerApi.genre().getAll();
        assertEquals("https://api.deezer.com/genre", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }

    @Test
    void getArtistsByGenreId() {
        PaginationRequest<ArtistData> request = deezerApi.genre().getArtistsByGenreId(1).limit(5).offset(1);
        assertEquals("https://api.deezer.com/genre/1/artists", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getRadiosByGenreId() {
        PaginationRequest<RadioData> request = deezerApi.genre().getRadiosByGenreId(1).limit(5).offset(1);
        assertEquals("https://api.deezer.com/genre/1/radios", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }
}
