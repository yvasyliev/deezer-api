package api.deezer.requests;

import api.deezer.converters.PermissionsConverter;
import api.deezer.exceptions.DeezerException;
import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.utils.URLParamsEncoder;
import api.deezer.objects.AccessToken;
import api.deezer.objects.Permission;
import api.deezer.properties.DeezerProperties;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Authorization requests.
 */
public class AuthRequests extends DeezerRequests {
    /**
     * Converts list of {@link Permission} into comma separated values.
     */
    private final Function<List<Permission>, String> permissionsConverter = new PermissionsConverter();

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
        try {
            return property(
                    "auth.url",
                    appId,
                    URLParamsEncoder.encode(redirectUri),
                    URLParamsEncoder.encode(permissionsConverter.apply(perms))
            );
        } catch (UnsupportedEncodingException e) {
            throw new DeezerException(e);
        }
    }

    /**
     * Gets Deezer <i>access_token</i>.
     *
     * @param appId  application ID.
     * @param secret application secret string.
     * @param code   login code.
     * @return Deezer <i>access_token</i>.
     */
    public DeezerRequest<AccessToken> getAccessToken(long appId, String secret, String code) {
        Map<String, String> params = new HashMap<>();
        params.put("app_id", String.valueOf(appId));
        params.put("secret", secret);
        params.put("code", code);
        params.put("output", "json"); // TODO: 31.10.2021 why does https://connect.deezer.com/oauth/access_token.php always return a string format "access_token=${access_token}&expires=${expires}" ignoring "output=json" parameter?

        return new DeezerGetRequest<>(
                DeezerProperties.getProperty("auth.access_token"),
                params,
                AccessToken.class
        );
    }
}
