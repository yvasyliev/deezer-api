package io.github.yvasyliev.deezer.v2.json.creators.album;

import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.json.creators.PagingMethodCreator;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbumTracks;

import java.lang.reflect.Type;

public class GetAlbumTracksCreator extends PagingMethodCreator<Track, AlbumService> {
    @Override
    public PagingMethod<Track> createInstance(Type type) {
        return new GetAlbumTracks(gson, service, DEFAULT_OBJECT_ID);
    }
}
