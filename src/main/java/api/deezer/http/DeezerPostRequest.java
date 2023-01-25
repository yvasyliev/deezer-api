package api.deezer.http;

import api.deezer.converters.PojoConverter;
import api.deezer.http.utils.RequestBodies;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Map;

/**
 * Executes Deezer API POST request.
 *
 * @param <Answer> response POJO type.
 */
public class DeezerPostRequest<Answer> extends DeezerRequest<Answer> {
    /**
     * Multipart body builder.
     */
    private MultipartBody.Builder multipartBodyBuilder;

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> responseClass) {
        super(url, params, responseClass);
    }

    @Override
    protected Request newRequest() {
        RequestBody requestBody = multipartBodyBuilder != null
                ? multipartBodyBuilder.setType(MultipartBody.FORM).build()
                : RequestBodies.EMPTY_REQUEST_BODY;
        return newRequestBuilder().post(requestBody).build();
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> responseClass, File file) {
        this(url, params, responseClass, file.getName(), RequestBody.create(file, MediaType.get(URLConnection.guessContentTypeFromName(file.getName()))));
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> responseClass, String filename, byte[] file) {
        this(url, params, responseClass, filename, RequestBody.create(file, MediaType.get(URLConnection.guessContentTypeFromName(filename))));
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> responseClass, String filename, InputStream file) {
        this(url, params, responseClass, filename, new InputStreamRequestBody(filename, file));
    }

    private DeezerPostRequest(String url, Map<String, String> params, Class<Answer> answerClass, String filename, RequestBody file) {
        super(url, params, new PojoConverter<>(answerClass));
        this.multipartBodyBuilder = new MultipartBody.Builder().addFormDataPart("file", filename, file);
    }
}
