package io.github.yvasyliev.deezer;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.yvasyliev.deezer.methods.GetMethod;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeezerClient {
    private static final String ALBUMS = "/albums";
    private static final String FANS = "/fans";
    private static final String RADIO = "/radio";
    private static final String RELATED = "/related";
    private static final String TOP = "/top";
    private static final String TRACKS = "/tracks";

    private static final String GET_ALBUM_TEMPLATE = "/album/%d";
    private static final String GET_ARTIST_TEMPLATE = "/artist/%d";
    private static final String GET_GENRE_TEMPLATE = "/genre/%d";

    private static final String GET_ALBUM_FANS_TEMPLATE = GET_ALBUM_TEMPLATE + FANS;
    private static final String GET_ALBUM_TRACKS_TEMPLATE = GET_ALBUM_TEMPLATE + TRACKS;
    private static final String GET_ARTIST_ALBUMS_TEMPLATE = GET_ARTIST_TEMPLATE + ALBUMS;
    private static final String GET_ARTIST_FANS_TEMPLATE = GET_ARTIST_TEMPLATE + FANS;
    private static final String GET_ARTIST_RADIO = GET_ARTIST_TEMPLATE + RADIO;
    private static final String GET_ARTIST_RELATED_TEMPLATE = GET_ARTIST_TEMPLATE + RELATED;
    private static final String GET_ARTIST_TOP_TEMPLATE = GET_ARTIST_TEMPLATE + TOP;

    private static final TypeReference<Page<Album>> ALBUM_PAGE_TYPE = new TypeReference<Page<Album>>() {
    };
    private static final TypeReference<Album> ALBUM_TYPE = new TypeReference<Album>() {
    };
    private static final TypeReference<Page<Artist>> ARTIST_PAGE_TYPE = new TypeReference<Page<Artist>>() {
    };
    private static final TypeReference<Artist> ARTIST_TYPE = new TypeReference<Artist>() {
    };
    private static final TypeReference<Genre> GENRE_TYPE = new TypeReference<Genre>() {
    };
    private static final TypeReference<Page<Track>> TRACK_PAGE_TYPE = new TypeReference<Page<Track>>() {
    };
    private static final TypeReference<Page<User>> USER_PAGE_TYPE = new TypeReference<Page<User>>() {
    };

    private String accessToken;
    private DeezerContext context;

    public DeezerClient() {
        this(null);
    }

    public DeezerClient(String accessToken) {
        this(accessToken, new DeezerContext());
    }

    /**
     * Returns {@link Album} by album ID.
     *
     * @param albumId album ID.
     * @return an {@link Album} object.
     */
    public GetMethod<Album> getAlbum(long albumId) {
        return getMethod(GET_ALBUM_TEMPLATE, albumId, ALBUM_TYPE);
    }

    /**
     * Return a list of album's fans. Represented by an array of User objects.
     *
     * @param albumId album ID.
     * @return album's fans.
     */
    public PagingMethod<User> getAlbumFans(long albumId) {
        return pagingMethod(GET_ALBUM_FANS_TEMPLATE, albumId, USER_PAGE_TYPE);
    }

    /**
     * Return a list of album's tracks. Represented by an array of Track objects.
     *
     * @param albumId album ID.
     * @return album's fans.
     */
    public PagingMethod<Track> getAlbumTracks(long albumId) {
        return pagingMethod(GET_ALBUM_TRACKS_TEMPLATE, albumId, TRACK_PAGE_TYPE);
    }

    /**
     * Returns {@link @Artist} object by artist ID.
     *
     * @param artistId artist ID.
     * @return an {@link @Artist} object.
     */
    public GetMethod<Artist> getArtist(long artistId) {
        return getMethod(GET_ARTIST_TEMPLATE, artistId, ARTIST_TYPE);
    }

    /**
     * Get the top 5 tracks of an artist.
     *
     * @param artistId artist ID.
     * @return top 5 tracks of an artist.
     */
    public PagingMethod<Track> getArtistTop(long artistId) {
        return pagingMethod(GET_ARTIST_TOP_TEMPLATE, artistId, TRACK_PAGE_TYPE);
    }

    /**
     * Return a list of artist's albums. Represented by an array of Album objects.
     *
     * @param artistId artist ID.
     * @return a list of artist's albums.
     */
    public PagingMethod<Album> getArtistAlbums(long artistId) {
        return pagingMethod(GET_ARTIST_ALBUMS_TEMPLATE, artistId, ALBUM_PAGE_TYPE);
    }

    /**
     * Return a list of artist's fans. Represented by an array of User objects.
     *
     * @param artistId artist ID.
     * @return a list of artist's fans.
     */
    public PagingMethod<User> getArtistFans(long artistId) {
        return pagingMethod(GET_ALBUM_FANS_TEMPLATE, artistId, USER_PAGE_TYPE);
    }

    /**
     * Return a list of related artists. Represented by an array of Artist objects.
     *
     * @param artistId artist ID.
     * @return a list of related artists.
     */
    public PagingMethod<Artist> getArtistRelated(long artistId) {
        return pagingMethod(GET_ARTIST_RELATED_TEMPLATE, artistId, ARTIST_PAGE_TYPE);
    }

    /**
     * Return a list of tracks. Represented by an array of Track object.
     *
     * @param artistId artist ID.
     * @return a list of tracks.
     */
    public PagingMethod<Track> getArtistRadio(long artistId) {
        return pagingMethod(GET_ARTIST_RADIO, artistId, TRACK_PAGE_TYPE);
    }

    public GetMethod<Genre> getGenre(long genreId) {
        return getMethod(GET_GENRE_TEMPLATE, genreId, GENRE_TYPE);
    }

    private <T> GetMethod<T> getMethod(String path, long id, TypeReference<T> responseType) {
        return new GetMethod<>(context, String.format(path, id), responseType);
    }

    private <T extends Pageable> PagingMethod<T> pagingMethod(String path, long id, TypeReference<Page<T>> responseType) {
        return new PagingMethod<>(context, String.format(path, id), responseType);
    }
}
