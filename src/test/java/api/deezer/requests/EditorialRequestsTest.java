package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
import api.deezer.objects.Chart;
import api.deezer.objects.data.AlbumData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditorialRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void getSelectedAlbums() {
        PaginationRequest<AlbumData> request = deezerApi.editorial().getSelectedAlbums().limit(5).offset(1);
        assertEquals("https://api.deezer.com/editorial/0/selection", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getChart() {
        DeezerRequest<Chart> request = deezerApi.editorial().getChart();
        assertEquals("https://api.deezer.com/editorial/0/charts", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }

    @Test
    void getNewReleases() {
        PaginationRequest<AlbumData> request = deezerApi.editorial().getNewReleases().limit(5).offset(1);
        assertEquals("https://api.deezer.com/editorial/0/releases", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }
}
