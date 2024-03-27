package com.gildedgames.aether.item.misc;

import com.gildedgames.aether.utils.MoaColour;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemMoaEgg extends TemplateItem
{
    public ItemMoaEgg(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.setHasSubtypes(true);
    }

    @Override
    public int method_440(final int damage)
    {
        return MoaColour.getColour(damage).colour;
    }

    @Override
    public String getTranslationKey(final ItemStack item)
    {
        int i = item.getDamage();
        if (i > MoaColour.colours.size() - 1)
        {
            i = MoaColour.colours.size() - 1;
        }
        return this.getTranslationKey() + i;
    }
}
