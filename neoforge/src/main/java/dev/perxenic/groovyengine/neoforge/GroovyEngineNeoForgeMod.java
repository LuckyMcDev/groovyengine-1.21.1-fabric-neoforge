package dev.perxenic.groovyengine.neoforge;

import net.neoforged.fml.common.Mod;

import dev.perxenic.groovyengine.GroovyEngine;

@Mod(GroovyEngine.MODID)
public final class GroovyEngineNeoForgeMod {
    public GroovyEngineNeoForgeMod() {
        // Run our common setup.
        GroovyEngine.init();
    }
}
