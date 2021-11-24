package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * A radio object
 */
public class Radio {
    /**
     * The radio deezer ID
     */
    @SerializedName("id")
    private Integer id;

    /**
     * The radio title
     */
    @SerializedName("title")
    private String title;

    /**
     * The radio title
     */
    @SerializedName("description")
    private String description;

    /**
     * The share link of the radio on Deezer
     */
    @SerializedName("share")
    private String share;

    /**
     * The url of the radio picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'
     */
    @SerializedName("picture")
    private String picture;

    /**
     * The url of the radio picture in size small.
     */
    @SerializedName("picture_small")
    private String pictureSmall;

    /**
     * The url of the radio picture in size medium.
     */
    @SerializedName("picture_medium")
    private String pictureMedium;

    /**
     * The url of the radio picture in size big.
     */
    @SerializedName("picture_big")
    private String pictureBig;

    /**
     * The url of the radio picture in size xl.
     */
    @SerializedName("picture_xl")
    private String pictureXl;

    /**
     * API Link to the tracklist of this radio
     */
    @SerializedName("tracklist")
    private String tracklist;

    @SerializedName("md5_image")
    private String md5Image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
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

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public String getMd5Image() {
        return md5Image;
    }

    public void setMd5Image(String md5Image) {
        this.md5Image = md5Image;
    }

    @Override
    public String toString() {
        return "Radio{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", share='" + share + '\'' +
                ", picture='" + picture + '\'' +
                ", pictureSmall='" + pictureSmall + '\'' +
                ", pictureMedium='" + pictureMedium + '\'' +
                ", pictureBig='" + pictureBig + '\'' +
                ", pictureXl='" + pictureXl + '\'' +
                ", tracklist='" + tracklist + '\'' +
                ", md5Image='" + md5Image + '\'' +
                '}';
    }
}
