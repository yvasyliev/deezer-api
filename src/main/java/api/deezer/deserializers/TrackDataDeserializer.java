package api.deezer.deserializers;

import api.deezer.objects.data.TrackData;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Deserializes track data.
 */
public class TrackDataDeserializer implements JsonDeserializer<TrackData> {
    /**
     * {@link Gson} instance.
     */
    private final Gson gson = new Gson();

    @Override
    public TrackData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return jsonElement.isJsonObject() ? gson.fromJson(jsonElement, type) : null;
    }
}
