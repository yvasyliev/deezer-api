package api.deezer.requests;

import api.deezer.http.impl.DeezerGetRequest;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.objects.Options;
import api.deezer.properties.DeezerProperties;

/**
 * Requests related to options.
 */
public class OptionsRequests extends DeezerRequests {
    /**
     * Gets options.
     *
     * @return options.
     */
    public DeezerRequest<Options> get() {
        return new DeezerGetRequest<>(DeezerProperties.getProperty("options.get"), Options.class);
    }
}
