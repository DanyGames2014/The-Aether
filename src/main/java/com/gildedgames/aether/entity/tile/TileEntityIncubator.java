package com.gildedgames.aether.entity.tile;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.animal.EntityMoa;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.MoaColour;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

public class TileEntityIncubator extends BlockEntity implements Inventory
{
    private ItemStack[] IncubatorItemStacks;
    public int torchPower;
    public int progress;

    public TileEntityIncubator()
    {
        this.IncubatorItemStacks = new ItemStack[2];
        this.progress = 0;
    }

    @Override
    public int size()
    {
        return this.IncubatorItemStacks.length;
    }

    @Override
    public ItemStack getStack(final int index)
    {
        return this.IncubatorItemStacks[index];
    }

    @Override
    public ItemStack removeStack(final int index, final int count)
    {
        if (this.IncubatorItemStacks[index] == null)
        {
            return null;
        }
        if (this.IncubatorItemStacks[index].count <= count)
        {
            final ItemStack itemstack = this.IncubatorItemStacks[index];
            this.IncubatorItemStacks[index] = null;
            return itemstack;
        }
        final ItemStack itemstack2 = this.IncubatorItemStacks[index].split(count);
        if (this.IncubatorItemStacks[index].count == 0)
        {
            this.IncubatorItemStacks[index] = null;
        }
        return itemstack2;
    }

    @Override
    public void setStack(final int slot, final ItemStack stack)
    {
        this.IncubatorItemStacks[slot] = stack;
        if (stack != null && stack.count > this.getMaxCountPerStack())
        {
            stack.count = this.getMaxCountPerStack();
        }
    }

    @Override
    public String getName()
    {
        return "Incubator";
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        final NbtList nbttaglist = tag.getList("Items");
        this.IncubatorItemStacks = new ItemStack[this.size()];
        for (int i = 0; i < nbttaglist.size(); ++i)
        {
            final NbtCompound nbttagcompound1 = (NbtCompound) nbttaglist.get(i);
            final byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 >= 0 && byte0 < this.IncubatorItemStacks.length)
            {
                this.IncubatorItemStacks[byte0] = new ItemStack(nbttagcompound1);
            }
        }
        this.progress = tag.getShort("BurnTime");
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("BurnTime", (short) this.progress);
        final NbtList nbttaglist = new NbtList();
        for (int i = 0; i < this.IncubatorItemStacks.length; ++i)
        {
            if (this.IncubatorItemStacks[i] != null)
            {
                final NbtCompound nbttagcompound1 = new NbtCompound();
                nbttagcompound1.putByte("Slot", (byte) i);
                this.IncubatorItemStacks[i].writeNbt(nbttagcompound1);
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
        return this.progress * i / 6000;
    }

    public int getBurnTimeRemainingScaled(final int i)
    {
        return this.torchPower * i / 500;
    }

    public boolean isBurning()
    {
        return this.torchPower > 0;
    }

    @Override
    public void method_1076()
    {
        if (this.torchPower > 0)
        {
            --this.torchPower;
            if (this.getStack(1) != null)
            {
                ++this.progress;
            }
        }
        if (this.IncubatorItemStacks[1] == null || this.IncubatorItemStacks[1].itemId != AetherItems.MoaEgg.id)
        {
            this.progress = 0;
        }
        if (this.progress >= 6000)
        {
            if (this.IncubatorItemStacks[1] != null)
            {
                final EntityMoa moa = new EntityMoa(this.world, true, false, false, MoaColour.getColour(this.IncubatorItemStacks[1].getDamage()));
                moa.method_1340(this.x + 0.5, this.y + 1.5, this.z + 0.5);
                this.world.method_210(moa);
            }
            TODO:
            AetherMod.giveAchievement(AetherAchievements.incubator);
            this.removeStack(1, 1);
            this.progress = 0;
        }
        if (this.torchPower <= 0 && this.IncubatorItemStacks[1] != null && this.IncubatorItemStacks[1].itemId == AetherItems.MoaEgg.id && this.getStack(0) != null && this.getStack(0).itemId == AetherBlocks.AMBROSIUM_TORCH.id)
        {
            this.torchPower += 1000;
            this.removeStack(0, 1);
        }
    }

    @Override
    public boolean canPlayerUse(final PlayerEntity player)
    {
        return this.world.method_1777(this.x, this.y, this.z) == this && player.method_1347(this.x + 0.5, this.y + 0.5, this.z + 0.5) <= 64.0;
    }
}
