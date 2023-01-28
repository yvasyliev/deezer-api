package api.deezer.http;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/**
 * A {@link RequestBody} wrapper for {@link InputStream}.
 */
public class InputStreamRequestBody extends RequestBody {
    /**
     * Filename.
     */
    private final String filename;

    /**
     * File.
     */
    private final InputStream file;

    public InputStreamRequestBody(String filename, InputStream file) {
        this.filename = filename;
        this.file = file;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.get(URLConnection.guessContentTypeFromName(filename));
    }

    @Override
    public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
        try (Source source = Okio.source(file)) {
            bufferedSink.writeAll(source);
        }
    }
}
