package api.deezer.requests;

import api.deezer.http.impl.DeezerGetRequest;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
import api.deezer.objects.Chart;
import api.deezer.objects.data.AlbumData;
import api.deezer.properties.DeezerProperties;

/**
 * Requests related to editorial.
 */
public class EditorialRequests extends DeezerRequests {
    /**
     * Gets selected albums.
     *
     * @return selected albums.
     */
    public PaginationRequest<AlbumData> getSelectedAlbums() {
        return new PaginationRequest<>(DeezerProperties.getProperty("editorial.selection"), AlbumData.class);
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
    public PaginationRequest<AlbumData> getNewReleases() {
        return new PaginationRequest<>(DeezerProperties.getProperty("editorial.releases"), AlbumData.class);
    }
}
