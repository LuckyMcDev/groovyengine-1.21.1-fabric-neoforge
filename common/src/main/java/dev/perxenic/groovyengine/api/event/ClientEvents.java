package dev.perxenic.groovyengine.api.event;

import java.util.function.Consumer;

/**
 * Client-side events API
 */
public interface ClientEvents {
    void onClientTick(Consumer<EventContext> handler);
    void onScreenInit(Consumer<EventContext> handler);
    void onJoinWorld(Consumer<EventContext> handler);
    void onDisconnect(Consumer<EventContext> handler);
}