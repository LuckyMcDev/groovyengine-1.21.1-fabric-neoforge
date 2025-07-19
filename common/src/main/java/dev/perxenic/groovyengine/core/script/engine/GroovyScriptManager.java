package dev.perxenic.groovyengine.core.script.engine;

import dev.architectury.platform.Platform;
import dev.perxenic.groovyengine.GroovyEngine;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import net.fabricmc.api.EnvType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroovyScriptManager {
    private static final Path ROOT    = Platform.getGameFolder().resolve("GroovyEngine");
    private static final Path SCRIPTS = ROOT.resolve("scripts");
    private static GroovyShell shell;

    public static void initialize() {
        try {
            createScriptFolders();
            shell = ScriptShellFactory.createSharedShell();
            loadAllScripts();
        } catch (Exception e) {
            GroovyEngine.LOGGER.error("[GroovyEngine] Error during initialization", e);
        }
    }

    public static void reloadScripts() {
        try {
            if (shell != null) {
                // Perform any necessary cleanup on the old shell
                // shell.getClassLoader().clearCache();
            }
            shell = ScriptShellFactory.createSharedShell();
            loadAllScripts();
        } catch (Exception e) {
            GroovyEngine.LOGGER.error("[GroovyEngine] Error during script reload", e);
        }
    }

    private static void createScriptFolders() {
        try {
            Files.createDirectories(SCRIPTS.resolve("common"));
            Files.createDirectories(SCRIPTS.resolve("client"));
            Files.createDirectories(SCRIPTS.resolve("server"));
        } catch (IOException e) {
            GroovyEngine.LOGGER.error("[GroovyEngine] Failed to create script folders", e);
        }
    }

    private static void loadAllScripts() {
        try {
            EnvType env = Platform.getEnv();
            runScriptsIn("common");
            if (env == EnvType.CLIENT) runScriptsIn("client");
            else                       runScriptsIn("server");
        } catch (Exception e) {
            GroovyEngine.LOGGER.error("[GroovyEngine] Error loading all scripts", e);
        }
    }

    private static void runScriptsIn(String subfolder) {
        Path folder = SCRIPTS.resolve(subfolder);
        if (!Files.exists(folder)) return;

        try (Stream<Path> paths = Files.walk(folder)) {
            List<Path> scripts = paths
                    .filter(p -> p.toString().endsWith(".groovy"))
                    .filter(p -> !ScriptMetadata.isDisabled(p))
                    .collect(Collectors.toList());

            scripts.sort(Comparator.comparingInt(ScriptMetadata::getPriority));

            for (Path script : scripts) {
                try {
                    evaluateScript(script);
                } catch (Exception e) {
                    GroovyEngine.LOGGER.error("[GroovyEngine] Error evaluating script: " + script.getFileName(), e);
                }
            }
        } catch (IOException e) {
            GroovyEngine.LOGGER.error("[GroovyEngine] Error walking scripts folder: " + subfolder, e);
        }
    }

    private static void evaluateScript(Path script) throws IOException {
        GroovyEngine.LOGGER.info("[GroovyEngine] Evaluating " + script.getFileName());
        try {
            Script compiledScript = shell.parse(script.toFile());
            compiledScript.run();
        } catch (Exception ex) {
            GroovyEngine.LOGGER.error("[GroovyEngine] Script error in " + script.getFileName(), ex);
            throw ex;
        }
    }
}