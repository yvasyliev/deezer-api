package io.github.yvasyliev.deezer.json;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Duration;

public class DurationToLongConverter extends StdConverter<Duration, Long> {
    @Override
    public Long convert(Duration value) {
        return value.getSeconds();
    }
}
