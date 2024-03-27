package com.gildedgames.aether.item.accessory;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class IceRing extends IcePendant
{
    public IceRing(Identifier identifier, int colour)
    {
        super(identifier, "", colour);
    }

    @Override
    public String[] getAccessoryTypes(ItemStack item)
    {
        return new String[]{"ring"};
    }
}
