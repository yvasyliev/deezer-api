package api.deezer.requests;

import api.deezer.exceptions.DeezerException;
import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.objects.DeezerAccessToken;
import api.deezer.objects.Permission;
import api.deezer.utils.DeezerProperties;
import api.deezer.utils.ParamUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Authorization requests.
 */
public class AuthRequests extends DeezerRequests {

    /**
     * Gets login url for current application.
     *
     * @param appId       application ID.
     * @param redirectUri redirect URI.
     * @param perms       permissions.
     * @return full login URL.
     * @throws DeezerException if errors occur.
     */
    public String getLoginUrl(long appId, String redirectUri, Permission... perms) throws DeezerException {
        return getLoginUrl(appId, redirectUri, Arrays.asList(perms));
    }

    /**
     * Gets login url for current application.
     *
     * @param appId       application ID.
     * @param redirectUri redirect URI.
     * @param perms       permissions.
     * @return full login URL.
     * @throws DeezerException if errors occur.
     */
    public String getLoginUrl(long appId, String redirectUri, List<Permission> perms) throws DeezerException {
        return property(
                "auth.url",
                appId,
                ParamUtils.encode(redirectUri),
                ParamUtils.encode(perms)
        );
    }

    /**
     * Gets Deezer <i>access_token</i>.
     *
     * @param appId  application ID.
     * @param secret application secret string.
     * @param code   login code.
     * @return Deezer <i>access_token</i>.
     */
    public DeezerRequest<DeezerAccessToken> getAccessToken(long appId, String secret, String code) {
        return new DeezerGetRequest<>(DeezerProperties.getProperty("auth.access_token"), DeezerAccessToken.class)
                .addParam("app_id", String.valueOf(appId))
                .addParam("secret", secret)
                .addParam("code", code)
                .addParam("output", "json");
    }
}
