package io.github.yvasyliev.deezer.service;

import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.AdvancedSearchPage;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.objects.SearchHistoryPage;
import io.github.yvasyliev.deezer.objects.SearchPage;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.v2.methods.search.SearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface SearchService extends DeezerService {
    String SEARCH = "/search";
    String SEARCH_ALBUM = "/search/album";
    String SEARCH_ARTIST = "/search/artist";
    String SEARCH_HISTORY = "/search/history";
    String SEARCH_PLAYLIST = "/search/playlist";
    String SEARCH_RADIO = "/search/radio";
    String SEARCH_TRACK = "/search/track";
    String SEARCH_USER = "/search/user";

    @RequestLine(GET + SEARCH)
    AdvancedSearchPage<Track> advancedSearch(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH)
    CompletableFuture<AdvancedSearchPage<Track>> advancedSearchAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    AdvancedSearchPage<Album> advancedSearchAlbum(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    CompletableFuture<AdvancedSearchPage<Album>> advancedSearchAlbumAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    AdvancedSearchPage<Artist> advancedSearchArtist(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    CompletableFuture<AdvancedSearchPage<Artist>> advancedSearchArtistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    AdvancedSearchPage<Playlist> advancedSearchPlaylist(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    CompletableFuture<AdvancedSearchPage<Playlist>> advancedSearchPlaylistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    AdvancedSearchPage<Radio> advancedSearchRadio(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    CompletableFuture<AdvancedSearchPage<Radio>> advancedSearchRadioAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    AdvancedSearchPage<Track> advancedSearchTrack(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    CompletableFuture<AdvancedSearchPage<Track>> advancedSearchTrackAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    AdvancedSearchPage<User> advancedSearchUser(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    CompletableFuture<AdvancedSearchPage<User>> advancedSearchUserAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH)
    SearchPage<Track> search(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH)
    CompletableFuture<SearchPage<Track>> searchAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    Page<Album, SearchMethod<Album>> searchAlbum(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    CompletableFuture<Page<Album, SearchMethod<Album>>> searchAlbumAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    SearchPage<Artist> searchArtist(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    CompletableFuture<SearchPage<Artist>> searchArtistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_HISTORY)
    SearchHistoryPage searchHistory(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_HISTORY)
    CompletableFuture<SearchHistoryPage> searchHistoryAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    SearchPage<Playlist> searchPlaylist(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    CompletableFuture<SearchPage<Playlist>> searchPlaylistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    SearchPage<Radio> searchRadio(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    CompletableFuture<SearchPage<Radio>> searchRadioAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    SearchPage<Track> searchTrack(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    CompletableFuture<SearchPage<Track>> searchTrackAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    SearchPage<User> searchUser(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    CompletableFuture<SearchPage<User>> searchUserAsync(@QueryMap Map<String, Object> queryParams);
}
