package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.objects.Album;
import api.deezer.objects.Artist;
import api.deezer.objects.Chart;
import api.deezer.objects.Playlist;
import api.deezer.objects.Track;
import api.deezer.objects.data.DeezerData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChartRequestsTest extends AbstractRequestsTest {
    DeezerApi deezerApi = new DeezerApi();

    @Test
    void getAll() {
        enqueue(readBody("responses/chart/get.json"));

        Chart chart = assertDoesNotThrow(() -> deezerApi.chart().getAll().execute());

        takeRequestUrl("chart");

        DeezerData<Track> chartTracks = chart.getTracks();
        assertNotNull(chartTracks);
        assertEquals(10, chartTracks.getTotal());

        List<Track> tracks = chartTracks.getData();
        assertNotNull(tracks);
        assertFalse(tracks.isEmpty());

        Track track = tracks.get(0);
        assertNotNull(track);
        assertEquals(2448358895L, track.getId());
        assertEquals("Знайди мене", track.getTitle());
        assertEquals("Знайди мене", track.getTitleShort());
        assertEquals("", track.getTitleVersion());
        assertEquals("https://www.deezer.com/track/2448358895", track.getLink());
        assertEquals(150, track.getDuration());
        assertEquals(633012, track.getRank());
        assertFalse(track.getExplicitLyrics());
        assertEquals(0, track.getExplicitContentLyrics());
        assertEquals(0, track.getExplicitContentCover());
        assertEquals("https://cdns-preview-1.dzcdn.net/stream/c-13974121939a558681af42166fd17887-1.mp3", track.getPreview());
        assertEquals("548f529e224e72f3c2d6c33f2cd4ad0a", track.getMd5Image());
        assertEquals(1, track.getPosition());

        Artist artist = track.getArtist();
        assertNotNull(artist);
        assertEquals(225100035, artist.getId());
        assertEquals("Klavdia Petrivna", artist.getName());
        assertEquals("https://www.deezer.com/artist/225100035", artist.getLink());
        assertEquals("https://api.deezer.com/artist/225100035/image", artist.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/548f529e224e72f3c2d6c33f2cd4ad0a/56x56-000000-80-0-0.jpg", artist.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/548f529e224e72f3c2d6c33f2cd4ad0a/250x250-000000-80-0-0.jpg", artist.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/548f529e224e72f3c2d6c33f2cd4ad0a/500x500-000000-80-0-0.jpg", artist.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/548f529e224e72f3c2d6c33f2cd4ad0a/1000x1000-000000-80-0-0.jpg", artist.getPictureXl());
        assertTrue(artist.getRadio());
        assertEquals("https://api.deezer.com/artist/225100035/top?limit=50", artist.getTracklist());

        Album album = track.getAlbum();
        assertNotNull(album);
        assertEquals(486462165, album.getId());
        assertEquals("Бережи мене", album.getTitle());
        assertEquals("https://api.deezer.com/album/486462165/image", album.getCover());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/548f529e224e72f3c2d6c33f2cd4ad0a/56x56-000000-80-0-0.jpg", album.getCoverSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/548f529e224e72f3c2d6c33f2cd4ad0a/250x250-000000-80-0-0.jpg", album.getCoverMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/548f529e224e72f3c2d6c33f2cd4ad0a/500x500-000000-80-0-0.jpg", album.getCoverBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/548f529e224e72f3c2d6c33f2cd4ad0a/1000x1000-000000-80-0-0.jpg", album.getCoverXl());
        assertEquals("548f529e224e72f3c2d6c33f2cd4ad0a", album.getMd5Image());
        assertEquals("https://api.deezer.com/album/486462165/tracks", album.getTracklist());

        DeezerData<Album> chartAlbums = chart.getAlbums();
        assertNotNull(chartAlbums);
        assertEquals(10, chartAlbums.getTotal());

        List<Album> albums = chartAlbums.getData();
        assertNotNull(albums);
        assertFalse(albums.isEmpty());

        album = albums.get(0);
        assertEquals(1434890, album.getId());
        assertEquals("Toxicity", album.getTitle());
        assertEquals("https://www.deezer.com/album/1434890", album.getLink());
        assertEquals("https://api.deezer.com/album/1434890/image", album.getCover());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/1d2f10d7d6bfb816148e8dda5c32f841/56x56-000000-80-0-0.jpg", album.getCoverSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/1d2f10d7d6bfb816148e8dda5c32f841/250x250-000000-80-0-0.jpg", album.getCoverMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/1d2f10d7d6bfb816148e8dda5c32f841/500x500-000000-80-0-0.jpg", album.getCoverBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/1d2f10d7d6bfb816148e8dda5c32f841/1000x1000-000000-80-0-0.jpg", album.getCoverXl());
        assertEquals("1d2f10d7d6bfb816148e8dda5c32f841", album.getMd5Image());
        assertEquals("album", album.getRecordType());
        assertEquals("https://api.deezer.com/album/1434890/tracks", album.getTracklist());
        assertFalse(album.getExplicitLyrics());
        assertEquals(1, album.getPosition());

        artist = album.getArtist();
        assertNotNull(artist);
        assertEquals(458, artist.getId());
        assertEquals("System of A Down", artist.getName());
        assertEquals("https://www.deezer.com/artist/458", artist.getLink());
        assertEquals("https://api.deezer.com/artist/458/image", artist.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/67460cdb0b52bfde1b807650958058d6/56x56-000000-80-0-0.jpg", artist.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/67460cdb0b52bfde1b807650958058d6/250x250-000000-80-0-0.jpg", artist.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/67460cdb0b52bfde1b807650958058d6/500x500-000000-80-0-0.jpg", artist.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/67460cdb0b52bfde1b807650958058d6/1000x1000-000000-80-0-0.jpg", artist.getPictureXl());
        assertTrue(artist.getRadio());
        assertEquals("https://api.deezer.com/artist/458/top?limit=50", artist.getTracklist());

        DeezerData<Artist> chartArtists = chart.getArtists();
        assertNotNull(chartArtists);
        assertEquals(10, chartArtists.getTotal());

        List<Artist> artists = chartArtists.getData();
        assertNotNull(artists);
        assertFalse(artists.isEmpty());

        artist = artists.get(0);
        assertNotNull(artists);
        assertEquals(230, artist.getId());
        assertEquals("Kanye West", artist.getName());
        assertEquals("https://api.deezer.com/artist/230/image", artist.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/bb76c2ee3b068726ab4c37b0aabdb57a/56x56-000000-80-0-0.jpg", artist.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/bb76c2ee3b068726ab4c37b0aabdb57a/250x250-000000-80-0-0.jpg", artist.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/bb76c2ee3b068726ab4c37b0aabdb57a/500x500-000000-80-0-0.jpg", artist.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/bb76c2ee3b068726ab4c37b0aabdb57a/1000x1000-000000-80-0-0.jpg", artist.getPictureXl());
        assertTrue(artist.getRadio());
        assertEquals("https://api.deezer.com/artist/230/top?limit=50", artist.getTracklist());
        assertEquals(1, artist.getPosition());

        DeezerData<Playlist> chartPlaylists = chart.getPlaylists();
        assertNotNull(chartPlaylists);
        assertEquals(10, chartPlaylists.getTotal());

        List<Playlist> playlists = chartPlaylists.getData();
        assertNotNull(playlists);
        assertFalse(playlists.isEmpty());

        Playlist playlist = playlists.get(0);
        assertNotNull(playlist);
        assertEquals(8454338222L, playlist.getId());
        assertEquals("Christmas Hits", playlist.getTitle());
        assertTrue(playlist.getPublic());
        assertEquals(50, playlist.getNbTracks());
        assertEquals("https://www.deezer.com/playlist/8454338222", playlist.getLink());
        assertEquals("https://api.deezer.com/playlist/8454338222/image", playlist.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/playlist/d2ea6cd34136caa84a0f95f5ab4a85e0/56x56-000000-80-0-0.jpg", playlist.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/playlist/d2ea6cd34136caa84a0f95f5ab4a85e0/250x250-000000-80-0-0.jpg", playlist.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/playlist/d2ea6cd34136caa84a0f95f5ab4a85e0/500x500-000000-80-0-0.jpg", playlist.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/playlist/d2ea6cd34136caa84a0f95f5ab4a85e0/1000x1000-000000-80-0-0.jpg", playlist.getPictureXl());
        assertEquals("67d605831ed2204c131aa52c9e8bcb3c", playlist.getChecksum());
        // TODO: 11/26/2023 implement playlist.getTracklist
        // TODO: 11/26/2023 implement playlist.getCreationDate
        // TODO: 11/26/2023 implement playlist.getMd5Image
        // TODO: 11/26/2023 implement playlist.getPictureType
        // TODO: 11/26/2023 implement playlist.getUser

        DeezerData<Playlist> chartPodcasts = chart.getPodcasts();
        assertNotNull(chartPodcasts);
        assertEquals(10, chartPodcasts.getTotal());

        List<Playlist> podcasts = chartPodcasts.getData(); // TODO: 11/26/2023 Migrate to List<Podcast>
    }

    @Test
    void getTopTracks() {
    }

    @Test
    void getTopAlbums() {
    }

    @Test
    void getTopArtists() {
    }

    @Test
    void getTopPlaylists() {
    }

    @Test
    void getTopPodcasts() {
    }
}