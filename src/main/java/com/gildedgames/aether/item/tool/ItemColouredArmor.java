package com.gildedgames.aether.item.tool;

import net.minecraft.item.ArmorItem;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;

import static com.gildedgames.aether.event.listener.TextureListener.MOD_ID;

public class ItemColouredArmor extends TemplateArmorItem implements ArmorTextureProvider
{
    private int colour;
    private String name;

    public ItemColouredArmor(final Identifier i, final int j, final String s, final int l, final int col)
    {
        super(i, j, 0, l);
        this.name = s;
        this.colour = col;
    }

    @Override
    public int method_440(final int i)
    {
        return this.colour;
    }

    @Override
    public Identifier getTexture(ArmorItem armour)
    {
        return Identifier.of(MOD_ID, name);
    }
}
