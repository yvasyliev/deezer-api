package io.github.yvasyliev.deezer.methods.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.yvasyliev.deezer.json.AdvancedSearchQuerySerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize(using = AdvancedSearchQuerySerializer.class)
public class AdvancedSearchQuery implements SearchQuery {
    /**
     * The artist name.
     */
    @JsonProperty("artist")
    private String artistName;

    /**
     * The album's title.
     */
    @JsonProperty("album")
    private String albumTitle;

    /**
     * The track's title.
     */
    @JsonProperty("track")
    private String trackTitle;

    /**
     * The label name.
     */
    @JsonProperty("label")
    private String label;

    /**
     * The track's minimum duration in seconds.
     */
    @JsonProperty("dur_min")
    private Integer minDuration;

    /**
     * The track's maximum duration in seconds.
     */
    @JsonProperty("dur_max")
    private Integer maxDuration;

    /**
     * The track's minimum bpm.
     */
    @JsonProperty("bpm_min")
    private Integer minBpm;

    /**
     * The track's maximum bpm.
     */
    @JsonProperty("bpm_max")
    private Integer maxBpm;
}
