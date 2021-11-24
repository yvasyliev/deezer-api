package api.deezer.requests;

import api.deezer.properties.DeezerProperties;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Deezer requests.
 */
public abstract class DeezerRequests {
    /**
     * Deezer <i>access_token</i>.
     */
    private String accessToken;

    public DeezerRequests() {
    }

    public DeezerRequests(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Gets property from {@link DeezerProperties} and formats string with arguments.
     *
     * @param property property key.
     * @param args     format arguments.
     * @return formatted string.
     */
    protected String property(String property, Object... args) {
        return String.format(
                DeezerProperties.getProperty(property),
                args
        );
    }

    /**
     * Creates params map.
     *
     * @param entries params.
     * @return params map.
     */
    @SafeVarargs
    protected final Map<String, String> params(Map.Entry<String, String>... entries) {
        return Arrays.stream(entries).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Creates param.
     *
     * @param key   param key.
     * @param value param value.
     * @return param entry.
     */
    public Map.Entry<String, String> entry(String key, String value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    /**
     * Gets Deezer <i>access_token</i>.
     *
     * @return Deezer <i>access_token</i>.
     */
    protected String getAccessToken() {
        return accessToken;
    }
}
