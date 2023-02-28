package api.deezer.requests;

import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
import api.deezer.objects.Chart;
import api.deezer.objects.data.AlbumData;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.PlaylistData;
import api.deezer.objects.data.PodcastData;
import api.deezer.objects.data.TrackData;
import api.deezer.utils.DeezerPropertyKeys;

/**
 * Requests related to charts.
 */
public class ChartRequests extends ArtistRequests {
    /**
     * Get all charts.
     *
     * @return all charts.
     */
    public DeezerRequest<Chart> getAll() {
        return new DeezerGetRequest<>(property(DeezerPropertyKeys.CHART_GET), Chart.class);
    }

    /**
     * Gets top tracks.
     *
     * @return top tracks.
     */
    public PagingRequest<TrackData> getTopTracks() {
        return new PagingRequest<>(property(DeezerPropertyKeys.CHART_TRACKS), TrackData.class);
    }

    /**
     * Gets top albums.
     *
     * @return top albums.
     */
    public PagingRequest<AlbumData> getTopAlbums() {
        return new PagingRequest<>(property(DeezerPropertyKeys.CHART_ALBUMS), AlbumData.class);
    }

    /**
     * Gets top artists.
     *
     * @return top artists.
     */
    public PagingRequest<ArtistData> getTopArtists() {
        return new PagingRequest<>(property(DeezerPropertyKeys.CHART_ARTISTS), ArtistData.class);
    }

    /**
     * Gets top playlists.
     *
     * @return top playlists.
     */
    public PagingRequest<PlaylistData> getTopPlaylists() {
        return new PagingRequest<>(property(DeezerPropertyKeys.CHART_PLAYLISTS), PlaylistData.class);
    }

    /**
     * Gets top podcasts.
     *
     * @return top podcasts.
     */
    public PagingRequest<PodcastData> getTopPodcasts() {
        return new PagingRequest<>(property(DeezerPropertyKeys.CHART_PODCASTS), PodcastData.class);
    }
}
