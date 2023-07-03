package api.deezer.deserializers;

import api.deezer.objects.Track;
import api.deezer.objects.data.TrackData;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TrackDataDeserializer implements JsonDeserializer<TrackData> {
    @Override
    public TrackData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isBoolean() && !jsonElement.getAsBoolean()) {
            return null;
        }

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        TrackData trackData = new TrackData();
        if (jsonObject.has("next")) {
            trackData.setNext(jsonObject.get("next").getAsString());
        }
        trackData.setTotal(jsonObject.get("total").getAsInt());
        trackData.setData(jsonDeserializationContext.deserialize(jsonObject.get("data"), TypeToken.getParameterized(List.class, Track.class).getType()));
        return trackData;
    }
}
