package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.objects.Album;
import api.deezer.objects.Artist;
import api.deezer.objects.Playlist;
import api.deezer.objects.Track;
import api.deezer.objects.data.AlbumData;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.PlaylistData;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArtistRequestsTest extends AbstractRequestsTest {
    DeezerApi deezerApi = new DeezerApi();

    @Test
    void getById() {
        enqueue(readBody("responses/artist/artist.json"));

        Artist artist = assertDoesNotThrow(() -> deezerApi.artist().getById(ARTIST_ID).execute());

        takeRequestUrl("artist", String.valueOf(ARTIST_ID));

        assertNotNull(artist);
        assertEquals(ARTIST_ID, artist.getId());
        assertEquals("YONAKA", artist.getName());
        assertEquals("https://www.deezer.com/artist/9834474", artist.getLink());
        assertEquals("https://www.deezer.com/artist/9834474?utm_source=deezer&utm_content=artist-9834474&utm_term=0_1701021314&utm_medium=web", artist.getShare());
        assertEquals("https://api.deezer.com/artist/9834474/image", artist.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/9e77a3d8704aa53f0de712795d3c7c62/56x56-000000-80-0-0.jpg", artist.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/9e77a3d8704aa53f0de712795d3c7c62/250x250-000000-80-0-0.jpg", artist.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/9e77a3d8704aa53f0de712795d3c7c62/500x500-000000-80-0-0.jpg", artist.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/9e77a3d8704aa53f0de712795d3c7c62/1000x1000-000000-80-0-0.jpg", artist.getPictureXl());
        assertEquals(28, artist.getNbAlbum());
        assertEquals(8624, artist.getNbFan());
        assertTrue(artist.getRadio());
        assertEquals("https://api.deezer.com/artist/9834474/top?limit=50", artist.getTracklist());
    }

    @Test
    void getArtistTopFiveTracks() {
        enqueue(readBody("responses/artist/top.json"));

        TrackData topTracks = assertDoesNotThrow(() -> deezerApi.artist().getArtistTopFiveTracks(ARTIST_ID).execute());

        takeRequestUrl("artist", String.valueOf(ARTIST_ID), "top");

        assertEquals(50, topTracks.getTotal());
        assertEquals("https://api.deezer.com/artist/9834474/top?index=5", topTracks.getNext());

        List<Track> tracks = topTracks.getData();
        assertFalse(tracks.isEmpty());

        Track track = tracks.get(0);
        assertEquals(2079531117, track.getId());
        assertTrue(track.getIsReadable());
        assertEquals("Detonate", track.getTitle());
        assertEquals("Detonate", track.getTitleShort());
        assertEquals("", track.getTitleVersion());
        assertEquals("https://www.deezer.com/track/2079531117", track.getLink());
        assertEquals(167, track.getDuration());
        assertEquals(370165, track.getRank());
        assertFalse(track.getExplicitLyrics());
        assertEquals(0, track.getExplicitContentLyrics());
        assertEquals(2, track.getExplicitContentCover());
        assertEquals("https://cdns-preview-0.dzcdn.net/stream/c-0e62c2a959e7398cf982ba566d993f5a-3.mp3", track.getPreview());
        assertEquals("2ccb97861febc689ea6eaf32434048ca", track.getMd5Image());

        List<Artist> contributors = track.getContributors();
        assertFalse(contributors.isEmpty());

        Artist contributor = contributors.get(0);
        assertEquals(155921852, contributor.getId());
        assertEquals("G2", contributor.getName());
        assertEquals("https://www.deezer.com/artist/155921852", contributor.getLink());
        assertEquals("https://www.deezer.com/artist/155921852?utm_source=deezer&utm_content=artist-155921852&utm_term=0_1695821581&utm_medium=web", contributor.getShare());
        assertEquals("https://api.deezer.com/artist/155921852/image", contributor.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/d2e44bbcc90e22a6037b38be063b063e/56x56-000000-80-0-0.jpg", contributor.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/d2e44bbcc90e22a6037b38be063b063e/250x250-000000-80-0-0.jpg", contributor.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/d2e44bbcc90e22a6037b38be063b063e/500x500-000000-80-0-0.jpg", contributor.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/d2e44bbcc90e22a6037b38be063b063e/1000x1000-000000-80-0-0.jpg", contributor.getPictureXl());
        assertTrue(contributor.getRadio());
        assertEquals("https://api.deezer.com/artist/155921852/top?limit=50", contributor.getTracklist());
        // TODO: 9/27/2023 add "role" to Artist object

        Artist artist = track.getArtist();
        assertEquals(155921852, artist.getId());
        assertEquals("G2", artist.getName());
        assertEquals("https://api.deezer.com/artist/155921852/top?limit=50", artist.getTracklist());

        Album album = track.getAlbum();
        assertEquals(389017917, album.getId());
        assertEquals("Detonate", album.getTitle());
        assertEquals("https://api.deezer.com/album/389017917/image", album.getCover());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/2ccb97861febc689ea6eaf32434048ca/56x56-000000-80-0-0.jpg", album.getCoverSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/2ccb97861febc689ea6eaf32434048ca/250x250-000000-80-0-0.jpg", album.getCoverMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/2ccb97861febc689ea6eaf32434048ca/500x500-000000-80-0-0.jpg", album.getCoverBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/2ccb97861febc689ea6eaf32434048ca/1000x1000-000000-80-0-0.jpg", album.getCoverXl());
        assertEquals("2ccb97861febc689ea6eaf32434048ca", album.getMd5Image());
        assertEquals("https://api.deezer.com/album/389017917/tracks", album.getTracklist());
    }

    @Test
    void getAlbums() {
        enqueue(readBody("responses/artist/albums.json"));

        AlbumData artistAlbums = assertDoesNotThrow(() -> deezerApi.artist().getAlbums(ARTIST_ID).execute());

        takeRequestUrl("artist", String.valueOf(ARTIST_ID), "albums");

        assertEquals(29, artistAlbums.getTotal());
        assertEquals("https://api.deezer.com/artist/9834474/albums?index=25", artistAlbums.getNext());

        List<Album> albums = artistAlbums.getData();
        assertFalse(albums.isEmpty());

        Album album = albums.get(0);
        assertEquals(467093475, album.getId());
        assertEquals("Welcome To My House", album.getTitle());
        assertEquals("https://www.deezer.com/album/467093475", album.getLink());
        assertEquals("https://api.deezer.com/album/467093475/image", album.getCover());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/9311cd61dc991356f32e7880532808dd/56x56-000000-80-0-0.jpg", album.getCoverSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/9311cd61dc991356f32e7880532808dd/250x250-000000-80-0-0.jpg", album.getCoverMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/9311cd61dc991356f32e7880532808dd/500x500-000000-80-0-0.jpg", album.getCoverBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/9311cd61dc991356f32e7880532808dd/1000x1000-000000-80-0-0.jpg", album.getCoverXl());
        assertEquals("9311cd61dc991356f32e7880532808dd", album.getMd5Image());
        assertEquals(85, album.getGenreId());
        assertEquals(220, album.getFans());
        assertEquals(Date.from(LocalDate.of(2023, Month.JULY, 28).atStartOfDay(ZoneId.systemDefault()).toInstant()), album.getReleaseDate());
        assertEquals("album", album.getRecordType());
        assertEquals("https://api.deezer.com/album/467093475/tracks", album.getTracklist());
        assertTrue(album.getExplicitLyrics());
    }

    @Test
    void getFans() {
        enqueue(readBody("responses/artist/fans.json"));

        UserData fans = assertDoesNotThrow(() -> deezerApi.artist().getFans(ARTIST_ID).execute());

        takeRequestUrl("artist", String.valueOf(ARTIST_ID), "fans");

        assertEquals(0, fans.getTotal());
        assertTrue(fans.getData().isEmpty());
    }

    @Test
    void getRelatedArtists() {
        enqueue(readBody("responses/artist/related.json"));

        ArtistData relatedArtists = assertDoesNotThrow(() -> deezerApi.artist().getRelatedArtists(ARTIST_ID).execute());

        takeRequestUrl("artist", String.valueOf(ARTIST_ID), "related");

        assertEquals(20, relatedArtists.getTotal());

        List<Artist> artists = relatedArtists.getData();
        assertFalse(artists.isEmpty());

        Artist artist = artists.get(0);
        assertEquals(2210161, artist.getId());
        assertEquals("Nothing But Thieves", artist.getName());
        assertEquals("https://www.deezer.com/artist/2210161", artist.getLink());
        assertEquals("https://api.deezer.com/artist/2210161/image", artist.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/0c2903ae7c3566b8229bf73584b2a141/56x56-000000-80-0-0.jpg", artist.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/0c2903ae7c3566b8229bf73584b2a141/250x250-000000-80-0-0.jpg", artist.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/0c2903ae7c3566b8229bf73584b2a141/500x500-000000-80-0-0.jpg", artist.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/0c2903ae7c3566b8229bf73584b2a141/1000x1000-000000-80-0-0.jpg", artist.getPictureXl());
        assertEquals(47, artist.getNbAlbum());
        assertEquals(202175, artist.getNbFan());
        assertTrue(artist.getRadio());
        assertEquals("https://api.deezer.com/artist/2210161/top?limit=50", artist.getTracklist());
    }

    @Test
    void getRadio() {
        enqueue(readBody("responses/artist/radio.json"));

        TrackData radio = assertDoesNotThrow(() -> deezerApi.artist().getRadio(ARTIST_ID).execute());

        takeRequestUrl("artist", String.valueOf(ARTIST_ID), "radio");

        List<Track> tracks = radio.getData();
        assertFalse(tracks.isEmpty());

        Track track = tracks.get(0);
        assertEquals(2126040137, track.getId());
        assertTrue(track.getIsReadable());
        assertEquals("PANIC", track.getTitle());
        assertEquals("PANIC", track.getTitleShort());
        assertEquals("", track.getTitleVersion());
        assertEquals(174, track.getDuration());
        assertEquals(345440, track.getRank());
        assertTrue(track.getExplicitLyrics());
        assertEquals(1, track.getExplicitContentLyrics());
        assertEquals(1, track.getExplicitContentCover());
        assertEquals("https://cdns-preview-3.dzcdn.net/stream/c-39433afd5db8f242bef5f585cd188c94-6.mp3", track.getPreview());
        assertEquals("1696bfec718716bbafd6e358ef9fb77f", track.getMd5Image());

        Artist artist = track.getArtist();
        assertEquals(9834474, artist.getId());
        assertEquals("Yonaka", artist.getName());
        assertEquals("https://api.deezer.com/artist/9834474/image", artist.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/9e77a3d8704aa53f0de712795d3c7c62/56x56-000000-80-0-0.jpg", artist.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/9e77a3d8704aa53f0de712795d3c7c62/250x250-000000-80-0-0.jpg", artist.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/9e77a3d8704aa53f0de712795d3c7c62/500x500-000000-80-0-0.jpg", artist.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/9e77a3d8704aa53f0de712795d3c7c62/1000x1000-000000-80-0-0.jpg", artist.getPictureXl());
        assertEquals("https://api.deezer.com/artist/9834474/top?limit=50", artist.getTracklist());

        Album album = track.getAlbum();
        assertEquals(401016697, album.getId());
        assertEquals("PANIC", album.getTitle());
        assertEquals("https://api.deezer.com/album/401016697/image", album.getCover());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/1696bfec718716bbafd6e358ef9fb77f/56x56-000000-80-0-0.jpg", album.getCoverSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/1696bfec718716bbafd6e358ef9fb77f/250x250-000000-80-0-0.jpg", album.getCoverMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/1696bfec718716bbafd6e358ef9fb77f/500x500-000000-80-0-0.jpg", album.getCoverBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/1696bfec718716bbafd6e358ef9fb77f/1000x1000-000000-80-0-0.jpg", album.getCoverXl());
        assertEquals("1696bfec718716bbafd6e358ef9fb77f", album.getMd5Image());
        assertEquals("https://api.deezer.com/album/401016697/tracks", album.getTracklist());
    }

    @Test
    void getPlaylists() {
        enqueue(readBody("responses/artist/playlists.json"));

        PlaylistData playlistData = assertDoesNotThrow(() -> deezerApi.artist().getPlaylists(ARTIST_ID).execute());

        takeRequestUrl("artist", String.valueOf(ARTIST_ID), "playlists");

        assertNotNull(playlistData);
        assertEquals(19, playlistData.getTotal());
        assertEquals("https://api.deezer.com/artist/9834474/playlists?index=10", playlistData.getNext());

        List<Playlist> playlists = playlistData.getData();
        assertNotNull(playlists);
        assertFalse(playlists.isEmpty());

        Playlist playlist = playlists.get(0);
        assertNotNull(playlist);
        assertEquals(10519540222L, playlist.getId());
        assertEquals("new violence", playlist.getTitle());
        assertTrue(playlist.getPublic());
        assertEquals("https://www.deezer.com/playlist/10519540222", playlist.getLink());
        assertEquals("https://api.deezer.com/playlist/10519540222/image", playlist.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/playlist/de0517f687118e356103890b616709e0/56x56-000000-80-0-0.jpg", playlist.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/playlist/de0517f687118e356103890b616709e0/250x250-000000-80-0-0.jpg", playlist.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/playlist/de0517f687118e356103890b616709e0/500x500-000000-80-0-0.jpg", playlist.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/playlist/de0517f687118e356103890b616709e0/1000x1000-000000-80-0-0.jpg", playlist.getPictureXl());
        assertEquals("10161ca6f6030e4c5589c4818eda255c", playlist.getChecksum());
        // TODO: 11/26/2023 implement playlist.getTracklist
        // TODO: 11/26/2023 implement playlist.getCreationDate
        // TODO: 11/26/2023 implement playlist.getPictureType
        // TODO: 11/26/2023 implement playlist.getUser
    }
}