package api.deezer.objects;

import api.deezer.deserializers.ZeroDateDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * A user object
 */
public class User {
    /**
     * The user's Deezer ID
     */
    @SerializedName("id")
    private Long id;

    /**
     * The user's Deezer nickname
     */
    @SerializedName("name")
    private String name;

    /**
     * The user's last name
     */
    @SerializedName("lastname")
    private String lastName;

    /**
     * The user's first name
     */
    @SerializedName("firstname")
    private String firstName;

    /**
     * The user's email
     */
    @SerializedName("email")
    private String email;

    /**
     * The user's status
     */
    @SerializedName("status")
    private Integer status;

    /**
     * The user's birthday
     */
    @SerializedName("birthday")
    @JsonAdapter(ZeroDateDeserializer.class)
    private Date birthday;

    /**
     * The user's inscription date
     */
    @SerializedName("inscription_date")
    private Date inscriptionDate;

    /**
     * The user's gender : F or M
     */
    @SerializedName("gender")
    private String gender;

    /**
     * The url of the profil for the user on Deezer
     */
    @SerializedName("link")
    private String link;

    /**
     * The url of the user's profil picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'
     */
    @SerializedName("picture")
    private String picture;

    /**
     * The url of the user's profil picture in size small.
     */
    @SerializedName("picture_small")
    private String pictureSmall;

    /**
     * The url of the user's profil picture in size medium.
     */
    @SerializedName("picture_medium")
    private String pictureMedium;

    /**
     * The url of the user's profil picture in size big.
     */
    @SerializedName("picture_big")
    private String pictureBig;

    /**
     * The url of the user's profil picture in size xl.
     */
    @SerializedName("picture_xl")
    private String pictureXl;

    /**
     * The user's country
     */
    @SerializedName("country")
    private String country;

    /**
     * The user's language
     */
    @SerializedName("lang")
    private String lang;

    /**
     * If the user is a kid or not
     */
    @SerializedName("is_kid")
    private Boolean isKid;

    /**
     * The user's explicit content level according to his country
     */
    @SerializedName("explicit_content_level")
    private String explicitContentLevel;

    /**
     * The user's available explicit content levels according to his country. Possible values are: explicit_display, explicit_no_recommendation and explicit_hide
     */
    @SerializedName("explicit_content_levels_available")
    private List<String> explicitContentLevelsAvailable;

    /**
     * API Link to the flow of this user
     */
    @SerializedName("tracklist")
    private String tracklist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Date inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureSmall() {
        return pictureSmall;
    }

    public void setPictureSmall(String pictureSmall) {
        this.pictureSmall = pictureSmall;
    }

    public String getPictureMedium() {
        return pictureMedium;
    }

    public void setPictureMedium(String pictureMedium) {
        this.pictureMedium = pictureMedium;
    }

    public String getPictureBig() {
        return pictureBig;
    }

    public void setPictureBig(String pictureBig) {
        this.pictureBig = pictureBig;
    }

    public String getPictureXl() {
        return pictureXl;
    }

    public void setPictureXl(String pictureXl) {
        this.pictureXl = pictureXl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getKid() {
        return isKid;
    }

    public void setKid(Boolean kid) {
        isKid = kid;
    }

    public String getExplicitContentLevel() {
        return explicitContentLevel;
    }

    public void setExplicitContentLevel(String explicitContentLevel) {
        this.explicitContentLevel = explicitContentLevel;
    }

    public List<String> getExplicitContentLevelsAvailable() {
        return explicitContentLevelsAvailable;
    }

    public void setExplicitContentLevelsAvailable(List<String> explicitContentLevelsAvailable) {
        this.explicitContentLevelsAvailable = explicitContentLevelsAvailable;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", birthday=" + birthday +
                ", inscriptionDate=" + inscriptionDate +
                ", gender='" + gender + '\'' +
                ", link='" + link + '\'' +
                ", picture='" + picture + '\'' +
                ", pictureSmall='" + pictureSmall + '\'' +
                ", pictureMedium='" + pictureMedium + '\'' +
                ", pictureBig='" + pictureBig + '\'' +
                ", pictureXl='" + pictureXl + '\'' +
                ", country='" + country + '\'' +
                ", lang='" + lang + '\'' +
                ", isKid=" + isKid +
                ", explicitContentLevel='" + explicitContentLevel + '\'' +
                ", explicitContentLevelsAvailable=" + explicitContentLevelsAvailable +
                ", tracklist='" + tracklist + '\'' +
                '}';
    }
}
