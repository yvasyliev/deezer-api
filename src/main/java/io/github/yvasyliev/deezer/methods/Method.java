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
import io.github.yvasyliev.deezer.http.HttpMethod;
import io.github.yvasyliev.deezer.http.HttpRequest;
import io.github.yvasyliev.deezer.http.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.net.URL;

@Data
@AllArgsConstructor
public abstract class Method<T> implements HttpRequest {
    @JacksonInject
    @JsonIgnore
    private DeezerContext context;

    @JsonProperty(value = "path", access = JsonProperty.Access.WRITE_ONLY)
    private String path;

    @JacksonInject
    @JsonIgnore
    private TypeReference<T> responseType;

    public T execute() throws IOException {
        HttpResponse response = context.getHttpClient().send(this);
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
    @Override
    public URL getUrl() {
        ObjectMapper mapper = context.getObjectMapper();
        String endpoint = context.getDeezerApiHost() + path;
        QueryParams queryParams = mapper.convertValue(this, QueryParams.class);
        if (!queryParams.isEmpty()) {
            endpoint += "?" + queryParams.toQuery();
        }
        return mapper.convertValue(endpoint, URL.class);
    }

    @JsonIgnore
    @Override
    public abstract HttpMethod getMethod();

    @Override
    public String toString() {
        return getMethod() + " " + getUrl();
    }
}
