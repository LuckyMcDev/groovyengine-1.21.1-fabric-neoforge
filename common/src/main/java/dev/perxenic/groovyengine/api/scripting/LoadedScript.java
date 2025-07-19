package dev.perxenic.groovyengine.api.scripting;

import java.nio.file.Path;
import java.time.Instant;

/**
 * Represents a loaded script
 */
public interface LoadedScript {
    String getName();

    String getId();
    Path getPath();
    ScriptMetadata getMetadata();
    ScriptState getState();

    Object getResult();

    long getLoadTime();

    enum ScriptState {
        LOADED,
        RUNNING,
        ERRORED,
        DISABLED
    }
}
