package io.github.yvasyliev.deezer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.AsyncFeign;
import feign.Logger;
import feign.gson.GsonDecoder;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.json.DurationDeserializer;
import io.github.yvasyliev.deezer.json.LocalDateDeserializer;
import io.github.yvasyliev.deezer.json.PagingMethodDeserializer;
import io.github.yvasyliev.deezer.v2.logger.DeezerLogger;
import io.github.yvasyliev.deezer.methods.Method;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.service.GenreService;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class DeezerClient {
    private static final String API_HOST = "https://api.deezer.com";
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final GenreService genreService;

    public DeezerClient() {
        this(null, null, null);
    }

    @Builder
    private DeezerClient(
            Function<GsonBuilder, GsonBuilder> gsonBuilderCustomizer,
            Function<AsyncFeign.AsyncBuilder<Object>, AsyncFeign.AsyncBuilder<Object>> asyncFeignBuilderCustomizer,
            Function<Gson, GsonDecoder> gsonDecoderCreator
    ) {
        if (gsonBuilderCustomizer == null) {
            gsonBuilderCustomizer = gsonBuilder -> gsonBuilder;
        }
        if (asyncFeignBuilderCustomizer == null) {
            asyncFeignBuilderCustomizer = feignBuilder -> feignBuilder;
        }
        if (gsonDecoderCreator == null) {
            gsonDecoderCreator = GsonDecoder::new;
        }

        Map<Pattern, Function<Long, ? extends PagingMethod<? extends Pageable>>> pagingMethodFactories = new HashMap<>();
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Duration.class, new DurationDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(PagingMethod.class, new PagingMethodDeserializer(pagingMethodFactories));

        Gson gson = gsonBuilderCustomizer.apply(gsonBuilder).create();
        AsyncFeign.AsyncBuilder<Object> asyncBuilder = AsyncFeign
                .builder()
                .logger(new DeezerLogger()) // TODO: remove
                .logLevel(Logger.Level.FULL) // TODO: remove
                .decoder(gsonDecoderCreator.apply(gson));

        asyncBuilder = asyncFeignBuilderCustomizer.apply(asyncBuilder);

        albumService = asyncBuilder.target(AlbumService.class, API_HOST);
        artistService = asyncBuilder.target(ArtistService.class, API_HOST);
        genreService = asyncBuilder.target(GenreService.class, API_HOST);

        pagingMethodFactories.put(Pattern.compile("/album/(\\d+)/tracks"), pagingMethodFactory(
                albumService::getAlbumTracks,
                albumService::getAlbumTracksAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/artist/(\\d+)/albums"), pagingMethodFactory(
                artistService::getArtistAlbums,
                artistService::getArtistAlbumsAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/artist/(\\d+)/top"), pagingMethodFactory(
                artistService::getArtistTop,
                artistService::getArtistTopAsync
        ));
    }

    public Method<Album> getAlbum(long albumId) {
        return method(albumService::getAlbum, albumService::getAlbumAsync, albumId);
    }

    public Method<Artist> getArtist(long artistId) {
        return method(artistService::getArtist, artistService::getArtistAsync, artistId);
    }

    public PagingMethod<Album> getArtistAlbums(long artistId) {
        return pagingMethod(artistService::getArtistAlbums, artistService::getArtistAlbumsAsync, artistId);
    }

    public PagingMethod<Track> getArtistTop(long artistId) {
        return pagingMethod(artistService::getArtistTop, artistService::getArtistTopAsync, artistId);
    }

    public Method<Genre> getGenre(long genreId) {
        return method(genreService::getGenre, genreService::getGenreAsync, genreId);
    }

    public Method<Page<Genre>> getAllGenres() {
        return method(genreService::getAllGenres, genreService::getAllGenresAsync);
    }

    private <T> Method<T> method(Supplier<T> invoker, Supplier<CompletableFuture<T>> asyncInvoker) {
        return new Method<>(
                queryParams -> invoker.get(),
                queryParams -> asyncInvoker.get()
        );
    }

    private <T> Method<T> method(Function<Long, T> invoker, Function<Long, CompletableFuture<T>> asyncInvoker, long objectId) {
        return method(
                () -> invoker.apply(objectId),
                () -> asyncInvoker.apply(objectId)
        );
    }

    private <T extends Pageable> PagingMethod<T> pagingMethod(
            BiFunction<Long, Map<String, Object>, Page<T>> invoker,
            BiFunction<Long, Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker,
            long objectId
    ) {
        return pagingMethodFactory(invoker, asyncInvoker).apply(objectId);
    }

    private <T extends Pageable> Function<Long, PagingMethod<T>> pagingMethodFactory(
            BiFunction<Long, Map<String, Object>, Page<T>> invoker,
            BiFunction<Long, Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker
    ) {
        return objectId -> new PagingMethod<>(
                queryParams -> invoker.apply(objectId, queryParams),
                queryParams -> asyncInvoker.apply(objectId, queryParams)
        );
    }
}
