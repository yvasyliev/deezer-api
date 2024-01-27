package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;

public enum ExplicitContent {
    @SerializedName("0") NOT_EXPLICIT,
    @SerializedName("1") EXPLICIT,
    @SerializedName("2") UNKNOWN,
    @SerializedName("3") EDITED,
    @SerializedName("6") NO_ADVICE_AVAILABLE,
    @SerializedName("7") PARTIALLY_NO_ADVICE_AVAILABLE
}
