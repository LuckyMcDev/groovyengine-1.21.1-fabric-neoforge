package dev.perxenic.groovyengine.util.events;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;

public interface ShellBindingEvents {
    /**
     * Called after all default bindings are set but before shell creation
     */
    Event<BindingReady> BINDING_READY = EventFactory.createLoop(BindingReady.class);

    @FunctionalInterface
    interface BindingReady {
        void onBindingReady(Object binding);
    }
}