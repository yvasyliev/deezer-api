package io.github.yvasyliev.deezer.objects;

import api.deezer.objects.ChartMember;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.yvasyliev.deezer.json.DateToLocalDateConverter;
import io.github.yvasyliev.deezer.json.DurationToLongConverter;
import io.github.yvasyliev.deezer.json.LocalDateToDateConverter;
import io.github.yvasyliev.deezer.json.LongToDurationConverter;
import io.github.yvasyliev.deezer.json.MethodDeserializer;
import io.github.yvasyliev.deezer.json.MethodToUrlConverter;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

/**
 * An album object.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Album extends ChartMember implements BaseObject, Pageable {
    /**
     * The Deezer album id.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The album title.
     */
    @JsonProperty("title")
    private String title;

    /**
     * The album UPC.
     */
    @JsonProperty("upc")
    private String upc;

    /**
     * The url of the album on Deezer.
     */
    @JsonProperty("link")
    private URL link;

    /**
     * The share link of the album on Deezer.
     */
    @JsonProperty("share")
    private URL share;

    /**
     * The url of the album's cover. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @JsonProperty("cover")
    private Picture cover;

    /**
     * The url of the album's cover in size small.
     */
    @JsonProperty("cover_small")
    private URL coverSmall;

    /**
     * The url of the album's cover in size medium.
     */
    @JsonProperty("cover_medium")
    private URL coverMedium;

    /**
     * The url of the album's cover in size big.
     */
    @JsonProperty("cover_big")
    private URL coverBig;

    /**
     * The url of the album's cover in size xl.
     */
    @JsonProperty("cover_xl")
    private URL coverXl;

    /**
     * Image hash.
     */
    @JsonProperty("md5_image")
    private String md5Image;

    /**
     * The album's first genre id (You should use the genre list instead). NB : -1 for not found.
     */
    @JsonProperty("genre_id")
    private Integer genreId;

    /**
     * List of genre object.
     */
    @JsonProperty("genres")
    private Page<Genre> genres;

    /**
     * The album's label name.
     */
    @JsonProperty("label")
    private String label;

    /**
     * The number of album's tracks.
     */
    @JsonProperty("nb_tracks")
    private Integer numberOfTracks;

    /**
     * The album's duration (seconds).
     */
    @JsonProperty("duration")
    @JsonDeserialize(converter = LongToDurationConverter.class)
    @JsonSerialize(converter = DurationToLongConverter.class)
    private Duration duration;

    /**
     * The number of album's Fans.
     */
    @JsonProperty("fans")
    private Integer fans;

    /**
     * The album's rate.
     */
    @JsonProperty("rating")
    private Integer rating;

    /**
     * The album's release date.
     */
    @JsonProperty("release_date")
    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    @JsonSerialize(converter = LocalDateToDateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    /**
     * The record type of the album (EP / ALBUM / etc..).
     */
    @JsonProperty("record_type")
    private RecordType recordType;

    /**
     * {@code true}, if the album is available in your country.
     */
    @JsonProperty("available")
    private Boolean isAvailable;

    /**
     * Return an alternative album object if the current album is not available.
     */
    @JsonProperty("alternative")
    private Album alternative;

    /**
     * API Link to the tracklist of this album.
     */
    @JsonProperty("tracklist")
    @JsonDeserialize(using = MethodDeserializer.class)
    @JsonSerialize(converter = MethodToUrlConverter.class)
    private PagingMethod<Track> trackList;

    /**
     * Whether the album contains explicit lyrics.
     */
    @JsonProperty("explicit_lyrics")
    private Boolean containsExplicitLyrics;

    /**
     * The explicit content lyrics values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 4:Partially Explicit (Album "lyrics" only); 5:Partially Unknown (Album "lyrics" only); 6:No Advice Available; 7:Partially No Advice Available (Album "lyrics" only)).
     */
    @JsonProperty("explicit_content_lyrics")
    private Integer explicitContentLyrics;

    /**
     * The explicit cover values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 4:Partially Explicit (Album "lyrics" only); 5:Partially Unknown (Album "lyrics" only); 6:No Advice Available; 7:Partially No Advice Available (Album "lyrics" only)).
     */
    @JsonProperty("explicit_content_cover")
    private Integer explicitContentCover;

    /**
     * Return a list of contributors on the album.
     */
    @JsonProperty("contributors")
    private Set<Contributor> contributors;

    /**
     * {@link Artist} object containing : id, name, picture, picture_small, picture_medium, picture_big, picture_xl.
     */
    @JsonProperty("artist")
    private Artist artist;

    /**
     * list of {@link Track}.
     */
    @JsonProperty("tracks")
    private Page<Track> tracks;

    @Override
    public ObjectType getType() {
        return ObjectType.ALBUM;
    }
}
