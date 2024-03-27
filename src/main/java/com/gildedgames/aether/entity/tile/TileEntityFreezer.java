package com.gildedgames.aether.entity.tile;

import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.Frozen;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.List;

public class TileEntityFreezer extends BlockEntity implements Inventory
{
    private static List<Frozen> frozen;
    private ItemStack[] frozenItemStacks;
    public int frozenProgress;
    public int frozenPowerRemaining;
    public int frozenTimeForItem;
    private Frozen currentFrozen;

    public TileEntityFreezer()
    {
        this.frozenItemStacks = new ItemStack[3];
        this.frozenProgress = 0;
        this.frozenPowerRemaining = 0;
        this.frozenTimeForItem = 0;
    }

    @Override
    public int size()
    {
        return this.frozenItemStacks.length;
    }

    @Override
    public ItemStack getStack(final int index)
    {
        return this.frozenItemStacks[index];
    }

    @Override
    public ItemStack removeStack(final int index, final int count)
    {
        if (this.frozenItemStacks[index] == null)
        {
            return null;
        }
        if (this.frozenItemStacks[index].count <= count)
        {
            final ItemStack itemstack = this.frozenItemStacks[index];
            this.frozenItemStacks[index] = null;
            return itemstack;
        }
        final ItemStack itemstack2 = this.frozenItemStacks[index].split(count);
        if (this.frozenItemStacks[index].count == 0)
        {
            this.frozenItemStacks[index] = null;
        }
        return itemstack2;
    }

    @Override
    public void setStack(final int slot, final ItemStack stack)
    {
        this.frozenItemStacks[slot] = stack;
        if (stack != null && stack.count > this.getMaxCountPerStack())
        {
            stack.count = this.getMaxCountPerStack();
        }
    }

