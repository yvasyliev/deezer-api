package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.net.URL;

/**
 * A podcast object.
 */
@Data
public class Podcast implements BaseObject, Pageable {
    /**
     * The podcast's Deezer id.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The podcast's title.
     */
    @JsonProperty("title")
    private String title;

    /**
     * The podcast's description.
     */
    @JsonProperty("description")
    private String description;

    /**
     * If the podcast is available or not.
     */
    @JsonProperty("available")
    private Boolean isAvailable;

    /**
     * The playlist's rate.
     */
    @JsonProperty("rating")
    private Integer rating;

    /**
     * The number of playlist's fans.
     */
    @JsonProperty("fans")
    private Integer fans;

    /**
     * The url of the podcast on Deezer.
     */
    @JsonProperty("link")
    private URL link;

    /**
     * The share link of the podcast on Deezer.
     */
    @JsonProperty("share")
    private URL share;

    /**
     * The url of the podcast's cover.
     */
    @JsonProperty("picture")
    private URL picture;

    /**
     * The url of the podcast's cover in size small.
     */
    @JsonProperty("picture_small")
    private URL pictureSmall;

    /**
     * The url of the podcast's cover in size medium.
     */
    @JsonProperty("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the podcast's cover in size big.
     */
    @JsonProperty("picture_big")
    private URL pictureBig;

    /**
     * The url of the podcast's cover in size xl.
     */
    @JsonProperty("picture_xl")
    private URL pictureXl;

    @Override
    public ObjectType getType() {
        return ObjectType.PODCAST;
    }
}
