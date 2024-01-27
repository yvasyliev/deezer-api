package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Track;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ArtistService extends DeezerService {
    String ARTIST = "/artist/{artistId}";
    String ARTIST_ALBUMS = "/artist/{artistId}/albums";
    String ARTIST_TOP = "/artist/{artistId}/top";

    @RequestLine(GET + ARTIST)
    Artist getArtist(@Param("artistId") long artistId);

    @RequestLine(GET + ARTIST)
    CompletableFuture<Artist> getArtistAsync(@Param("artistId") long artistId);

    @RequestLine(GET + ARTIST_ALBUMS)
    Page<Album> getArtistAlbums(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_ALBUMS)
    CompletableFuture<Page<Album>> getArtistAlbumsAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_TOP)
    Page<Track> getArtistTop(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_TOP)
    CompletableFuture<Page<Track>> getArtistTopAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);
}
