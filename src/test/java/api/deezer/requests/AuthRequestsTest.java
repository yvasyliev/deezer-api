package api.deezer.requests;

import api.deezer.DeezerApi;
import api.deezer.objects.DeezerAccessToken;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthRequestsTest extends AbstractRequestsTest {
    DeezerApi deezerApi = new DeezerApi();

    @Test
    void getLoginUrl() {
        String loginUrl = assertDoesNotThrow(() -> deezerApi.auth().getLoginUrl(APP_ID, REDIRECT_URI, PERMISSIONS));
        HttpUrl url = new Request.Builder().url(loginUrl).build().url();

        assertTrue(url.isHttps());
        assertEquals("connect.deezer.com", url.host());
        assertEquals(Arrays.asList("oauth", "auth.php"), url.pathSegments());
        assertEquals(String.valueOf(APP_ID), url.queryParameter("app_id"));
        assertEquals(REDIRECT_URI, url.queryParameter("redirect_uri"));
        assertEquals("email,listening_history", url.queryParameter("perms"));
    }

    @Test
    void getAccessToken() {
        enqueue(readBody("responses/auth/access_token.json", ACCESS_TOKEN));

        DeezerAccessToken deezerAccessToken = assertDoesNotThrow(() -> deezerApi.auth().getAccessToken(APP_ID, SECRET, CODE).execute());

        HttpUrl requestUrl = takeRequestUrl("oauth", "access_token.php");
        assertEquals(String.valueOf(APP_ID), requestUrl.queryParameter("app_id"));
        assertEquals(CODE, requestUrl.queryParameter("code"));
        assertEquals(SECRET, requestUrl.queryParameter("secret"));
        assertEquals("json", requestUrl.queryParameter("output"));

        assertEquals(ACCESS_TOKEN, deezerAccessToken.getAccessToken());
        assertEquals(0, deezerAccessToken.getExpires());
    }
}