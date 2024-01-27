package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;

public enum ExplicitContentLevel {
    @SerializedName("explicit_display") EXPLICIT_DISPLAY,
    @SerializedName("explicit_no_recommendation") EXPLICIT_NO_RECOMMENDATION,
    @SerializedName("explicit_hide") EXPLICIT_HIDE
}
