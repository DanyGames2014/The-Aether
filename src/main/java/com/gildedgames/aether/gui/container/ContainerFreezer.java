package com.gildedgames.aether.gui.container;

import com.gildedgames.aether.entity.tile.TileEntityFreezer;
import net.minecraft.class_633;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;

public class ContainerFreezer extends ScreenHandler
{
    private TileEntityFreezer freezer;
    private int cookTime;
    private int burnTime;
    private int itemBurnTime;

    public ContainerFreezer(final PlayerInventory inventoryplayer, final TileEntityFreezer tileentityfreezer)
    {
        this.cookTime = 0;
        this.burnTime = 0;
        this.itemBurnTime = 0;
        this.freezer = tileentityfreezer;
        this.addSlot(new Slot(tileentityfreezer, 0, 56, 17));
        this.addSlot(new Slot(tileentityfreezer, 1, 56, 53));
        this.addSlot(new FurnaceOutputSlot(inventoryplayer.player, tileentityfreezer, 2, 116, 35));
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
    public void method_2075()
    {
        super.method_2075();
        for (int i = 0; i < this.listeners.size(); ++i)
        {
            final class_633 icrafting = (class_633) this.listeners.get(i);
            if (this.cookTime != this.freezer.frozenTimeForItem)
            {
                icrafting.method_2099(this, 0, this.freezer.frozenTimeForItem);
            }
            if (this.burnTime != this.freezer.frozenProgress)
            {
                icrafting.method_2099(this, 1, this.freezer.frozenProgress);
            }
            if (this.itemBurnTime != this.freezer.frozenPowerRemaining)
            {
                icrafting.method_2099(this, 2, this.freezer.frozenPowerRemaining);
            }
        }
        this.cookTime = this.freezer.frozenTimeForItem;
        this.burnTime = this.freezer.frozenProgress;
        this.itemBurnTime = this.freezer.frozenPowerRemaining;
    }

    @Override
    public void method_2077(final int index, final int value)
    {
        if (index == 0)
        {
            this.freezer.frozenTimeForItem = value;
        }
        if (index == 1)
        {
            this.freezer.frozenProgress = value;
        }
        if (index == 2)
        {
            this.freezer.frozenPowerRemaining = value;
        }
    }

    @Override
    public boolean canUse(final PlayerEntity player)
    {
        return this.freezer.canPlayerUse(player);
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
