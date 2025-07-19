package dev.perxenic.groovyengine.api.platform;

import java.nio.file.Path;

/**
 * Resource and file management services
 */
public interface ResourceServices {
    Path getConfigDirectory();
    Path getModDirectory();
    Path getScriptsDirectory();
    void reloadDataPacks();
    void reloadResourcePacks();

    static ResourceServices getInstance() {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}
