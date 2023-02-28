package api.deezer.requests;

import api.deezer.http.AdvancedSearchRequest;
import api.deezer.http.SearchRequest;
import api.deezer.objects.data.AlbumData;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.PlaylistData;
import api.deezer.objects.data.RadioData;
import api.deezer.objects.data.TrackData;
import api.deezer.objects.data.UserData;
import api.deezer.utils.DeezerPropertyKeys;

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
        return new SearchRequest<>(DeezerPropertyKeys.SEARCH_ALBUM, q, AlbumData.class);
    }

    /**
     * Searches albums.
     *
     * @return list of albums.
     */
    public AdvancedSearchRequest<AlbumData> searchAlbum() {
        return new AdvancedSearchRequest<>(DeezerPropertyKeys.SEARCH_ALBUM, AlbumData.class);
    }

    /**
     * Searches artists.
     *
     * @param q artist name.
     * @return list of artists.
     */
    public SearchRequest<ArtistData> searchArtist(String q) {
        return new SearchRequest<>(DeezerPropertyKeys.SEARCH_ARTIST, q, ArtistData.class);
    }

    /**
     * Searches artists.
     *
     * @return list of artists.
     */
    public AdvancedSearchRequest<ArtistData> searchArtist() {
        return new AdvancedSearchRequest<>(DeezerPropertyKeys.SEARCH_ARTIST, ArtistData.class);
    }

    /**
     * Searches playlists.
     *
     * @param q playlist name.
     * @return list of playlists.
     */
    public SearchRequest<PlaylistData> searchPlaylist(String q) {
        return new SearchRequest<>(DeezerPropertyKeys.SEARCH_PLAYLIST, q, PlaylistData.class);
    }

    /**
     * Searches playlists.
     *
     * @return list of playlists.
     */
    public AdvancedSearchRequest<PlaylistData> searchPlaylist() {
        return new AdvancedSearchRequest<>(DeezerPropertyKeys.SEARCH_PLAYLIST, PlaylistData.class);
    }

    /**
     * Searches radio.
     *
     * @param q radio name.
     * @return list of radios.
     */
    public SearchRequest<RadioData> searchRadio(String q) {
        return new SearchRequest<>(DeezerPropertyKeys.SEARCH_RADIO, q, RadioData.class);
    }

    /**
     * Searches radio.
     *
     * @return list of radios.
     */
    public AdvancedSearchRequest<RadioData> searchRadio() {
        return new AdvancedSearchRequest<>(DeezerPropertyKeys.SEARCH_RADIO, RadioData.class);
    }

    /**
     * Searches tracks.
     *
     * @param q track name.
     * @return list of tracks.
     */
    public SearchRequest<TrackData> searchTrack(String q) {
        return new SearchRequest<>(DeezerPropertyKeys.SEARCH_TRACK, q, TrackData.class);
    }

    /**
     * Searches tracks.
     *
     * @return list of tracks.
     */
    public AdvancedSearchRequest<TrackData> searchTrack() {
        return new AdvancedSearchRequest<>(DeezerPropertyKeys.SEARCH_TRACK, TrackData.class);
    }

    /**
     * Searches users.
     *
     * @param q username.
     * @return list of users.
     */
    public SearchRequest<UserData> searchUser(String q) {
        return new SearchRequest<>(DeezerPropertyKeys.SEARCH_USER, q, UserData.class);
    }

    /**
     * Searches users.
     *
     * @return list of users.
     */
    public AdvancedSearchRequest<UserData> searchUser() {
        return new AdvancedSearchRequest<>(DeezerPropertyKeys.SEARCH_USER, UserData.class);
    }
    // TODO: 01.11.2021 search.history
}
