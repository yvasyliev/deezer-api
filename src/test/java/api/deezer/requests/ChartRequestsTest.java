package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
import api.deezer.objects.Chart;
import api.deezer.objects.data.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChartRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void getAll() {
        DeezerRequest<Chart> request = deezerApi.chart().getAll();
        assertEquals("https://api.deezer.com/chart", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }

    @Test
    void getTopTracks() {
        PaginationRequest<TrackData> request = deezerApi.chart().getTopTracks().limit(5).offset(1);
        assertEquals("https://api.deezer.com/chart/0/tracks", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getTopAlbums() {
        PaginationRequest<AlbumData> request = deezerApi.chart().getTopAlbums().limit(5).offset(1);
        assertEquals("https://api.deezer.com/chart/0/albums", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getTopArtists() {
        PaginationRequest<ArtistData> request = deezerApi.chart().getTopArtists().limit(5).offset(1);
        assertEquals("https://api.deezer.com/chart/0/artists", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getTopPlaylists() {
        PaginationRequest<PlaylistData> request = deezerApi.chart().getTopPlaylists().limit(5).offset(1);
        assertEquals("https://api.deezer.com/chart/0/playlists", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getTopPodcasts() {
        PaginationRequest<PodcastData> request = deezerApi.chart().getTopPodcasts().limit(5).offset(1);
        assertEquals("https://api.deezer.com/chart/0/podcasts", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("5", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }
}
