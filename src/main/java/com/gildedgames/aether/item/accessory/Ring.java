package com.gildedgames.aether.item.accessory;

import com.matthewperiut.accessoryapi.api.Accessory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class Ring extends TemplateItem implements Accessory
{
    int colour;

    public Ring(Identifier identifier, int colour)
    {
        super(identifier);
        this.colour = colour;
        this.setMaxCount(1);
        this.setMaxDamage(100);
    }

    @Override
    public String[] getAccessoryTypes(ItemStack item)
    {
        return new String[]{"ring"};
    }

    @Environment(EnvType.CLIENT)
    public int getColourMultiplier(int i)
    {
        return colour;
    }
}
