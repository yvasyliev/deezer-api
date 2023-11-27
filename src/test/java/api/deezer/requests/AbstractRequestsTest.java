package api.deezer.requests;

import api.deezer.objects.Permission;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

abstract class AbstractRequestsTest {
    static final long APP_ID = ThreadLocalRandom.current().nextInt(100_000_000, Integer.MAX_VALUE);
    static final String REDIRECT_URI = "https://google.com";
    static final Permission[] PERMISSIONS = {Permission.EMAIL, Permission.LISTENING_HISTORY};
    static final String SECRET = UUID.randomUUID().toString();
    static final String CODE = UUID.randomUUID().toString();
    static final String ACCESS_TOKEN = UUID.randomUUID().toString();
    static final long ALBUM_ID = 326060237;
    static final long ARTIST_ID = 9834474;

    MockWebServer server;

    Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        server = new MockWebServer();
        assertDoesNotThrow(() -> server.start(1998));
    }

    @AfterEach
    void tearDown() {
        assertDoesNotThrow(() -> server.close());
    }

    void enqueue(String body) {
        server.enqueue(new MockResponse().setBody(body));
    }

    HttpUrl takeRequestUrl(String... pathSegments) {
        return assertDoesNotThrow(() -> {
            RecordedRequest recordedRequest = server.takeRequest();
            assertEquals("GET", recordedRequest.getMethod());

            HttpUrl requestUrl = recordedRequest.getRequestUrl();
            assertNotNull(requestUrl);
            assertEquals(Arrays.asList(pathSegments), requestUrl.pathSegments());

            return requestUrl;
        });
    }

    String readBody(String path, Object... args) {
        return assertDoesNotThrow(() -> {
            try (Reader reader = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)))) {
                JsonElement body = gson.fromJson(reader, JsonElement.class);
                return String.format(gson.toJson(body), args);
            }
        });
    }
}
