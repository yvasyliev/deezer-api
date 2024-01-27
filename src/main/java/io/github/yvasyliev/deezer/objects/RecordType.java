package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;

public enum RecordType {
    @SerializedName("album") ALBUM,
    @SerializedName("compilation") COMPILATION,
    @SerializedName("ep") EP,
    @SerializedName("single") SINGLE
}
