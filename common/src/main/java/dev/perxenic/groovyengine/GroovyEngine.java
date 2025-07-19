package dev.perxenic.groovyengine;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.ReloadListenerRegistry;
import dev.perxenic.groovyengine.util.logging.LogCapture;
import dev.perxenic.groovyengine.datagen.OpenloaderConfigPatcher;
import dev.perxenic.groovyengine.datagen.generator.GroovyEnginePackRootGenerator;
import dev.perxenic.groovyengine.datagen.generator.ResourcepackGenerator;
import dev.perxenic.groovyengine.core.script.engine.GroovyScriptManager;
import dev.perxenic.groovyengine.util.mapping.MappingResolver;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReload;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.profiler.Profiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

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
    }
}