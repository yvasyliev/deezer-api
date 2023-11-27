package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.objects.Album;
import api.deezer.objects.Artist;
import api.deezer.objects.Contributor;
import api.deezer.objects.Genre;
import api.deezer.objects.Track;
import api.deezer.objects.User;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlbumRequestsTest extends AbstractRequestsTest {
    DeezerApi deezerApi = new DeezerApi();

    @Test
    void getById() {
        enqueue(readBody("responses/album/get.json"));

        Album album = assertDoesNotThrow(() -> deezerApi.album().getById(ALBUM_ID).execute());

        takeRequestUrl("album", String.valueOf(ALBUM_ID));

        assertEquals(ALBUM_ID, album.getId());
        assertEquals("Blue Lullaby", album.getTitle());
        assertEquals("196589133120", album.getUpc());
        assertEquals("https://www.deezer.com/album/326060237", album.getLink());
        assertEquals("https://www.deezer.com/album/326060237?utm_source=deezer&utm_content=album-326060237&utm_term=0_1695643773&utm_medium=web", album.getShare());
        assertEquals("https://api.deezer.com/album/326060237/image", album.getCover());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/a22ac557760820340e1ac5ba9fee496a/56x56-000000-80-0-0.jpg", album.getCoverSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/a22ac557760820340e1ac5ba9fee496a/250x250-000000-80-0-0.jpg", album.getCoverMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/a22ac557760820340e1ac5ba9fee496a/500x500-000000-80-0-0.jpg", album.getCoverBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/a22ac557760820340e1ac5ba9fee496a/1000x1000-000000-80-0-0.jpg", album.getCoverXl());
        assertEquals("a22ac557760820340e1ac5ba9fee496a", album.getMd5Image());
        assertEquals(85, album.getGenreId());
        assertEquals("Dirty Hit", album.getLabel());
        assertEquals(5, album.getNbTracks());
        assertEquals(1135, album.getDuration());
        assertEquals(421, album.getFans());
        assertEquals(LocalDate.of(2022, Month.JUNE, 24), album.getReleaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        assertEquals("ep", album.getRecordType());
        assertTrue(album.getAvailable());
        assertEquals("https://api.deezer.com/album/326060237/tracks", album.getTracklist());
        assertFalse(album.getExplicitLyrics());
        assertEquals(0, album.getExplicitContentLyrics());
        assertEquals(0, album.getExplicitContentCover());

        List<Genre> genres = album.getGenres().getData();
        assertFalse(genres.isEmpty());

        Genre genre = genres.get(0);
        assertEquals(85, genre.getId());
        assertEquals("Alternative", genre.getName());
        assertEquals("https://api.deezer.com/genre/85/image", genre.getPicture());

        List<Contributor> contributors = album.getContributors();
        assertFalse(contributors.isEmpty());

        Contributor contributor = contributors.get(0);
        assertEquals(4710481, contributor.getId());
        assertEquals("Wolf Alice", contributor.getName());
        assertEquals("https://api.deezer.com/artist/4710481/top?limit=50", contributor.getTracklist());
        assertEquals("https://api.deezer.com/artist/4710481/image", contributor.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/e3d1c21164c3005b0c0b15cc4160cab4/56x56-000000-80-0-0.jpg", contributor.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/e3d1c21164c3005b0c0b15cc4160cab4/250x250-000000-80-0-0.jpg", contributor.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/e3d1c21164c3005b0c0b15cc4160cab4/500x500-000000-80-0-0.jpg", contributor.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/e3d1c21164c3005b0c0b15cc4160cab4/1000x1000-000000-80-0-0.jpg", contributor.getPictureXl());
        assertEquals("https://www.deezer.com/artist/4710481", contributor.getLink());
        assertEquals("https://www.deezer.com/artist/4710481?utm_source=deezer&utm_content=artist-4710481&utm_term=0_1695643773&utm_medium=web", contributor.getShare());

        assertTrue(contributor.getRadio());
        assertEquals("Main", contributor.getRole());

        Artist artist = album.getArtist();
        assertEquals(4710481, artist.getId());
        assertEquals("Wolf Alice", artist.getName());
        assertEquals("https://api.deezer.com/artist/4710481/top?limit=50", artist.getTracklist());
        assertEquals("https://api.deezer.com/artist/4710481/image", artist.getPicture());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/e3d1c21164c3005b0c0b15cc4160cab4/56x56-000000-80-0-0.jpg", artist.getPictureSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/e3d1c21164c3005b0c0b15cc4160cab4/250x250-000000-80-0-0.jpg", artist.getPictureMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/e3d1c21164c3005b0c0b15cc4160cab4/500x500-000000-80-0-0.jpg", artist.getPictureBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/artist/e3d1c21164c3005b0c0b15cc4160cab4/1000x1000-000000-80-0-0.jpg", artist.getPictureXl());

        List<Track> tracks = album.getTracks().getData();
        assertFalse(tracks.isEmpty());

        Track track = tracks.get(0);
        assertEquals(1785318497, track.getId());
        assertTrue(track.getIsReadable());
        assertEquals("Lipstick on the Glass (Lullaby Version)", track.getTitle());
        assertEquals("Lipstick on the Glass (Lullaby Version)", track.getTitleShort());
        assertEquals("", track.getTitleVersion());
        assertEquals("https://www.deezer.com/track/1785318497", track.getLink());
        assertEquals(246, track.getDuration());
        assertEquals(161033, track.getRank());
        assertFalse(track.getExplicitLyrics());
        assertEquals(0, track.getExplicitContentLyrics());
        assertEquals(0, track.getExplicitContentCover());
        assertEquals("https://cdns-preview-0.dzcdn.net/stream/c-0067483c97b74f66de5b0bfd9d76dd83-4.mp3", track.getPreview());
        assertEquals("a22ac557760820340e1ac5ba9fee496a", track.getMd5Image());

        artist = track.getArtist();
        assertEquals(4710481, artist.getId());
        assertEquals("Wolf Alice", artist.getName());
        assertEquals("https://api.deezer.com/artist/4710481/top?limit=50", artist.getTracklist());

        album = track.getAlbum();
        assertEquals(ALBUM_ID, album.getId());
        assertEquals("Blue Lullaby", album.getTitle());
        assertEquals("https://api.deezer.com/album/326060237/image", album.getCover());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/a22ac557760820340e1ac5ba9fee496a/56x56-000000-80-0-0.jpg", album.getCoverSmall());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/a22ac557760820340e1ac5ba9fee496a/250x250-000000-80-0-0.jpg", album.getCoverMedium());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/a22ac557760820340e1ac5ba9fee496a/500x500-000000-80-0-0.jpg", album.getCoverBig());
        assertEquals("https://e-cdns-images.dzcdn.net/images/cover/a22ac557760820340e1ac5ba9fee496a/1000x1000-000000-80-0-0.jpg", album.getCoverXl());
        assertEquals("a22ac557760820340e1ac5ba9fee496a", album.getMd5Image());
        assertEquals("https://api.deezer.com/album/326060237/tracks", album.getTracklist());
    }

    @Test
    void getFans() {
        enqueue(readBody("responses/album/fans.json"));

        UserData albumFans = assertDoesNotThrow(() -> deezerApi.album().getFans(ALBUM_ID).execute());

        takeRequestUrl("album", String.valueOf(ALBUM_ID), "fans");

        assertEquals(0, albumFans.getTotal());

        List<User> users = albumFans.getData();
        assertTrue(users.isEmpty());
    }

    @Test
    void getTracks() {
        enqueue(readBody("responses/album/tracks.json"));

        TrackData albumTracks = assertDoesNotThrow(() -> deezerApi.album().getTracks(ALBUM_ID).execute());

        takeRequestUrl("album", String.valueOf(ALBUM_ID), "tracks");

        assertEquals(5, albumTracks.getTotal());

        List<Track> tracks = albumTracks.getData();
        assertFalse(tracks.isEmpty());

        Track track = tracks.get(0);
        assertEquals(1785318497, track.getId());
        assertTrue(track.getIsReadable());
        assertEquals("Lipstick on the Glass (Lullaby Version)", track.getTitle());
        assertEquals("Lipstick on the Glass (Lullaby Version)", track.getTitleShort());
        assertEquals("", track.getTitleVersion());
        assertEquals("GBK3W2202213", track.getIsrc());
        assertEquals("https://www.deezer.com/track/1785318497", track.getLink());
        assertEquals(246, track.getDuration());
        assertEquals(1, track.getTrackPosition());
        assertEquals(1, track.getDiskNumber());
        assertEquals(161033, track.getRank());
        assertFalse(track.getExplicitLyrics());
        assertEquals(0, track.getExplicitContentLyrics());
        assertEquals(0, track.getExplicitContentCover());
        assertEquals("https://cdns-preview-0.dzcdn.net/stream/c-0067483c97b74f66de5b0bfd9d76dd83-4.mp3", track.getPreview());
        assertEquals("a22ac557760820340e1ac5ba9fee496a", track.getMd5Image());

        Artist artist = track.getArtist();
        assertEquals(4710481, artist.getId());
        assertEquals("Wolf Alice", artist.getName());
        assertEquals("https://api.deezer.com/artist/4710481/top?limit=50", artist.getTracklist());
    }
}