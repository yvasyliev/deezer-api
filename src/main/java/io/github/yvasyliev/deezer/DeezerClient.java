package io.github.yvasyliev.deezer;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.yvasyliev.deezer.methods.GetMethod;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Track;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeezerClient {
    private static final String TOP = "/top";
    private static final String TRACKS = "/tracks";
    private static final String GET_ALBUM_TEMPLATE = "/album/%d";
    private static final String GET_ALBUM_TRACKS_TEMPLATE = GET_ALBUM_TEMPLATE + TRACKS;
    private static final String GET_ARTIST_TEMPLATE = "/artist/%d";
    private static final String GET_ARTIST_TOP_TEMPLATE = GET_ARTIST_TEMPLATE + TOP;
    private static final String GET_GENRE_TEMPLATE = "/genre/%d";

    private DeezerContext context;

    public DeezerClient() {
        this.context = new DeezerContext();
    }

    public GetMethod<Album> getAlbum(long albumId) {
        return new GetMethod<>(context, String.format(GET_ALBUM_TEMPLATE, albumId), new TypeReference<Album>() {
        });
    }

    public PagingMethod<Track> getAlbumTracks(long albumId) {
        return new PagingMethod<>(context, String.format(GET_ALBUM_TRACKS_TEMPLATE, albumId), new TypeReference<Page<Track>>() {
        });
    }

    public GetMethod<Artist> getArtist(long artistId) {
        return new GetMethod<>(context, String.format(GET_ARTIST_TEMPLATE, artistId), new TypeReference<Artist>() {
        });
    }

    public PagingMethod<Track> getArtistTop(long artistId) {
        return new PagingMethod<>(context, String.format(GET_ARTIST_TOP_TEMPLATE, artistId), new TypeReference<Page<Track>>() {
        });
    }

    public GetMethod<Genre> getGenre(long genreId) {
        return new GetMethod<>(context, String.format(GET_GENRE_TEMPLATE, genreId), new TypeReference<Genre>() {
        });
    }
}
