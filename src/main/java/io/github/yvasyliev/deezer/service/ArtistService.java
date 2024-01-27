package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ArtistService extends DeezerService {
    String ARTIST = "/artist/{artistId}";
    String ARTIST_ALBUMS = "/artist/{artistId}/albums";
    String ARTIST_FANS = "/artist{artistId}/fans";
    String ARTIST_PLAYLISTS = "/artist/{artistId}/playlists";
    String ARTIST_RADIO = "/artist/{artistId}/radio";
    String ARTIST_RELATED = "/artist/{artistId}/related";
    String ARTIST_TOP = "/artist/{artistId}/top";

    @RequestLine(GET + ARTIST)
    Artist getArtist(@Param("artistId") long artistId);

    @RequestLine(GET + ARTIST)
    CompletableFuture<Artist> getArtistAsync(@Param("artistId") long artistId);

    @RequestLine(GET + ARTIST_ALBUMS)
    Page<Album> getArtistAlbums(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_ALBUMS)
    CompletableFuture<Page<Album>> getArtistAlbumsAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_FANS)
    Page<User> getArtistFans(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_FANS)
    CompletableFuture<Page<User>> getArtistFansAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_PLAYLISTS)
    Page<Playlist> getArtistPlaylists(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_PLAYLISTS)
    CompletableFuture<Page<Playlist>> getArtistPlaylistsAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_RADIO)
    Page<Track> getArtistRadio(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_RADIO)
    CompletableFuture<Page<Track>> getArtistRadioAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_RELATED)
    Page<Artist> getArtistRelated(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_RELATED)
    CompletableFuture<Page<Artist>> getArtistRelatedAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_TOP)
    Page<Track> getArtistTop(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_TOP)
    CompletableFuture<Page<Track>> getArtistTopAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);
}
