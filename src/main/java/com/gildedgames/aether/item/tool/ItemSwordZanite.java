package com.gildedgames.aether.item.tool;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemSwordZanite extends TemplateSwordItem
{
    public ItemSwordZanite(final @NotNull Identifier identifier, final ToolMaterial mat)
    {
        super(identifier, mat);
    }

    @Override
    public float getMiningSpeedMultiplier(final ItemStack item, final Block tile)
    {
        return super.getMiningSpeedMultiplier(item, tile) * (2.0f * item.getDamage() / item.getItem().getMaxDamage() + 0.5f);
    }
}
