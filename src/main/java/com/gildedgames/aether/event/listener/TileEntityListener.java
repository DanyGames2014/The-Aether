package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.entity.tile.TileEntityEnchanter;
import com.gildedgames.aether.entity.tile.TileEntityFreezer;
import com.gildedgames.aether.entity.tile.TileEntityIncubator;
import com.gildedgames.aether.entity.tile.TileEntityTreasureChest;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Null;
import org.jetbrains.annotations.NotNull;

import static com.gildedgames.aether.AetherMod.of;

public class TileEntityListener
{
    @Entrypoint.Namespace
    public static final net.modificationstation.stationapi.api.util.@NotNull Namespace MOD_ID = Null.get();

    @EventListener
    public void registerTileEntities(BlockEntityRegisterEvent event)
    {
        event.register(TileEntityTreasureChest.class, of("treasure_chest").toString());
        event.register(TileEntityFreezer.class, of("freezer").toString());
        event.register(TileEntityEnchanter.class, of("enchanter").toString());
        event.register(TileEntityIncubator.class, of("incubator").toString());
    }
}
