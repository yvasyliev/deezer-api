package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.yvasyliev.deezer.helpers.QueryParams;
import io.github.yvasyliev.deezer.helpers.URLHelper;
import io.github.yvasyliev.deezer.json.PictureToUrlConverter;
import io.github.yvasyliev.deezer.json.UrlToPictureConverter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Holds a picture URL. By adding {@code size} parameter to the URL, we can get different picture sizes.
 * Can be 'small', 'medium', 'big', 'xl'.
 */
@Data
@AllArgsConstructor
@JsonDeserialize(converter = UrlToPictureConverter.class)
@JsonSerialize(converter = PictureToUrlConverter.class)
public class Picture {
    private static final String SIZE = "size";
    private static final String SMALL = "small";
    private static final String MEDIUM = "medium";
    private static final String BIG = "big";
    private static final String XL = "xl";
    private URL url;

    public URL getSmallPictureUrl() {
        return getPictureUrl(SMALL);
    }

    public URL getMediumPictureUrl() {
        return getPictureUrl(MEDIUM);
    }

    public URL getBigPictureUrl() {
        return getPictureUrl(BIG);
    }

    public URL getXlPictureUrl() {
        return getPictureUrl(XL);
    }

    private URL getPictureUrl(String pictureSize) {
        try {
            return URLHelper.setQueryParams(url, new QueryParams(SIZE, pictureSize));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
