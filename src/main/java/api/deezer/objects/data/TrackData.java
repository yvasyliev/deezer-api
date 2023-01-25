package api.deezer.objects.data;

import api.deezer.deserializers.TrackDataDeserializer;
import api.deezer.objects.Track;
import com.google.gson.annotations.JsonAdapter;

/**
 * List of tracks.
 */
@JsonAdapter(TrackDataDeserializer.class)
public class TrackData extends Data<Track> {
}
