package api.deezer.http.impl;

import api.deezer.http.HttpClient;
import api.deezer.http.HttpRequest;
import api.deezer.http.utils.URLParamsEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
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

    /**
     * List of keys to hide sensitive params from logging.
     */
    private List<String> sensitiveParamKeys = Collections.emptyList();

    @Override
    public String execute(HttpRequest httpRequest) throws IOException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(
                    "Request: {} {}{}",
                    httpRequest.getRequestMethod(),
                    httpRequest.getUrl(),
                    toUrlParams(httpRequest.getParams())
            );
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(httpRequest.getUrl()).openConnection();
            connection.setRequestMethod(httpRequest.getRequestMethod());
            connection.setDoOutput(true);

            Map<String, String> params = httpRequest.getParams();
            if (params != null && !params.isEmpty()) {
                try (DataOutputStream output = new DataOutputStream(connection.getOutputStream())) {
                    output.writeBytes(URLParamsEncoder.encode(params));
                    output.flush();
                }
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = reader.lines().collect(Collectors.joining());
                LOGGER.debug("Response: {}", response);
                return response;
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Converts HTTP params to URL params hiding sensitive values.
     *
     * @param params HTTP params.
     * @return URL params.
     */
    private String toUrlParams(Map<String, String> params) {
        String urlParams = "";
        if (params != null && !params.isEmpty()) {
            urlParams = '?' + params.entrySet().stream()
                    .map(entry -> {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (sensitiveParamKeys.contains(key)) {
                            value = hide(value);
                        }
                        return key + "=" + value;
                    })
                    .collect(Collectors.joining("&"));
        }
        return urlParams;
    }

    /**
     * Hides sensitive values from logging.
     *
     * @param value sensitive value.
     * @return non-sensitive value.
     */
    private String hide(String value) {
        int i = value.length() - 4;
        i = i > 0 ? i : value.length();
        return String.join("", Collections.nCopies(i, "*")) + value.substring(i);
    }

    public void setSensitiveParamKeys(List<String> sensitiveParamKeys) {
        this.sensitiveParamKeys = sensitiveParamKeys;
    }
}
