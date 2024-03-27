package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.block.AetherPortal;
import com.gildedgames.aether.registry.AetherBlocks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.event.world.BlockSetEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class BlockPlacementListener
{

    @EventListener
    private static void blockSet(BlockSetEvent event)
    {
        if (
                (event.blockState.getBlock().id == Block.WATER.id || event.blockState.getBlock().id == Block.FLOWING_WATER.id) &&
                        event.world.getBlockId(event.x, event.y - 1, event.z) == Block.GLOWSTONE.id &&
                        ((AetherPortal) AetherBlocks.AETHER_PORTAL).method_736(event.world, event.x, event.y, event.z)
        )
        {
            event.cancel();
            event.world.setBlock(event.x, event.y, event.z, AetherBlocks.AETHER_PORTAL.id);
        }
    }
}
