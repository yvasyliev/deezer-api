package io.github.yvasyliev.deezer.methods;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.yvasyliev.deezer.DeezerContext;
import io.github.yvasyliev.deezer.exceptions.DeezerResponseException;
import io.github.yvasyliev.deezer.exceptions.UnsupportedHttpResponseException;
import io.github.yvasyliev.deezer.helpers.QueryParams;
import io.github.yvasyliev.deezer.http.HttpClient;
import io.github.yvasyliev.deezer.http.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;

@Data
@AllArgsConstructor
public abstract class Method<T> {
    @JacksonInject
    @JsonIgnore
    private DeezerContext context;

    @JsonProperty("path")
    private String path;

    @JacksonInject
    @JsonIgnore
    private TypeReference<T> responseType;

    public T execute() throws IOException {
        ObjectMapper mapper = context.getObjectMapper();
        QueryParams queryParams = mapper.convertValue(this, QueryParams.class);
        HttpResponse response = fetch(context.getHttpClient(), context.getDeezerApiHost() + path, queryParams);
        if (!response.isOk()) {
            throw new UnsupportedHttpResponseException(response);
        }
        JsonNode jsonResponse = mapper.readTree(response.getContent());
        if (!context.getResponseValidator().validate(jsonResponse)) {
            throw new DeezerResponseException(jsonResponse);
        }
        return mapper.treeToValue(jsonResponse, getResponseType());
    }

    protected abstract HttpResponse fetch(HttpClient httpClient, String url, QueryParams queryParams) throws IOException;

    @Override
    public String toString() {
        String endpoint = context.getDeezerApiHost() + path;
        QueryParams queryParams = context.getObjectMapper().convertValue(this, QueryParams.class);
        if (!queryParams.isEmpty()) {
            endpoint += "?" + queryParams.toQuery();
        }
        return endpoint;
    }
}
