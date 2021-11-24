package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * A genre object
 */
public class Genre {
    /**
     * The editorial's Deezer id
     */
    @SerializedName("id")
    private Integer id;

    /**
     * The editorial's name
     */
    @SerializedName("name")
    private String name;

    /**
     * The url of the genre picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'
     */
    @SerializedName("picture")
    private String picture;

    /**
     * The url of the genre picture in size small.
     */
    @SerializedName("picture_small")
    private String pictureSmall;

    /**
     * The url of the genre picture in size medium.
     */
    @SerializedName("picture_medium")
    private String pictureMedium;

    /**
     * The url of the genre picture in size big.
     */
    @SerializedName("picture_big")
    private String pictureBig;

    /**
     * The url of the genre picture in size xl.
     */
    @SerializedName("picture_xl")
    private String pictureXl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", pictureSmall='" + pictureSmall + '\'' +
                ", pictureMedium='" + pictureMedium + '\'' +
                ", pictureBig='" + pictureBig + '\'' +
                ", pictureXl='" + pictureXl + '\'' +
                '}';
    }
}
