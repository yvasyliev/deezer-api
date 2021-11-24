package api.deezer.converters;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Converts list of objects to comma separated values.
 *
 * @param <T> object type.
 */
public class ListConverter<T> implements Converter<List<T>, String> {
    @Override
    public String covert(List<T> list) {
        return list.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
