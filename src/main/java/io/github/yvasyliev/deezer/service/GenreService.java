package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Radio;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface GenreService extends DeezerService {
    String GENRE = "/genre/{genreId}";
    String GENRES = "/genre";
    String GENRE_ARTISTS = "/genre/{genreId}/artists";
    String GENRE_RADIOS = "/genre/{genreId}/radios";

    @RequestLine(GET + GENRE)
    Genre getGenre(@Param("genreId") long genreId);

    @RequestLine(GET + GENRE)
    CompletableFuture<Genre> getGenreAsync(@Param("genreId") long genreId);

    @RequestLine(GET + GENRES)
    Page<Genre> getAllGenres(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + GENRES)
    CompletableFuture<Page<Genre>> getAllGenresAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + GENRE_ARTISTS)
    Page<Artist> getGenreArtists(@Param("genreId") long genreId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + GENRE_ARTISTS)
    CompletableFuture<Page<Artist>> getGenreArtistsAsync(@Param("genreId") long genreId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + GENRE_RADIOS)
    Page<Radio> getGenreRadios(@Param("genreId") long genreId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + GENRE_RADIOS)
    CompletableFuture<Page<Radio>> getGenreRadiosAsync(@Param("genreId") long genreId, @QueryMap Map<String, Object> queryParams);
}
