package dev.perxenic.groovyengine.api.builder;

import java.util.function.Consumer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

/**
 * Public API for block creation
 */
public interface BlockBuilder {
    BlockBuilder settings(Consumer<AbstractBlock.Settings> consumer);
    BlockBuilder displayName(String name);
    BlockBuilder texture(String path);
    BlockBuilder blockClass(Class<? extends Block> blockClass);
    Block build();

    static BlockBuilder create(String id) {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}
