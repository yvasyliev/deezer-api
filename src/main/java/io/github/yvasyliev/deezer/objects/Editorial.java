package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;

import java.net.URL;

/**
 * An editorial object.
 */
public class Editorial implements Pageable {
    /**
     * The editorial's Deezer id.
     */
    @SerializedName("id")
    private Long id;

    /**
     * The editorial's name.
     */
    @SerializedName("name")
    private String name;

    /**
     * The url of the editorial picture.
     */
    @SerializedName("picture")
    private URL picture;

    /**
     * The url of the editorial picture in size small.
     */
    @SerializedName("picture_small")
    private URL pictureSmall;

    /**
     * The url of the editorial picture in size medium.
     */
    @SerializedName("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the editorial picture in size big.
     */
    @SerializedName("picture_big")
    private URL pictureBig;

    /**
     * The url of the editorial picture in size xl.
     */
    @SerializedName("picture_xl")
    private URL pictureXl;
}
