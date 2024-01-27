package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Page;

import java.util.concurrent.CompletableFuture;

public interface GenreService extends DeezerService {
    String GENRE = "/genre/{genreId}";
    String GENRES = "/genre";

    @RequestLine(GET + GENRE)
    Genre getGenre(@Param("genreId") long genreId);

    @RequestLine(GET + GENRE)
    CompletableFuture<Genre> getGenreAsync(@Param("genreId") long genreId);

    @RequestLine(GET + GENRES)
    Page<Genre> getAllGenres();

    @RequestLine(GET + GENRES)
    CompletableFuture<Page<Genre>> getAllGenresAsync();
}
