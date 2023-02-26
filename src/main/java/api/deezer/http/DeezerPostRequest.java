package api.deezer.http;

import api.deezer.utils.HttpBodies;
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
 * @param <T> response POJO type.
 */
public class DeezerPostRequest<T> extends DeezerRequest<T> {
    /**
     * Request body.
     */
    private RequestBody requestBody = HttpBodies.EMPTY_REQUEST_BODY;

    public DeezerPostRequest(String url, Class<T> answerClass) {
        super(url, answerClass);
    }

    public DeezerPostRequest(String url, Class<T> answerClass, File file) {
        this(url, answerClass, file.getName(), RequestBody.create(file, MediaType.get(URLConnection.guessContentTypeFromName(file.getName()))));
    }

    public DeezerPostRequest(String url, Class<T> answerClass, String filename, byte[] file) {
        this(url, answerClass, filename, RequestBody.create(file, MediaType.get(URLConnection.guessContentTypeFromName(filename))));
    }

    public DeezerPostRequest(String url, Class<T> answerClass, String filename, InputStream file) {
        this(url, answerClass, filename, new InputStreamRequestBody(filename, file));
    }

    private DeezerPostRequest(String url, Class<T> answerClass, String filename, RequestBody file) {
        super(url, answerClass);
        this.requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", filename, file)
                .build();
    }

    @Override
    public DeezerPostRequest<T> addParam(String name, String value) {
        return (DeezerPostRequest<T>) super.addParam(name, value);
    }

    @Override
    protected Request newRequest() {
        return newRequestBuilder().post(requestBody).build();
    }
}
