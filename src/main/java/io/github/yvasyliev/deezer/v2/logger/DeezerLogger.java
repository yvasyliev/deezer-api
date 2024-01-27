package io.github.yvasyliev.deezer.v2.logger;

import feign.slf4j.Slf4jLogger;

// TODO remove
public class DeezerLogger extends Slf4jLogger {
    @Override
    protected boolean shouldLogResponseHeader(String header) {
        return false;
    }
}
