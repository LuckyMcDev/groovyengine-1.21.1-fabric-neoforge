package dev.perxenic.groovyengine.scripting.events;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.perxenic.groovyengine.scripting.events.context.EventContext;
import groovy.lang.Closure;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;

public class PlayerEvents {
    /*
    private static <T> CompoundEventResult<T> fromTypedAction(TypedActionResult<T> tar) {
        T value = tar.getValue();
        switch (tar.getResult()) {
            case SUCCESS:
            case CONSUME:
                return CompoundEventResult.interrupt(true, value);
            case FAIL:
                return CompoundEventResult.interrupt(false, value);
            case PASS:
            default:
                return CompoundEventResult.pass();
        }
    }

    public static void onBlockBreak(Closure<?> closure) {
        BlockEvent.BREAK.register((level, pos, state, player, xp) -> {
            EventContext ctx = new EventContext("block_break")
                    .withWorld(player.getWorld())
                    .withPos(pos)
                    .withBlockState(state)
                    .withPlayer(player);

            Object r = closure.call(ctx);
            if (r instanceof EventResult) {
                return (EventResult) r;
            }
            if (r instanceof ActionResult) {
                return fromTypedAction((ActionResult) r);
            }
            return EventResult.pass();
        });
    }

    public static void onBlockUse(Closure<?> closure) {
        InteractionEvent.RIGHT_CLICK_BLOCK.register((player, hand, pos, face) -> {
            EventContext ctx = new EventContext("block_use")
                    .withPlayer(player)
                    .withWorld(player.getWorld())
                    .withHand(hand)
                    .withPos(pos)
                    .withDirection(face);

            Object r = closure.call(ctx);
            if (r instanceof EventResult) {
                return (EventResult) r;
            }
            if (r instanceof ActionResult) {
                return fromTypedAction(r);
            }
            return EventResult.pass();
        });
    }

    public static void onItemUse(Closure<?> closure) {
        InteractionEvent.RIGHT_CLICK_ITEM.register((player, hand) -> {
            EventContext ctx = new EventContext("item_use")
                    .withPlayer(player)
                    .withWorld(player.getWorld())
                    .withHand(hand);

            Object r = closure.call(ctx);

            if (r instanceof CompoundEventResult) {
                //noinspection unchecked
                return (CompoundEventResult<ItemStack>) r;
            }
            if (r instanceof TypedActionResult) {
                //noinspection unchecked
                return fromTypedAction((TypedActionResult<ItemStack>) r);
            }
            if (r instanceof ActionResult) {
                ActionResult ar = (ActionResult) r;
                ItemStack stack = player.getStackInHand(hand);
                return fromTypedAction(new TypedActionResult<>(ar, stack));
            }
            // Default PASS + unchanged stack
            return CompoundEventResult.pass();
        });
    }

    public static void onEntityUse(Closure<?> closure) {
        InteractionEvent.INTERACT_ENTITY.register((player, entity, hand) -> {
            EventContext ctx = new EventContext("entity_use")
                    .withPlayer(player)
                    .withWorld(player.getWorld())
                    .withHand(hand)
                    .withEntity(entity);

            Object r = closure.call(ctx);
            if (r instanceof EventResult) {
                return (EventResult) r;
            }
            if (r instanceof ActionResult) {
                return fromActionResult((ActionResult) r);
            }
            return EventResult.pass();
        });
    }

    public static void onEntityAttack(Closure<?> closure) {
        PlayerEvent.ATTACK_ENTITY.register((player, level, target, hand, result) -> {
            EventContext ctx = new EventContext("entity_attack")
                    .withPlayer(player)
                    .withWorld(player.getWorld())
                    .withHand(hand)
                    .withEntity(target);

            Object r = closure.call(ctx);
            if (r instanceof EventResult) {
                return (EventResult) r;
            }
            if (r instanceof ActionResult) {
                return fromActionResult((ActionResult) r);
            }
            return EventResult.pass();
        });
    }

    public static void onBlockAttack(Closure<?> closure) {
        InteractionEvent.LEFT_CLICK_BLOCK.register((player, hand, pos, face) -> {
            EventContext ctx = new EventContext("block_attack")
                    .withPlayer(player)
                    .withWorld(player.getWorld())
                    .withPos(pos);

            Object r = closure.call(ctx);
            if (r instanceof EventResult) {
                return (EventResult) r;
            }
            if (r instanceof ActionResult) {
                return fromActionResult((ActionResult) r);
            }
            return EventResult.pass();
        });
    }
    */
}
