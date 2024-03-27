package com.gildedgames.aether.item.accessory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class AgileCape extends CosmeticCape
{
    public AgileCape(Identifier identifier, String texture)
    {
        super(identifier, texture);
    }

    @Override
    public void onAccessoryAdded(PlayerEntity playerBase, ItemStack itemInstance)
    {
        playerBase.field_1641 = 1.0f;
    }

    @Override
    public void onAccessoryRemoved(PlayerEntity playerBase, ItemStack itemInstance)
    {
        playerBase.field_1641 = 0.5f;
    }
}
