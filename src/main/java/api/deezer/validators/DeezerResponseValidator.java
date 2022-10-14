package api.deezer.validators;

import api.deezer.converters.Converter;
import api.deezer.converters.JsonElementConverter;
import com.google.gson.JsonElement;

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
    private static final Converter<String, JsonElement> JSON_ELEMENT_CONVERTER = new JsonElementConverter();

    @Override
    public boolean test(String responseBody) {
        return !isWrongCode(responseBody) && !hasErrorField(responseBody);
    }

    private boolean isWrongCode(String responseBody) {
        return WRONG_CODE.equals(responseBody);
    }

    private boolean hasErrorField(String responseBody) {
        JsonElement jsonElement = JSON_ELEMENT_CONVERTER.convert(responseBody);
        return jsonElement.isJsonObject() && jsonElement.getAsJsonObject().has("error");
    }
}
