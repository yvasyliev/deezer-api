package api.deezer.converters;

import com.google.gson.Gson;

import java.util.function.Function;

/**
 * Converts {@link String} to {@link Pojo}.
 *
 * @param <Pojo> target type.
 */
public class PojoConverter<Pojo> implements Function<String, Pojo> {
    /**
     * {@link Gson} converter.
     */
    protected static final Gson GSON = new Gson();

    /**
     * {@link Pojo} class.
     */
    private final Class<Pojo> clazz;

    public PojoConverter(Class<Pojo> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Pojo apply(String json) {
        return GSON.fromJson(json, clazz);
    }
}
