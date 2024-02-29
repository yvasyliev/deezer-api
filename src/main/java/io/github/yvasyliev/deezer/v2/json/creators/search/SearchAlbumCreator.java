package io.github.yvasyliev.deezer.v2.json.creators.search;

import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.search.SearchAlbum;
import lombok.Setter;

import java.lang.reflect.Type;

@Setter
public class SearchAlbumCreator implements InstanceCreator<SearchAlbum> {
    private Gson gson;
    private SearchService searchService;

    @Override
    public SearchAlbum createInstance(Type type) {
        return new SearchAlbum(gson, searchService, null);
    }
}
