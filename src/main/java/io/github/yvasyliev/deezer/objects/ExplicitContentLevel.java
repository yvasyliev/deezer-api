package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ExplicitContentLevel {
    @JsonProperty("explicit_display") EXPLICIT_DISPLAY,
    @JsonProperty("explicit_no_recommendation") EXPLICIT_NO_RECOMMENDATION,
    @JsonProperty("explicit_hide") EXPLICIT_HIDE
}
