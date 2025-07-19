package dev.perxenic.groovyengine.api.scripting;

/**
 * Logger interface for scripts
 */
public interface ScriptLogger {
    void info(String message);
    void warn(String message);
    void error(String message);
    void debug(String message);

    static ScriptLogger create(String scriptId) {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}
