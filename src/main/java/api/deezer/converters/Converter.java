package api.deezer.converters;

/**
 * Converts object from type of {@link From} to type of {@link To}.
 *
 * @param <From> source object type.
 * @param <To>   destination object type.
 */
public interface Converter<From, To> {
    /**
     * Converts object from type of {@link From} to type of {@link To}.
     *
     * @param from original object.
     * @return converted object.
     */
    To covert(From from);
}
