package api.deezer.requests;

import api.deezer.http.DeezerGetRequest;
import api.deezer.http.DeezerRequest;
import api.deezer.http.PagingRequest;
import api.deezer.objects.data.ArtistData;
import api.deezer.objects.data.GenreData;
import api.deezer.objects.data.RadioData;
import api.deezer.utils.DeezerPropertyKeys;

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
        return new DeezerGetRequest<>(property(DeezerPropertyKeys.GENRE_ALL), GenreData.class);
    }

    /**
     * Gets artists by genre.
     *
     * @param genreId genre ID.
     * @return list of artists.
     */
    public PagingRequest<ArtistData> getArtistsByGenreId(long genreId) {
        return new PagingRequest<>(property(DeezerPropertyKeys.GENRE_ARTISTS, genreId), ArtistData.class);
    }

    /**
     * Gets radios by genre.
     *
     * @param genreId genre ID.
     * @return list of radios.
     */
    public PagingRequest<RadioData> getRadiosByGenreId(long genreId) {
        return new PagingRequest<>(property(DeezerPropertyKeys.GENRE_RADIOS, genreId), RadioData.class);
    }
}
