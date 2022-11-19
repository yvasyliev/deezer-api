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
        DeezerRequest<Artist> request = deezerApi.artist().getById(27272727272727L);
        assertEquals("https://api.deezer.com/artist/27272727272727", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
    }

    @Test
    void getArtistTopFiveTracks() {
        PaginationRequest<TrackData> request = deezerApi.artist().getArtistTopFiveTracks(27272727272727L).limit(3).index(1);
        assertEquals("https://api.deezer.com/artist/27272727272727/top", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("index"));
    }

    @Test
    void getAlbums() {
        PaginationRequest<AlbumData> request = deezerApi.artist().getAlbums(27272727272727L).limit(3).index(1);
        assertEquals("https://api.deezer.com/artist/27272727272727/albums", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("index"));
    }

    @Test
    void getFans() {
        PaginationRequest<UserData> request = deezerApi.artist().getFans(27272727272727L).limit(3).index(1);
        assertEquals("https://api.deezer.com/artist/27272727272727/fans", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("index"));
    }

    @Test
    void getRelatedArtists() {
        PaginationRequest<ArtistData> request = deezerApi.artist().getRelatedArtists(27272727272727L).limit(3).index(1);
        assertEquals("https://api.deezer.com/artist/27272727272727/related", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("index"));
    }

    @Test
    void getRadio() {
        PaginationRequest<TrackData> request = deezerApi.artist().getRadio(27272727272727L).limit(3).index(1);
        assertEquals("https://api.deezer.com/artist/27272727272727/radio", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("index"));
    }

    @Test
    void getPlaylists() {
        PaginationRequest<PlaylistData> request = deezerApi.artist().getPlaylists(27272727272727L).limit(3).index(1);
        assertEquals("https://api.deezer.com/artist/27272727272727/playlists", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("3", request.getParams().get("limit"));
        assertEquals("1", request.getParams().get("index"));
    }
}
