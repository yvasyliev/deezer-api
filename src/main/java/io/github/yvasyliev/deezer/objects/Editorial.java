package io.github.yvasyliev.deezer.objects;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

/**
 * An editorial object.
 */
public class Editorial implements BaseObject, Pageable {
    /**
     * The editorial's Deezer id.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The editorial's name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The url of the editorial picture.
     */
    @JsonProperty("picture")
    private URL picture;

    /**
     * The url of the editorial picture in size small.
     */
    @JsonProperty("picture_small")
    private URL pictureSmall;

    /**
     * The url of the editorial picture in size medium.
     */
    @JsonProperty("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the editorial picture in size big.
     */
    @JsonProperty("picture_big")
    private URL pictureBig;

    /**
     * The url of the editorial picture in size xl.
     */
    @JsonProperty("picture_xl")
    private URL pictureXl;

    @Override
    public ObjectType getType() {
        return ObjectType.EDITORIAL;
    }
}
