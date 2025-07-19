package dev.perxenic.groovyengine.scripting.events;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.perxenic.groovyengine.scripting.events.context.EventContext;
import groovy.lang.Closure;

public class WorldEvents {

    public static void onLoad(Closure<Void> closure) {
        LifecycleEvent.SERVER_LEVEL_LOAD.register(world -> {
            EventContext ctx = new EventContext("world_load")
                    .withServer(world.getServer())
                    .withServerWorld(world);
            closure.call(ctx);
        });
    }

    public static void onUnload(Closure<Void> closure) {
        LifecycleEvent.SERVER_LEVEL_UNLOAD.register(world -> {
            EventContext ctx = new EventContext("world_unload")
                    .withServer(world.getServer())
                    .withServerWorld(world);
            closure.call(ctx);
        });
    }
}