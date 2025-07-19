package dev.perxenic.groovyengine.api.scripting;

/**
 * Registry for sharing data between scripts
 */
public interface GlobalRegistry {
    void put(String key, Object value);
    <T> T get(String key, Class<T> type);
    boolean has(String key);
    void remove(String key);
    void clear();

    static GlobalRegistry getInstance() {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}
