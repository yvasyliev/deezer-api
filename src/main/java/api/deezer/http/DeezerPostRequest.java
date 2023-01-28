package api.deezer.http;

import api.deezer.http.utils.HttpBodies;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.File;
import java.io.InputStream;
import java.net.URLConnection;

/**
 * Executes Deezer API POST request.
 *
 * @param <Answer> response POJO type.
 */
public class DeezerPostRequest<Answer> extends DeezerRequest<Answer> {
    /**
     * Request body.
     */
    private RequestBody requestBody = HttpBodies.EMPTY_REQUEST_BODY;

    public DeezerPostRequest(String url, Class<Answer> answerClass) {
        super(url, answerClass);
    }

    public DeezerPostRequest(String url, Class<Answer> answerClass, File file) {
        this(url, answerClass, file.getName(), RequestBody.create(file, MediaType.get(URLConnection.guessContentTypeFromName(file.getName()))));
    }

    public DeezerPostRequest(String url, Class<Answer> answerClass, String filename, byte[] file) {
        this(url, answerClass, filename, RequestBody.create(file, MediaType.get(URLConnection.guessContentTypeFromName(filename))));
    }

    public DeezerPostRequest(String url, Class<Answer> answerClass, String filename, InputStream file) {
        this(url, answerClass, filename, new InputStreamRequestBody(filename, file));
    }

    private DeezerPostRequest(String url, Class<Answer> answerClass, String filename, RequestBody file) {
        super(url, answerClass);
        this.requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", filename, file)
                .build();
    }

    @Override
    public DeezerPostRequest<Answer> addParam(String name, String value) {
        return (DeezerPostRequest<Answer>) super.addParam(name, value);
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().post(requestBody).build();
    }
}
