package api.deezer.converters;

import api.deezer.objects.AccessToken;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converts Deezer API response to {@link AccessToken} object.
 */
public class AccessTokenConverter implements Function<String, AccessToken> {
    @Override
    public AccessToken apply(String response) {
        Map<String, String> params = toParams(response);

        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(params.get("access_token"));
        accessToken.setExpires(Integer.valueOf(params.get("expires")));

        return accessToken;
    }

    private Map<String, String> toParams(String response) {
        return Arrays.stream(response.split("&"))
                .map(param -> param.split("="))
                .collect(Collectors.toMap(param -> param[0], param -> param[1]));
    }
}
