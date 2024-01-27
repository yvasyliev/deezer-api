package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import lombok.Data;

import java.net.URL;
import java.time.LocalDate;
import java.util.Set;

/**
 * A user object.
 */
@Data
public class User implements Pageable {
    /**
     * The user's Deezer ID.
     */
    @SerializedName("id")
    private Long id;

    /**
     * The user's Deezer nickname.
     */
    @SerializedName("name")
    private String name;

    /**
     * The user's last name.
     */
    @SerializedName("lastname")
    private String lastName;

    /**
     * The user's first name.
     */
    @SerializedName("firstname")
    private String firstName;

    /**
     * The user's email.
     */
    @SerializedName("email")
    private String email;

    /**
     * The user's status.
     */
    @SerializedName("status")
    private Integer status;

    /**
     * The user's birthday.
     */
    @SerializedName("birthday")
    private LocalDate birthday; // TODO: handle 0000-00-00 date

    /**
     * The user's inscription date.
     */
    @SerializedName("inscription_date")
    private LocalDate inscriptionDate;

    /**
     * The user's gender : F or M.
     */
    @SerializedName("gender")
    private Gender gender;

    /**
     * The url of the profile for the user on Deezer.
     */
    @SerializedName("link")
    private URL link;

    /**
     * The url of the user's profile picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @SerializedName("picture")
    private Picture picture;

    /**
     * The url of the user's profile picture in size small.
     */
    @SerializedName("picture_small")
    private URL pictureSmall;

    /**
     * The url of the user's profile picture in size medium.
     */
    @SerializedName("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the user's profile picture in size big.
     */
    @SerializedName("picture_big")
    private URL pictureBig;

    /**
     * The url of the user's profile picture in size xl.
     */
    @SerializedName("picture_xl")
    private URL pictureXl;

    /**
     * The user's country.
     */
    @SerializedName("country")
    private Country country;

    /**
     * The user's language.
     */
    @SerializedName("lang")
    private String lang;

    /**
     * If the user is a kid or not.
     */
    @SerializedName("is_kid")
    private Boolean isKid;

    /**
     * The user's explicit content level according to his country.
     */
    @SerializedName("explicit_content_level")
    private ExplicitContentLevel explicitContentLevel;

    /**
     * The user's available explicit content levels according to his country. Possible values are: explicit_display, explicit_no_recommendation and explicit_hide.
     */
    @SerializedName("explicit_content_levels_available")
    private Set<ExplicitContentLevel> explicitContentLevelsAvailable;

    /**
     * API Link to the flow of this user.
     */
    @SerializedName("tracklist")
    private PagingMethod<Track> trackList;
}
