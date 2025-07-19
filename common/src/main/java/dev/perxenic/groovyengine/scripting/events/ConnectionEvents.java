package dev.perxenic.groovyengine.scripting.events;

import dev.architectury.event.events.common.PlayerEvent;
import dev.perxenic.groovyengine.scripting.events.context.EventContext;
import groovy.lang.Closure;

public class ConnectionEvents {

    public static void onClientJoin(Closure<Void> closure) {
        // For client-side join, you might want to use a client tick event or similar
        // This is a server-side event replacement
        PlayerEvent.PLAYER_JOIN.register((player) -> {
            EventContext ctx = new EventContext("server_join")
                    .withServerPlayer(player)
                    .withServer(player.getServer());
            closure.call(ctx);
        });
    }

    public static void onClientDisconnect(Closure<Void> closure) {
        // Client disconnect would need to be handled differently
        // This is server-side equivalent
        PlayerEvent.PLAYER_QUIT.register((player) -> {
            EventContext ctx = new EventContext("server_disconnect")
                    .withServerPlayer(player)
                    .withServer(player.getServer());
            closure.call(ctx);
        });
    }

    public static void onServerJoin(Closure<Void> closure) {
        PlayerEvent.PLAYER_JOIN.register((player) -> {
            EventContext ctx = new EventContext("server_join")
                    .withServerPlayer(player)
                    .withServer(player.getServer());
            closure.call(ctx);
        });
    }

    public static void onServerDisconnect(Closure<Void> closure) {
        PlayerEvent.PLAYER_QUIT.register((player) -> {
            EventContext ctx = new EventContext("server_disconnect")
                    .withServerPlayer(player)
                    .withServer(player.getServer());
            closure.call(ctx);
        });
    }
}