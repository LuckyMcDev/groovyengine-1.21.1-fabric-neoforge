package dev.perxenic.groovyengine.api.platform;


/**
 * Core platform abstraction API
 */
public interface PlatformService {
    boolean isClient();
    String getPlatformName();
    Platform getPlatform();

    static PlatformService getInstance() {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }

    enum Platform {
        FABRIC,
        NEOFORGE
    }
}