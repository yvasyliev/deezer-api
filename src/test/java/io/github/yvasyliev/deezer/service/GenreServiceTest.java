package io.github.yvasyliev.deezer.service;

import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.DeezerClient;
import org.junit.jupiter.api.Test;

class GenreServiceTest {
    DeezerClient deezerClient = DeezerClient.create();

    @Test
    void getGenre() {
        Genre genre = deezerClient.getGenre(132).execute();
        System.out.println(genre);
    }

    @Test
    void getAllGenres() {
        Page<Genre> genrePage = deezerClient.getAllGenres().execute();
        System.out.println(genrePage);
    }
}