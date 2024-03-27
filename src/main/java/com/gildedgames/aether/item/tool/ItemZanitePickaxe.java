package com.gildedgames.aether.item.tool;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemZanitePickaxe extends TemplatePickaxeItem
{
    public ItemZanitePickaxe(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial)
    {
        super(identifier, enumtoolmaterial);
    }

    @Override
    public float getMiningSpeedMultiplier(final ItemStack item, final Block tile)
    {
        return super.getMiningSpeedMultiplier(item, tile) * (2.0f * item.getDamage() / item.getItem().getMaxDamage() + 0.5f);
    }
}
