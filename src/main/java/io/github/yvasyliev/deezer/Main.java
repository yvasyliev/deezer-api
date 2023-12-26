package io.github.yvasyliev.deezer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Track;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        DeezerClient deezerClient = new DeezerClient();
        ObjectMapper objectMapper = deezerClient.getContext().getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(objectMapper.convertValue("https://google.com", URL.class));
        PagingMethod<Track> artistTop = deezerClient.getArtistTop(27);
        Map<String, String> map = deezerClient.getContext().getObjectMapper().convertValue(artistTop, new TypeReference<Map<String, String>>() {
        });
        System.out.println(map);
        Page<Track> page1 = deezerClient.getArtistTop(27).execute();
        System.out.println(objectMapper.writeValueAsString(page1));
        Page<Track> page2 = page1.getNext().execute();
        System.out.println(objectMapper.writeValueAsString(page2));
        Page<Track> page3 = page2.getNext().execute();
        System.out.println(objectMapper.writeValueAsString(page3));
    }
}
