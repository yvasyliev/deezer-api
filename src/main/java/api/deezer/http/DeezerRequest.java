package api.deezer.http;

import api.deezer.exceptions.DeezerException;
import api.deezer.http.utils.HttpBodies;
import api.deezer.validators.DeezerResponseValidator;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Executes Deezer API requests.
 *
 * @param <Answer> response POJO type.
 */
public abstract class DeezerRequest<Answer> {
    /**
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DeezerRequest.class);

    /**
     * URL builder.
     */
    protected final HttpUrl.Builder urlBuilder;

    /**
     * Answer class.
     */
    private final Class<Answer> answerClass;

    /**
     * JSON converter.
     */
    private final Gson gson = new Gson();

    /**
     * Validates Deezer response.
     */
    private final Predicate<String> responseValidator = new DeezerResponseValidator();

    /**
     * HTTP client.
     */
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                Request request = chain.request();

                LOGGER.debug(
                        "--> {} {} {} {}",
                        request.method(),
                        request.url(),
                        request.body() != null ? "Content-Type: " + request.body().contentType() : "",
                        request.body() != null ? "Content-Length: " + request.body().contentLength() : ""
                );

                long start = System.currentTimeMillis();
                Response response = chain.proceed(request);
                long end = System.currentTimeMillis();

                ResponseBody responseBody = response.body();
                String stringBody = responseBody != null ? responseBody.string() : null;

                LOGGER.debug(
                        "<-- {}ms: {} {}",
                        end - start,
                        response.code(),
                        stringBody
                );

                ResponseBody responseBodyCopy = responseBody != null
                        ? ResponseBody.create(stringBody, responseBody.contentType())
                        : HttpBodies.EMPTY_RESPONSE_BODY;
                response.close();
                return response.newBuilder().body(responseBodyCopy).build();
            })
            .build();

    public DeezerRequest(String url, Map<String, String> params, Class<Answer> answerClass) {
        this.urlBuilder = HttpUrl.get(url).newBuilder();
        params.forEach(this.urlBuilder::addQueryParameter);
        this.answerClass = answerClass;
    }

    /**
     * Executes Deezer API request.
     *
     * @return Deezer API response.
     * @throws DeezerException if errors occur.
     */
    public Answer execute() throws DeezerException {
        try (Response response = okHttpClient.newCall(newRequest()).execute()) {
            try (ResponseBody responseBody = response.body()) {
                if (responseBody == null) {
                    throw new DeezerException("Response body is null. " + response);
                }
                String rawAnswer = responseBody.string();
                if (!responseValidator.test(rawAnswer)) {
                    throw new DeezerException(rawAnswer);
                }
                return gson.fromJson(rawAnswer, answerClass);
            }
        } catch (IOException e) {
            throw new DeezerException(e);
        }
    }

    /**
     * Creates HTTP request.
     *
     * @return HTTP request.
     */
    protected abstract Request newRequest();

    /**
     * Creates HTTP request builder.
     *
     * @return HTTP request builder.
     */
    protected Request.Builder newRequestBuilder() {
        return new Request.Builder().url(urlBuilder.build());
    }
}
