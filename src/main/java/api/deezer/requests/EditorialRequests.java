package api.deezer.requests;

import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
import api.deezer.objects.Chart;
import api.deezer.objects.data.AlbumData;
import api.deezer.utils.DeezerProperties;

/**
 * Requests related to editorial.
 */
public class EditorialRequests extends DeezerRequests {
    /**
     * Gets selected albums.
     *
     * @return selected albums.
     */
    public PagingRequest<AlbumData> getSelectedAlbums() {
        return new PagingRequest<>(DeezerProperties.getProperty("editorial.selection"), AlbumData.class);
    }

    /**
     * Get chart.
     *
     * @return chart.
     */
    public DeezerRequest<Chart> getChart() {
        return new DeezerGetRequest<>(DeezerProperties.getProperty("editorial.charts"), Chart.class);
    }

    /**
     * Gets new releases.
     *
     * @return new releases.
     */
    public PagingRequest<AlbumData> getNewReleases() {
        return new PagingRequest<>(DeezerProperties.getProperty("editorial.releases"), AlbumData.class);
    }
}
