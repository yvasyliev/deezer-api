package api.deezer.http;

import api.deezer.exceptions.DeezerException;
import api.deezer.utils.HttpBodies;
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
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

/**
 * Executes Deezer API requests.
 *
 * @param <T> response POJO type.
 */
public abstract class DeezerRequest<T> {
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
    private final Class<T> answerClass;

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

    protected DeezerRequest(String url, Class<T> answerClass) {
        this.urlBuilder = HttpUrl.get(url).newBuilder();
        this.answerClass = answerClass;
    }

    /**
     * Executes Deezer API request.
     *
     * @return Deezer API response.
     * @throws DeezerException if errors occur.
     */
    public T execute() throws DeezerException {
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
     */
    public CompletableFuture<T> executeAsync() {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
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
     * Adds query params to the request.
     *
     * @param name  param name.
     * @param value param value.
     * @return {@code this} instance.
     */
    public DeezerRequest<T> addParam(String name, String value) {
        this.urlBuilder.addQueryParameter(name, value);
        return this;
    }

    /**
     * Extracts an {@link T} from response body.
     *
     * @param responseBody response body.
     * @return {@link T}.
     * @throws DeezerException if errors occur.
     * @throws IOException     if errors occur.
     */
    private T extractAnswer(ResponseBody responseBody) throws DeezerException, IOException {
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
