package com.gildedgames.aether.item.tool;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateShovelItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemZaniteSpade extends TemplateShovelItem
{
    private static Block[] blocksEffectiveAgainst;

    public ItemZaniteSpade(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial)
    {
        super(identifier, enumtoolmaterial);
    }

    @Override
    public float getMiningSpeedMultiplier(final ItemStack item, final Block tile)
    {
        return super.getMiningSpeedMultiplier(item, tile) * (item.getDamage() / item.getItem().getMaxDamage() + 0.5f);
    }
}
