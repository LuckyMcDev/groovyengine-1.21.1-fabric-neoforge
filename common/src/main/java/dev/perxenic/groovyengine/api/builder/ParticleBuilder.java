package dev.perxenic.groovyengine.api.builder;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.math.Vec3d;

/**
 * Public API for particle creation
 */
public interface ParticleBuilder {
    ParticleBuilder setType(ParticleType<?> type);
    ParticleBuilder setParticleData(ParticleEffect data);
    ParticleBuilder setPosition(Vec3d pos);
    ParticleBuilder setVelocity(double x, double y, double z);
    ParticleBuilder setColor(float r, float g, float b);
    ParticleBuilder setScale(float scale);
    ParticleBuilder setCount(int count);
    void build();

    static ParticleBuilder create() {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}