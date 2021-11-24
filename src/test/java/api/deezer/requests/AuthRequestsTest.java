package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.exceptions.DeezerException;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.objects.AccessToken;
import api.deezer.objects.Permission;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthRequestsTest {
    DeezerApi deezerApi = new DeezerApi("accessToken");

    @Test
    void getLoginUrl() throws DeezerException {
        String loginUrl = deezerApi.auth().getLoginUrl(123, "redirect-uri.com", Permission.BASIC_ACCESS, Permission.EMAIL);
        assertEquals("https://connect.deezer.com/oauth/auth.php?app_id=123&redirect_uri=redirect-uri.com&perms=basic_access%2Cemail", loginUrl);
    }

    @Test
    void getAccessToken() {
        DeezerRequest<AccessToken> request = deezerApi.auth().getAccessToken(123, "secret_string", "code");
        assertEquals("https://connect.deezer.com/oauth/access_token.php", request.getUrl());
        assertEquals("get", request.getParams().get("request_method"));
        assertEquals("123", request.getParams().get("app_id"));
        assertEquals("secret_string", request.getParams().get("secret"));
        assertEquals("code", request.getParams().get("code"));
    }
}
