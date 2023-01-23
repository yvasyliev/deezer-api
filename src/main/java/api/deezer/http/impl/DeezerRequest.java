package api.deezer.http.impl;

import api.deezer.converters.PojoConverter;
import api.deezer.exceptions.DeezerException;
import api.deezer.http.HttpClient;
import api.deezer.http.HttpRequestFilePart;
import api.deezer.http.HttpRequest;
import api.deezer.http.HttpResponse;
import api.deezer.validators.DeezerResponseValidator;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Basic implementation if {@link HttpRequest}. Executes Deezer API requests.
 *
 * @param <Response> response POJO type.
 */
public abstract class DeezerRequest<Response> implements HttpRequest {
    /**
     * Deezer API URL.
     */
    private final String url;

    /**
     * Request params.
     */
    private final Map<String, String> params;

    /**
     * File
     */
    private final HttpRequestFilePart[] parts;

    /**
     * Converts Deezer response.
     */
    private final Function<String, Response> responseConverter;

    /**
     * HTTP client.
     */
    private final HttpClient httpClient = new DefaultHttpClient();

    /**
     * Validates Deezer response.
     */
    private final Predicate<HttpResponse> responseValidator;

    public DeezerRequest(String url, Map<String, String> params, Class<Response> responseClass) {
        this(url, params, new PojoConverter<>(responseClass));
    }

    public DeezerRequest(String url, Map<String, String> params, Function<String, Response> responseConverter) {
        this(url, params, new DeezerResponseValidator(), responseConverter);
    }

    public DeezerRequest(String url, Map<String, String> params, Predicate<HttpResponse> responseValidator, Function<String, Response> responseConverter) {
		this(url, params, responseValidator, responseConverter, null);
    }

    public DeezerRequest(String url, Map<String, String> params, Function<String, Response> responseConverter,  HttpRequestFilePart[] parts) {
        this(url, params, new DeezerResponseValidator(), responseConverter, parts);
    }

    public DeezerRequest(String url, Map<String, String> params, Predicate<HttpResponse> responseValidator, Function<String, Response> responseConverter, HttpRequestFilePart[] parts) {
        this.url = url;
        this.params = params;
        this.parts = parts;
        this.responseValidator = responseValidator;
        this.responseConverter = responseConverter;
    }

    /**
     * Executes Deezer API request.
     *
     * @return Deezer API response.
     * @throws DeezerException if errors occur.
     */
    public Response execute() throws DeezerException {
        try {
            HttpResponse httpResponse = httpClient.execute(this);
            if (!responseValidator.test(httpResponse)) {
                throw new DeezerException(httpResponse.getBody());
            }
            return responseConverter.apply(httpResponse.getBody());
        } catch (IOException e) {
            throw new DeezerException(e);
        }
    }

    @Override
    public HttpRequestFilePart[]  getFileParts() {
        return parts;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getRequestMethod() {
        return "GET";
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
