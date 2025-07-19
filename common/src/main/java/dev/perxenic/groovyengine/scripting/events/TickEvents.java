package dev.perxenic.groovyengine.scripting.events;

import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.event.events.common.TickEvent;
import dev.perxenic.groovyengine.scripting.events.context.EventContext;
import groovy.lang.Closure;

public class TickEvents {

    public static void onStartClientTick(Closure<Void> closure) {
        ClientTickEvent.CLIENT_PRE.register(client -> {
            EventContext ctx = new EventContext("client_tick_start")
                    .withClient(client);
            closure.call(ctx);
        });
    }

    public static void onEndClientTick(Closure<Void> closure) {
        ClientTickEvent.CLIENT_POST.register(client -> {
            EventContext ctx = new EventContext("client_tick_end")
                    .withClient(client);
            closure.call(ctx);
        });
    }

    public static void onStartServerTick(Closure<Void> closure) {
        TickEvent.SERVER_PRE.register(server -> {
            EventContext ctx = new EventContext("server_tick_start")
                    .withServer(server);
            closure.call(ctx);
        });
    }

    public static void onEndServerTick(Closure<Void> closure) {
        TickEvent.SERVER_POST.register(server -> {
            EventContext ctx = new EventContext("server_tick_end")
                    .withServer(server);
            closure.call(ctx);
        });
    }
}