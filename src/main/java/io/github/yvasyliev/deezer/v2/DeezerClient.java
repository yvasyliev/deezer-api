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
import io.github.yvasyliev.deezer.v2.json.AbstractPagingMethodDeserializer;
import io.github.yvasyliev.deezer.v2.json.creators.album.GetAlbumTracksCreator;
import io.github.yvasyliev.deezer.v2.json.creators.search.SearchAlbumCreator;
import io.github.yvasyliev.deezer.v2.logger.DeezerLogger;
import io.github.yvasyliev.deezer.v2.methods.Method;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbum;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbumTracks;
import io.github.yvasyliev.deezer.v2.methods.search.SearchAlbum;
import io.github.yvasyliev.deezer.v2.methods.search.SearchMethod;
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
        AbstractPagingMethodDeserializer abstractPagingMethodDeserializer = new AbstractPagingMethodDeserializer();
        abstractPagingMethodDeserializer.put(Pattern.compile("/album/(\\d+)/tracks"), GetAlbumTracks.class);
        abstractPagingMethodDeserializer.put(Pattern.compile(SearchService.SEARCH_ALBUM), SearchAlbum.class);

        GetAlbumTracksCreator getAlbumTracksCreator = new GetAlbumTracksCreator();
        SearchAlbumCreator searchAlbumCreator = new SearchAlbumCreator();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .registerTypeAdapter(Duration.class, new DurationDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(PagingMethod.class, abstractPagingMethodDeserializer)
                .registerTypeAdapter(SearchMethod.class, abstractPagingMethodDeserializer)
                .registerTypeAdapter(GetAlbumTracks.class, getAlbumTracksCreator)
                .registerTypeAdapter(SearchAlbum.class, searchAlbumCreator);

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

        getAlbumTracksCreator.setGson(gson);
        getAlbumTracksCreator.setService(albumService);
        searchAlbumCreator.setGson(gson);
        searchAlbumCreator.setSearchService(searchService);

        return new DeezerClient(gson, albumService, searchService);
    }

    public Method<Album> getAlbum(long albumId) {
        return new GetAlbum(albumService, albumId);
    }

    public SearchMethod<Album> searchAlbums(String q) {
        return new SearchAlbum(gson, searchService, q);
    }

    public static class Builder {
        private Function<GsonBuilder, GsonBuilder> gsonBuilderCreator = gsonBuilder -> gsonBuilder;
        private Function<Gson, GsonDecoder> gsonDecoderCreator = GsonDecoder::new;
        private Function<AsyncFeign.AsyncBuilder<Object>, AsyncFeign.AsyncBuilder<Object>> asyncFeignBuilderCreator = asyncFeignBuilder -> asyncFeignBuilder;
    }
}
