package api.deezer.http;

import api.deezer.exceptions.DeezerException;
import api.deezer.validators.DeezerResponseValidator;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
        try (Response response = new OkHttpClient().newCall(newRequest()).execute()) {
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
