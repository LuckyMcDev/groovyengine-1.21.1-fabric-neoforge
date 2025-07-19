package dev.perxenic.groovyengine.api.scripting;

import java.util.Set;

/**
 * Script security and sandboxing API
 */
public interface ScriptSecurity {
    void allowClass(String className);
    void denyClass(String className);
    void allowPackage(String packageName);
    void denyPackage(String packageName);
    Set<String> getAllowedClasses();
    Set<String> getAllowedPackages();

    static ScriptSecurity getInstance() {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}
