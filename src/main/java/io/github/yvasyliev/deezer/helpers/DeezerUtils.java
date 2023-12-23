package io.github.yvasyliev.deezer.helpers;

import lombok.Cleanup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class DeezerUtils {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int EOF = -1;
    private static final int WRITE_OFFSET = 0;

    private DeezerUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static byte[] readAllBytes(InputStream from) throws IOException {
        @Cleanup ByteArrayOutputStream to = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int len;
        while ((len = from.read(buffer)) > EOF) {
            to.write(buffer, WRITE_OFFSET, len);
        }
        return to.toByteArray();
    }
}
