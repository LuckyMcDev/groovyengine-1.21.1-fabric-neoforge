package dev.perxenic.groovyengine.api.builder;

import java.util.function.Consumer;
import net.minecraft.item.Item;

/**
 * Public API for item creation
 */
public interface ItemBuilder {
    ItemBuilder settings(Consumer<Item.Settings> consumer);
    ItemBuilder displayName(String name);
    ItemBuilder texture(String path);
    Item build();

    static ItemBuilder create(String id) {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}
