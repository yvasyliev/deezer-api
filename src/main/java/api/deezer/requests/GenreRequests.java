package api.deezer.requests;

import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.GenreData;
import api.deezer.objects.data.RadioData;
import api.deezer.properties.DeezerProperties;

/**
 * Requests related to genres.
 */
public class GenreRequests extends DeezerRequests {
    /**
     * Gets all genres.
     *
     * @return all genres.
     */
    public DeezerRequest<GenreData> getAll() {
        return new DeezerGetRequest<>(DeezerProperties.getProperty("genre.all"), GenreData.class);
    }

    /**
     * Gets artists by genre.
     *
     * @param genreId genre ID.
     * @return list of artists.
     */
    public PagingRequest<ArtistData> getArtistsByGenreId(long genreId) {
        return new PagingRequest<>(property("genre.artists", genreId), ArtistData.class);
    }

    /**
     * Gets radios by genre.
     *
     * @param genreId genre ID.
     * @return list of radios.
     */
    public PagingRequest<RadioData> getRadiosByGenreId(long genreId) {
        return new PagingRequest<>(property("genre.radios", genreId), RadioData.class);
    }
}
