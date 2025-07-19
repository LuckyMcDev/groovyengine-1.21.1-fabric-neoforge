package dev.perxenic.groovyengine.api.event;


import java.util.function.Consumer;

/**
 * World-related events API
 */
public interface WorldEvents {
    void onLoad(Consumer<EventContext> handler);
    void onUnload(Consumer<EventContext> handler);
    void onTick(Consumer<EventContext> handler);
}
