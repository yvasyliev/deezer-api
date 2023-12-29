package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.net.URL;

/**
 * A genre object.
 */
@Data
@JsonIgnoreProperties(value = "type", allowGetters = true, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Genre {
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
     * The url of the genre picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @JsonProperty("picture")
    private Picture picture;

    /**
     * The url of the genre picture in size small.
     */
    @JsonProperty("picture_small")
    private URL pictureSmall;

    /**
     * The url of the genre picture in size medium.
     */
    @JsonProperty("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the genre picture in size big.
     */
    @JsonProperty("picture_big")
    private URL pictureBig;

    /**
     * The url of the genre picture in size xl.
     */
    @JsonProperty("picture_xl")
    private URL pictureXl;

    @JsonProperty("type")
    public ObjectType getType() {
        return ObjectType.GENRE;
    }
}
