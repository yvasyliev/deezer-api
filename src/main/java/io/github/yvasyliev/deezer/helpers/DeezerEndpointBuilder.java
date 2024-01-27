package io.github.yvasyliev.deezer.helpers;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeezerEndpointBuilder {
    private final StringBuilder stringBuilder;

    public DeezerEndpointBuilder(String baseHost) {
        this(new StringBuilder(baseHost));
    }

    public DeezerEndpointBuilder longFragment(long longFragment) {
        return append(longFragment);
    }

    public DeezerEndpointBuilder fans() {
        return append("/fans");
    }

    public DeezerEndpointBuilder album() {
        return append("/album");
    }

    public String build() {
        return stringBuilder.toString();
    }

    private DeezerEndpointBuilder append(long longFragment) {
        this.stringBuilder.append(longFragment);
        return this;
    }

    private DeezerEndpointBuilder append(String stringFragment) {
        this.stringBuilder.append(stringFragment);
        return this;
    }
}
