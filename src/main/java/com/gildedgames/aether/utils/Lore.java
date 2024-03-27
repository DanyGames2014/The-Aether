package com.gildedgames.aether.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Lore
{
    public ItemStack stack;
    public String name;
    public String line1;
    public String line2;
    public String line3;
    public String line4;
    public String line5;
    public String line6;
    public int type;

    public Lore(final ItemStack item, final String s, final String s1, final String s2, final String s3, final String s4, final String s5, final String s6, final int i)
    {
        this.stack = item;
        this.name = s;
        this.line1 = s1;
        this.line2 = s2;
        this.line3 = s3;
        this.line4 = s4;
        this.line5 = s5;
        this.line6 = s6;
        this.type = i;
    }

    public Lore(final int id, final String s, final String s1, final String s2, final String s3, final String s4, final String s5, final String s6, final int i)
    {
        this(new ItemStack(id, 1, -1), s, s1, s2, s3, s4, s5, s6, i);
    }

    public Lore(final Block block, final String s, final String s1, final String s2, final String s3, final String s4, final String s5, final String s6, final int i)
    {
        this(new ItemStack(block == null ? 0 : block.id, 1, -1), s, s1, s2, s3, s4, s5, s6, i);
    }

    public Lore(final Item item, final String s, final String s1, final String s2, final String s3, final String s4, final String s5, final String s6, final int i)
    {
        this(new ItemStack(item == null ? 0 : item.id, 1, -1), s, s1, s2, s3, s4, s5, s6, i);
    }

    public boolean equals(final Object other)
    {
        if (other == null)
        {
            return this.stack == null;
        }
        if (other instanceof Lore)
        {
            return ((Lore) other).stack.itemId == this.stack.itemId && (((Lore) other).stack.getDamage() == this.stack.getDamage() || this.stack.getDamage() == -1);
        }
        return other instanceof ItemStack && ((ItemStack) other).itemId == this.stack.itemId && (((ItemStack) other).getDamage() == this.stack.getDamage() || this.stack.getDamage() == -1);
    }
}
