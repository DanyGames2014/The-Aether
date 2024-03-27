package com.gildedgames.aether.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

public class TileEntityIncubatorSlot extends Slot
{
    public TileEntityIncubatorSlot(final Inventory inventory, final int index, final int x, final int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxItemCount()
    {
        return 1;
    }
}
