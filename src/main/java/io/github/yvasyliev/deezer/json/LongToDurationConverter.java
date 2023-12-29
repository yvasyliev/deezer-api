package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Duration;

public class LongToDurationConverter extends StdConverter<Long, Duration> {
    @Override
    public Duration convert(Long value) {
        return Duration.ofSeconds(value);
    }
}
