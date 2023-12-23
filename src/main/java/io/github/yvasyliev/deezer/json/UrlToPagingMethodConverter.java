package io.github.yvasyliev.deezer.json;

import io.github.yvasyliev.deezer.exceptions.ConverterException;
import io.github.yvasyliev.deezer.helpers.URLHelper;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Pageable;

import java.net.URL;

public class UrlToPagingMethodConverter<R extends Pageable> extends UrlToMethodConverter<PagingMethod<R>, R> {
    @Override
    public PagingMethod<R> convert(URL url) throws ConverterException {
        PagingMethod<R> pagingMethod = super.convert(url);
        URLHelper.getQueryParams(url).forEach((name, value) -> {
            switch (name) {
                case "index":
                    pagingMethod.setIndex(Integer.parseInt(value));
                    break;

                case "limit":
                    pagingMethod.setLimit(Integer.parseInt(value));
                    break;
            }
        });
        return pagingMethod;
    }
}
