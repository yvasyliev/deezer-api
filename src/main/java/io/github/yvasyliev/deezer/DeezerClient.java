package io.github.yvasyliev.deezer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.AsyncFeign;
import feign.Logger;
import feign.gson.GsonDecoder;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Podcast;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.json.DurationDeserializer;
import io.github.yvasyliev.deezer.json.LocalDateDeserializer;
import io.github.yvasyliev.deezer.json.PagingMethodDeserializer;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.v2.logger.DeezerLogger;
import io.github.yvasyliev.deezer.methods.Method;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.service.GenreService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeezerClient {
    private static final String API_HOST = "https://api.deezer.com";
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final ChartService chartService;
    private final EditorialService editorialService;
    private final GenreService genreService;

    public static DeezerClient create() {
        return create(null, null, null);
    }

    @Builder(builderMethodName = "custom", buildMethodName = "create")
    private static DeezerClient create(
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

        AlbumService albumService = asyncBuilder.target(AlbumService.class, API_HOST);
        ArtistService artistService = asyncBuilder.target(ArtistService.class, API_HOST);
        ChartService chartService = asyncBuilder.target(ChartService.class, API_HOST);
        EditorialService editorialService = asyncBuilder.target(EditorialService.class, API_HOST);
        GenreService genreService = asyncBuilder.target(GenreService.class, API_HOST);

        pagingMethodFactories.put(Pattern.compile("/album/(\\d+)/fans"), pagingMethodFactory(
                albumService::getAlbumFans,
                albumService::getAlbumFansAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/album/(\\d+)/tracks"), pagingMethodFactory(
                albumService::getAlbumTracks,
                albumService::getAlbumTracksAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/artist/(\\d+)/albums"), pagingMethodFactory(
                artistService::getArtistAlbums,
                artistService::getArtistAlbumsAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/artist/(\\d+)/fans"), pagingMethodFactory(
                artistService::getArtistFans,
                artistService::getArtistFansAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/artist/(\\d+)/playlists"), pagingMethodFactory(
                artistService::getArtistPlaylists,
                artistService::getArtistPlaylistsAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/artist/(\\d+)/radio"), pagingMethodFactory(
                artistService::getArtistRadio,
                artistService::getArtistRadioAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/artist/(\\d+)/related"), pagingMethodFactory(
                artistService::getArtistRelated,
                artistService::getArtistRelatedAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/artist/(\\d+)/top"), pagingMethodFactory(
                artistService::getArtistTop,
                artistService::getArtistTopAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/chart/(\\d+)/albums"), pagingMethodFactory(
                chartService::getChartAlbums,
                chartService::getChartAlbumsAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/chart/(\\d+)/artists"), pagingMethodFactory(
                chartService::getChartArtists,
                chartService::getChartArtistsAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/chart/(\\d+)/playlists"), pagingMethodFactory(
                chartService::getChartPlaylists,
                chartService::getChartPlaylistsAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/chart/(\\d+)/podcasts"), pagingMethodFactory(
                chartService::getChartPodcasts,
                chartService::getChartPodcastsAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/chart/(\\d+)/tracks"), pagingMethodFactory(
                chartService::getChartTracks,
                chartService::getChartTracksAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/editorial"), pagingMethodFactory(
                editorialService::getEditorial,
                editorialService::getEditorialAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/editorial/(\\d+)/releases"), pagingMethodFactory(
                editorialService::getEditorialReleases,
                editorialService::getEditorialReleasesAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/editorial/(\\d+)/selection"), pagingMethodFactory(
                editorialService::getEditorialSelection,
                editorialService::getEditorialSelectionAsync
        ));

        return new DeezerClient(albumService, artistService, chartService, editorialService, genreService);
    }

    // ALBUM METHODS

    public Method<Album> getAlbum(long albumId) {
        return method(albumService::getAlbum, albumService::getAlbumAsync, albumId);
    }

    public PagingMethod<User> getAlbumFans(long albumId) {
        return pagingMethod(albumService::getAlbumFans, albumService::getAlbumFansAsync, albumId);
    }

    public PagingMethod<Track> getAlbumTracks(long albumId) {
        return pagingMethod(albumService::getAlbumTracks, albumService::getAlbumTracksAsync, albumId);
    }

    // ARTIST METHODS

    public Method<Artist> getArtist(long artistId) {
        return method(artistService::getArtist, artistService::getArtistAsync, artistId);
    }

    public PagingMethod<Album> getArtistAlbums(long artistId) {
        return pagingMethod(artistService::getArtistAlbums, artistService::getArtistAlbumsAsync, artistId);
    }

    public PagingMethod<User> getArtistFans(long artistId) {
        return pagingMethod(artistService::getArtistFans, artistService::getArtistFansAsync, artistId);
    }

    public PagingMethod<Playlist> getArtistPlaylists(long artistId) {
        return pagingMethod(artistService::getArtistPlaylists, artistService::getArtistPlaylistsAsync, artistId);
    }

    public PagingMethod<Track> getArtistRadio(long artistId) {
        return pagingMethod(artistService::getArtistRadio, artistService::getArtistRadioAsync, artistId);
    }

    public PagingMethod<Artist> getArtistRelated(long artistId) {
        return pagingMethod(artistService::getArtistRelated, artistService::getArtistRelatedAsync, artistId);
    }

    public PagingMethod<Track> getArtistTop(long artistId) {
        return pagingMethod(artistService::getArtistTop, artistService::getArtistTopAsync, artistId);
    }

    // CHART METHODS

    public Method<Chart> getChart() {
        return method(chartService::getChart, chartService::getChartAsync);
    }

    public Method<Chart> getChart(long chartId) {
        return method(chartService::getChartById, chartService::getChartByIdAsync, chartId);
    }

    public PagingMethod<Album> getChartAlbums(long chartId) {
        return pagingMethod(chartService::getChartAlbums, chartService::getChartAlbumsAsync, chartId);
    }

    public PagingMethod<Artist> getChartArtists(long chartId) {
        return pagingMethod(chartService::getChartArtists, chartService::getChartArtistsAsync, chartId);
    }

    public PagingMethod<Playlist> getChartPlaylists(long chatId) {
        return pagingMethod(chartService::getChartPlaylists, chartService::getChartPlaylistsAsync, chatId);
    }

    public PagingMethod<Podcast> getChartPodcasts(long chatId) {
        return pagingMethod(chartService::getChartPodcasts, chartService::getChartPodcastsAsync, chatId);
    }

    public PagingMethod<Track> getChartTracks(long chartId) {
        return pagingMethod(chartService::getChartTracks, chartService::getChartTracksAsync, chartId);
    }

    // EDITORIAL METHODS

    public PagingMethod<Editorial> getEditorial() {
        return pagingMethod(editorialService::getEditorial, editorialService::getEditorialAsync);
    }

    public Method<Editorial> getEditorial(long editorialId) {
        return method(editorialService::getEditorial, editorialService::getEditorialAsync, editorialId);
    }

    public Method<Chart> getEditorialCharts(long editorialId) {
        return method(editorialService::getEditorialCharts, editorialService::getEditorialChartsAsync, editorialId);
    }

    public PagingMethod<Album> getEditorialReleases(long editorialId) {
        return pagingMethod(editorialService::getEditorialReleases, editorialService::getEditorialReleasesAsync, editorialId);
    }

    public PagingMethod<Album> getEditorialSelection(long editorialId) {
        return pagingMethod(editorialService::getEditorialSelection, editorialService::getEditorialSelectionAsync, editorialId);
    }

    // GENRE METHODS

    public Method<Genre> getGenre(long genreId) {
        return method(genreService::getGenre, genreService::getGenreAsync, genreId);
    }

    public Method<Page<Genre>> getAllGenres() {
        return method(genreService::getAllGenres, genreService::getAllGenresAsync);
    }

    // METHOD CREATORS

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
            Function<Map<String, Object>, Page<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker
    ) {
        return pagingMethod(
                (objectId, queryParams) -> invoker.apply(queryParams),
                (objectId, queryParams) -> asyncInvoker.apply(queryParams),
                null
        );
    }

    private <T extends Pageable> PagingMethod<T> pagingMethod(
            BiFunction<Long, Map<String, Object>, Page<T>> invoker,
            BiFunction<Long, Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker,
            Long objectId
    ) {
        return pagingMethodFactory(invoker, asyncInvoker).apply(objectId);
    }

    private static <T extends Pageable> Function<Long, PagingMethod<T>> pagingMethodFactory(
            BiFunction<Long, Map<String, Object>, Page<T>> invoker,
            BiFunction<Long, Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker
    ) {
        return objectId -> new PagingMethod<>(
                queryParams -> invoker.apply(objectId, queryParams),
                queryParams -> asyncInvoker.apply(objectId, queryParams)
        );
    }

    private static <T extends Pageable> Function<Long, PagingMethod<T>> pagingMethodFactory(
            Function<Map<String, Object>, Page<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker
    ) {
        return pagingMethodFactory(
                (objectId, queryParams) -> invoker.apply(queryParams),
                (objectId, queryParams) -> asyncInvoker.apply(queryParams)
        );
    }
}
