package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * A track object
 */
public class Track extends ChartMember {
    /**
     * The track's Deezer id
     */
    @SerializedName("id")
    private Long id;

    /**
     * true if the track is readable in the player for the current user
     */
    @SerializedName("readable")
    private Boolean isReadable;

    /**
     * The track's fulltitle
     */
    @SerializedName("title")
    private String title;

    /**
     * The track's short title
     */
    @SerializedName("title_short")
    private String titleShort;

    /**
     * The track version
     */
    @SerializedName("title_version")
    private String titleVersion;

    /**
     * The track unseen status
     */
    @SerializedName("unseen")
    private Boolean isUnseen;

    /**
     * The track isrc
     */
    @SerializedName("isrc")
    private String isrc;

    /**
     * The url of the track on Deezer
     */
    @SerializedName("link")
    private String link;

    /**
     * The share link of the track on Deezer
     */
    @SerializedName("share")
    private String share;

    /**
     * The track's duration in seconds
     */
    @SerializedName("duration")
    private Integer duration;

    /**
     * The position of the track in its album
     */
    @SerializedName("track_position")
    private Integer trackPosition;

    /**
     * The track's album's disk number
     */
    @SerializedName("disk_number")
    private Integer diskNumber;

    /**
     * The track's Deezer rank
     */
    @SerializedName("rank")
    private Integer rank;

    /**
     * The track's release date
     */
    @SerializedName("release_date")
    private Date releaseDate;

    /**
     * Whether the track contains explicit lyrics
     */
    @SerializedName("explicit_lyrics")
    private Boolean containsExplicitLyrics;

    /**
     * The explicit content lyrics values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 6:No Advice Available)
     */
    @SerializedName("explicit_content_lyrics")
    private Integer explicitContentLyrics;

    /**
     * The explicit cover value (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 6:No Advice Available)
     */
    @SerializedName("explicit_content_cover")
    private Integer explicitContentCover;

    /**
     * The url of track's preview file. This file contains the first 30 seconds of the track
     */
    @SerializedName("preview")
    private String preview;

    /**
     * Beats per minute
     */
    @SerializedName("bpm")
    private Float bpm;

    /**
     * Signal strength
     */
    @SerializedName("gain")
    private Float gain;

    /**
     * List of countries where the track is available
     */
    @SerializedName("available_countries")
    private List<String> availableCountries;

    /**
     * Return an alternative readable track if the current track is not readable
     */
    @SerializedName("alternative")
    private Track alternative;

    /**
     * Return a list of contributors on the track
     */
    @SerializedName("contributors")
    private List<Artist> contributors;

    /**
     *
     */
    @SerializedName("md5_image")
    private String md5Image;

    /**
     * {@link Artist} object containing : id, name, link, share, picture, picture_small, picture_medium, picture_big, picture_xl, nb_album, nb_fan, radio, tracklist, role
     */
    @SerializedName("artist")
    private Artist artist;

    /**
     * {@link Album} object containing : id, title, link, cover, cover_small, cover_medium, cover_big, cover_xl, release_date
     */
    @SerializedName("album")
    private Album album;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsReadable() {
        return isReadable;
    }

    public void setIsReadable(Boolean isReadable) {
        this.isReadable = isReadable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
    }

    public String getTitleVersion() {
        return titleVersion;
    }

    public void setTitleVersion(String titleVersion) {
        this.titleVersion = titleVersion;
    }

    public Boolean getIsUnseen() {
        return isUnseen;
    }

    public void setIsUnseen(Boolean isUnseen) {
        this.isUnseen = isUnseen;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTrackPosition() {
        return trackPosition;
    }

    public void setTrackPosition(Integer trackPosition) {
        this.trackPosition = trackPosition;
    }

    public Integer getDiskNumber() {
        return diskNumber;
    }

    public void setDiskNumber(Integer diskNumber) {
        this.diskNumber = diskNumber;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getExplicitLyrics() {
        return containsExplicitLyrics;
    }

    public void setExplicitLyrics(Boolean containsExplicitLyrics) {
        this.containsExplicitLyrics = containsExplicitLyrics;
    }

    public Integer getExplicitContentLyrics() {
        return explicitContentLyrics;
    }

    public void setExplicitContentLyrics(Integer explicitContentLyrics) {
        this.explicitContentLyrics = explicitContentLyrics;
    }

    public Integer getExplicitContentCover() {
        return explicitContentCover;
    }

    public void setExplicitContentCover(Integer explicitContentCover) {
        this.explicitContentCover = explicitContentCover;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Float getBpm() {
        return bpm;
    }

    public void setBpm(Float bpm) {
        this.bpm = bpm;
    }

    public Float getGain() {
        return gain;
    }

    public void setGain(Float gain) {
        this.gain = gain;
    }

    public List<String> getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(List<String> availableCountries) {
        this.availableCountries = availableCountries;
    }

    public Track getAlternative() {
        return alternative;
    }

    public void setAlternative(Track alternative) {
        this.alternative = alternative;
    }

    public List<Artist> getContributors() {
        return contributors;
    }

    public void setContributors(List<Artist> contributors) {
        this.contributors = contributors;
    }

    public String getMd5Image() {
        return md5Image;
    }

    public void setMd5Image(String md5Image) {
        this.md5Image = md5Image;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", readable=" + isReadable +
                ", title='" + title + '\'' +
                ", titleShort='" + titleShort + '\'' +
                ", titleVersion='" + titleVersion + '\'' +
                ", unseen=" + isUnseen +
                ", isrc='" + isrc + '\'' +
                ", link='" + link + '\'' +
                ", share='" + share + '\'' +
                ", duration=" + duration +
                ", trackPosition=" + trackPosition +
                ", diskNumber=" + diskNumber +
                ", rank=" + rank +
                ", releaseDate=" + releaseDate +
                ", containsExplicitLyrics=" + containsExplicitLyrics +
                ", explicitContentLyrics=" + explicitContentLyrics +
                ", explicitContentCover=" + explicitContentCover +
                ", preview='" + preview + '\'' +
                ", bpm=" + bpm +
                ", gain=" + gain +
                ", availableCountries=" + availableCountries +
                ", alternative=" + alternative +
                ", contributors=" + contributors +
                ", md5Image='" + md5Image + '\'' +
                ", artist=" + artist +
                ", album=" + album +
                "} " + super.toString();
    }
}
