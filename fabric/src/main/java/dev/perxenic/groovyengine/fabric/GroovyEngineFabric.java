package dev.perxenic.groovyengine.fabric;

import net.fabricmc.api.ModInitializer;

import dev.perxenic.groovyengine.GroovyEngine;

public final class GroovyEngineFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GroovyEngine.init();
    }
}
