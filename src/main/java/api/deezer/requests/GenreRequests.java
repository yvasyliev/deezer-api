package api.deezer.requests;

import api.deezer.http.impl.DeezerGetRequest;
import api.deezer.http.impl.DeezerRequest;
import api.deezer.http.impl.PaginationRequest;
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
    public PaginationRequest<ArtistData> getArtistsByGenreId(int genreId) {
        return new PaginationRequest<>(property("genre.artists", genreId), ArtistData.class);
    }

    /**
     * Gets radios by genre.
     *
     * @param genreId genre ID.
     * @return list of radios.
     */
    public PaginationRequest<RadioData> getRadiosByGenreId(int genreId) {
        return new PaginationRequest<>(property("genre.radios", genreId), RadioData.class);
    }
}
