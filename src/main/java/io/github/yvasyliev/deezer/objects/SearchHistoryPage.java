package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.methods.SearchHistoryMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchHistoryPage extends AbstractPage<SearchHistory, SearchHistoryMethod, SearchHistoryPage> {
    /**
     * Count of objects on this page.
     */
    @SerializedName("count")
    private Integer count;
}
