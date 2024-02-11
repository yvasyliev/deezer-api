package io.github.yvasyliev.deezer.json.deserializers;

import io.github.yvasyliev.deezer.json.exceptions.AdvancedSearchMethodDeserializeException;
import io.github.yvasyliev.deezer.methods.AbstractSearchMethod;
import io.github.yvasyliev.deezer.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.objects.AdvancedSearchPage;
import io.github.yvasyliev.deezer.objects.Pageable;
import lombok.AllArgsConstructor;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class AdvancedSearchMethodDeserializer<T extends Pageable> extends AbstractSearchMethodDeserializer<T, AdvancedSearchMethod<T>, AdvancedSearchPage<T>> {
    private static final Pattern Q_PATTERN = Pattern.compile("(\\w+):(?:\"(\\w+)\"|(\\d+))");

    private final Map<String, Supplier<? extends AdvancedSearchMethod<? extends Pageable>>> advancedSearchMethodFactories;

    @SuppressWarnings("unchecked")
    @Override
    protected AdvancedSearchMethod<T> createPagingMethod(String path, Map<String, String> queryParams) {
        for (Map.Entry<String, Supplier<? extends AdvancedSearchMethod<? extends Pageable>>> advancedSearchMethodFactory : advancedSearchMethodFactories.entrySet()) {
            if (advancedSearchMethodFactory.getKey().equals(path)) {
                return (AdvancedSearchMethod<T>) advancedSearchMethodFactory.getValue().get();
            }
        }
        throw new AdvancedSearchMethodDeserializeException(path, queryParams);
    }

    @Override
    protected void setQueryParams(AdvancedSearchMethod<T> advancedSearchMethod, Map<String, String> queryParams) {
        Matcher matcher = Q_PATTERN.matcher(queryParams.get(AbstractSearchMethod.Q));
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case AdvancedSearchMethod.ARTIST:
                    advancedSearchMethod.artist(matcher.group(2));
                    break;

                case AdvancedSearchMethod.ALBUM:
                    advancedSearchMethod.album(matcher.group(2));
                    break;

                case AdvancedSearchMethod.TRACK:
                    advancedSearchMethod.track(matcher.group(2));
                    break;

                case AdvancedSearchMethod.LABEL:
                    advancedSearchMethod.label(matcher.group(2));
                    break;

                case AdvancedSearchMethod.MAXIMUM_DURATION:
                    advancedSearchMethod.maximumDuration(duration(matcher.group(3)));
                    break;

                case AdvancedSearchMethod.MINIMUM_DURATION:
                    advancedSearchMethod.minimumDuration(duration(matcher.group(3)));
                    break;

                case AdvancedSearchMethod.MAXIMUM_BPM:
                    advancedSearchMethod.maximumBpm(duration(matcher.group(3)));
                    break;

                case AdvancedSearchMethod.MINIMUM_BPM:
                    advancedSearchMethod.minimumBpm(duration(matcher.group(3)));
                    break;
            }
        }
        super.setQueryParams(advancedSearchMethod, queryParams);
    }

    protected Duration duration(String s) {
        return Duration.ofSeconds(Long.parseLong(s));
    }
}
