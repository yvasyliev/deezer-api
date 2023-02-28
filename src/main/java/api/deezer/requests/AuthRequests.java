package api.deezer.requests;

import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.objects.DeezerAccessToken;
import api.deezer.objects.Permission;
import api.deezer.utils.DeezerPropertyKeys;
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
     */
    public String getLoginUrl(long appId, String redirectUri, Permission... perms) {
        return getLoginUrl(appId, redirectUri, Arrays.asList(perms));
    }

    /**
     * Gets login url for current application.
     *
     * @param appId       application ID.
     * @param redirectUri redirect URI.
     * @param perms       permissions.
     * @return full login URL.
     */
    public String getLoginUrl(long appId, String redirectUri, List<Permission> perms) {
        return property(
                DeezerPropertyKeys.AUTH_URL,
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
        return new DeezerGetRequest<>(property(DeezerPropertyKeys.AUTH_ACCESS_TOKEN), DeezerAccessToken.class)
                .addParam(ParamUtils.APP_ID, String.valueOf(appId))
                .addParam(ParamUtils.SECRET, secret)
                .addParam(ParamUtils.CODE, code)
                .addParam(ParamUtils.OUTPUT, ParamUtils.JSON);
    }
}
