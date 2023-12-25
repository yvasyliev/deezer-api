package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.yvasyliev.deezer.helpers.QueryParams;
import io.github.yvasyliev.deezer.helpers.URLHelper;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Pageable;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;

@Setter
public class PagingMethodToUrlConverter<T extends Pageable> extends ThrowingStdConverter<PagingMethod<T>, URL> {
    private ObjectMapper objectMapper;

    @Override
    public URL tryConvert(PagingMethod<T> value) throws IOException {
        return URLHelper.newUrl(
                value.getContext().getDeezerApiHost() + value.getPath(),
                objectMapper.convertValue(value, QueryParams.class)
        );
    }
}
