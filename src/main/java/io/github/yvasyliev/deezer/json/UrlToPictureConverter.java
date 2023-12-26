package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import io.github.yvasyliev.deezer.objects.Picture;

import java.net.URL;

public class UrlToPictureConverter extends StdConverter<URL, Picture> {
    @Override
    public Picture convert(URL value) {
        return new Picture(value);
    }
}
