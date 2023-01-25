package api.deezer.http;

import api.deezer.converters.PojoConverter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Executes Deezer API POST request.
 *
 * @param <Answer> response POJO type.
 */
public class DeezerPostRequest<Answer> extends DeezerRequest<Answer> {
    private MultipartBody.Builder multipartBodyBuilder;

    public DeezerPostRequest(String url, Class<Answer> responseClass) {
        this(url, new HashMap<>(), responseClass);
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> responseClass) {
        super(url, params, responseClass);
        this.urlBuilder.addQueryParameter("request_method", "post");
    }

    @Override
    protected Request newRequest() {
        return multipartBodyBuilder != null
                ? newRequestBuilder().post(multipartBodyBuilder.setType(MultipartBody.FORM).build()).build()
                : newRequestBuilder().get().build();
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> responseClass, File file) {
        super(url, params, new PojoConverter<>(responseClass));
        this.multipartBodyBuilder = new MultipartBody.Builder().addFormDataPart(
                "file",
                file.getName(),
                RequestBody.create(
                        file,
                        MediaType.get(URLConnection.guessContentTypeFromName(file.getName()))
                )
        );
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> responseClass, String filename, byte[] file) {
        super(url, params, new PojoConverter<>(responseClass));
        this.multipartBodyBuilder = new MultipartBody.Builder().addFormDataPart(
                "file",
                filename,
                RequestBody.create(
                        file,
                        MediaType.get(URLConnection.guessContentTypeFromName(filename))
                )
        );
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> responseClass, String filename, InputStream file) {
        super(url, params, new PojoConverter<>(responseClass));
        this.multipartBodyBuilder = new MultipartBody.Builder().addFormDataPart(
                "file",
                filename,
                new InputStreamRequestBody(filename, file)
        );
    }
}
