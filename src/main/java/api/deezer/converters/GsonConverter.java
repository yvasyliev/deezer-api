package api.deezer.converters;

import com.google.gson.Gson;

/**
 * Converts Deezer API response to POJO. Implementation is based on {@link Gson}.
 *
 * @param <Response> POJO type.
 */
public class GsonConverter<Response> implements Converter<String, Response> {
    /**
     * POJO type.
     */
    private final Class<Response> clazz;

    /**
     * {@link Gson} object.
     */
    private Gson gson = new Gson();

    public GsonConverter(Class<Response> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Response covert(String response) {
        return gson.fromJson(response, clazz);
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
