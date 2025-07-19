package dev.perxenic.groovyengine.core.events;

import dev.architectury.event.events.client.ClientGuiEvent;
import dev.architectury.event.events.client.ClientTooltipEvent;
import dev.perxenic.groovyengine.core.events.context.EventContext;
import groovy.lang.Closure;

public class GuiEvents {

    public static void onScreenInit(Closure<Void> closure) {
        ClientGuiEvent.INIT_POST.register((screen, access) -> {
            EventContext ctx = new EventContext("screen_init")
                    .withScreen(screen)
                    .withScreenSize(screen.width, screen.height);
            closure.call(ctx);
        });
    }

    public static void onTooltip(Closure<Void> closure) {
        ClientTooltipEvent.ITEM.register((stack, lines, tooltipContext, flag) -> {
            EventContext ctx = new EventContext("item_tooltip")
                    .withItemStack(stack)
                    .withTooltipLines(lines);
            closure.call(ctx);
        });
    }
}