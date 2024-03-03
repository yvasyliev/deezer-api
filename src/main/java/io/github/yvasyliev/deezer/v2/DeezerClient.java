package io.github.yvasyliev.deezer.v2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import feign.AsyncFeign;
import feign.Logger;
import feign.gson.GsonDecoder;
import io.github.yvasyliev.deezer.json.deserializers.DurationDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.LocalDateDeserializer;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Podcast;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.json.creators.AbstractPagingMethodCreator;
import io.github.yvasyliev.deezer.v2.json.creators.AdvancedSearchMethodCreator;
import io.github.yvasyliev.deezer.v2.json.creators.PagingMethodCreator;
import io.github.yvasyliev.deezer.v2.json.creators.SearchMethodCreator;
import io.github.yvasyliev.deezer.v2.json.deserializers.PagingMethodDeserializer;
import io.github.yvasyliev.deezer.v2.json.deserializers.SearchMethodDeserializer;
import io.github.yvasyliev.deezer.v2.logger.DeezerLogger;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.Method;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbum;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbumFans;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbumTracks;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtist;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistAlbums;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistFans;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistPlaylists;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistRadio;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistRelated;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistTop;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChart;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartAlbums;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartArtists;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartById;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartPlaylists;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartPodcasts;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartTracks;
import io.github.yvasyliev.deezer.v2.methods.editorial.GetEditorial;
import io.github.yvasyliev.deezer.v2.methods.editorial.GetEditorialCharts;
import io.github.yvasyliev.deezer.v2.methods.editorial.GetEditorialReleases;
import io.github.yvasyliev.deezer.v2.methods.editorial.GetEditorialSelection;
import io.github.yvasyliev.deezer.v2.methods.editorial.GetEditorials;
import io.github.yvasyliev.deezer.v2.methods.genre.GetGenre;
import io.github.yvasyliev.deezer.v2.methods.genre.GetGenreArtists;
import io.github.yvasyliev.deezer.v2.methods.genre.GetGenreRadios;
import io.github.yvasyliev.deezer.v2.methods.genre.GetGenres;
import io.github.yvasyliev.deezer.v2.methods.search.AdvancedSearchAlbum;
import io.github.yvasyliev.deezer.v2.methods.search.SearchAlbum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DeezerClient {
    private static final String API_HOST = "https://api.deezer.com";
    private final Gson gson;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final ChartService chartService;
    private final EditorialService editorialService;
    private final GenreService genreService;
    private final SearchService searchService;

    public static DeezerClient create() {
        return new DeezerClientBuilder().create();
    }

    @Builder(builderClassName = "DeezerClientBuilder", builderMethodName = "custom", buildMethodName = "create")
    private static DeezerClient create(
            Function<GsonBuilder, GsonBuilder> gsonBuilderCreator,
            Function<Gson, GsonDecoder> gsonDecoderCreator,
            Function<AsyncFeign.AsyncBuilder<Object>, AsyncFeign.AsyncBuilder<Object>> asyncFeignBuilderCreator
    ) {
        PagingMethodDeserializer pagingMethodDeserializer = new PagingMethodDeserializer();
        pagingMethodDeserializer.put(Pattern.compile("/album/(\\d+)/fans"), GetAlbumFans.class);
        pagingMethodDeserializer.put(Pattern.compile("/album/(\\d+)/tracks"), GetAlbumTracks.class);
        pagingMethodDeserializer.put(Pattern.compile("/artist/(\\d+)/albums"), GetArtistAlbums.class);
        pagingMethodDeserializer.put(Pattern.compile("/artist/(\\d+)/fans"), GetArtistFans.class);
        pagingMethodDeserializer.put(Pattern.compile("/artist/(\\d+)/playlists"), GetArtistPlaylists.class);
        pagingMethodDeserializer.put(Pattern.compile("/artist/(\\d+)/radio"), GetArtistRadio.class);
        pagingMethodDeserializer.put(Pattern.compile("/artist/(\\d+)/related"), GetArtistRelated.class);
        pagingMethodDeserializer.put(Pattern.compile("/artist/(\\d+)/top"), GetArtistTop.class);
        pagingMethodDeserializer.put(Pattern.compile("/chart/(\\d+)/albums"), GetChartAlbums.class);
        pagingMethodDeserializer.put(Pattern.compile("/chart/(\\d+)/artists"), GetChartArtists.class);
        pagingMethodDeserializer.put(Pattern.compile("/chart/(\\d+)/playlists"), GetChartPlaylists.class);
        pagingMethodDeserializer.put(Pattern.compile("/chart/(\\d+)/podcasts"), GetChartPodcasts.class);
        pagingMethodDeserializer.put(Pattern.compile("/chart/(\\d+)/tracks"), GetChartTracks.class);
        pagingMethodDeserializer.put(Pattern.compile(EditorialService.EDITORIALS), GetEditorials.class);
        pagingMethodDeserializer.put(Pattern.compile("/editorial/(\\d+)/releases"), GetEditorialReleases.class);
        pagingMethodDeserializer.put(Pattern.compile("/editorial/(\\d+)/selection"), GetEditorialSelection.class);
        pagingMethodDeserializer.put(Pattern.compile(GenreService.GENRES), GetGenres.class);
        pagingMethodDeserializer.put(Pattern.compile("/genre/(\\d+)/artists"), GetGenreArtists.class);
        pagingMethodDeserializer.put(Pattern.compile("/genre/(\\d+)/radios"), GetGenreRadios.class);

        AbstractPagingMethodCreator<AlbumService> albumPagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<ArtistService> artistPagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<ChartService> chartPagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<EditorialService> editorialPagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<GenreService> genrePagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<SearchService> searchMethodCreator = new SearchMethodCreator();
        AbstractPagingMethodCreator<SearchService> advancedSearchMethodCreator = new AdvancedSearchMethodCreator();

        SearchMethodDeserializer searchMethodDeserializer = new SearchMethodDeserializer();
        searchMethodDeserializer.put(SearchService.SEARCH_ALBUM, SearchAlbum.class);

        GsonBuilder gsonBuilder = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                // deserializers
                .registerTypeAdapter(Duration.class, new DurationDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(PagingMethod.class, pagingMethodDeserializer)
                .registerTypeAdapter(SearchMethod.class, searchMethodDeserializer)
                .registerTypeAdapter(AdvancedSearchMethod.class, searchMethodDeserializer)
                // creators
                .registerTypeAdapter(GetAlbumFans.class, albumPagingMethodCreator)
                .registerTypeAdapter(GetAlbumTracks.class, albumPagingMethodCreator)
                .registerTypeAdapter(GetArtistAlbums.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetArtistFans.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetArtistPlaylists.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetArtistRadio.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetArtistRelated.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetArtistTop.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetChartAlbums.class, chartPagingMethodCreator)
                .registerTypeAdapter(GetChartArtists.class, chartPagingMethodCreator)
                .registerTypeAdapter(GetChartPlaylists.class, chartPagingMethodCreator)
                .registerTypeAdapter(GetChartPodcasts.class, chartPagingMethodCreator)
                .registerTypeAdapter(GetChartTracks.class, chartPagingMethodCreator)
                .registerTypeAdapter(GetEditorials.class, editorialPagingMethodCreator)
                .registerTypeAdapter(GetEditorialReleases.class, editorialPagingMethodCreator)
                .registerTypeAdapter(GetEditorialSelection.class, editorialPagingMethodCreator)
                .registerTypeAdapter(GetGenres.class, genrePagingMethodCreator)
                .registerTypeAdapter(GetGenreArtists.class, genrePagingMethodCreator)
                .registerTypeAdapter(GetGenreRadios.class, genrePagingMethodCreator)
                .registerTypeAdapter(SearchAlbum.class, searchMethodCreator)
                .registerTypeAdapter(AdvancedSearchAlbum.class, advancedSearchMethodCreator);

        Gson gson = gsonBuilderCreator.apply(gsonBuilder).create();

        GsonDecoder gsonDecoder = gsonDecoderCreator.apply(gson);

        AsyncFeign.AsyncBuilder<Object> asyncFeignBuilder = AsyncFeign
                .builder()
                .logger(new DeezerLogger()) // TODO: remove
                .logLevel(Logger.Level.FULL) // TODO: remove
                .decoder(gsonDecoder);

        asyncFeignBuilder = asyncFeignBuilderCreator.apply(asyncFeignBuilder);

        AlbumService albumService = asyncFeignBuilder.target(AlbumService.class, API_HOST);
        ArtistService artistService = asyncFeignBuilder.target(ArtistService.class, API_HOST);
        ChartService chartService = asyncFeignBuilder.target(ChartService.class, API_HOST);
        EditorialService editorialService = asyncFeignBuilder.target(EditorialService.class, API_HOST);
        GenreService genreService = asyncFeignBuilder.target(GenreService.class, API_HOST);
        SearchService searchService = asyncFeignBuilder.target(SearchService.class, API_HOST);

        Stream.of(
                albumPagingMethodCreator,
                artistPagingMethodCreator,
                chartPagingMethodCreator,
                editorialPagingMethodCreator,
                genrePagingMethodCreator,
                searchMethodCreator,
                advancedSearchMethodCreator
        ).forEach(deezerService -> deezerService.setGson(gson));

        albumPagingMethodCreator.setDeezerService(albumService);
        artistPagingMethodCreator.setDeezerService(artistService);
        chartPagingMethodCreator.setDeezerService(chartService);
        editorialPagingMethodCreator.setDeezerService(editorialService);
        genrePagingMethodCreator.setDeezerService(genreService);
        searchMethodCreator.setDeezerService(searchService);
        advancedSearchMethodCreator.setDeezerService(searchService);

        return new DeezerClient(
                gson,
                albumService,
                artistService,
                chartService,
                editorialService,
                genreService,
                searchService
        );
    }

    public Method<Album> getAlbum(long albumId) {
        return new GetAlbum(albumService, albumId);
    }

    public PagingMethod<User> getAlbumFans(long albumId) {
        return new GetAlbumFans(gson, albumService, albumId);
    }

    public PagingMethod<Track> getAlbumTracks(long albumId) {
        return new GetAlbumTracks(gson, albumService, albumId);
    }

    public Method<Artist> getArtist(long artistId) {
        return new GetArtist(artistService, artistId);
    }

    public PagingMethod<Album> getArtistAlbums(long artistId) {
        return new GetArtistAlbums(gson, artistService, artistId);
    }

    public PagingMethod<User> getArtistFans(long artistId) {
        return new GetArtistFans(gson, artistService, artistId);
    }

    public PagingMethod<Playlist> getArtistPlaylists(long artistId) {
        return new GetArtistPlaylists(gson, artistService, artistId);
    }

    public PagingMethod<Track> getArtistRadio(long artistId) {
        return new GetArtistRadio(gson, artistService, artistId);
    }

    public PagingMethod<Artist> getArtistRelated(long artistId) {
        return new GetArtistRelated(gson, artistService, artistId);
    }

    public PagingMethod<Track> getArtistTop(long artistId) {
        return new GetArtistRadio(gson, artistService, artistId);
    }


    public Method<Chart> getChart() {
        return new GetChart(chartService);
    }

    public Method<Chart> getChart(long chartId) {
        return new GetChartById(chartService, chartId);
    }

    public PagingMethod<Album> getChartAlbums(long chartId) {
        return new GetChartAlbums(gson, chartService, chartId);
    }

    public PagingMethod<Artist> getChartArtist(long chartId) {
        return new GetChartArtists(gson, chartService, chartId);
    }

    public PagingMethod<Playlist> getChartPlaylists(long chartId) {
        return new GetChartPlaylists(gson, chartService, chartId);
    }

    public PagingMethod<Podcast> getChartPodcasts(long chartId) {
        return new GetChartPodcasts(gson, chartService, chartId);
    }

    public PagingMethod<Track> getChartTracks(long chartId) {
        return new GetChartTracks(gson, chartService, chartId);
    }

    public Method<Editorial> getEditorial(long editorialId) {
        return new GetEditorial(editorialService, editorialId);
    }

    public PagingMethod<Editorial> getEditorials() {
        return new GetEditorials(gson, editorialService);
    }

    public Method<Chart> getEditorialCharts(long editorialId) {
        return new GetEditorialCharts(editorialService, editorialId);
    }

    public PagingMethod<Album> getEditorialReleases(long editorialId) {
        return new GetEditorialReleases(gson, editorialService, editorialId);
    }

    public PagingMethod<Album> getEditorialSelection(long editorialId) {
        return new GetEditorialSelection(gson, editorialService, editorialId);
    }

    public Method<Genre> getGenre(long genreId) {
        return new GetGenre(genreService, genreId);
    }

    public PagingMethod<Genre> getGenres() {
        return new GetGenres(gson, genreService);
    }

    public PagingMethod<Artist> getGenreArtists(long genreId) {
        return new GetGenreArtists(gson, genreService, genreId);
    }

    public PagingMethod<Radio> getGenreRadios(long genreId) {
        return new GetGenreRadios(gson, genreService, genreId);
    }

    public SearchMethod<Album> searchAlbums(String q) {
        return new SearchAlbum(gson, searchService, q);
    }

    public AdvancedSearchMethod<Album> searchAlbums() {
        return new AdvancedSearchAlbum(gson, searchService);
    }

    public static class DeezerClientBuilder {
        private Function<GsonBuilder, GsonBuilder> gsonBuilderCreator = gsonBuilder -> gsonBuilder;
        private Function<Gson, GsonDecoder> gsonDecoderCreator = GsonDecoder::new;
        private Function<AsyncFeign.AsyncBuilder<Object>, AsyncFeign.AsyncBuilder<Object>> asyncFeignBuilderCreator = asyncFeignBuilder -> asyncFeignBuilder;
    }
}
