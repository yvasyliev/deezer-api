package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * A podcast object
 */
public class Podcast extends ChartMember {
    /**
     * The podcast's Deezer id
     */
    @SerializedName("id")
    private Integer id;

    /**
     * The podcast's title
     */
    @SerializedName("title")
    private String title;

    /**
     * The podcast's description
     */
    @SerializedName("description")
    private String description;

    /**
     * If the podcast is available or not
     */
    @SerializedName("available")
    private Boolean available;

    /**
     * The playlist's rate
     */
    @SerializedName("rating")
    private Integer rating;

    /**
     * The number of playlist's fans
     */
    @SerializedName("fans")
    private Integer fans;

    /**
     * The url of the podcast on Deezer
     */
    @SerializedName("link")
    private String link;

    /**
     * The share link of the podcast on Deezer
     */
    @SerializedName("share")
    private String share;

    /**
     * The url of the podcast's cover.
     */
    @SerializedName("picture")
    private String picture;

    /**
     * The url of the podcast's cover in size small.
     */
    @SerializedName("picture_small")
    private String picture_small;

    /**
     * The url of the podcast's cover in size medium.
     */
    @SerializedName("picture_medium")
    private String picture_medium;

    /**
     * The url of the podcast's cover in size big.
     */
    @SerializedName("picture_big")
    private String picture_big;

    /**
     * The url of the podcast's cover in size xl.
     */
    @SerializedName("picture_xl")
    private String picture_xl;

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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getPicture_small() {
        return picture_small;
    }

    public void setPicture_small(String picture_small) {
        this.picture_small = picture_small;
    }

    public String getPicture_medium() {
        return picture_medium;
    }

    public void setPicture_medium(String picture_medium) {
        this.picture_medium = picture_medium;
    }

    public String getPicture_big() {
        return picture_big;
    }

    public void setPicture_big(String picture_big) {
        this.picture_big = picture_big;
    }

    public String getPicture_xl() {
        return picture_xl;
    }

    public void setPicture_xl(String picture_xl) {
        this.picture_xl = picture_xl;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", rating=" + rating +
                ", fans=" + fans +
                ", link='" + link + '\'' +
                ", share='" + share + '\'' +
                ", picture='" + picture + '\'' +
                ", picture_small='" + picture_small + '\'' +
                ", picture_medium='" + picture_medium + '\'' +
                ", picture_big='" + picture_big + '\'' +
                ", picture_xl='" + picture_xl + '\'' +
                "} " + super.toString();
    }
}
