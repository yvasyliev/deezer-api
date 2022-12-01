package api.deezer.converters;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converts list of objects to comma separated values.
 *
 * @param <T> object type.
 */
public class ListConverter<T> implements Function<List<T>, String> {
    @Override
    public String apply(List<T> list) {
        return list.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
