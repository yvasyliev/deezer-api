package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * An artist object
 */
public class Artist extends ChartMember {
    /**
     * The artist's Deezer id
     */
    @SerializedName("id")
    private Integer id;

    /**
     * The artist's name
     */
    @SerializedName("name")
    private String name;

    /**
     * The url of the artist on Deezer
     */
    @SerializedName("link")
    private String link;

    /**
     * The share link of the artist on Deezer
     */
    @SerializedName("share")
    private String share;

    /**
     * The url of the artist picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'
     */
    @SerializedName("picture")
    private String picture;

    /**
     * The url of the artist picture in size small.
     */
    @SerializedName("picture_small")
    private String pictureSmall;

    /**
     * The url of the artist picture in size medium.
     */
    @SerializedName("picture_medium")
    private String pictureMedium;

    /**
     * The url of the artist picture in size big.
     */
    @SerializedName("picture_big")
    private String pictureBig;

    /**
     * The url of the artist picture in size xl.
     */
    @SerializedName("picture_xl")
    private String pictureXl;

    /**
     * The number of artist's albums
     */
    @SerializedName("nb_album")
    private Integer nbAlbum;

    /**
     * The number of artist's fans
     */
    @SerializedName("nb_fan")
    private Integer nbFan;

    /**
     * true if the artist has a smartradio
     */
    @SerializedName("radio")
    private Boolean radio;

    /**
     * API Link to the top of this artist
     */
    @SerializedName("tracklist")
    private String tracklist;

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

    public Integer getNbAlbum() {
        return nbAlbum;
    }

    public void setNbAlbum(Integer nbAlbum) {
        this.nbAlbum = nbAlbum;
    }

    public Integer getNbFan() {
        return nbFan;
    }

    public void setNbFan(Integer nbFan) {
        this.nbFan = nbFan;
    }

    public Boolean getRadio() {
        return radio;
    }

    public void setRadio(Boolean radio) {
        this.radio = radio;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", share='" + share + '\'' +
                ", picture='" + picture + '\'' +
                ", pictureSmall='" + pictureSmall + '\'' +
                ", pictureMedium='" + pictureMedium + '\'' +
                ", pictureBig='" + pictureBig + '\'' +
                ", pictureXl='" + pictureXl + '\'' +
                ", nbAlbum=" + nbAlbum +
                ", nbFan=" + nbFan +
                ", radio=" + radio +
                ", tracklist='" + tracklist + '\'' +
                "} " + super.toString();
    }
}
