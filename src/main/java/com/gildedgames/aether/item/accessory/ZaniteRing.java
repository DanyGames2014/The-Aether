package com.gildedgames.aether.item.accessory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class ZaniteRing extends Ring
{
    public ZaniteRing(Identifier identifier, int colour)
    {
        super(identifier, colour);
    }

    @Override
    public ItemStack tickWhileWorn(PlayerEntity playerBase, ItemStack itemInstance)
    {
        // todo: mine faster
        return itemInstance;
    }
}
