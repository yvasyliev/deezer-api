package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface EditorialService extends DeezerService {
    String EDITORIAL = "/editorial";
    String EDITORIAL_ID = "/editorial/{editorialId}";
    String EDITORIAL_CHARTS = "/editorial/{editorialId}/charts";
    String EDITORIAL_RELEASES = "/editorial/{editorialId}/releases";
    String EDITORIAL_SELECTION = "/editorial/{editorialId}/selection";

    @RequestLine(GET + EDITORIAL)
    Page<Editorial> getEditorial(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + EDITORIAL)
    CompletableFuture<Page<Editorial>> getEditorialAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + EDITORIAL_ID)
    Editorial getEditorial(@Param("editorialId") long editorialId);

    @RequestLine(GET + EDITORIAL_ID)
    CompletableFuture<Editorial> getEditorialAsync(@Param("editorialId") long editorialId);

    @RequestLine(GET + EDITORIAL_CHARTS)
    Chart getEditorialCharts(@Param("editorialId") long editorialId);

    @RequestLine(GET + EDITORIAL_CHARTS)
    CompletableFuture<Chart> getEditorialChartsAsync(@Param("editorialId") long editorialId);

    @RequestLine(GET + EDITORIAL_RELEASES)
    Page<Album> getEditorialReleases(@Param("editorialId") long editorialId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + EDITORIAL_RELEASES)
    CompletableFuture<Page<Album>> getEditorialReleasesAsync(@Param("editorialId") long editorialId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + EDITORIAL_SELECTION)
    Page<Album> getEditorialSelection(@Param("editorialId") long editorialId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + EDITORIAL_SELECTION)
    CompletableFuture<Page<Album>> getEditorialSelectionAsync(@Param("editorialId") long editorialId, @QueryMap Map<String, Object> queryParams);
}
