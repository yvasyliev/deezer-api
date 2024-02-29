package io.github.yvasyliev.deezer.v2.methods;

import java.util.concurrent.CompletableFuture;

public interface Method<T> {
    String ALBUM_ID = "albumId";
    String ARTIST_ID = "artistId";
    String OBJECT_ID = "objectId";

    T execute();

    CompletableFuture<T> executeAsync();
}
