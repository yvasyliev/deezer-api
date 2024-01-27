package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.net.URL;
import java.time.Duration;

/**
 * A playlist object.
 */
@Data
public class Playlist implements BaseObject, Pageable {
    /**
     * The playlist's Deezer id.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The playlist's title.
     */
    @JsonProperty("title")
    private String title;

    /**
     * The playlist description.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The playlist's duration (seconds).
     */
    @JsonProperty("duration")
//    @JsonDeserialize(converter = LongToDurationConverter.class)
//    @JsonSerialize(converter = DurationToLongConverter.class)
    private Duration duration;

    /**
     * If the playlist is public or not.
     */
    @JsonProperty("public")
    private Boolean isPublic;

    /**
     * If the playlist is the love tracks playlist.
     */
    @JsonProperty("is_loved_track")
    private Boolean isLovedTrack;

    /**
     * If the playlist is collaborative or not.
     */
    @JsonProperty("collaborative")
    private Boolean isCollaborative;

    /**
     * The playlist's rate.
     */
    @JsonProperty("rating")
    private Integer rating;

    /**
     * Nb tracks in the playlist.
     */
    @JsonProperty("nb_tracks")
    private Integer numberOfTracks;

    /**
     * Nb tracks not seen.
     */
    @JsonProperty("unseen_track_count")
    private Integer unseenTrackCount;

    /**
     * The number of playlist's fans.
     */
    @JsonProperty("fans")
    private Integer fans;

    /**
     * The url of the playlist on Deezer.
     */
    @JsonProperty("link")
    private URL link;

    /**
     * The share link of the playlist on Deezer.
     */
    @JsonProperty("share")
    private URL share;

    /**
     * The url of the playlist's cover. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @JsonProperty("picture")
    private Picture picture;

    /**
     * The url of the playlist's cover in size small.
     */
    @JsonProperty("picture_small")
    private URL pictureSmall;

    /**
     * The url of the playlist's cover in size medium.
     */
    @JsonProperty("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the playlist's cover in size big.
     */
    @JsonProperty("picture_big")
    private URL pictureBig;

    /**
     * The url of the playlist's cover in size xl.
     */
    @JsonProperty("picture_xl")
    private URL pictureXl;

    /**
     * The checksum for the track list.
     */
    @JsonProperty("checksum")
    private String checksum;

    /**
     * {@link User} object containing : id, name.
     */
    @JsonProperty("creator")
    private User creator;

    /**
     * list of {@link Track}.
     */
    @JsonProperty("tracks")
    private Page<Track> tracks;

    @Override
    public ObjectType getType() {
        return ObjectType.PLAYLIST;
    }
}
