package api.deezer.requests;

import api.deezer.http.AdvancedSearchRequest;
import api.deezer.http.SearchRequest;
import api.deezer.objects.data.AlbumData;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.PlaylistData;
import api.deezer.objects.data.RadioData;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;
import api.deezer.utils.DeezerProperties;

/**
 * Requests related to search.
 */
public class SearchRequests extends DeezerRequests {
    /**
     * Searches albums.
     *
     * @param q album name.
     * @return list of albums.
     */
    public SearchRequest<AlbumData> searchAlbum(String q) {
        return new SearchRequest<>(DeezerProperties.getProperty("search.album"), q, AlbumData.class);
    }

    /**
     * Searches albums.
     *
     * @return list of albums.
     */
    public AdvancedSearchRequest<AlbumData> searchAlbum() {
        return new AdvancedSearchRequest<>(DeezerProperties.getProperty("search.album"), AlbumData.class);
    }

    /**
     * Searches artists.
     *
     * @param q artist name.
     * @return list of artists.
     */
    public SearchRequest<ArtistData> searchArtist(String q) {
        return new SearchRequest<>(DeezerProperties.getProperty("search.artist"), q, ArtistData.class);
    }

    /**
     * Searches artists.
     *
     * @return list of artists.
     */
    public AdvancedSearchRequest<ArtistData> searchArtist() {
        return new AdvancedSearchRequest<>(DeezerProperties.getProperty("search.artist"), ArtistData.class);
    }

    /**
     * Searches playlists.
     *
     * @param q playlist name.
     * @return list of playlists.
     */
    public SearchRequest<PlaylistData> searchPlaylist(String q) {
        return new SearchRequest<>(DeezerProperties.getProperty("search.playlist"), q, PlaylistData.class);
    }

    /**
     * Searches playlists.
     *
     * @return list of playlists.
     */
    public AdvancedSearchRequest<PlaylistData> searchPlaylist() {
        return new AdvancedSearchRequest<>(DeezerProperties.getProperty("search.playlist"), PlaylistData.class);
    }

    /**
     * Searches radio.
     *
     * @param q radio name.
     * @return list of radios.
     */
    public SearchRequest<RadioData> searchRadio(String q) {
        return new SearchRequest<>(DeezerProperties.getProperty("search.radio"), q, RadioData.class);
    }

    /**
     * Searches radio.
     *
     * @return list of radios.
     */
    public AdvancedSearchRequest<RadioData> searchRadio() {
        return new AdvancedSearchRequest<>(DeezerProperties.getProperty("search.radio"), RadioData.class);
    }

    /**
     * Searches tracks.
     *
     * @param q track name.
     * @return list of tracks.
     */
    public SearchRequest<TrackData> searchTrack(String q) {
        return new SearchRequest<>(DeezerProperties.getProperty("search.track"), q, TrackData.class);
    }

    /**
     * Searches tracks.
     *
     * @return list of tracks.
     */
    public AdvancedSearchRequest<TrackData> searchTrack() {
        return new AdvancedSearchRequest<>(DeezerProperties.getProperty("search.track"), TrackData.class);
    }

    /**
     * Searches users.
     *
     * @param q username.
     * @return list of users.
     */
    public SearchRequest<UserData> searchUser(String q) {
        return new SearchRequest<>(DeezerProperties.getProperty("search.user"), q, UserData.class);
    }

    /**
     * Searches users.
     *
     * @return list of users.
     */
    public AdvancedSearchRequest<UserData> searchUser() {
        return new AdvancedSearchRequest<>(DeezerProperties.getProperty("search.user"), UserData.class);
    }
    // TODO: 01.11.2021 search.history
}
