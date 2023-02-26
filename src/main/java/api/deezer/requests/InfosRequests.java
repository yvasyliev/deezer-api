package api.deezer.requests;

import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.objects.Infos;
import api.deezer.utils.DeezerProperties;

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
