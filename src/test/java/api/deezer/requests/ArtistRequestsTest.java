package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
import api.deezer.objects.Artist;
import api.deezer.objects.data.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArtistRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void getById() {
        DeezerRequest<Artist> request = deezerApi.artist().getById(27);
        assertEquals("https://api.deezer.com/artist/27", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }

    @Test
    void getArtistTopFiveTracks() {
        PaginationRequest<TrackData> request = deezerApi.artist().getArtistTopFiveTracks(27).limit(3).offset(1);
        assertEquals("https://api.deezer.com/artist/27/top", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getAlbums() {
        PaginationRequest<AlbumData> request = deezerApi.artist().getAlbums(27).limit(3).offset(1);
        assertEquals("https://api.deezer.com/artist/27/albums", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getFans() {
        PaginationRequest<UserData> request = deezerApi.artist().getFans(27).limit(3).offset(1);
        assertEquals("https://api.deezer.com/artist/27/fans", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getRelatedArtists() {
        PaginationRequest<ArtistData> request = deezerApi.artist().getRelatedArtists(27).limit(3).offset(1);
        assertEquals("https://api.deezer.com/artist/27/related", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getRadio() {
        PaginationRequest<TrackData> request = deezerApi.artist().getRadio(27).limit(3).offset(1);
        assertEquals("https://api.deezer.com/artist/27/radio", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }

    @Test
    void getPlaylists() {
        PaginationRequest<PlaylistData> request = deezerApi.artist().getPlaylists(27).limit(3).offset(1);
        assertEquals("https://api.deezer.com/artist/27/playlists", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("offset"));
    }
}
