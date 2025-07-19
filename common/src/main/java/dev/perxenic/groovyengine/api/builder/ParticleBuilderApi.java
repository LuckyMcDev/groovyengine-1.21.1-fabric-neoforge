package dev.perxenic.groovyengine.api.builder;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.math.Vec3d;

/**
 * Public API for particle creation
 */
public interface ParticleBuilderApi {
    ParticleBuilderApi setType(ParticleType<?> type);
    ParticleBuilderApi setParticleData(ParticleEffect data);
    ParticleBuilderApi setPosition(Vec3d pos);
    ParticleBuilderApi setVelocity(double x, double y, double z);
    ParticleBuilderApi setColor(float r, float g, float b);
    ParticleBuilderApi setScale(float scale);
    ParticleBuilderApi setCount(int count);
    void build();

    static ParticleBuilderApi create() {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}