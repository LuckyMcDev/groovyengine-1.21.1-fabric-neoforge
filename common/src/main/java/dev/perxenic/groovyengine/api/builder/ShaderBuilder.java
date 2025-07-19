package dev.perxenic.groovyengine.api.builder;

/**
 * Public API for shader creation
 */
public interface ShaderBuilder {
    ShaderBuilder path(String shaderPath);

    ShaderBuilder enable();

    ShaderBuilder disable();

    void build();

    static ShaderBuilder create(String id) {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}

