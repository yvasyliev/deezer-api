package io.github.yvasyliev.deezer.methods;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.http.HttpMethod;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetMethod<T> extends Method<T> {
    public GetMethod(DeezerContext context, String path, TypeReference<T> responseType) {
        super(context, path, responseType);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }
}
