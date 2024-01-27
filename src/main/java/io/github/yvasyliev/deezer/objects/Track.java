package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.net.URL;
import java.time.LocalDate;
import java.util.Set;

/**
 * A track object.
 */
@Data
public class Track implements Pageable {
    /**
     * The track's Deezer id.
     */
    @SerializedName("id")
    private Long id;

    /**
     * {@code true} if the track is readable in the player for the current user.
     */
    @SerializedName("readable")
    private Boolean isReadable;

    /**
     * The track's fulltitle.
     */
    @SerializedName("title")
    private String title;

    /**
     * The track's short title.
     */
    @SerializedName("title_short")
    private String titleShort;

    /**
     * The track version.
     */
    @SerializedName("title_version")
    private String titleVersion;

    /**
     * The track unseen status.
     */
    @SerializedName("unseen")
    private Boolean isUnseen;

    /**
     * The track isrc.
     */
    @SerializedName("isrc")
    private String isrc;

    /**
     * The url of the track on Deezer.
     */
    @SerializedName("link")
    private URL link;

    /**
     * The share link of the track on Deezer.
     */
    @SerializedName("share")
    private URL share;

    /**
     * The track's duration in seconds.
     */
//    @SerializedName("duration")
//    private Duration duration;

    /**
     * The position of the track in its album.
     */
    @SerializedName("track_position")
    private Integer trackPosition;

    /**
     * The track's album's disk number.
     */
    @SerializedName("disk_number")
    private Integer diskNumber;

    /**
     * The track's Deezer rank.
     */
    @SerializedName("rank")
    private Integer rank;

    /**
     * The track's release date.
     */
    @SerializedName("release_date")
    private LocalDate releaseDate;

    /**
     * Whether the track contains explicit lyrics.
     */
    @SerializedName("explicit_lyrics")
    private Boolean containsExplicitLyrics;

    /**
     * The explicit content lyrics values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 6:No Advice Available).
     */
    @SerializedName("explicit_content_lyrics")
    private ExplicitContent explicitContentLyrics;

    /**
     * The explicit cover value (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 6:No Advice Available).
     */
    @SerializedName("explicit_content_cover")
    private ExplicitContent explicitContentCover;

    /**
     * The url of track's preview file. This file contains the first 30 seconds of the track.
     */
    @SerializedName("preview")
    private URL preview;

    /**
     * Beats per minute.
     */
    @SerializedName("bpm")
    private Float bpm;

    /**
     * Signal strength.
     */
    @SerializedName("gain")
    private Float gain;

    /**
     * List of countries where the track is available.
     */
    @SerializedName("available_countries")
    private Set<Country> availableCountries;

    /**
     * Return an alternative readable track if the current track is not readable.
     */
    @SerializedName("alternative")
    private Track alternative;

    /**
     * Return a list of contributors on the track.
     */
//    @SerializedName("contributors")
//    private Set<Contributor> contributors;

    /**
     * Image hash.
     */
//    @SerializedName("md5_image")
//    private String md5Image;

    /**
     * {@link Artist} object containing : id, name, link, share, picture, picture_small, picture_medium, picture_big, picture_xl, nb_album, nb_fan, radio, tracklist, role
     */
//    @SerializedName("artist")
//    private Artist artist;

    /**
     * {@link Album} object containing : id, title, link, cover, cover_small, cover_medium, cover_big, cover_xl, release_date
     */
//    @SerializedName("album")
//    private Album album;
}
