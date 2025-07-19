package dev.perxenic.groovyengine.api.event;

import java.util.function.Consumer;

/**
 * GUI-related events API
 */
public interface GuiEvents {
    void onScreenInit(Consumer<EventContext> handler);
    void onTooltip(Consumer<EventContext> handler);
    void onRender(Consumer<EventContext> handler);
}