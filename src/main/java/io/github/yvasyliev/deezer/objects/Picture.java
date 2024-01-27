package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.JsonAdapter;
import io.github.yvasyliev.deezer.helpers.QueryParams;
import io.github.yvasyliev.deezer.helpers.URLHelper;
import io.github.yvasyliev.deezer.json.PictureDeserializer;
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
@JsonAdapter(PictureDeserializer.class)
public class Picture {
    private static final String SIZE = "size";
    private static final String SMALL = "small";
    private static final String MEDIUM = "medium";
    private static final String BIG = "big";
    private static final String XL = "xl";

    /**
     * Base picture URL.
     */
    private URL url;

    /**
     * Gets small picture URL.
     *
     * @return small picture URL.
     */
    public URL getSmallPictureUrl() {
        return getPictureUrl(SMALL);
    }

    /**
     * Gets medium picture URL.
     *
     * @return medium picture URL.
     */
    public URL getMediumPictureUrl() {
        return getPictureUrl(MEDIUM);
    }

    /**
     * Gets big picture URL.
     *
     * @return big picture URL.
     */
    public URL getBigPictureUrl() {
        return getPictureUrl(BIG);
    }

    /**
     * Gets xl picture URL.
     *
     * @return xl picture URL.
     */
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
