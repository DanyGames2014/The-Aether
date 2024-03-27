package com.gildedgames.aether.item.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemAmbrosium extends TemplateItem
{
    private int healAmount;

    public ItemAmbrosium(final @NotNull Identifier identifier, final int j)
    {
        super(identifier);
        this.healAmount = j;
        this.maxCount = 64;
    }

    @Override
    public ItemStack use(final ItemStack item, final World level, final PlayerEntity player)
    {
        --item.count;
        player.method_939(this.healAmount);
        return item;
    }

    public int getHealAmount()
    {
        return this.healAmount;
    }
}
