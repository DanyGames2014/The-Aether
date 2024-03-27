package com.gildedgames.aether.item.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemGummieSwet extends ItemAether
{
    private int healAmount;
    private boolean damZero;
    private boolean damOne;

    public ItemGummieSwet(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxCount = 64;
        this.damZero = false;
        this.damOne = false;
        this.healAmount = 20;
        this.setHasSubtypes(true);
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

    @Override
    public int method_440(final int damage)
    {
        if (damage == 1)
        {
            return 16777087;
        }
        return 8765927;
    }

    @Override
    public String getTranslationKey(final ItemStack item)
    {
        int i = item.getDamage();
        if (i > 1)
        {
            i = 1;
        }
        return this.getTranslationKey() + i;
    }
}
