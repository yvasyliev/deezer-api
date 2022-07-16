package api.deezer.converters;

import com.google.gson.JsonElement;

/**
 * Converts {@link String} to {@link JsonElement}.
 */
public class JsonElementConverter extends PojoConverter<JsonElement> {
    public JsonElementConverter() {
        super(JsonElement.class);
    }
}
