package api.deezer.http.impl;

import api.deezer.http.HttpClient;
import api.deezer.http.HttpRequestFilePart;
import api.deezer.http.HttpRequest;
import api.deezer.http.HttpResponse;
import api.deezer.http.utils.URLParamsEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link HttpClient}. Based on {@link HttpURLConnection}.
 */
public class DefaultHttpClient implements HttpClient {
    /**
     * {@link Logger} object.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHttpClient.class);

    @Override
    public HttpResponse execute(HttpRequest httpRequest) throws IOException {
        LOGGER.debug(
                "Request: method={}, url={}, params={}",
                httpRequest.getRequestMethod(),
                httpRequest.getUrl(),
                httpRequest.getParams()
        );

        HttpURLConnection connection = null;
        try {

            if (httpRequest.getFileParts() == null || httpRequest.getFileParts().length == 0) {
                connection = prepareRegularRequest(httpRequest);
            } else {
                connection = prepareMultipartRequest(httpRequest);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                HttpResponse httpResponse = new HttpResponseImpl(
                        connection.getResponseCode(),
                        reader.lines().collect(Collectors.joining())
                );
                LOGGER.debug(
                        "Response: status={}, body={}",
                        httpResponse.getResponseCode(),
                        httpResponse.getBody()
                );
                return httpResponse;
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Creates GET request.
     *
     * @param httpRequest request data.
     * @return {@link HttpURLConnection}.
     * @throws IOException if errors occur.
     */
    private static HttpURLConnection prepareRegularRequest(HttpRequest httpRequest) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(httpRequest.getUrl()).openConnection();
        connection.setRequestMethod(httpRequest.getRequestMethod());
        connection.setDoOutput(true);
        Map<String, String> params = httpRequest.getParams();
        if (params != null && !params.isEmpty()) {
            try (DataOutputStream output = new DataOutputStream(connection.getOutputStream())) {
                output.writeBytes(URLParamsEncoder.encode(params));
                output.flush();
            }
        }
        return connection;
    }

    /**
     * Creates POST request.
     *
     * @param httpRequest request data.
     * @return {@link HttpURLConnection}.
     * @throws IOException if errors occur.
     */
    private static HttpURLConnection prepareMultipartRequest(HttpRequest httpRequest) throws IOException {
        Map<String, String> params = httpRequest.getParams();
        String urlParams = "";
        if (params != null && !params.isEmpty()) {
            urlParams = "?" + URLParamsEncoder.encode(params);
        }
        HttpURLConnection connection = (HttpURLConnection) new URL(httpRequest.getUrl() + urlParams).openConnection();
        connection.setRequestMethod(httpRequest.getRequestMethod());
        connection.setDoOutput(true);
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "----" + System.currentTimeMillis() + "----";
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            for (HttpRequestFilePart part : httpRequest.getFileParts()) {
                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + part.getName() + "\"");
                if (part.getFilename() != null)
                    outputStream.writeBytes("; filename=\"" + part.getFilename() + "\"");
                outputStream.writeBytes(lineEnd);

                if (part.getContentType() != null)
                    outputStream.writeBytes("Content-Type: " + part.getContentType() + lineEnd);
                if (part.getContentTransferEncoding() != null)
                    outputStream.writeBytes("Content-Transfer-Encoding: " + part.getContentTransferEncoding() + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.write(part.getValue());
                outputStream.writeBytes(lineEnd);
            }
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            outputStream.flush();
        }
        return connection;
    }
}
