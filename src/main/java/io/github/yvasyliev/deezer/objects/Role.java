package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;

public enum Role {
    @SerializedName("Main") MAIN,
    @SerializedName("Featured") FEATURED,
    @SerializedName("Remixer") REMIXER,
    @SerializedName("Composer") COMPOSER,
    @SerializedName("Author") AUTHOR,
    @SerializedName("Producer") PRODUCER,
    @SerializedName("Guest") GUEST
}
