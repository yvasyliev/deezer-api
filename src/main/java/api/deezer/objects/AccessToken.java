package api.deezer.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Deezer access_token. Returned by {@link api.deezer.requests.AuthRequests#getAccessToken(long, String, String)}
 */
public class AccessToken {
    /**
     * Access token string.
     */
    @SerializedName("access_token")
    private String accessToken;

    /**
     * Access token availability time.
     */
    @SerializedName("expires")
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
