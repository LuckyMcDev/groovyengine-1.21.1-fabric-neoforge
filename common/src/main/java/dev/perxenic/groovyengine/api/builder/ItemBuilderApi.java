package dev.perxenic.groovyengine.api.builder;

import java.util.function.Consumer;
import net.minecraft.item.Item;

/**
 * Public API for item creation
 */
public interface ItemBuilderApi {
    ItemBuilderApi settings(Consumer<Item.Settings> consumer);
    ItemBuilderApi displayName(String name);
    ItemBuilderApi texture(String path);
    Item build();

    static ItemBuilderApi create(String id) {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}
