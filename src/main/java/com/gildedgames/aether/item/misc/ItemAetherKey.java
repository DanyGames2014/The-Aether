package com.gildedgames.aether.item.misc;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemAetherKey extends TemplateItem
{
    public ItemAetherKey(final @NotNull Identifier identifier)
    {
        super(identifier);
        //this.setTexturePosition(ModLoader.addOverride("/gui/items.png", "/aether/items/Key.png"));
        this.setTranslationKey("AetherKey");
        this.setHasSubtypes(true);
        this.maxCount = 1;
    }

    @Override
    public String getTranslationKey(final ItemStack item)
    {
        int i = item.getDamage();
        if (i > 2)
        {
            i = 2;
        }
        return this.getTranslationKey() + i;
    }

    @Override
    public int method_440(final int damage)
    {
        if (damage == 1)
        {
            return -6710887;
        }
        if (damage == 2)
        {
            return -13312;
        }
        return -7638187;
    }
}
