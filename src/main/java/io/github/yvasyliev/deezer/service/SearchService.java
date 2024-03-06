package io.github.yvasyliev.deezer.service;

import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.objects.SearchHistoryPage;
import io.github.yvasyliev.deezer.objects.SearchPage;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
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
    Page<Track, AdvancedSearchMethod<Track>> advancedSearch(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH)
    CompletableFuture<Page<Track, AdvancedSearchMethod<Track>>> advancedSearchAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    Page<Album, AdvancedSearchMethod<Album>> advancedSearchAlbum(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    CompletableFuture<Page<Album, AdvancedSearchMethod<Album>>> advancedSearchAlbumAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    Page<Artist, AdvancedSearchMethod<Artist>> advancedSearchArtist(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    CompletableFuture<Page<Artist, AdvancedSearchMethod<Artist>>> advancedSearchArtistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    Page<Playlist, AdvancedSearchMethod<Playlist>> advancedSearchPlaylist(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    CompletableFuture<Page<Playlist, AdvancedSearchMethod<Playlist>>> advancedSearchPlaylistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    Page<Radio, AdvancedSearchMethod<Radio>> advancedSearchRadio(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    CompletableFuture<Page<Radio, AdvancedSearchMethod<Radio>>> advancedSearchRadioAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    Page<Track, AdvancedSearchMethod<Track>> advancedSearchTrack(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    CompletableFuture<Page<Track, AdvancedSearchMethod<Track>>> advancedSearchTrackAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    Page<User, AdvancedSearchMethod<User>> advancedSearchUser(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    CompletableFuture<Page<User, AdvancedSearchMethod<User>>> advancedSearchUserAsync(@QueryMap Map<String, Object> queryParams);

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
