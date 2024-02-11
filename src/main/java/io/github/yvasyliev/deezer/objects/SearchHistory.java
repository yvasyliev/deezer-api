package io.github.yvasyliev.deezer.objects;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SearchHistory implements Pageable {
    @SerializedName("query")
    private String query;

    @SerializedName("result")
    private JsonElement result;
}
