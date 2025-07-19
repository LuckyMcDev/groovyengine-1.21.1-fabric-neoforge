package dev.perxenic.groovyengine.api.event;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Clean, immutable context for all events
 */
public interface EventContextApi {
    String getEventId();

    // World context
    World getWorld();
    ServerWorld getServerWorld();
    MinecraftServer getServer();
    MinecraftClient getClient();

    // Entity context
    PlayerEntity getPlayer();
    Entity getEntity();

    // Block context
    BlockState getBlockState();
    BlockPos getPosition();
    Hand getHand();
    BlockHitResult getHitResult();

    // Builder pattern for creating contexts
    interface Builder {
        Builder withWorld(World world);
        Builder withPlayer(PlayerEntity player);
        Builder withEntity(Entity entity);
        Builder withBlock(BlockState block, BlockPos pos);
        Builder withHand(Hand hand);
        EventContextApi build();
    }
}
