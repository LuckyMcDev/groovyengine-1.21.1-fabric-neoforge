package dev.perxenic.groovyengine.api.event;


import java.util.function.Consumer;

/**
 * World-related events API
 */
public interface WorldEventsApi {
    void onLoad(Consumer<EventContextApi> handler);
    void onUnload(Consumer<EventContextApi> handler);
    void onTick(Consumer<EventContextApi> handler);
}
