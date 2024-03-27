package com.gildedgames.aether.utils;

import net.minecraft.item.ItemStack;

public class Enchantment
{
    public ItemStack enchantFrom;
    public ItemStack enchantTo;
    public int enchantPowerNeeded;

    public Enchantment(final ItemStack from, final ItemStack to, final int i)
    {
        this.enchantFrom = from;
        this.enchantTo = to;
        this.enchantPowerNeeded = i;
    }
}
