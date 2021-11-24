package api.deezer.requests;

import api.deezer.http.impl.DeezerGetRequest;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.objects.Infos;
import api.deezer.properties.DeezerProperties;

/**
 * Requests related to infos.
 */
public class InfosRequests extends DeezerRequests {
    /**
     * Gets infos.
     *
     * @return infos.
     */
    public DeezerRequest<Infos> get() {
        return new DeezerGetRequest<>(DeezerProperties.getProperty("infos.get"), Infos.class);
    }
}
