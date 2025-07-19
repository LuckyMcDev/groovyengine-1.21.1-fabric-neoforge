package dev.perxenic.groovyengine.api.builder;

import java.util.function.Consumer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

/**
 * Public API for block creation
 */
public interface BlockBuilderApi {
    BlockBuilderApi settings(Consumer<AbstractBlock.Settings> consumer);
    BlockBuilderApi displayName(String name);
    BlockBuilderApi texture(String path);
    BlockBuilderApi blockClass(Class<? extends Block> blockClass);
    Block build();

    static BlockBuilderApi create(String id) {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}
