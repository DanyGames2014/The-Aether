package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.event.listener.TextureListener;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemDart extends TemplateItem
{

    public ItemDart(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.setHasSubtypes(true);
    }

    @Override
    public int getTextureId(final int damage)
    {
        if (damage == 0)
        {
            return TextureListener.sprDartGolden;
        }
        if (damage == 1)
        {
            return TextureListener.sprDartPoison;
        }
        if (damage == 2)
        {
            return TextureListener.sprDartEnchanted;
        }
        return TextureListener.sprDartGolden;
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

    static
    {
    }
}
