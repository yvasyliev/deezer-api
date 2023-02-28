package api.deezer.requests;

import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.objects.Options;
import api.deezer.utils.DeezerPropertyKeys;

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
        return new DeezerGetRequest<>(property(DeezerPropertyKeys.OPTIONS_GET), Options.class);
    }
}
