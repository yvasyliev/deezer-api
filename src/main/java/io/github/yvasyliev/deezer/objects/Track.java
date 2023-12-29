package io.github.yvasyliev.deezer.objects;

import api.deezer.objects.Album;
import api.deezer.objects.ChartMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

/**
 * A track object.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(value = "type", allowGetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Track extends ChartMember implements Pageable { //TODO: do something with ChartMember
    /**
     * The track's Deezer id.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * {@code true} if the track is readable in the player for the current user.
     */
    @JsonProperty("readable")
    private Boolean isReadable;

    /**
     * The track's fulltitle.
     */
    @JsonProperty("title")
    private String title;

    /**
     * The track's short title.
     */
    @JsonProperty("title_short")
    private String titleShort;

    /**
     * The track version.
     */
    @JsonProperty("title_version")
    private String titleVersion;

    /**
     * The track unseen status.
     */
    @JsonProperty("unseen")
    private Boolean isUnseen;

    /**
     * The track isrc.
     */
    @JsonProperty("isrc")
    private String isrc;

    /**
     * The url of the track on Deezer.
     */
    @JsonProperty("link")
    private String link;

    /**
     * The share link of the track on Deezer.
     */
    @JsonProperty("share")
    private String share;

    /**
     * The track's duration in seconds.
     */
    @JsonProperty("duration")
    private Integer duration;

    /**
     * The position of the track in its album.
     */
    @JsonProperty("track_position")
    private Integer trackPosition;

    /**
     * The track's album's disk number.
     */
    @JsonProperty("disk_number")
    private Integer diskNumber;

    /**
     * The track's Deezer rank.
     */
    @JsonProperty("rank")
    private Integer rank;

    /**
     * The track's release date.
     */
    @JsonProperty("release_date")
    private LocalDate releaseDate;

    /**
     * Whether the track contains explicit lyrics.
     */
    @JsonProperty("explicit_lyrics")
    private Boolean containsExplicitLyrics;

    /**
     * The explicit content lyrics values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 6:No Advice Available).
     */
    @JsonProperty("explicit_content_lyrics")
    private ExplicitContent explicitContentLyrics;

    /**
     * The explicit cover value (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 6:No Advice Available).
     */
    @JsonProperty("explicit_content_cover")
    private ExplicitContent explicitContentCover;

    /**
     * The url of track's preview file. This file contains the first 30 seconds of the track.
     */
    @JsonProperty("preview")
    private String preview;

    /**
     * Beats per minute.
     */
    @JsonProperty("bpm")
    private Float bpm;

    /**
     * Signal strength.
     */
    @JsonProperty("gain")
    private Float gain;

    /**
     * List of countries where the track is available.
     */
    @JsonProperty("available_countries")
    private List<Country> availableCountries;

    /**
     * Return an alternative readable track if the current track is not readable.
     */
    @JsonProperty("alternative")
    private Track alternative;

    /**
     * Return a list of contributors on the track.
     */
    @JsonProperty("contributors")
    private List<Contributor> contributors;

    /**
     * Image hash.
     */
    @JsonProperty("md5_image")
    private String md5Image;

    /**
     * {@link Artist} object containing : id, name, link, share, picture, picture_small, picture_medium, picture_big, picture_xl, nb_album, nb_fan, radio, tracklist, role
     */
    @JsonProperty("artist")
    private Artist artist;

    /**
     * {@link Album} object containing : id, title, link, cover, cover_small, cover_medium, cover_big, cover_xl, release_date
     */
    @JsonProperty("album")
    private Album album;

    @JsonProperty("type")
    public ObjectType getType() {
        return ObjectType.TRACK;
    }
}
