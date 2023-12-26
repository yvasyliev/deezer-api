package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import io.github.yvasyliev.deezer.objects.Picture;

import java.net.URL;

public class PictureToUrlConverter extends StdConverter<Picture, URL> {
    @Override
    public URL convert(Picture value) {
        return value.getUrl();
    }
}
