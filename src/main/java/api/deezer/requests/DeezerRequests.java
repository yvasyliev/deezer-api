package api.deezer.requests;

import api.deezer.properties.DeezerProperties;

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
     * Gets Deezer <i>access_token</i>.
     *
     * @return Deezer <i>access_token</i>.
     */
    protected String getAccessToken() {
        return accessToken;
    }
}
