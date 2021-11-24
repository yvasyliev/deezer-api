package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * A folder object, containing playlists and albums
 */
public class Folder {
    /**
     * The folder's Deezer id
     */
    @SerializedName("id")
    private Integer id;

    /**
     * The folder's title
     */
    @SerializedName("title")
    private String title;

    /**
     * User object containing : id
     */
    @SerializedName("creator")
    private User creator;

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creator=" + creator +
                '}';
    }
}
