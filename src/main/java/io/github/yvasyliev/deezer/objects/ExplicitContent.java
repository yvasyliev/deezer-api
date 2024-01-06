package io.github.yvasyliev.deezer.objects;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExplicitContent {
    NOT_EXPLICIT(0),
    EXPLICIT(1),
    UNKNOWN(2),
    EDITED(3),
    NO_ADVICE_AVAILABLE(6);

    private final int id;

    @JsonValue
    public int getId() {
        return id;
    }
}
