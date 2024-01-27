package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.net.URL;

/**
 * A podcast object.
 */
@Data
public class Podcast implements Pageable {
    /**
     * The podcast's Deezer id.
     */
    @SerializedName("id")
    private Long id;

    /**
     * The podcast's title.
     */
    @SerializedName("title")
    private String title;

    /**
     * The podcast's description.
     */
    @SerializedName("description")
    private String description;

    /**
     * If the podcast is available or not.
     */
    @SerializedName("available")
    private Boolean isAvailable;

    /**
     * The playlist's rate.
     */
    @SerializedName("rating")
    private Integer rating;

    /**
     * The number of playlist's fans.
     */
    @SerializedName("fans")
    private Integer fans;

    /**
     * The url of the podcast on Deezer.
     */
    @SerializedName("link")
    private URL link;

    /**
     * The share link of the podcast on Deezer.
     */
    @SerializedName("share")
    private URL share;

    /**
     * The url of the podcast's cover.
     */
    @SerializedName("picture")
    private URL picture;

    /**
     * The url of the podcast's cover in size small.
     */
    @SerializedName("picture_small")
    private URL pictureSmall;

    /**
     * The url of the podcast's cover in size medium.
     */
    @SerializedName("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the podcast's cover in size big.
     */
    @SerializedName("picture_big")
    private URL pictureBig;

    /**
     * The url of the podcast's cover in size xl.
     */
    @SerializedName("picture_xl")
    private URL pictureXl;
}
