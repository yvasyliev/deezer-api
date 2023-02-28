package api.deezer.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parameter utilities.
 */
public final class ParamUtils {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String APP_ID = "app_id";
    public static final String SECRET = "secret";
    public static final String CODE = "code";
    public static final String OUTPUT = "output";
    public static final String JSON = "json";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String COLLABORATIVE = "collaborative";
    public static final String SONGS = "songs";
    public static final String ORDER = "order";
    public static final String ALBUM_ID = "album_id";
    public static final String ARTIST_ID = "artist_id";
    public static final String USER_ID = "user_id";
    public static final String PLAYLIST_ID = "playlist_id";
    public static final String PODCAST_ID = "podcast_id";
    public static final String RADIO_ID = "radio_id";
    public static final String TRACK_ID = "track_id";
    public static final String ME = "me";
    public static final String Q = "q";
    public static final String STRICT = "strict";
    public static final String ON = "on";
    public static final String ARTIST = "artist";
    public static final String ALBUM = "album";
    public static final String TRACK = "track";
    public static final String LABEL = "label";
    public static final String DUR_MIN = "dur_min";
    public static final String DUR_MAX = "dur_max";
    public static final String BPM_MIN = "bpm_min";
    public static final String BPM_MAX = "bpm_max";
    public static final String QUOTE = "\"";
    public static final String COLON = ":";
    public static final String SPACE = " ";
    public static final String FILE = "file";
    public static final String LIMIT = "limit";
    public static final String INDEX = "index";

    private ParamUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    /**
     * Encodes a string into URL-encoded string.
     *
     * @param s source string.
     * @return URL-encoded string.
     */
    public static String encode(String s) {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Encodes a list of objects into URL-encoded CSV.
     *
     * @param list list of objects.
     * @param <T>  objects' type.
     * @return URL-encoded string.
     */
    public static <T> String encode(List<T> list) {
        return list.stream()
                .map(String::valueOf)
                .map(ParamUtils::encode)
                .collect(Collectors.joining(","));
    }
}
