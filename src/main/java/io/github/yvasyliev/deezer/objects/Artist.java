package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.yvasyliev.deezer.json.MethodDeserializer;
import io.github.yvasyliev.deezer.json.MethodToUrlConverter;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import lombok.Data;

import java.net.URL;

/**
 * An artist object.
 */
@Data
public class Artist implements BaseObject, Pageable {
    /**
     * The artist's Deezer id.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The artist's name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The url of the artist on Deezer.
     */
    @JsonProperty("link")
    private URL link;

    /**
     * The share link of the artist on Deezer.
     */
    @JsonProperty("share")
    private URL share;

    /**
     * The url of the artist picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @JsonProperty("picture")
    private Picture picture;

    /**
     * The url of the artist picture in size small.
     */
    @JsonProperty("picture_small")
    private URL pictureSmall;

    /**
     * The url of the artist picture in size medium.
     */
    @JsonProperty("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the artist picture in size big.
     */
    @JsonProperty("picture_big")
    private URL pictureBig;

    /**
     * The url of the artist picture in size xl.
     */
    @JsonProperty("picture_xl")
    private URL pictureXl;

    /**
     * The number of artist's albums.
     */
    @JsonProperty("nb_album")
    private Integer numberOfAlbums;

    /**
     * The number of artist's fans.
     */
    @JsonProperty("nb_fan")
    private Integer numberOfFans;

    /**
     * true if the artist has a smartradio.
     */
    @JsonProperty("radio")
    private Boolean radio;

    /**
     * API Link to the top of this artist.
     */
    @JsonProperty("tracklist")
    @JsonDeserialize(using = MethodDeserializer.class)
    @JsonSerialize(converter = MethodToUrlConverter.class)
    private PagingMethod<Track> trackList;

    @Override
    public ObjectType getType() {
        return ObjectType.ARTIST;
    }
}
