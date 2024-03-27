package com.gildedgames.aether.gui.container;

import com.gildedgames.aether.entity.tile.TileEntityIncubator;
import com.gildedgames.aether.slot.TileEntityIncubatorSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ContainerIncubator extends ScreenHandler
{
    private TileEntityIncubator Incubator;
    private int cookTime;
    private int burnTime;
    private int itemBurnTime;

    public ContainerIncubator(final PlayerInventory inventoryplayer, final TileEntityIncubator tileentityIncubator)
    {
        this.cookTime = 0;
        this.burnTime = 0;
        this.itemBurnTime = 0;
        this.Incubator = tileentityIncubator;
        this.addSlot(new TileEntityIncubatorSlot(tileentityIncubator, 1, 73, 17));
        this.addSlot(new Slot(tileentityIncubator, 0, 73, 53));
        for (int i = 0; i < 3; ++i)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }
        for (int j = 0; j < 9; ++j)
        {
            this.addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }
    }

    @Override
    protected void method_2081(final ItemStack itemstack, final int i, final int j, final boolean flag)
    {
    }

    @Override
    public boolean canUse(final PlayerEntity player)
    {
        return this.Incubator.canPlayerUse(player);
    }

    @Override
    public ItemStack getStackInSlot(final int slotIndex)
    {
        ItemStack itemstack = null;
        final Slot slot = (Slot) this.slots.get(slotIndex);
        if (slot != null && slot.hasStack())
        {
            final ItemStack itemstack2 = slot.getStack();
            itemstack = itemstack2.copy();
            if (slotIndex == 2)
            {
                this.method_2081(itemstack2, 3, 39, true);
            }
            else if (slotIndex >= 3 && slotIndex < 30)
            {
                this.method_2081(itemstack2, 30, 39, false);
            }
            else if (slotIndex >= 30 && slotIndex < 39)
            {
                this.method_2081(itemstack2, 3, 30, false);
            }
            else
            {
                this.method_2081(itemstack2, 3, 39, false);
            }
            if (itemstack2.count == 0)
            {
                slot.setStack(null);
            }
            else
            {
                slot.markDirty();
            }
            if (itemstack2.count == itemstack.count)
            {
                return null;
            }
            slot.onCrafted(itemstack2);
        }
        return itemstack;
    }
}
