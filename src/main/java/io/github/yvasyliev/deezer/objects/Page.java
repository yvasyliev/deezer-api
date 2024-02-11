package io.github.yvasyliev.deezer.objects;

import io.github.yvasyliev.deezer.methods.PagingMethod;

/**
 * A page containing list of objects.
 *
 * @param <T> type of objects.
 */
public class Page<T extends Pageable> extends AbstractPage<T, PagingMethod<T>, Page<T>> {
}
