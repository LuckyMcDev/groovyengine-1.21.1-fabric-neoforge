package dev.perxenic.groovyengine.api.event;

import java.util.function.Consumer;

/**
 * GUI-related events API
 */
public interface GuiEventsApi {
    void onScreenInit(Consumer<EventContextApi> handler);
    void onTooltip(Consumer<EventContextApi> handler);
    void onRender(Consumer<EventContextApi> handler);
}