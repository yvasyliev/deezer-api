package io.github.yvasyliev.deezer.v2.service;

import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.DeezerClient;
import org.junit.jupiter.api.Test;

class ArtistServiceTest {
    DeezerClient deezerClient = new DeezerClient();

    @Test
    void getArtist() {
        Artist artist = deezerClient.getArtist(27).execute();
        System.out.println(artist);
    }

    @Test
    void getArtistAlbums() {
        Page<Album> albumPage = deezerClient.getArtistAlbums(27).limit(5).execute();
        System.out.println(albumPage);
        albumPage = albumPage.getNext().execute();
        System.out.println(albumPage);
        DeezerClient.builder().build();
    }
}