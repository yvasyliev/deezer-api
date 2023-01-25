package api.deezer.validators;

import api.deezer.converters.JsonElementConverter;
import com.google.gson.JsonElement;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Validates Deezer response.
 */
public class DeezerResponseValidator implements Predicate<String> {
    /**
     * <code>wrong code</code> body content.
     */
    private static final String WRONG_CODE = "wrong code";

    /**
     * Converts Deezer response to {@link JsonElement}.
     */
    private final Function<String, JsonElement> jsonElementConverter = new JsonElementConverter();

    @Override
    public boolean test(String response) {
        return !isWrongCode(response) && !hasErrorField(response);
    }

    private boolean isWrongCode(String responseBody) {
        return WRONG_CODE.equals(responseBody);
    }

    private boolean hasErrorField(String responseBody) {
        JsonElement jsonElement = jsonElementConverter.apply(responseBody);
        return jsonElement.isJsonObject() && jsonElement.getAsJsonObject().has("error");
    }
}
