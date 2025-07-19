package dev.perxenic.groovyengine;

import dev.perxenic.groovyengine.api.platform.PlatformService;
import dev.perxenic.groovyengine.api.scripting.ScriptEngine;
import dev.perxenic.groovyengine.api.platform.ResourceServices;
import dev.perxenic.groovyengine.logging.LogCapture;
import dev.perxenic.groovyengine.packs.OpenloaderConfigPatcher;
import dev.perxenic.groovyengine.packs.structure.GroovyEnginePackRootGenerator;
import dev.perxenic.groovyengine.packs.structure.ResourcepackGenerator;
import dev.perxenic.groovyengine.scripting.core.GroovyScriptManager;
import dev.perxenic.groovyengine.util.mapping.MappingResolver;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

public final class GroovyEngine {
    public static final String MODID = "groovyengine";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static void init() {
        LogCapture.hookLog4j();
        OpenloaderConfigPatcher.patch();

        LOGGER.info("Generating Pack Structure");
        GroovyEnginePackRootGenerator.generate();
        ResourcepackGenerator.generate();

        MappingResolver resolver = new MappingResolver();
        try {
            resolver.loadAllMappings("/assets/groovyengine/mappings/");
            String obfItemClass = resolver.getObfClass("net.minecraft.item.Item");
            String obfBlockClass = resolver.getObfClass("net.minecraft.block.Block");
            System.out.println("Item Obfuscated class: " + obfItemClass);
            System.out.println("Block Obfuscated class: " + obfBlockClass);
        } catch (IOException e) {
            e.printStackTrace();
        }


        LOGGER.info("Loading Scripts");
        GroovyScriptManager.initialize();
        GroovyScriptManager.reloadScripts();

    }


    private void onDataPackReloadStart(MinecraftServer server, ResourceManager resourceManager) {
        LOGGER.info("GroovyEngine: Scripts reload is starting");
        GroovyScriptManager.reloadScripts();
    }
}