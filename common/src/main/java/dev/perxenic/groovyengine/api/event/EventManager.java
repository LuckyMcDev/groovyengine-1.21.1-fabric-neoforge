package dev.perxenic.groovyengine.api.event;

/**
 * Central event management API
 */
public interface EventManager {
    PlayerEvents getPlayerEvents();
    WorldEvents getWorldEvents();
    ClientEvents getClientEvents();
    GuiEvents getGuiEvents();

    static EventManager getInstance() {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}