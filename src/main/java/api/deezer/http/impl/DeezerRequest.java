package api.deezer.http.impl;

import api.deezer.converters.Converter;
import api.deezer.converters.GsonConverter;
import api.deezer.exceptions.DeezerException;
import api.deezer.http.DefaultHttpClient;
import api.deezer.http.HttpClient;
import api.deezer.http.HttpRequest;

import java.io.IOException;
import java.util.Map;

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
     * HTTP client.
     */
    private HttpClient httpClient;

    /**
     * Deezer API response converter.
     */
    private Converter<String, Response> responseConverter;

    public DeezerRequest(String url, Map<String, String> params, Class<Response> responseClass) {
        this.url = url;
        this.params = params;
        setHttpClient(new DefaultHttpClient());
        setResponseConverter(new GsonConverter<>(responseClass));
    }

    /**
     * Executes Deezer API request.
     *
     * @return Deezer API response.
     * @throws DeezerException if errors occur.
     */
    public Response execute() throws DeezerException {
        try {
            return responseConverter.covert(httpClient.execute(this));
        } catch (IOException e) {
            throw new DeezerException(e);
        }
    }

    public DeezerRequest<Response> setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public DeezerRequest<Response> setResponseConverter(Converter<String, Response> responseConverter) {
        this.responseConverter = responseConverter;
        return this;
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
