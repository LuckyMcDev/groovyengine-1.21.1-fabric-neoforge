package dev.perxenic.groovyengine.api.event;

import net.minecraft.util.ActionResult;
import java.util.function.Function;

/**
 * Player-related events API
 */
public interface PlayerEventsApi {
    void onBlockBreak(Function<EventContextApi, ActionResult> handler);
    void onBlockUse(Function<EventContextApi, ActionResult> handler);
    void onItemUse(Function<EventContextApi, ActionResult> handler);
    void onEntityUse(Function<EventContextApi, ActionResult> handler);
    void onEntityAttack(Function<EventContextApi, ActionResult> handler);
    void onBlockAttack(Function<EventContextApi, ActionResult> handler);
}
