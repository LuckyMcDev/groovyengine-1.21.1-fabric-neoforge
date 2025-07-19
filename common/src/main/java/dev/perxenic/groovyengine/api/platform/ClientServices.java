package dev.perxenic.groovyengine.api.platform;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.resource.ResourceManager;

/**
 * Client-specific platform services
 */
public interface ClientServices {
    void openScreen(Screen screen);
    ResourceManager getResourceManager();
    void reloadResources();

    static ClientServices getInstance() {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}