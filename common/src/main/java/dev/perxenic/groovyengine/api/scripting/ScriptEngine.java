package dev.perxenic.groovyengine.api.scripting;

import java.nio.file.Path;
import java.util.List;

/**
 * Main scripting engine API
 */
public interface ScriptEngine {
    void loadScript(Path scriptPath);
    void loadScripts(Path directory);
    void reloadScripts();
    List<LoadedScript> getLoadedScripts();
    void executeScript(String scriptContent);

    static ScriptEngine getInstance() {
        return null;
    }
}