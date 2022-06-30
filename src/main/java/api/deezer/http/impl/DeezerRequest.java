package api.deezer.http.impl;

import api.deezer.converters.Converter;
import api.deezer.converters.GsonConverter;
import api.deezer.exceptions.DeezerException;
import api.deezer.http.HttpClient;
import api.deezer.http.HttpRequest;
import api.deezer.http.HttpResponse;
import api.deezer.objects.ErrorWrapper;
import com.google.gson.JsonParseException;

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

    /**
     * Deezer API Error response converter.
     */
    private Converter<String, ErrorWrapper> errorResponseConverter;


    public DeezerRequest(String url, Map<String, String> params, Class<Response> responseClass) {
        this.url = url;
        this.params = params;
        setHttpClient(new DefaultHttpClient());
        setResponseConverter(new GsonConverter<>(responseClass));
        setErrorResponseConverter(new GsonConverter<>(ErrorWrapper.class));
    }

    /**
     * Executes Deezer API request.
     *
     * @return Deezer API response.
     * @throws DeezerException if errors occur.
     */
    public Response execute() throws DeezerException {

        HttpResponse httpResponse = null;

        try {
            httpResponse = httpClient.execute(this);
            return responseConverter.convert(httpResponse.getBody());
        } catch (IOException e) {
            throw new DeezerException(e);
        } catch (JsonParseException e) {
            if(httpResponse != null && httpResponse.getBody() != null) {
                throw new DeezerException(errorResponseConverter.convert(httpResponse.getBody()).getError().getMessage());
            }
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

    public DeezerRequest<Response> setErrorResponseConverter(Converter<String, ErrorWrapper> errorResponseConverter) {
        this.errorResponseConverter = errorResponseConverter;
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
