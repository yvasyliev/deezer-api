package io.github.yvasyliev.deezer.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.github.yvasyliev.deezer.objects.Picture;

import java.lang.reflect.Type;
import java.net.URL;

public class PictureDeserializer implements JsonDeserializer<Picture> {
    @Override
    public Picture deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Picture(context.deserialize(json, URL.class));
    }
}
