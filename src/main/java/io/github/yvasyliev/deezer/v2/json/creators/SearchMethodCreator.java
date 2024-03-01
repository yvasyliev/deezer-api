package io.github.yvasyliev.deezer.v2.json.creators;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.service.SearchService;

import java.lang.reflect.InvocationTargetException;

public class SearchMethodCreator extends AbstractPagingMethodCreator<SearchService> {
    @Override
    public Object createInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return clazz.getConstructor(Gson.class, SearchService.class, String.class).newInstance(gson, service, null);
    }
}
