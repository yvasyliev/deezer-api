package api.deezer.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Deserializers {@link Date}. If the date equals to {@code "0000-00-00"}, then {@code null} will be returned.
 */
public class ZeroDateDeserializer implements JsonDeserializer<Date> {
    /**
     * Zero date.
     */
    private static final String ZERO_DATE = "0000-00-00";

    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ZERO_DATE.equals(jsonElement.getAsString())
                ? null
                : jsonDeserializationContext.deserialize(jsonElement, type);
    }
}
