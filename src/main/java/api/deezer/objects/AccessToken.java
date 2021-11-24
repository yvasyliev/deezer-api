package api.deezer.objects;

/**
 * Deezer access_token. Returned by {@link api.deezer.requests.AuthRequests#getAccessToken(int, String, String)}
 */
public class AccessToken {
    /**
     * Access token string.
     */
    private String accessToken;

    /**
     * Access token availability time.
     */
    private Integer expires;

    public String getAccessToken() {
        return accessToken;
    }

    public AccessToken setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Integer getExpires() {
        return expires;
    }

    public AccessToken setExpires(Integer expires) {
        this.expires = expires;
        return this;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expires=" + expires +
                '}';
    }
}
