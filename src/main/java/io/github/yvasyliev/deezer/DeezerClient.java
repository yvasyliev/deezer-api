package io.github.yvasyliev.deezer;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.yvasyliev.deezer.methods.GetMethod;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Podcast;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeezerClient {
    private static final String ALBUMS = "/albums";
    private static final String ARTISTS = "/artists";
    private static final String FANS = "/fans";
    private static final String PLAYLISTS = "/playlists";
    private static final String PODCASTS = "/podcasts";
    private static final String RADIO = "/radio";
    private static final String RELATED = "/related";
    private static final String TOP = "/top";
    private static final String TRACKS = "/tracks";

    private static final String GET_ALBUM_TEMPLATE = "/album/%d";
    private static final String GET_ARTIST_TEMPLATE = "/artist/%d";
    private static final String GET_CHART_TEMPLATE = "/chart/%d";
    private static final String GET_GENRE_TEMPLATE = "/genre/%d";

    private static final String GET_ALBUM_FANS_TEMPLATE = GET_ALBUM_TEMPLATE + FANS;
    private static final String GET_ALBUM_TRACKS_TEMPLATE = GET_ALBUM_TEMPLATE + TRACKS;
    private static final String GET_ARTIST_ALBUMS_TEMPLATE = GET_ARTIST_TEMPLATE + ALBUMS;
    private static final String GET_ARTIST_FANS_TEMPLATE = GET_ARTIST_TEMPLATE + FANS;
    private static final String GET_ARTIST_PLAYLISTS_TEMPLATE = GET_ARTIST_TEMPLATE + PLAYLISTS;
    private static final String GET_ARTIST_RADIO_TEMPLATE = GET_ARTIST_TEMPLATE + RADIO;
    private static final String GET_ARTIST_RELATED_TEMPLATE = GET_ARTIST_TEMPLATE + RELATED;
    private static final String GET_ARTIST_TOP_TEMPLATE = GET_ARTIST_TEMPLATE + TOP;
    private static final String GET_CHART_ALBUMS_TEMPLATE = GET_CHART_TEMPLATE + ALBUMS;
    private static final String GET_CHART_ARTIST_TEMPLATE = GET_CHART_TEMPLATE + ARTISTS;
    private static final String GET_CHART_PLAYLISTS_TEMPLATE = GET_CHART_TEMPLATE + PLAYLISTS;
    private static final String GET_CHART_PODCASTS_TEMPLATE = GET_CHART_TEMPLATE + PODCASTS;
    private static final String GET_CHART_TRACKS_TEMPLATE = GET_CHART_TEMPLATE + TRACKS;

    private static final TypeReference<Page<Album>> ALBUM_PAGE_TYPE = new TypeReference<Page<Album>>() {
    };
    private static final TypeReference<Album> ALBUM_TYPE = new TypeReference<Album>() {
    };
    private static final TypeReference<Page<Artist>> ARTIST_PAGE_TYPE = new TypeReference<Page<Artist>>() {
    };
    private static final TypeReference<Artist> ARTIST_TYPE = new TypeReference<Artist>() {
    };
    private static final TypeReference<Chart> CHART_TYPE = new TypeReference<Chart>() {
    };
    private static final TypeReference<Genre> GENRE_TYPE = new TypeReference<Genre>() {
    };
    private static final TypeReference<Page<Playlist>> PLAYLIST_PAGE_TYPE = new TypeReference<Page<Playlist>>() {
    };
    private static final TypeReference<Page<Podcast>> PODCAST_PAGE_TYPE = new TypeReference<Page<Podcast>>() {
    };
    private static final TypeReference<Page<Track>> TRACK_PAGE_TYPE = new TypeReference<Page<Track>>() {
    };
    private static final TypeReference<Page<User>> USER_PAGE_TYPE = new TypeReference<Page<User>>() {
    };

    private static final long DEFAULT_CHART_ID = 0;

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
        return pagingMethod(GET_ARTIST_RADIO_TEMPLATE, artistId, TRACK_PAGE_TYPE);
    }

    /**
     * Return a list of artist's playlists. Represented by an array of Playlist object.
     *
     * @param artistId artist ID.
     * @return list of playlists.
     */
    public PagingMethod<Playlist> getArtistPlaylists(long artistId) {
        return pagingMethod(GET_ARTIST_PLAYLISTS_TEMPLATE, artistId, PLAYLIST_PAGE_TYPE);
    }

    /**
     * Returns a chart of tracks, albums, artists, playlists and podcasts for {@code chart_id=0}.
     *
     * @return a chart of tracks, albums, artists, playlists and podcasts.
     */
    public GetMethod<Chart> getChart() {
        return getChart(DEFAULT_CHART_ID);
    }

    /**
     * Returns a chart of tracks, albums, artists, playlists and podcasts by a specified ID.
     *
     * @param chartId chart ID.
     * @return a chart of tracks, albums, artists, playlists and podcasts.
     */
    public GetMethod<Chart> getChart(long chartId) {
        return getMethod(GET_CHART_TEMPLATE, chartId, CHART_TYPE);
    }

    /**
     * Returns a chart of tracks for {@code chart_id=0}.
     *
     * @return a chart of tracks.
     */
    public PagingMethod<Track> getChartTracks() {
        return getChartTracks(DEFAULT_CHART_ID);
    }

    /**
     * Returns a chart of tracks by a specified ID.
     *
     * @param chartId chart ID.
     * @return a chart of tracks.
     */
    public PagingMethod<Track> getChartTracks(long chartId) {
        return pagingMethod(GET_CHART_TRACKS_TEMPLATE, chartId, TRACK_PAGE_TYPE);
    }

    /**
     * Returns a chart of albums for {@code chart_id=0}.
     *
     * @return a chart of albums.
     */
    public PagingMethod<Album> getChartAlbums() {
        return getChartAlbums(DEFAULT_CHART_ID);
    }

    /**
     * Returns a chart of albums by a specified ID.
     *
     * @param chartId chart ID.
     * @return a chart of albums.
     */
    public PagingMethod<Album> getChartAlbums(long chartId) {
        return pagingMethod(GET_CHART_ALBUMS_TEMPLATE, chartId, ALBUM_PAGE_TYPE);
    }

    /**
     * Returns a chart of artists for {@code chart_id=0}.
     *
     * @return a chart of artists.
     */
    public PagingMethod<Artist> getChartArtists() {
        return getChartArtists(DEFAULT_CHART_ID);
    }

    /**
     * Returns a chart of artists by a specified ID.
     *
     * @param chartId chart ID.
     * @return a chart of artists.
     */
    public PagingMethod<Artist> getChartArtists(long chartId) {
        return pagingMethod(GET_CHART_ARTIST_TEMPLATE, chartId, ARTIST_PAGE_TYPE);
    }

    /**
     * Returns a chart of playlists for {@code chart_id=0}.
     *
     * @return a chart of playlists.
     */
    public PagingMethod<Playlist> getChartPlaylists() {
        return getChartPlaylists(DEFAULT_CHART_ID);
    }

    /**
     * Returns a chart of playlists by a specified ID.
     *
     * @param chartId chart ID.
     * @return a chart of playlists.
     */
    public PagingMethod<Playlist> getChartPlaylists(long chartId) {
        return pagingMethod(GET_CHART_PLAYLISTS_TEMPLATE, chartId, PLAYLIST_PAGE_TYPE);
    }

    /**
     * Returns a chart of podcasts for {@code chart_id=0}.
     *
     * @return a chart of podcasts.
     */
    public PagingMethod<Podcast> getChartPodcasts() {
        return getChartPodcasts(DEFAULT_CHART_ID);
    }

    /**
     * Returns a chart of podcasts by a specified ID.
     *
     * @param chartId chart ID.
     * @return a chart of podcasts.
     */
    public PagingMethod<Podcast> getChartPodcasts(long chartId) {
        return pagingMethod(GET_CHART_PODCASTS_TEMPLATE, chartId, PODCAST_PAGE_TYPE);
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
