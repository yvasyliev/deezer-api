package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface AlbumService extends DeezerService {
    String ALBUM = "/album/{albumId}";
    String ALBUM_FANS = "/album/{albumId}/fans";
    String ALBUM_TRACKS = "/album/{albumId}/tracks";

    @RequestLine(GET + ALBUM)
    Album getAlbum(@Param("albumId") long albumId);

    @RequestLine(GET + ALBUM)
    CompletableFuture<Album> getAlbumAsync(@Param("albumId") long albumId);

    @RequestLine(GET + ALBUM_FANS)
    Page<User> getAlbumFans(@Param("albumId") long albumId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ALBUM_FANS)
    CompletableFuture<Page<User>> getAlbumFansAsync(@Param("albumId") long albumId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ALBUM_TRACKS)
    Page<Track> getAlbumTracks(@Param("albumId") long albumId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ALBUM_TRACKS)
    CompletableFuture<Page<Track>> getAlbumTracksAsync(@Param("albumId") long albumId, @QueryMap Map<String, Object> queryParams);
}