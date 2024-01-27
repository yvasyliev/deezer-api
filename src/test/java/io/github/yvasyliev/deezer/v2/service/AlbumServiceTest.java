package io.github.yvasyliev.deezer.v2.service;

import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.DeezerClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class AlbumServiceTest {
    DeezerClient deezerClient = new DeezerClient();

    @Test
    void getAlbum() throws IOException {
        Album album = deezerClient.getAlbum(302127).execute();
        System.out.println(album);
        Page<Track> trackPage = album.getTrackList().limit(1).execute();
        System.out.println(trackPage);
    }
}