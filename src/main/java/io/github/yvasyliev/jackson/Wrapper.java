package io.github.yvasyliev.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class Wrapper<T extends Wrappable> {
    @JsonDeserialize(using = WrappedListDeserializer.class)
    private List<T> wrappedList;

    public List<T> getWrappedList() {
        return wrappedList;
    }
}
