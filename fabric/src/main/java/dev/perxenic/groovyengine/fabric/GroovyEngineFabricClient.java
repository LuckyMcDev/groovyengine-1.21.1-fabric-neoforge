package dev.perxenic.groovyengine.fabric;

import dev.perxenic.groovyengine.GroovyEngineClient;
import net.fabricmc.api.ClientModInitializer;

public final class GroovyEngineFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GroovyEngineClient.init();
    }
}
