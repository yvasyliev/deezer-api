package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.yvasyliev.deezer.json.DateToLocalDateConverter;
import io.github.yvasyliev.deezer.json.LocalDateToDateConverter;
import io.github.yvasyliev.deezer.json.MethodDeserializer;
import io.github.yvasyliev.deezer.json.MethodToUrlConverter;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import lombok.Data;

import java.net.URL;
import java.time.LocalDate;
import java.util.Set;

/**
 * A user object.
 */
@Data
public class User {
    /**
     * The user's Deezer ID.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The user's Deezer nickname.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The user's last name.
     */
    @JsonProperty("lastname")
    private String lastName;

    /**
     * The user's first name.
     */
    @JsonProperty("firstname")
    private String firstName;

    /**
     * The user's email.
     */
    @JsonProperty("email")
    private String email;

    /**
     * The user's status.
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * The user's birthday.
     */
    @JsonProperty("birthday")
    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    @JsonSerialize(converter = LocalDateToDateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * The user's inscription date.
     */
    @JsonProperty("inscription_date")
    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    @JsonSerialize(converter = LocalDateToDateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate inscriptionDate;

    /**
     * The user's gender : F or M.
     */
    @JsonProperty("gender")
    private Gender gender;

    /**
     * The url of the profile for the user on Deezer.
     */
    @JsonProperty("link")
    private URL link;

    /**
     * The url of the user's profile picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @JsonProperty("picture")
    private Picture picture;

    /**
     * The url of the user's profile picture in size small.
     */
    @JsonProperty("picture_small")
    private URL pictureSmall;

    /**
     * The url of the user's profile picture in size medium.
     */
    @JsonProperty("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the user's profile picture in size big.
     */
    @JsonProperty("picture_big")
    private URL pictureBig;

    /**
     * The url of the user's profile picture in size xl.
     */
    @JsonProperty("picture_xl")
    private URL pictureXl;

    /**
     * The user's country.
     */
    @JsonProperty("country")
    private Country country;

    /**
     * The user's language.
     */
    @JsonProperty("lang")
    private String lang;

    /**
     * If the user is a kid or not.
     */
    @JsonProperty("is_kid")
    private Boolean isKid;

    /**
     * The user's explicit content level according to his country.
     */
    @JsonProperty("explicit_content_level")
    private ExplicitContentLevel explicitContentLevel;

    /**
     * The user's available explicit content levels according to his country. Possible values are: explicit_display, explicit_no_recommendation and explicit_hide.
     */
    @JsonProperty("explicit_content_levels_available")
    private Set<ExplicitContentLevel> explicitContentLevelsAvailable;

    /**
     * API Link to the flow of this user.
     */
    @JsonProperty("tracklist")
    @JsonDeserialize(using = MethodDeserializer.class)
    @JsonSerialize(converter = MethodToUrlConverter.class)
    private PagingMethod<Track> trackList;
}
