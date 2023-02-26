package api.deezer;

import api.deezer.objects.DeezerAccessToken;
import api.deezer.requests.AlbumRequests;
import api.deezer.requests.ArtistRequests;
import api.deezer.requests.AuthRequests;
import api.deezer.requests.ChartRequests;
import api.deezer.requests.EditorialRequests;
import api.deezer.requests.GenreRequests;
import api.deezer.requests.InfosRequests;
import api.deezer.requests.OptionsRequests;
import api.deezer.requests.PlaylistRequests;
import api.deezer.requests.RadioRequests;
import api.deezer.requests.SearchRequests;
import api.deezer.requests.TrackRequests;
import api.deezer.requests.UserRequests;

/**
 * Deezer APIs.
 */
public class DeezerApi {
    /**
     * Deezer <i>access_token</i>.
     */
    private String accessToken;

    public DeezerApi() {
    }

    public DeezerApi(String accessToken) {
        this.accessToken = accessToken;
    }

    public DeezerApi(DeezerAccessToken deezerAccessToken) {
        this(deezerAccessToken.getAccessToken());
    }

    /**
     * Album requests.
     *
     * @return album requests.
     */
    public AlbumRequests album() {
        return new AlbumRequests();
    }

    /**
     * Artist requests.
     *
     * @return artist requests.
     */
    public ArtistRequests artist() {
        return new ArtistRequests();
    }

    /**
     * Chart requests
     *
     * @return chart requests.
     */
    public ChartRequests chart() {
        return new ChartRequests();
    }

    /**
     * Editorial requests.
     *
     * @return editorial requests.
     */
    public EditorialRequests editorial() {
        return new EditorialRequests();
    }

    /**
     * Genre requests.
     *
     * @return genre requests.
     */
    public GenreRequests genre() {
        return new GenreRequests();
    }

    /**
     * Infos requests.
     *
     * @return infos requests.
     */
    public InfosRequests infos() {
        return new InfosRequests();
    }

    /**
     * Options requests.
     *
     * @return options requests.
     */
    public OptionsRequests options() {
        return new OptionsRequests();
    }

    /**
     * Playlist requests.
     *
     * @return playlist requests.
     */
    public PlaylistRequests playlist() {
        validateAccessToken();
        return new PlaylistRequests(accessToken);
    }

    /**
     * Radio requests.
     *
     * @return radio requests.
     */
    public RadioRequests radio() {
        return new RadioRequests();
    }

    /**
     * Auth requests.
     *
     * @return auth requests.
     */
    public AuthRequests auth() {
        return new AuthRequests();
    }

    /**
     * Search requests.
     *
     * @return search requests.
     */
    public SearchRequests search() {
        return new SearchRequests();
    }

    /**
     * Track requests.
     *
     * @return track requests.
     */
    public TrackRequests track() {
        validateAccessToken();
        return new TrackRequests(accessToken);
    }

    /**
     * User requests.
     *
     * @return user requests.
     */
    public UserRequests user() {
        validateAccessToken();
        return new UserRequests(accessToken);
    }

    public DeezerApi setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public DeezerApi setAccessToken(DeezerAccessToken deezerAccessToken) {
        return setAccessToken(deezerAccessToken.getAccessToken());
    }

    public void validateAccessToken() {
        if (accessToken == null) {
            throw new IllegalStateException("'accessToken' is not set.");
        }
    }
}
