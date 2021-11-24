package api.deezer.objects;

import api.deezer.objects.data.Data;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * An album object
 */
public class Album extends ChartMember {
    /**
     * The Deezer album id
     */
    @SerializedName("id")
    private Integer id;

    /**
     * The album title
     */
    @SerializedName("title")
    private String title;

    /**
     * The album UPC
     */
    @SerializedName("upc")
    private String upc;

    /**
     * The url of the album on Deezer
     */
    @SerializedName("link")
    private String link;

    /**
     * The share link of the album on Deezer
     */
    @SerializedName("share")
    private String share;

    /**
     * The url of the album's cover. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'
     */
    @SerializedName("cover")
    private String cover;

    /**
     * The url of the album's cover in size small.
     */
    @SerializedName("cover_small")
    private String coverSmall;

    /**
     * The url of the album's cover in size medium.
     */
    @SerializedName("cover_medium")
    private String coverMedium;

    /**
     * The url of the album's cover in size big.
     */
    @SerializedName("cover_big")
    private String coverBig;

    /**
     * The url of the album's cover in size xl.
     */
    @SerializedName("cover_xl")
    private String coverXl;

    @SerializedName("md5_image")
    private String md5Image;

    /**
     * The album's first genre id (You should use the genre list instead). NB : -1 for not found
     */
    @SerializedName("genre_id")
    private Integer genreId;

    /**
     * List of genre object
     */
    @SerializedName("genres")
    private Data<Genre> genres;

    /**
     * The album's label name
     */
    @SerializedName("label")
    private String label;

    /**
     * The number of album's tracks
     */
    @SerializedName("nb_tracks")
    private Integer nbTracks;

    /**
     * The album's duration (seconds)
     */
    @SerializedName("duration")
    private Integer duration;

    /**
     * The number of album's Fans
     */
    @SerializedName("fans")
    private Integer fans;

    /**
     * The album's rate
     */
    @SerializedName("rating")
    private Integer rating;

    /**
     * The album's release date
     */
    @SerializedName("release_date")
    private Date releaseDate;

    /**
     * The record type of the album (EP / ALBUM / etc..)
     */
    @SerializedName("record_type")
    private String recordType;

    @SerializedName("available")
    private Boolean isAvailable;

    /**
     * Return an alternative album object if the current album is not available
     */
    @SerializedName("alternative")
    private Album alternative;

    /**
     * API Link to the tracklist of this album
     */
    @SerializedName("tracklist")
    private String tracklist;

    /**
     * Whether the album contains explicit lyrics
     */
    @SerializedName("explicit_lyrics")
    private Boolean containsExplicitLyrics;

    /**
     * The explicit content lyrics values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 4:Partially Explicit (Album "lyrics" only); 5:Partially Unknown (Album "lyrics" only); 6:No Advice Available; 7:Partially No Advice Available (Album "lyrics" only))
     */
    @SerializedName("explicit_content_lyrics")
    private Integer explicitContentLyrics;

    /**
     * The explicit cover values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 4:Partially Explicit (Album "lyrics" only); 5:Partially Unknown (Album "lyrics" only); 6:No Advice Available; 7:Partially No Advice Available (Album "lyrics" only))
     */
    @SerializedName("explicit_content_cover")
    private Integer explicitContentCover;

    /**
     * Return a list of contributors on the album
     */
    @SerializedName("contributors")
    private List<Contributor> contributors;

    /**
     * {@link Artist} object containing : id, name, picture, picture_small, picture_medium, picture_big, picture_xl
     */
    @SerializedName("artist")
    private Artist artist;

    /**
     * list of {@link Track}
     */
    @SerializedName("tracks")
    private Data<Track> tracks;

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

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public String getCoverMedium() {
        return coverMedium;
    }

    public void setCoverMedium(String coverMedium) {
        this.coverMedium = coverMedium;
    }

    public String getCoverBig() {
        return coverBig;
    }

    public void setCoverBig(String coverBig) {
        this.coverBig = coverBig;
    }

    public String getCoverXl() {
        return coverXl;
    }

    public void setCoverXl(String coverXl) {
        this.coverXl = coverXl;
    }

    public String getMd5Image() {
        return md5Image;
    }

    public void setMd5Image(String md5Image) {
        this.md5Image = md5Image;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public Data<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Data<Genre> genres) {
        this.genres = genres;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getNbTracks() {
        return nbTracks;
    }

    public void setNbTracks(Integer nbTracks) {
        this.nbTracks = nbTracks;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Album getAlternative() {
        return alternative;
    }

    public void setAlternative(Album alternative) {
        this.alternative = alternative;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
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

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Data<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Data<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", upc='" + upc + '\'' +
                ", link='" + link + '\'' +
                ", share='" + share + '\'' +
                ", cover='" + cover + '\'' +
                ", coverSmall='" + coverSmall + '\'' +
                ", coverMedium='" + coverMedium + '\'' +
                ", coverBig='" + coverBig + '\'' +
                ", coverXl='" + coverXl + '\'' +
                ", md5Image='" + md5Image + '\'' +
                ", genreId=" + genreId +
                ", genres=" + genres +
                ", label='" + label + '\'' +
                ", nbTracks=" + nbTracks +
                ", duration=" + duration +
                ", fans=" + fans +
                ", rating=" + rating +
                ", releaseDate=" + releaseDate +
                ", recordType='" + recordType + '\'' +
                ", isAvailable=" + isAvailable +
                ", alternative=" + alternative +
                ", tracklist='" + tracklist + '\'' +
                ", containsExplicitLyrics=" + containsExplicitLyrics +
                ", explicitContentLyrics=" + explicitContentLyrics +
                ", explicitContentCover=" + explicitContentCover +
                ", contributors=" + contributors +
                ", artist=" + artist +
                ", tracks=" + tracks +
                "} " + super.toString();
    }
}
