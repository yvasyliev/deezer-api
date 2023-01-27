package api.deezer.deserializers;

import api.deezer.objects.data.TrackData;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class TrackDataDeserializer implements JsonDeserializer<TrackData> {
    @Override
    public TrackData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        boolean isFalse = jsonElement.isJsonPrimitive()
                && jsonElement.getAsJsonPrimitive().isBoolean()
                && jsonElement.getAsBoolean();
        return isFalse ? null : jsonDeserializationContext.deserialize(jsonElement, type);
    }
}
