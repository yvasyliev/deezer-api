package io.github.yvasyliev.deezer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.AsyncFeign;
import feign.Logger;
import feign.gson.GsonDecoder;
import io.github.yvasyliev.deezer.json.deserializers.AdvancedSearchMethodDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.DurationDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.LocalDateDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.PagingMethodDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.SearchHistoryMethodDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.SearchMethodDeserializer;
import io.github.yvasyliev.deezer.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.methods.Method;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.methods.SearchHistoryMethod;
import io.github.yvasyliev.deezer.methods.SearchMethod;
import io.github.yvasyliev.deezer.objects.AdvancedSearchPage;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Infos;
import io.github.yvasyliev.deezer.objects.Options;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Podcast;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.objects.SearchHistoryPage;
import io.github.yvasyliev.deezer.objects.SearchPage;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.service.InfosService;
import io.github.yvasyliev.deezer.service.OptionsService;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.logger.DeezerLogger;
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
    private final InfosService infosService;
    private final OptionsService optionsService;
    private final PlaylistService playlistService;
    private final RadioService radioService;
    private final SearchService searchService;
    private String accessToken;

    public static DeezerClient create() {
        return create(null);
    }

    public static DeezerClient create(String accessToken) {
        return create(null, null, null, accessToken);
    }

    @Builder(builderMethodName = "custom", buildMethodName = "create")
    private static DeezerClient create(
            Function<GsonBuilder, GsonBuilder> gsonBuilderCustomizer,
            Function<AsyncFeign.AsyncBuilder<Object>, AsyncFeign.AsyncBuilder<Object>> asyncFeignBuilderCustomizer,
            Function<Gson, GsonDecoder> gsonDecoderCreator,
            String accessToken
    ) {
        Map<Pattern, Function<Long, ? extends PagingMethod<? extends Pageable>>> pagingMethodFactories = new HashMap<>();
        Map<String, Function<String, ? extends SearchMethod<? extends Pageable>>> searchMethodFactories = new HashMap<>();
        Map<String, Supplier<? extends AdvancedSearchMethod<? extends Pageable>>> advancedSearchMethodFactories = new HashMap<>();
        SearchHistoryMethodDeserializer searchHistoryMethodDeserializer = new SearchHistoryMethodDeserializer();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Duration.class, new DurationDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(PagingMethod.class, new PagingMethodDeserializer<>(pagingMethodFactories))
                .registerTypeAdapter(SearchMethod.class, new SearchMethodDeserializer<>(searchMethodFactories))
                .registerTypeAdapter(AdvancedSearchMethod.class, new AdvancedSearchMethodDeserializer<>(advancedSearchMethodFactories))
                .registerTypeAdapter(SearchHistoryMethod.class, searchHistoryMethodDeserializer);

        if (gsonBuilderCustomizer != null) {
            gsonBuilder = gsonBuilderCustomizer.apply(gsonBuilder);
        }

        Gson gson = gsonBuilder.create();

        GsonDecoder gsonDecoder = gsonDecoderCreator != null
                ? gsonDecoderCreator.apply(gson)
                : new GsonDecoder(gson);

        AsyncFeign.AsyncBuilder<Object> asyncBuilder = AsyncFeign
                .builder()
                .logger(new DeezerLogger()) // TODO: remove
                .logLevel(Logger.Level.FULL) // TODO: remove
                .decoder(gsonDecoder);

        if (asyncFeignBuilderCustomizer != null) {
            asyncBuilder = asyncFeignBuilderCustomizer.apply(asyncBuilder);
        }

        AlbumService albumService = asyncBuilder.target(AlbumService.class, API_HOST);
        ArtistService artistService = asyncBuilder.target(ArtistService.class, API_HOST);
        ChartService chartService = asyncBuilder.target(ChartService.class, API_HOST);
        EditorialService editorialService = asyncBuilder.target(EditorialService.class, API_HOST);
        GenreService genreService = asyncBuilder.target(GenreService.class, API_HOST);
        InfosService infosService = asyncBuilder.target(InfosService.class, API_HOST);
        OptionsService optionsService = asyncBuilder.target(OptionsService.class, API_HOST);
        PlaylistService playlistService = asyncBuilder.target(PlaylistService.class, API_HOST);
        RadioService radioService = asyncBuilder.target(RadioService.class, API_HOST);
        SearchService searchService = asyncBuilder.target(SearchService.class, API_HOST);

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
        pagingMethodFactories.put(Pattern.compile(EditorialService.EDITORIALS), pagingMethodFactory(
                editorialService::getAllEditorials,
                editorialService::getAllEditorialsAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/editorial/(\\d+)/releases"), pagingMethodFactory(
                editorialService::getEditorialReleases,
                editorialService::getEditorialReleasesAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/editorial/(\\d+)/selection"), pagingMethodFactory(
                editorialService::getEditorialSelection,
                editorialService::getEditorialSelectionAsync
        ));
        pagingMethodFactories.put(Pattern.compile(GenreService.GENRES), pagingMethodFactory(
                genreService::getAllGenres,
                genreService::getAllGenresAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/genre/(\\d+)/artists"), pagingMethodFactory(
                genreService::getGenreArtists,
                genreService::getGenreArtistsAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/genre/(\\d+)/radios"), pagingMethodFactory(
                genreService::getGenreRadios,
                genreService::getGenreRadiosAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/playlist/(\\d+)/fans"), pagingMethodFactory(
                playlistService::getPlaylistFans,
                playlistService::getPlaylistFansAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/playlist/(\\d+)/radio"), pagingMethodFactory(
                playlistService::getPlaylistFans,
                playlistService::getPlaylistFansAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/playlist/(\\d+)/tracks"), pagingMethodFactory(
                playlistService::getPlaylistTracks,
                playlistService::getPlaylistTracksAsync
        ));
        pagingMethodFactories.put(Pattern.compile(RadioService.RADIOS), pagingMethodFactory(
                radioService::getAllRadios,
                radioService::getAllRadiosAsync
        ));
        pagingMethodFactories.put(Pattern.compile(RadioService.RADIO_GENRES), pagingMethodFactory(
                radioService::getGenresRadio,
                radioService::getGenresRadioAsync
        ));
        pagingMethodFactories.put(Pattern.compile(RadioService.RADIO_LISTS), pagingMethodFactory(
                radioService::getRadioLists,
                radioService::getRadioListsAsync
        ));
        pagingMethodFactories.put(Pattern.compile(RadioService.RADIO_TOP), pagingMethodFactory(
                radioService::getRadioTop,
                radioService::getRadioTopAsync
        ));
        pagingMethodFactories.put(Pattern.compile("/radio/(\\d+)/tracks"), pagingMethodFactory(
                radioService::getRadioTracks,
                radioService::getRadioTracksAsync
        ));

        searchMethodFactories.put(SearchService.SEARCH, searchMethodFactory(
                searchService::search,
                searchService::searchAsync
        ));
        searchMethodFactories.put(SearchService.SEARCH_ALBUM, searchMethodFactory(
                searchService::searchAlbum,
                searchService::searchAlbumAsync
        ));
        searchMethodFactories.put(SearchService.SEARCH_ARTIST, searchMethodFactory(
                searchService::searchArtist,
                searchService::searchArtistAsync
        ));
        searchMethodFactories.put(SearchService.SEARCH_PLAYLIST, searchMethodFactory(
                searchService::searchPlaylist,
                searchService::searchPlaylistAsync
        ));
        searchMethodFactories.put(SearchService.SEARCH_RADIO, searchMethodFactory(
                searchService::searchRadio,
                searchService::searchRadioAsync
        ));
        searchMethodFactories.put(SearchService.SEARCH_TRACK, searchMethodFactory(
                searchService::searchTrack,
                searchService::searchTrackAsync
        ));
        searchMethodFactories.put(SearchService.SEARCH_USER, searchMethodFactory(
                searchService::searchUser,
                searchService::searchUserAsync
        ));

        advancedSearchMethodFactories.put(SearchService.SEARCH, advancedSearchMethodFactory(
                searchService::advancedSearch,
                searchService::advancedSearchAsync
        ));
        advancedSearchMethodFactories.put(SearchService.SEARCH_ALBUM, advancedSearchMethodFactory(
                searchService::advancedSearchAlbum,
                searchService::advancedSearchAlbumAsync
        ));
        advancedSearchMethodFactories.put(SearchService.SEARCH_ARTIST, advancedSearchMethodFactory(
                searchService::advancedSearchArtist,
                searchService::advancedSearchArtistAsync
        ));
        advancedSearchMethodFactories.put(SearchService.SEARCH_PLAYLIST, advancedSearchMethodFactory(
                searchService::advancedSearchPlaylist,
                searchService::advancedSearchPlaylistAsync
        ));
        advancedSearchMethodFactories.put(SearchService.SEARCH_RADIO, advancedSearchMethodFactory(
                searchService::advancedSearchRadio,
                searchService::advancedSearchRadioAsync
        ));
        advancedSearchMethodFactories.put(SearchService.SEARCH_TRACK, advancedSearchMethodFactory(
                searchService::advancedSearchTrack,
                searchService::advancedSearchTrackAsync
        ));
        advancedSearchMethodFactories.put(SearchService.SEARCH_USER, advancedSearchMethodFactory(
                searchService::advancedSearchUser,
                searchService::advancedSearchUserAsync
        ));

        searchHistoryMethodDeserializer.setSearchHistoryMethodFactory(token -> searchHistoryMethodFactory(
                        searchService::searchHistory,
                        searchService::searchHistoryAsync,
                        token
                ).get()
        );

        return new DeezerClient(
                albumService,
                artistService,
                chartService,
                editorialService,
                genreService,
                infosService,
                optionsService,
                playlistService,
                radioService,
                searchService,
                accessToken
        );
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
        return pagingMethod(editorialService::getAllEditorials, editorialService::getAllEditorialsAsync);
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

    public PagingMethod<Genre> getAllGenres() {
        return pagingMethod(genreService::getAllGenres, genreService::getAllGenresAsync);
    }

    public PagingMethod<Artist> getGenreArtists(long genreId) {
        return pagingMethod(genreService::getGenreArtists, genreService::getGenreArtistsAsync, genreId);
    }

    public PagingMethod<Radio> getGenreRadios(long genreId) {
        return pagingMethod(genreService::getGenreRadios, genreService::getGenreRadiosAsync, genreId);
    }

    // INFOS METHODS

    public Method<Infos> getInfos() {
        return method(infosService::getInfos, infosService::getInfosAsync);
    }

    // OPTIONS METHODS

    public Method<Options> getOptions() {
        return method(optionsService::getOptions, optionsService::getOptionsAsync);
    }

    // PLAYLIST METHODS

    public Method<Playlist> getPlaylist(long playlistId) {
        return method(playlistService::getPlaylist, playlistService::getPlaylistAsync, playlistId);
    }

    public PagingMethod<User> getPlaylistFans(long playlistId) {
        return pagingMethod(playlistService::getPlaylistFans, playlistService::getPlaylistFansAsync, playlistId);
    }

    public PagingMethod<Track> getPlaylistRadio(long playlistId) {
        return pagingMethod(playlistService::getPlaylistRadio, playlistService::getPlaylistRadioAsync, playlistId);
    }

    public PagingMethod<Track> getPlaylistTracks(long playlistId) {
        return pagingMethod(playlistService::getPlaylistTracks, playlistService::getPlaylistTracksAsync, playlistId);
    }

    // RADIO METHODS

    public Method<Radio> getRadio(long radioId) {
        return method(radioService::getRadio, radioService::getRadioAsync, radioId);
    }

    public PagingMethod<Radio> getAllRadios() {
        return pagingMethod(radioService::getAllRadios, radioService::getAllRadiosAsync);
    }

    public PagingMethod<Genre> getGenresRadio() {
        return pagingMethod(radioService::getGenresRadio, radioService::getGenresRadioAsync);
    }

    public PagingMethod<Radio> getRadioLists() {
        return pagingMethod(radioService::getRadioLists, radioService::getRadioListsAsync);
    }

    public PagingMethod<Radio> getRadioTop() {
        return pagingMethod(radioService::getRadioTop, radioService::getRadioTopAsync);
    }

    public PagingMethod<Track> getRadioTracks(long radioId) {
        return pagingMethod(radioService::getRadioTracks, radioService::getRadioTracksAsync, radioId);
    }

    // SEARCH METHODS

    public SearchMethod<Track> search(String q) {
        return searchMethod(searchService::search, searchService::searchAsync, q);
    }

    public AdvancedSearchMethod<Track> search() {
        return advancedSearchMethod(searchService::advancedSearch, searchService::advancedSearchAsync);
    }

    public SearchMethod<Album> searchAlbum(String q) {
        return searchMethod(searchService::searchAlbum, searchService::searchAlbumAsync, q);
    }

    public AdvancedSearchMethod<Album> searchAlbum() {
        return advancedSearchMethod(searchService::advancedSearchAlbum, searchService::advancedSearchAlbumAsync);
    }

    public SearchMethod<Artist> searchArtist(String q) {
        return searchMethod(searchService::searchArtist, searchService::searchArtistAsync, q);
    }

    public AdvancedSearchMethod<Artist> searchArtist() {
        return advancedSearchMethod(searchService::advancedSearchArtist, searchService::advancedSearchArtistAsync);
    }

    public SearchHistoryMethod searchHistory() {
        return searchHistoryMethod(searchService::searchHistory, searchService::searchHistoryAsync);
    }

    public SearchMethod<Playlist> searchPlaylist(String q) {
        return searchMethod(searchService::searchPlaylist, searchService::searchPlaylistAsync, q);
    }

    public AdvancedSearchMethod<Playlist> searchPlaylist() {
        return advancedSearchMethod(searchService::advancedSearchPlaylist, searchService::advancedSearchPlaylistAsync);
    }

    public SearchMethod<Radio> searchRadio(String q) {
        return searchMethod(searchService::searchRadio, searchService::searchRadioAsync, q);
    }

    public AdvancedSearchMethod<Radio> searchRadio() {
        return advancedSearchMethod(searchService::advancedSearchRadio, searchService::advancedSearchRadioAsync);
    }

    public SearchMethod<Track> searchTrack(String q) {
        return searchMethod(searchService::searchTrack, searchService::searchTrackAsync, q);
    }

    public AdvancedSearchMethod<Track> searchTrack() {
        return advancedSearchMethod(searchService::advancedSearchTrack, searchService::advancedSearchTrackAsync);
    }

    public AdvancedSearchMethod<User> searchUser() {
        return advancedSearchMethod(searchService::advancedSearchUser, searchService::advancedSearchUserAsync);
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

    private <T extends Pageable> SearchMethod<T> searchMethod(
            Function<Map<String, Object>, SearchPage<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<SearchPage<T>>> asyncInvoker,
            String q
    ) {
        return searchMethodFactory(invoker, asyncInvoker).apply(q);
    }

    private <T extends Pageable> AdvancedSearchMethod<T> advancedSearchMethod(
            Function<Map<String, Object>, AdvancedSearchPage<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<AdvancedSearchPage<T>>> asyncInvoker
    ) {
        return advancedSearchMethodFactory(invoker, asyncInvoker).get();
    }

    private SearchHistoryMethod searchHistoryMethod(
            Function<Map<String, Object>, SearchHistoryPage> invoker,
            Function<Map<String, Object>, CompletableFuture<SearchHistoryPage>> asyncInvoker
    ) {
        return searchHistoryMethodFactory(invoker, asyncInvoker, accessToken).get();
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

    private static <T extends Pageable> Function<String, SearchMethod<T>> searchMethodFactory(
            Function<Map<String, Object>, SearchPage<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<SearchPage<T>>> asyncInvoker
    ) {
        return q -> new SearchMethod<>(invoker, asyncInvoker, q);
    }

    private static <T extends Pageable> Supplier<AdvancedSearchMethod<T>> advancedSearchMethodFactory(
            Function<Map<String, Object>, AdvancedSearchPage<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<AdvancedSearchPage<T>>> asyncInvoker
    ) {
        return () -> new AdvancedSearchMethod<>(invoker, asyncInvoker);
    }

    private static Supplier<SearchHistoryMethod> searchHistoryMethodFactory(
            Function<Map<String, Object>, SearchHistoryPage> invoker,
            Function<Map<String, Object>, CompletableFuture<SearchHistoryPage>> asyncInvoker,
            String accessToken
    ) {
        return () -> new SearchHistoryMethod(invoker, asyncInvoker, accessToken);
    }
}
