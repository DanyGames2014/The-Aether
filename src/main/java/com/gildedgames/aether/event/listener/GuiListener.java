package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.tile.TileEntityEnchanter;
import com.gildedgames.aether.entity.tile.TileEntityFreezer;
import com.gildedgames.aether.entity.tile.TileEntityIncubator;
import com.gildedgames.aether.entity.tile.TileEntityTreasureChest;
import com.gildedgames.aether.gui.GuiEnchanter;
import com.gildedgames.aether.gui.GuiFreezer;
import com.gildedgames.aether.gui.GuiIncubator;
import com.gildedgames.aether.gui.GuiTreasureChest;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class GuiListener
{

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event)
    {
        GuiHandlerRegistry registry = event.registry;
        registry.registerValueNoMessage(Identifier.of(AetherMod.MODID, "treasure_chest"), BiTuple.of(this::openTreasureChest, TileEntityTreasureChest::new));
        registry.registerValueNoMessage(Identifier.of(AetherMod.MODID, "freezer"), BiTuple.of(this::openFreezer, TileEntityFreezer::new));
        registry.registerValueNoMessage(Identifier.of(AetherMod.MODID, "enchanter"), BiTuple.of(this::openEnchanter, TileEntityEnchanter::new));
        registry.registerValueNoMessage(Identifier.of(AetherMod.MODID, "incubator"), BiTuple.of(this::openIncubator, TileEntityIncubator::new));

    }

    @Environment(EnvType.CLIENT)
    public Screen openTreasureChest(PlayerEntity player, Inventory inventoryBase)
    {
        return new GuiTreasureChest(player.inventory, (TileEntityTreasureChest) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public Screen openFreezer(PlayerEntity player, Inventory inventoryBase)
    {
        return new GuiFreezer(player.inventory, (TileEntityFreezer) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public Screen openEnchanter(PlayerEntity player, Inventory inventoryBase)
    {
        return new GuiEnchanter(player.inventory, (TileEntityEnchanter) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public Screen openIncubator(PlayerEntity player, Inventory inventoryBase)
    {
        return new GuiIncubator(player.inventory, (TileEntityIncubator) inventoryBase);
    }
}