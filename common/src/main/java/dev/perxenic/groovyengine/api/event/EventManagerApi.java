package dev.perxenic.groovyengine.api.event;

/**
 * Central event management API
 */
public interface EventManagerApi {
    PlayerEventsApi getPlayerEvents();
    WorldEventsApi getWorldEvents();
    ClientEventsApi getClientEvents();
    GuiEventsApi getGuiEvents();

    static EventManagerApi getInstance() {
        throw new UnsupportedOperationException("Implementation provided by platform");
    }
}