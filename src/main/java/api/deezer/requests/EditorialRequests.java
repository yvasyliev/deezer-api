package api.deezer.requests;

import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
import api.deezer.objects.Chart;
import api.deezer.objects.data.AlbumData;
import api.deezer.utils.DeezerPropertyKeys;

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
        return new PagingRequest<>(DeezerPropertyKeys.EDITORIAL_SELECTION, AlbumData.class);
    }

    /**
     * Get chart.
     *
     * @return chart.
     */
    public DeezerRequest<Chart> getChart() {
        return new DeezerGetRequest<>(DeezerPropertyKeys.EDITORIAL_CHARTS, Chart.class);
    }

    /**
     * Gets new releases.
     *
     * @return new releases.
     */
    public PagingRequest<AlbumData> getNewReleases() {
        return new PagingRequest<>(DeezerPropertyKeys.EDITORIAL_RELEASES, AlbumData.class);
    }
}
