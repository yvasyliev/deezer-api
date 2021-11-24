package api.deezer.requests;

import api.deezer.http.impl.DeezerGetRequest;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
import api.deezer.objects.Chart;
import api.deezer.objects.data.AlbumData;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.PlaylistData;
import api.deezer.objects.data.PodcastData;
import api.deezer.objects.data.TrackData;
import api.deezer.properties.DeezerProperties;

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
        return new DeezerGetRequest<>(DeezerProperties.getProperty("chart.get"), Chart.class);
    }

    /**
     * Gets top tracks.
     *
     * @return top tracks.
     */
    public PaginationRequest<TrackData> getTopTracks() {
        return new PaginationRequest<>(DeezerProperties.getProperty("chart.tracks"), TrackData.class);
    }

    /**
     * Gets top albums.
     *
     * @return top albums.
     */
    public PaginationRequest<AlbumData> getTopAlbums() {
        return new PaginationRequest<>(DeezerProperties.getProperty("chart.albums"), AlbumData.class);
    }

    /**
     * Gets top artists.
     *
     * @return top artists.
     */
    public PaginationRequest<ArtistData> getTopArtists() {
        return new PaginationRequest<>(DeezerProperties.getProperty("chart.artists"), ArtistData.class);
    }

    /**
     * Gets top playlists.
     *
     * @return top playlists.
     */
    public PaginationRequest<PlaylistData> getTopPlaylists() {
        return new PaginationRequest<>(DeezerProperties.getProperty("chart.playlists"), PlaylistData.class);
    }

    /**
     * Gets top podcasts.
     *
     * @return top podcasts.
     */
    public PaginationRequest<PodcastData> getTopPodcasts() {
        return new PaginationRequest<>(DeezerProperties.getProperty("chart.podcasts"), PodcastData.class);
    }
}
