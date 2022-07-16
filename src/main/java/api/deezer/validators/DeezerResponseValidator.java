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
     * Converts Deezer response to {@link JsonElement}.
     */
    private static final Converter<String, JsonElement> JSON_ELEMENT_CONVERTER = new JsonElementConverter();

    @Override
    public boolean test(String responseBody) {
        JsonElement jsonElement = JSON_ELEMENT_CONVERTER.convert(responseBody);
        return !jsonElement.isJsonObject() || !jsonElement.getAsJsonObject().has("error");
    }
}
