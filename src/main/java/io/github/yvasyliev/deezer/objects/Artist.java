package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import lombok.Data;

import java.net.URL;

/**
 * An artist object.
 */
@Data
public class Artist implements Pageable {
    /**
     * The artist's Deezer id.
     */
    @SerializedName("id")
    private Long id;

    /**
     * The artist's name.
     */
    @SerializedName("name")
    private String name;

    /**
     * The url of the artist on Deezer.
     */
    @SerializedName("link")
    private URL link;

    /**
     * The share link of the artist on Deezer.
     */
    @SerializedName("share")
    private URL share;

    /**
     * The url of the artist picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @SerializedName("picture")
    private Picture picture;

    /**
     * The url of the artist picture in size small.
     */
    @SerializedName("picture_small")
    private URL pictureSmall;

    /**
     * The url of the artist picture in size medium.
     */
    @SerializedName("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the artist picture in size big.
     */
    @SerializedName("picture_big")
    private URL pictureBig;

    /**
     * The url of the artist picture in size xl.
     */
    @SerializedName("picture_xl")
    private URL pictureXl;

    /**
     * The number of artist's albums.
     */
    @SerializedName("nb_album")
    private Integer numberOfAlbums;

    /**
     * The number of artist's fans.
     */
    @SerializedName("nb_fan")
    private Integer numberOfFans;

    /**
     * true if the artist has a smartradio.
     */
    @SerializedName("radio")
    private Boolean radio;

    /**
     * API Link to the top of this artist.
     */
    @SerializedName("tracklist")
    private PagingMethod<Track> trackList;
}
