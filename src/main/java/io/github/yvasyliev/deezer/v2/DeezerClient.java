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
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.json.deserializers.PagingMethodDeserializer;
import io.github.yvasyliev.deezer.v2.json.deserializers.SearchMethodDeserializer;
import io.github.yvasyliev.deezer.v2.json.creators.AbstractPagingMethodCreator;
import io.github.yvasyliev.deezer.v2.json.creators.AdvancedSearchMethodCreator;
import io.github.yvasyliev.deezer.v2.json.creators.PagingMethodCreator;
import io.github.yvasyliev.deezer.v2.json.creators.SearchMethodCreator;
import io.github.yvasyliev.deezer.v2.logger.DeezerLogger;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.Method;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbum;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbumTracks;
import io.github.yvasyliev.deezer.v2.methods.search.AdvancedSearchAlbum;
import io.github.yvasyliev.deezer.v2.methods.search.SearchAlbum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.regex.Pattern;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DeezerClient {
    private static final String API_HOST = "https://api.deezer.com";
    private final Gson gson;
    private final AlbumService albumService;
    private final SearchService searchService;

    public static DeezerClient create() {
        return new Builder().create();
    }

    @lombok.Builder(builderClassName = "Builder", builderMethodName = "custom", buildMethodName = "create")
    private static DeezerClient create(
            Function<GsonBuilder, GsonBuilder> gsonBuilderCreator,
            Function<Gson, GsonDecoder> gsonDecoderCreator,
            Function<AsyncFeign.AsyncBuilder<Object>, AsyncFeign.AsyncBuilder<Object>> asyncFeignBuilderCreator
    ) {
        PagingMethodDeserializer pagingMethodDeserializer = new PagingMethodDeserializer();
        pagingMethodDeserializer.put(Pattern.compile("/album/(\\d+)/tracks"), GetAlbumTracks.class);

        SearchMethodDeserializer searchMethodDeserializer = new SearchMethodDeserializer();
        searchMethodDeserializer.put(SearchService.SEARCH_ALBUM, SearchAlbum.class);

        AbstractPagingMethodCreator<AlbumService> pagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<SearchService> searchMethodCreator = new SearchMethodCreator();
        AbstractPagingMethodCreator<SearchService> advancedSearchMethodCreator = new AdvancedSearchMethodCreator();

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
                .registerTypeAdapter(GetAlbumTracks.class, pagingMethodCreator)
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
        SearchService searchService = asyncFeignBuilder.target(SearchService.class, API_HOST);

        pagingMethodCreator.setGson(gson);
        pagingMethodCreator.setService(albumService);
        searchMethodCreator.setGson(gson);
        searchMethodCreator.setService(searchService);
        advancedSearchMethodCreator.setGson(gson);
        advancedSearchMethodCreator.setService(searchService);

        return new DeezerClient(gson, albumService, searchService);
    }

    public Method<Album> getAlbum(long albumId) {
        return new GetAlbum(albumService, albumId);
    }

    public SearchMethod<Album> searchAlbums(String q) {
        return new SearchAlbum(gson, searchService, q);
    }

    public AdvancedSearchMethod<Album> searchAlbums() {
        return new AdvancedSearchAlbum(gson, searchService);
    }

    public static class Builder {
        private Function<GsonBuilder, GsonBuilder> gsonBuilderCreator = gsonBuilder -> gsonBuilder;
        private Function<Gson, GsonDecoder> gsonDecoderCreator = GsonDecoder::new;
        private Function<AsyncFeign.AsyncBuilder<Object>, AsyncFeign.AsyncBuilder<Object>> asyncFeignBuilderCreator = asyncFeignBuilder -> asyncFeignBuilder;
    }
}
