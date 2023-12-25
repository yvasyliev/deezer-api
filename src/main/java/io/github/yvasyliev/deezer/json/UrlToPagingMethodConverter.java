package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.github.yvasyliev.deezer.helpers.URLHelper;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Pageable;
import lombok.Setter;

import java.net.URL;

@Setter
public class UrlToPagingMethodConverter<T extends Pageable> extends StdConverter<URL, PagingMethod<T>> {
    private ObjectMapper objectMapper;

    @Override
    public PagingMethod<T> convert(URL value) {
        return objectMapper.convertValue(
                objectMapper
                        .convertValue(URLHelper.getQueryParams(value), ObjectNode.class)
                        .put("path", value.getPath()),
                new TypeReference<PagingMethod<T>>() {
                }
        );
    }
}
