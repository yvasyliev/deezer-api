package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.github.yvasyliev.deezer.exceptions.ConverterException;
import io.github.yvasyliev.deezer.methods.Method;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class MethodToURLConverter<T extends Method<R>, R> extends StdConverter<T, URL> {
    private final ObjectMapper mapper;

    public MethodToURLConverter() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public URL convert(T method) {
        try {
            JsonNode jsonNode = mapper.convertValue(method, JsonNode.class);
            Map<String, String> queryParams = mapper.convertValue(method, new TypeReference<Map<String, String>>() {
            });
            return new URL(method.getContext().getDeezerApiHost() + method.getPath());
        } catch (MalformedURLException e) {
            throw new ConverterException(e);
        }
    }
}
