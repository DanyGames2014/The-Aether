package com.gildedgames.aether.gui.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ContainerLore extends ScreenHandler
{
    public Inventory loreSlot;

    public ContainerLore(final PlayerInventory inventoryplayer)
    {
        this.loreSlot = new SimpleInventory("Lore Item", 1);
        this.addSlot(new Slot(this.loreSlot, 0, 82, 66));
        for (int i1 = 0; i1 < 3; ++i1)
        {
            for (int l1 = 0; l1 < 9; ++l1)
            {
                this.addSlot(new Slot(inventoryplayer, l1 + i1 * 9 + 9, 48 + l1 * 18, 113 + i1 * 18));
            }
        }
        for (int j1 = 0; j1 < 9; ++j1)
        {
            this.addSlot(new Slot(inventoryplayer, j1, 48 + j1 * 18, 171));
        }
    }

    @Override
    protected void method_2081(final ItemStack itemstack, final int i, final int j, final boolean flag)
    {
    }

    @Override
    public void onClosed(final PlayerEntity player)
    {
        super.onClosed(player);
        final ItemStack itemstack = this.loreSlot.getStack(0);
        if (itemstack != null)
        {
            player.dropItem(itemstack);
        }
    }

    @Override
    public boolean canUse(final PlayerEntity player)
    {
        return true;
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
            if (slotIndex == 0)
            {
                this.method_2081(itemstack2, 10, 46, true);
            }
            else if (slotIndex >= 10 && slotIndex < 37)
            {
                this.method_2081(itemstack2, 37, 46, false);
            }
            else if (slotIndex >= 37 && slotIndex < 46)
            {
                this.method_2081(itemstack2, 10, 37, false);
            }
            else
            {
                this.method_2081(itemstack2, 10, 46, false);
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
