package dev.perxenic.groovyengine.api.event;

import net.minecraft.util.ActionResult;
import java.util.function.Function;

/**
 * Player-related events API
 */
public interface PlayerEvents {
    void onBlockBreak(Function<EventContext, ActionResult> handler);
    void onBlockUse(Function<EventContext, ActionResult> handler);
    void onItemUse(Function<EventContext, ActionResult> handler);
    void onEntityUse(Function<EventContext, ActionResult> handler);
    void onEntityAttack(Function<EventContext, ActionResult> handler);
    void onBlockAttack(Function<EventContext, ActionResult> handler);
}
