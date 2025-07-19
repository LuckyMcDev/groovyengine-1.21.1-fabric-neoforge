package dev.perxenic.groovyengine.api.event;

import java.util.function.Consumer;

/**
 * Client-side events API
 */
public interface ClientEventsApi {
    void onClientTick(Consumer<EventContextApi> handler);
    void onScreenInit(Consumer<EventContextApi> handler);
    void onJoinWorld(Consumer<EventContextApi> handler);
    void onDisconnect(Consumer<EventContextApi> handler);
}