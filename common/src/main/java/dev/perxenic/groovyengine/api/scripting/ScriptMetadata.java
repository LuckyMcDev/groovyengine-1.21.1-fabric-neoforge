package dev.perxenic.groovyengine.api.scripting;

/**
 * Script metadata information
 */
public interface ScriptMetadata {
    int getPriority();
    boolean isDisabled();
    String getPackage();
    String getDescription();
    String[] getDependencies();
}
