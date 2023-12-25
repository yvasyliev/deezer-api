package io.github.yvasyliev.deezer.methods;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.exceptions.DeezerResponseException;
import io.github.yvasyliev.deezer.exceptions.UnsupportedHttpResponseException;
import io.github.yvasyliev.deezer.helpers.QueryParams;
import io.github.yvasyliev.deezer.http.DeezerHttpResponse;

import java.io.IOException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetMethod<T> extends Method<T> {
    public GetMethod(DeezerContext context, String path, TypeReference<T> responseType) {
        super(context, path, responseType);
    }

    @Override
    public T execute() throws IOException {
        DeezerContext context = getContext();
        DeezerHttpResponse response = context
                .getHttpClient()
                .get(context.getDeezerApiHost() + getPath(), getQueryParams());
        if (!response.isOk()) {
            throw new UnsupportedHttpResponseException(response);
        }
        ObjectMapper mapper = context.getObjectMapper();
        JsonNode jsonResponse = mapper.readTree(response.getContent());
        if (!context.getResponseValidator().validate(jsonResponse)) {
            throw new DeezerResponseException(jsonResponse);
        }
        return mapper.treeToValue(jsonResponse, getResponseType());
    }

    @JsonIgnore
    public QueryParams getQueryParams() {
        return getContext().getObjectMapper().convertValue(this, QueryParams.class);
    }
}
