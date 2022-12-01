package api.deezer.converters;

import api.deezer.objects.data.TrackData;
import com.google.gson.JsonElement;

/**
 * Converts Deezer API response to {@link TrackData} object.
 */
public class TracksDataConverter extends PojoConverter<TrackData> {
    public TracksDataConverter() {
        super(TrackData.class);
    }

    @Override
    public TrackData apply(String response) {
        JsonElement jsonElement = GSON.fromJson(response, JsonElement.class);
        boolean isFalse = jsonElement.isJsonPrimitive()
                && jsonElement.getAsJsonPrimitive().isBoolean()
                && !jsonElement.getAsJsonPrimitive().getAsBoolean();
        return isFalse ? null : super.apply(response);
    }
}
