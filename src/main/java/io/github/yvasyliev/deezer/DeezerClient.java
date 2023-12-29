package io.github.yvasyliev.deezer;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.yvasyliev.deezer.methods.GetMethod;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Track;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Builder
public class DeezerClient {
    private static final String GET_ARTIST_TEMPLATE = "/artist/%d";
    private static final String GET_ARTIST_TOP_TEMPLATE = GET_ARTIST_TEMPLATE + "/top";
    private static final String GET_GENRE_TEMPLATE = "/genre/%d";

    @NonNull
    private DeezerContext context;

    public DeezerClient() {
        this.context = new DeezerContext();
    }

    public GetMethod<Artist> getArtist(int artistId) {
        return new GetMethod<>(context, String.format(GET_ARTIST_TEMPLATE, artistId), new TypeReference<Artist>() {
        });
    }

    public PagingMethod<Track> getArtistTop(int artistId) {
        return new PagingMethod<>(context, String.format(GET_ARTIST_TOP_TEMPLATE, artistId), new TypeReference<Page<Track>>() {
        });
    }

    public GetMethod<Genre> getGenre(long genreId) {
        return new GetMethod<>(context, String.format(GET_GENRE_TEMPLATE, genreId), new TypeReference<Genre>() {
        });
    }
}
