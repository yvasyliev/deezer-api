package api.deezer.converters;

import api.deezer.objects.Permission;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Converts list of {@link Permission} into comma separated values.
 */
public class PermissionsConverter extends ListConverter<Permission> {
    @Override
    public String apply(List<Permission> list) {
        return list.stream().map(Permission::getValue).collect(Collectors.joining(","));
    }
}
