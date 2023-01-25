package api.deezer.validators;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.function.Predicate;

/**
 * Validates Deezer response.
 */
public class DeezerResponseValidator implements Predicate<String> {
    /**
     * {@code wrong code} body content.
     */
    private static final String WRONG_CODE = "wrong code";

    /**
     * JSON converter.
     */
    private final Gson gson = new Gson();

    @Override
    public boolean test(String response) {
        return !isWrongCode(response) && !hasErrorField(response);
    }

    private boolean isWrongCode(String responseBody) {
        return WRONG_CODE.equals(responseBody);
    }

    private boolean hasErrorField(String responseBody) {
        JsonElement jsonElement = gson.fromJson(responseBody, JsonElement.class);
        return jsonElement.isJsonObject() && jsonElement.getAsJsonObject().has("error");
    }
}
