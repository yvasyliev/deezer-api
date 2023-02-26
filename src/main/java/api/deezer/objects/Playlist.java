package api.deezer.objects;

import api.deezer.objects.data.DeezerData;
import com.google.gson.annotations.SerializedName;

/**
 * A radio object
 */
public class Playlist extends ChartMember {
    /**
     * The playlist's Deezer id
     */
    @SerializedName("id")
    private Long id;

    /**
     * The playlist's title
     */
    @SerializedName("title")
    private String title;

    /**
     * The playlist description
     */
    @SerializedName("description")
    private String description;

    /**
     * The playlist's duration (seconds)
     */
    @SerializedName("duration")
    private Integer duration;

    /**
     * If the playlist is public or not
     */
    @SerializedName("public")
    private Boolean isPublic;

    /**
     * If the playlist is the love tracks playlist
     */
    @SerializedName("is_loved_track")
    private Boolean isLovedTrack;

    /**
     * If the playlist is collaborative or not
     */
    @SerializedName("collaborative")
    private Boolean isCollaborative;

    /**
     * The playlist's rate
     */
    @SerializedName("rating")
    private Integer rating;

    /**
     * Nb tracks in the playlist
     */
    @SerializedName("nb_tracks")
    private Integer nbTracks;

    /**
     * Nb tracks not seen
     */
    @SerializedName("unseen_track_count")
    private Integer unseenTrackCount;

    /**
     * The number of playlist's fans
     */
    @SerializedName("fans")
    private Integer fans;

    /**
     * The url of the playlist on Deezer
     */
    @SerializedName("link")
    private String link;

    /**
     * The share link of the playlist on Deezer
     */
    @SerializedName("share")
    private String share;

    /**
     * The url of the playlist's cover. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'
     */
    @SerializedName("picture")
    private String picture;

    /**
     * The url of the playlist's cover in size small.
     */
    @SerializedName("picture_small")
    private String pictureSmall;

    /**
     * The url of the playlist's cover in size medium.
     */
    @SerializedName("picture_medium")
    private String pictureMedium;

    /**
     * The url of the playlist's cover in size big.
     */
    @SerializedName("picture_big")
    private String pictureBig;

    /**
     * The url of the playlist's cover in size xl.
     */
    @SerializedName("picture_xl")
    private String pictureXl;

    /**
     * The checksum for the track list
     */
    @SerializedName("checksum")
    private String checksum;

    /**
     * {@link User} object containing : id, name
     */
    @SerializedName("creator")
    private User creator;

    /**
     * list of {@link Track}
     */
    @SerializedName("tracks")
    private DeezerData<Track> tracks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Boolean getLovedTrack() {
        return isLovedTrack;
    }

    public void setLovedTrack(Boolean lovedTrack) {
        isLovedTrack = lovedTrack;
    }

    public Boolean getIsCollaborative() {
        return isCollaborative;
    }

    public void setIsCollaborative(Boolean isCollaborative) {
        this.isCollaborative = isCollaborative;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getNbTracks() {
        return nbTracks;
    }

    public void setNbTracks(Integer nbTracks) {
        this.nbTracks = nbTracks;
    }

    public Integer getUnseenTrackCount() {
        return unseenTrackCount;
    }

    public void setUnseenTrackCount(Integer unseenTrackCount) {
        this.unseenTrackCount = unseenTrackCount;
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

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public DeezerData<Track> getTracks() {
        return tracks;
    }

    public void setTracks(DeezerData<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", isPublic=" + isPublic +
                ", isLovedTrack=" + isLovedTrack +
                ", collaborative=" + isCollaborative +
                ", rating=" + rating +
                ", nbTracks=" + nbTracks +
                ", unseenTrackCount=" + unseenTrackCount +
                ", fans=" + fans +
                ", link='" + link + '\'' +
                ", share='" + share + '\'' +
                ", picture='" + picture + '\'' +
                ", pictureSmall='" + pictureSmall + '\'' +
                ", pictureMedium='" + pictureMedium + '\'' +
                ", pictureBig='" + pictureBig + '\'' +
                ", pictureXl='" + pictureXl + '\'' +
                ", checksum='" + checksum + '\'' +
                ", creator=" + creator +
                ", tracks=" + tracks +
                "} " + super.toString();
    }
}
