package dev.perxenic.groovyengine.neoforge;

import dev.perxenic.groovyengine.GroovyEngine;
import dev.perxenic.groovyengine.GroovyEngineClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = GroovyEngine.MODID, dist = Dist.CLIENT)
public final class GroovyEngineNeoForgeClient {

    public GroovyEngineNeoForgeClient() {
        // Run our common setup.
        GroovyEngineClient.init();
    }
}
