package api.deezer.http;

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
     * Request body.
     */
    private RequestBody requestBody = RequestBodies.EMPTY_REQUEST_BODY;

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> answerClass) {
        super(url, params, answerClass);
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> answerClass, File file) {
        this(url, params, answerClass, file.getName(), RequestBody.create(file, MediaType.get(URLConnection.guessContentTypeFromName(file.getName()))));
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> answerClass, String filename, byte[] file) {
        this(url, params, answerClass, filename, RequestBody.create(file, MediaType.get(URLConnection.guessContentTypeFromName(filename))));
    }

    public DeezerPostRequest(String url, Map<String, String> params, Class<Answer> answerClass, String filename, InputStream file) {
        this(url, params, answerClass, filename, new InputStreamRequestBody(filename, file));
    }

    private DeezerPostRequest(String url, Map<String, String> params, Class<Answer> answerClass, String filename, RequestBody file) {
        super(url, params, answerClass);
        this.requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", filename, file)
                .build();
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().post(requestBody).build();
    }
}
