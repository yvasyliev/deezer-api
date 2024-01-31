package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.net.URL;
import java.util.List;

/**
 * A genre object.
 */
@Data
public class Genre implements Pageable {
    /**
     * The editorial's Deezer id.
     */
    @SerializedName("id")
    private Long id;

    /**
     * The editorial's name.
     */
    @SerializedName(value = "name", alternate = "title")
    private String name;

    /**
     * The url of the genre picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @SerializedName("picture")
    private Picture picture;

    /**
     * The url of the genre picture in size small.
     */
    @SerializedName("picture_small")
    private URL pictureSmall;

    /**
     * The url of the genre picture in size medium.
     */
    @SerializedName("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the genre picture in size big.
     */
    @SerializedName("picture_big")
    private URL pictureBig;

    /**
     * The url of the genre picture in size xl.
     */
    @SerializedName("picture_xl")
    private URL pictureXl;

    /**
     * A list of radios for current genre.
     */
    @SerializedName("radios")
    private List<Radio> radios;
}
