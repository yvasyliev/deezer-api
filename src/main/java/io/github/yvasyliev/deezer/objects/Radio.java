package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import lombok.Data;

import java.net.URL;

/**
 * A radio object.
 */
@Data
public class Radio implements Pageable {
    /**
     * The radio deezer ID.
     */
    @SerializedName("id")
    private Long id;

    /**
     * The radio title.
     */
    @SerializedName("title")
    private String title;

    /**
     * The radio title.
     */
    @SerializedName("description")
    private String description;

    /**
     * The share link of the radio on Deezer.
     */
    @SerializedName("share")
    private URL share;

    /**
     * The url of the radio picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @SerializedName("picture")
    private Picture picture;

    /**
     * The url of the radio picture in size small.
     */
    @SerializedName("picture_small")
    private URL pictureSmall;

    /**
     * The url of the radio picture in size medium.
     */
    @SerializedName("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the radio picture in size big.
     */
    @SerializedName("picture_big")
    private URL pictureBig;

    /**
     * The url of the radio picture in size xl.
     */
    @SerializedName("picture_xl")
    private URL pictureXl;

    /**
     * API Link to the tracklist of this radio.
     */
    @SerializedName("tracklist")
    private PagingMethod<Track> trackList;

    /**
     * Image hash.
     */
    @SerializedName("md5_image")
    private String md5Image;
}