    @Override
    public String getName()
    {
        return "Freezer";
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        final NbtList nbttaglist = tag.getList("Items");
        this.frozenItemStacks = new ItemStack[this.size()];
        for (int i = 0; i < nbttaglist.size(); ++i)
        {
            final NbtCompound nbttagcompound1 = (NbtCompound) nbttaglist.get(i);
            final byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 >= 0 && byte0 < this.frozenItemStacks.length)
            {
                this.frozenItemStacks[byte0] = new ItemStack(nbttagcompound1);
            }
        }
        this.frozenProgress = tag.getShort("BurnTime");
        this.frozenTimeForItem = tag.getShort("CookTime");
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("BurnTime", (short) this.frozenProgress);
        tag.putShort("CookTime", (short) this.frozenTimeForItem);
        final NbtList nbttaglist = new NbtList();
        for (int i = 0; i < this.frozenItemStacks.length; ++i)
        {
            if (this.frozenItemStacks[i] != null)
            {
                final NbtCompound nbttagcompound1 = new NbtCompound();
                nbttagcompound1.putByte("Slot", (byte) i);
                this.frozenItemStacks[i].writeNbt(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }
        tag.put("Items", (NbtElement) nbttaglist);
    }

    @Override
    public int getMaxCountPerStack()
    {
        return 64;
    }

    public int getCookProgressScaled(final int i)
    {
        if (this.frozenTimeForItem == 0)
        {
            return 0;
        }
        return this.frozenProgress * i / this.frozenTimeForItem;
    }

    public int getBurnTimeRemainingScaled(final int i)
    {
        return this.frozenPowerRemaining * i / 500;
    }

    public boolean isBurning()
    {
        return this.frozenPowerRemaining > 0;
    }

    @Override
    public void method_1076()
    {
        if (this.frozenPowerRemaining > 0)
        {
            --this.frozenPowerRemaining;
            if (this.currentFrozen != null)
            {
                ++this.frozenProgress;
            }
        }
        if (this.currentFrozen != null && (this.frozenItemStacks[0] == null || this.frozenItemStacks[0].itemId != this.currentFrozen.frozenFrom.itemId))
        {
            this.currentFrozen = null;
            this.frozenProgress = 0;
        }
        if (this.currentFrozen != null && this.frozenProgress >= this.currentFrozen.frozenPowerNeeded)
        {
            if (this.frozenItemStacks[2] == null)
            {
                this.setStack(2, new ItemStack(this.currentFrozen.frozenTo.getItem(), 1, this.currentFrozen.frozenTo.getDamage()));
            }
            else
            {
                this.setStack(2, new ItemStack(this.currentFrozen.frozenTo.getItem(), this.getStack(2).count + 1, this.currentFrozen.frozenTo.getDamage()));
            }
            if (this.getStack(0).itemId == Item.WATER_BUCKET.id || this.getStack(0).itemId == Item.LAVA_BUCKET.id)
            {
                this.setStack(0, new ItemStack(Item.BUCKET));
            }
            else if (this.getStack(0).itemId == AetherItems.Bucket.id)
            {
                this.setStack(0, new ItemStack(AetherItems.Bucket));
            }
            else
            {
                this.removeStack(0, 1);
            }
            this.frozenProgress = 0;
            this.currentFrozen = null;
            this.frozenTimeForItem = 0;
        }
        if (this.frozenPowerRemaining <= 0 && this.currentFrozen != null && this.getStack(1) != null && this.getStack(1).itemId == AetherBlocks.ICESTONE.id)
        {
            this.frozenPowerRemaining += 500;
            this.removeStack(1, 1);
        }
        if (this.currentFrozen == null)
        {
            final ItemStack itemstack = this.getStack(0);
            for (int i = 0; i < TileEntityFreezer.frozen.size(); ++i)
            {
                if (itemstack != null && TileEntityFreezer.frozen.get(i) != null && itemstack.itemId == ((Frozen) TileEntityFreezer.frozen.get(i)).frozenFrom.itemId && itemstack.getDamage() == ((Frozen) TileEntityFreezer.frozen.get(i)).frozenFrom.getDamage())
                {
                    if (this.frozenItemStacks[2] == null)
                    {
                        this.currentFrozen = (Frozen) TileEntityFreezer.frozen.get(i);
                        this.frozenTimeForItem = this.currentFrozen.frozenPowerNeeded;
                    }
                    else if (this.frozenItemStacks[2].itemId == ((Frozen) TileEntityFreezer.frozen.get(i)).frozenTo.itemId && ((Frozen) TileEntityFreezer.frozen.get(i)).frozenTo.getItem().getMaxCount() > this.frozenItemStacks[2].count)
                    {
                        this.currentFrozen = (Frozen) TileEntityFreezer.frozen.get(i);
                        this.frozenTimeForItem = this.currentFrozen.frozenPowerNeeded;
                    }
                }
            }
        }
    }

    @Override
    public boolean canPlayerUse(final PlayerEntity player)
    {
        return this.world.method_1777(this.x, this.y, this.z) == this && player.method_1347(this.x + 0.5, this.y + 0.5, this.z + 0.5) <= 64.0;
    }

    public static void addFrozen(final ItemStack from, final ItemStack to, final int i)
    {
        TileEntityFreezer.frozen.add(new Frozen(from, to, i));
    }

    static
    {
        TileEntityFreezer.frozen = (List<Frozen>) new ArrayList();
        addFrozen(new ItemStack(Item.WATER_BUCKET, 1), new ItemStack(Block.ICE, 5), 500);
        addFrozen(new ItemStack(AetherItems.Bucket, 1, 8), new ItemStack(Block.ICE, 5), 500);
        addFrozen(new ItemStack(Item.LAVA_BUCKET, 1), new ItemStack(Block.OBSIDIAN, 2), 500);
        addFrozen(new ItemStack(AetherBlocks.AERCLOUD, 1, 0), new ItemStack(AetherBlocks.AERCLOUD, 1, 1), 50);
        addFrozen(new ItemStack(AetherItems.GoldPendant, 1), new ItemStack(AetherItems.IcePendant, 1), 2500);
        addFrozen(new ItemStack(AetherItems.GoldRing, 1), new ItemStack(AetherItems.IceRing, 1), 1500);
        addFrozen(new ItemStack(AetherItems.IronRing, 1), new ItemStack(AetherItems.IceRing, 1), 1500);
        addFrozen(new ItemStack(AetherItems.IronPendant, 1), new ItemStack(AetherItems.IcePendant, 1), 2500);
    }
}
