package io.github.yvasyliev.deezer.methods;

import io.github.yvasyliev.deezer.objects.AdvancedSearchPage;
import io.github.yvasyliev.deezer.objects.Order;
import io.github.yvasyliev.deezer.objects.Pageable;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Setter
@Accessors(fluent = true)
public class AdvancedSearchMethod<T extends Pageable> extends AbstractSearchMethod<T, AdvancedSearchMethod<T>, AdvancedSearchPage<T>> {
    public static final String ARTIST = "artist";
    public static final String ALBUM = "album";
    public static final String TRACK = "track";
    public static final String LABEL = "label";
    public static final String MAXIMUM_DURATION = "dur_max";
    public static final String MINIMUM_DURATION = "dur_min";
    public static final String MAXIMUM_BPM = "bpm_max";
    public static final String MINIMUM_BPM = "bpm_min";

    private static final String COLON = ":";
    private static final String SEARCH_STRING_TEMPLATE = "\"%s\"";
    private static final String WHITESPACE = " ";

    private String artist;
    private String album;
    private String track;
    private String label;
    private Duration maximumDuration;
    private Duration minimumDuration;
    private Duration maximumBpm;
    private Duration minimumBpm;

    public AdvancedSearchMethod(Function<Map<String, Object>, AdvancedSearchPage<T>> invoker, Function<Map<String, Object>, CompletableFuture<AdvancedSearchPage<T>>> asyncInvoker) {
        super(invoker, asyncInvoker);
    }

    @Override
    public AdvancedSearchMethod<T> strict(boolean strict) {
        super.strict(strict);
        return this;
    }

    @Override
    public AdvancedSearchMethod<T> order(Order order) {
        super.order(order);
        return this;
    }

    @Override
    protected String getQ() {
        StringBuilder qBuilder = new StringBuilder();
        if (notEmpty(artist)) {
            qBuilder.append(ARTIST).append(COLON).append(str(artist)).append(WHITESPACE);
        }
        if (notEmpty(album)) {
            qBuilder.append(ALBUM).append(COLON).append(str(album)).append(WHITESPACE);
        }
        if (notEmpty(track)) {
            qBuilder.append(TRACK).append(COLON).append(str(track)).append(WHITESPACE);
        }
        if (notEmpty(label)) {
            qBuilder.append(LABEL).append(COLON).append(str(label)).append(WHITESPACE);
        }
        if (maximumDuration != null) {
            qBuilder.append(MAXIMUM_DURATION).append(COLON).append(maximumDuration).append(WHITESPACE);
        }
        if (minimumDuration != null) {
            qBuilder.append(MINIMUM_DURATION).append(COLON).append(minimumDuration).append(WHITESPACE);
        }
        if (maximumBpm != null) {
            qBuilder.append(MAXIMUM_BPM).append(COLON).append(maximumBpm).append(WHITESPACE);
        }
        if (minimumBpm != null) {
            qBuilder.append(MINIMUM_BPM).append(COLON).append(minimumBpm).append(WHITESPACE);
        }
        return qBuilder.length() > 0 ? qBuilder.toString().trim() : null;
    }

    private boolean notEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    private String str(String s) {
        return String.format(SEARCH_STRING_TEMPLATE, s);
    }
}
