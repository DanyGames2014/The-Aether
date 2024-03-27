package com.gildedgames.aether.item.accessory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class ZanitePendant extends Pendant
{
    public ZanitePendant(Identifier identifier, String texture, int colour)
    {
        super(identifier, texture, colour);
    }

    @Override
    public ItemStack tickWhileWorn(PlayerEntity playerBase, ItemStack itemInstance)
    {
        // todo: mine faster as durability decreases
        return itemInstance;
    }
}