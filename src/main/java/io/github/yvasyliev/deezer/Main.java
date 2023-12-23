package io.github.yvasyliev.deezer;

import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Track;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DeezerClient deezerClient = new DeezerClient();
        Page<Track> page1 = deezerClient.getArtistTop(27).execute();
        System.out.println(page1);
        Page<Track> page2 = page1.getNext().execute();
        System.out.println(page2);
        Page<Track> page3 = page2.getNext().execute();
        System.out.println(page3);
    }
}
