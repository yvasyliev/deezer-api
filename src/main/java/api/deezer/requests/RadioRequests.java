package api.deezer.requests;

import api.deezer.http.impl.DeezerGetRequest;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.objects.data.RadioData;
import api.deezer.properties.DeezerProperties;

/**
 * Requests related to radio.
 */
public class RadioRequests extends DeezerRequests {
    /**
     * Gets radio.
     *
     * @return radio.
     */
    public DeezerRequest<RadioData> get() {
        return new DeezerGetRequest<>(DeezerProperties.getProperty("radio.get"), RadioData.class);
    }
}
