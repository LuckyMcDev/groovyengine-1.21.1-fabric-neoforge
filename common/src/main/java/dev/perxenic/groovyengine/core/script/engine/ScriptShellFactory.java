package dev.perxenic.groovyengine.core.script.engine;

import dev.architectury.platform.Platform;
import dev.perxenic.groovyengine.GroovyEngine;
import dev.perxenic.groovyengine.core.builders.BlockBuilder;
import dev.perxenic.groovyengine.core.builders.ItemBuilder;
import dev.perxenic.groovyengine.core.builders.RecipeBuilder;
import dev.perxenic.groovyengine.core.builders.particle.GroovyParticleTypes;
import dev.perxenic.groovyengine.core.builders.particle.ParticleBuilder;
import dev.perxenic.groovyengine.core.events.*;
import dev.perxenic.groovyengine.scripting.gui.GuiBinding;
import dev.perxenic.groovyengine.scripting.input.KeysBinding;
import dev.perxenic.groovyengine.scripting.utils.Globals;
import dev.perxenic.groovyengine.scripting.utils.GroovyEngineScriptUtils;
import dev.perxenic.groovyengine.scripting.utils.GroovyLogger;
import dev.perxenic.groovyengine.util.RegistryHelper;
import dev.perxenic.groovyengine.api.addon.ShellBindingEvents;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import imgui.ImGui;
import net.fabricmc.api.EnvType;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class ScriptShellFactory {
    public static GroovyClassLoader createClassLoader() {
        return new GroovyClassLoader(ScriptShellFactory.class.getClassLoader());
    }

    public static Binding createBinding() {
        Binding binding = new Binding();
        binding.setVariable("Logger", new GroovyLogger("Script"));
        binding.setVariable("GeUtils", GroovyEngineScriptUtils.class);
        binding.setVariable("UUID", UUID.class);
        binding.setVariable("Duration", Duration.class);
        binding.setVariable("Math", Math.class);

        if (Platform.getEnv() == EnvType.CLIENT) {
            binding.setVariable("Gui", GuiBinding.class);
            binding.setVariable("ImGui", ImGui.class);
            binding.setVariable("Keys", KeysBinding.class);
        }

        // Event classes
        binding.setVariable("ConnectionEvents", ConnectionEvents.class);
        binding.setVariable("GuiEventsApi", GuiEvents.class);
        binding.setVariable("PlayerEventsApi", PlayerEvents.class);
        binding.setVariable("TickEvents", TickEvents.class);
        binding.setVariable("WorldEventsApi", WorldEvents.class);

        // Builders & registries
        RegistryHelper<Item> itemHelper = new RegistryHelper<>(Registries.ITEM, GroovyEngine.MODID);
        ItemBuilder.setSharedHelper(itemHelper);
        binding.setVariable("ItemBuilderApi", ItemBuilder.class);

        RegistryHelper<Block> blockHelper = new RegistryHelper<>(Registries.BLOCK, GroovyEngine.MODID);
        BlockBuilder.setSharedHelper(blockHelper);
        binding.setVariable("BlockBuilderApi", BlockBuilder.class);

        binding.setVariable("RegistryHelper", RegistryHelper.class);

        binding.setVariable("RecipeBuilderApi", RecipeBuilder.class);

        // Vanilla shortcuts
        binding.setVariable("Item", Item.class);
        binding.setVariable("ItemGroups", ItemGroups.class);
        binding.setVariable("ItemSettings", Item.Settings.class);
        binding.setVariable("Block", Block.class);
        binding.setVariable("BlockSettings", net.minecraft.block.AbstractBlock.Settings.class);
        binding.setVariable("Fluid", Fluid.class);

        binding.setVariable("ParticleBuilderApi", ParticleBuilder.class);
        binding.setVariable("GroovyParticleTypes", GroovyParticleTypes.class);

        binding.setVariable("Globals", Globals.class);

        ShellBindingEvents.BINDING_READY.invoker().onBindingReady(binding);
        return binding;
    }

    public static CompilerConfiguration createCompilerConfig() {
        CompilerConfiguration config = new CompilerConfiguration();
        ImportCustomizer imports = new ImportCustomizer();
        imports.addStarImports(
                "java.lang", "java.util", "net.minecraft", "net.minecraft.util",
                "net.minecraft.item", "net.minecraft.block", "net.minecraft.entity",
                "net.minecraft.text", "com.mojang.brigadier", "net.minecraft.fluid"
        );
        config.addCompilationCustomizers(imports);

        SecureASTCustomizer secure = new SecureASTCustomizer();
        secure.setClosuresAllowed(true);
        secure.setMethodDefinitionAllowed(true);
        secure.setDisallowedImports(List.of(
                "java.io.*", "java.net.*", "javax.*", "sun.*", "com.sun.*", "jdk.*"
        ));
        secure.setDisallowedReceivers(List.of("System", "Runtime", "Thread", "Class"));
        config.addCompilationCustomizers(secure);

        return config;
    }

    /** Build a shared shell for all scripts */
    public static GroovyShell createSharedShell() {
        return new GroovyShell(
                createClassLoader(),
                createBinding(),
                createCompilerConfig()
        );
    }
}