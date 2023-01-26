package api.deezer.http;

import api.deezer.exceptions.DeezerException;
import api.deezer.http.utils.HttpBodies;
import api.deezer.validators.DeezerResponseValidator;
import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
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
                return extractAnswer(responseBody);
            }
        } catch (IOException e) {
            throw new DeezerException(e);
        }
    }

    /**
     * Executes Deezer API request.
     *
     * @return Deezer API response.
     * @return
     */
    public CompletableFuture<Answer> executeAsync() {
        CompletableFuture<Answer> completableFuture = new CompletableFuture<>();
        okHttpClient.newCall(newRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                completableFuture.completeExceptionally(e);
                okHttpClient.dispatcher().executorService().shutdown();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    completableFuture.complete(extractAnswer(response.body()));
                } catch (DeezerException e) {
                    completableFuture.completeExceptionally(e);
                }
                okHttpClient.dispatcher().executorService().shutdown();
            }
        });
        return completableFuture;
    }

    /**
     * Extracts an {@link Answer} from response body.
     *
     * @param responseBody response body.
     * @return {@link Answer}.
     * @throws DeezerException if errors occur.
     * @throws IOException     if errors occur.
     */
    private Answer extractAnswer(ResponseBody responseBody) throws DeezerException, IOException {
        if (responseBody == null) {
            throw new DeezerException("Response body is null.");
        }
        String rawAnswer = responseBody.string();
        if (!responseValidator.test(rawAnswer)) {
            throw new DeezerException(rawAnswer);
        }
        return gson.fromJson(rawAnswer, answerClass);
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
