package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.net.URL;
import java.time.Duration;

/**
 * A playlist object.
 */
@Data
public class Playlist implements Pageable {
    /**
     * The playlist's Deezer id.
     */
    @SerializedName("id")
    private Long id;

    /**
     * The playlist's title.
     */
    @SerializedName("title")
    private String title;

    /**
     * The playlist description.
     */
    @SerializedName("description")
    private String description;

    /**
     * The playlist's duration (seconds).
     */
    @SerializedName("duration")
    private Duration duration;

    /**
     * If the playlist is public or not.
     */
    @SerializedName("public")
    private Boolean isPublic;

    /**
     * If the playlist is the love tracks playlist.
     */
    @SerializedName("is_loved_track")
    private Boolean isLovedTrack;

    /**
     * If the playlist is collaborative or not.
     */
    @SerializedName("collaborative")
    private Boolean isCollaborative;

    /**
     * The playlist's rate.
     */
    @SerializedName("rating")
    private Integer rating;

    /**
     * Nb tracks in the playlist.
     */
    @SerializedName("nb_tracks")
    private Integer numberOfTracks;

    /**
     * Nb tracks not seen.
     */
    @SerializedName("unseen_track_count")
    private Integer unseenTrackCount;

    /**
     * The number of playlist's fans.
     */
    @SerializedName("fans")
    private Integer fans;

    /**
     * The url of the playlist on Deezer.
     */
    @SerializedName("link")
    private URL link;

    /**
     * The share link of the playlist on Deezer.
     */
    @SerializedName("share")
    private URL share;

    /**
     * The url of the playlist's cover. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @SerializedName("picture")
    private Picture picture;

    /**
     * The url of the playlist's cover in size small.
     */
    @SerializedName("picture_small")
    private URL pictureSmall;

    /**
     * The url of the playlist's cover in size medium.
     */
    @SerializedName("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the playlist's cover in size big.
     */
    @SerializedName("picture_big")
    private URL pictureBig;

    /**
     * The url of the playlist's cover in size xl.
     */
    @SerializedName("picture_xl")
    private URL pictureXl;

    /**
     * The checksum for the track list.
     */
    @SerializedName("checksum")
    private String checksum;

    /**
     * {@link User} object containing : id, name.
     */
    @SerializedName("creator")
    private User creator;

    /**
     * list of {@link Track}.
     */
    @SerializedName("tracks")
    private Page<Track> tracks;
}
