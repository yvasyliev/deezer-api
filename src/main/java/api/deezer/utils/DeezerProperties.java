package api.deezer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Stores Deezer API properties.
 */
public final class DeezerProperties {
    /**
     * {@link Logger} object.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DeezerProperties.class);

    /**
     * Path to properties file.
     */
    private static final String PROPERTIES_FILE = "/deezer/api.properties";

    /**
     * {@link Properties} object.
     */
    private static final Properties PROPERTIES = new Properties();

    private DeezerProperties() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    /**
     * Loads {@link DeezerProperties#PROPERTIES}
     */
    private static void loadProperties() {
        try (InputStream inputStream = DeezerProperties.class.getResourceAsStream(PROPERTIES_FILE)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("failed to load {}", PROPERTIES_FILE);
        }
    }

    /**
     * Gets Deezer API property.
     *
     * @param key property key.
     * @return property value.
     */
    public static String getProperty(String key) {
        if (PROPERTIES.isEmpty()) {
            loadProperties();
        }
        return PROPERTIES.getProperty(key);
    }
}
