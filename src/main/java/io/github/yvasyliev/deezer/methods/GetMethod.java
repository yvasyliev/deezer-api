package io.github.yvasyliev.deezer.methods;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.helpers.QueryParams;
import io.github.yvasyliev.deezer.http.HttpClient;
import io.github.yvasyliev.deezer.http.HttpResponse;

import java.io.IOException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetMethod<T> extends Method<T> {
    public GetMethod(DeezerContext context, String path, TypeReference<T> responseType) {
        super(context, path, responseType);
    }

    @Override
    protected HttpResponse fetch(HttpClient httpClient, String url, QueryParams queryParams) throws IOException {
        return httpClient.get(url, queryParams);
    }
}
