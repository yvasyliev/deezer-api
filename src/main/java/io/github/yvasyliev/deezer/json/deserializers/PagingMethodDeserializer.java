package io.github.yvasyliev.deezer.json.deserializers;

import io.github.yvasyliev.deezer.json.exceptions.PagingMethodDeserializeException;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class PagingMethodDeserializer<T extends Pageable> extends AbstractPagingMethodDeserializer<T, PagingMethod<T>, Page<T>> {
    private final Map<Pattern, Function<Long, ? extends PagingMethod<? extends Pageable>>> pagingMethodFactories;

    @SuppressWarnings("unchecked")
    @Override
    protected PagingMethod<T> createPagingMethod(String path, Map<String, String> queryParams) {
        for (Map.Entry<Pattern, Function<Long, ? extends PagingMethod<? extends Pageable>>> pagingMethodFactory : pagingMethodFactories.entrySet()) {
            Matcher matcher = pagingMethodFactory.getKey().matcher(path);
            if (matcher.matches()) {
                Long objectId = matcher.groupCount() > 0 ? Long.parseLong(matcher.group(1)) : null;
                return (PagingMethod<T>) pagingMethodFactory.getValue().apply(objectId);
            }
        }
        throw new PagingMethodDeserializeException(path, queryParams);
    }
}
