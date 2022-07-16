package api.deezer.http.impl;

import api.deezer.converters.Converter;
import api.deezer.converters.PojoConverter;
import api.deezer.exceptions.DeezerException;
import api.deezer.http.HttpClient;
import api.deezer.http.HttpRequest;
import api.deezer.http.HttpResponse;
import api.deezer.validators.DeezerResponseValidator;

import java.io.IOException;
import java.util.Map;
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
     * Converts Deezer response.
     */
    private final Converter<String, Response> responseConverter;

    /**
     * HTTP client.
     */
    private final HttpClient httpClient = new DefaultHttpClient();

    /**
     * Validates Deezer response.
     */
    private final Predicate<String> deezerResponseValidator = new DeezerResponseValidator();

    public DeezerRequest(String url, Map<String, String> params, Class<Response> responseClass) {
        this(url, params, new PojoConverter<>(responseClass));
    }

    public DeezerRequest(String url, Map<String, String> params, Converter<String, Response> responseConverter) {
        this.url = url;
        this.params = params;
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
            if (!deezerResponseValidator.test(httpResponse.getBody())) {
                throw new DeezerException(httpResponse.getBody());
            }
            return responseConverter.convert(httpResponse.getBody());
        } catch (IOException e) {
            throw new DeezerException(e);
        }
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
