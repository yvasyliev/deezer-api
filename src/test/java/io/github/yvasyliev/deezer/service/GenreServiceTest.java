package io.github.yvasyliev.deezer.service;

import io.github.yvasyliev.deezer.DeezerClient;
import io.github.yvasyliev.deezer.objects.Genre;
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
    }

    @Test
    void getGenreArtists() {
    }

    @Test
    void getGenreRadios() {
    }
}