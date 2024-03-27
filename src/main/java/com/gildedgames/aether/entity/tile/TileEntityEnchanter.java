package com.gildedgames.aether.entity.tile;

import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.Enchantment;
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

public class TileEntityEnchanter extends BlockEntity implements Inventory
{
    private static List<Enchantment> enchantments;
    private ItemStack[] enchanterItemStacks;
    public int enchantProgress;
    public int enchantPowerRemaining;
    public int enchantTimeForItem;
    private Enchantment currentEnchantment;

    public TileEntityEnchanter()
    {
        this.enchanterItemStacks = new ItemStack[3];
        this.enchantProgress = 0;
        this.enchantPowerRemaining = 0;
        this.enchantTimeForItem = 0;
    }

    @Override
    public int size()
    {
        return this.enchanterItemStacks.length;
    }

    @Override
    public ItemStack getStack(final int index)
    {
        return this.enchanterItemStacks[index];
    }

    @Override
    public ItemStack removeStack(final int index, final int count)
    {
        if (this.enchanterItemStacks[index] == null)
        {
            return null;
        }
        if (this.enchanterItemStacks[index].count <= count)
        {
            final ItemStack itemstack = this.enchanterItemStacks[index];
            this.enchanterItemStacks[index] = null;
            return itemstack;
        }
        final ItemStack itemstack2 = this.enchanterItemStacks[index].split(count);
        if (this.enchanterItemStacks[index].count == 0)
        {
            this.enchanterItemStacks[index] = null;
        }
        return itemstack2;
    }

    @Override
    public void setStack(final int slot, final ItemStack stack)
    {
        this.enchanterItemStacks[slot] = stack;
        if (stack != null && stack.count > this.getMaxCountPerStack())
        {
            stack.count = this.getMaxCountPerStack();
        }
    }

    @Override
    public String getName()
    {
        return "Enchanter";
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        final NbtList nbttaglist = tag.getList("Items");
        this.enchanterItemStacks = new ItemStack[this.size()];
        for (int i = 0; i < nbttaglist.size(); ++i)
        {
            final NbtCompound nbttagcompound1 = (NbtCompound) nbttaglist.get(i);
            final byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 >= 0 && byte0 < this.enchanterItemStacks.length)
            {
                this.enchanterItemStacks[byte0] = new ItemStack(nbttagcompound1);
            }
        }
        this.enchantProgress = tag.getShort("BurnTime");
        this.enchantTimeForItem = tag.getShort("CookTime");
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("BurnTime", (short) this.enchantProgress);
        tag.putShort("CookTime", (short) this.enchantTimeForItem);
        final NbtList nbttaglist = new NbtList();
        for (int i = 0; i < this.enchanterItemStacks.length; ++i)
        {
            if (this.enchanterItemStacks[i] != null)
            {
                final NbtCompound nbttagcompound1 = new NbtCompound();
                nbttagcompound1.putByte("Slot", (byte) i);
                this.enchanterItemStacks[i].writeNbt(nbttagcompound1);
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
        if (this.enchantTimeForItem == 0)
        {
            return 0;
        }
        return this.enchantProgress * i / this.enchantTimeForItem;
    }

    public int getBurnTimeRemainingScaled(final int i)
    {
        return this.enchantPowerRemaining * i / 500;
    }

    public boolean isBurning()
    {
        return this.enchantPowerRemaining > 0;
    }

    @Override
    public void method_1076()
    {
        if (this.enchantPowerRemaining > 0)
        {
            --this.enchantPowerRemaining;
            if (this.currentEnchantment != null)
            {
                ++this.enchantProgress;
            }
        }
        if (this.currentEnchantment != null && (this.enchanterItemStacks[0] == null || this.enchanterItemStacks[0].itemId != this.currentEnchantment.enchantFrom.itemId))
        {
            this.currentEnchantment = null;
            this.enchantProgress = 0;
        }
        if (this.currentEnchantment != null && this.enchantProgress >= this.currentEnchantment.enchantPowerNeeded)
        {
            if (this.enchanterItemStacks[2] == null)
            {
                this.setStack(2, new ItemStack(this.currentEnchantment.enchantTo.getItem(), 1, this.currentEnchantment.enchantTo.getDamage()));
            }
            else
            {
                this.setStack(2, new ItemStack(this.currentEnchantment.enchantTo.getItem(), this.getStack(2).count + 1, this.currentEnchantment.enchantTo.getDamage()));
            }
            this.removeStack(0, 1);
            this.enchantProgress = 0;
            this.currentEnchantment = null;
            this.enchantTimeForItem = 0;
        }
        if (this.enchantPowerRemaining <= 0 && this.currentEnchantment != null && this.getStack(1) != null && this.getStack(1).itemId == AetherItems.AmbrosiumShard.id)
        {
            this.enchantPowerRemaining += 500;
            this.removeStack(1, 1);
        }
        if (this.currentEnchantment == null)
        {
            final ItemStack itemstack = this.getStack(0);
            for (int i = 0; i < TileEntityEnchanter.enchantments.size(); ++i)
            {
                if (itemstack != null && TileEntityEnchanter.enchantments.get(i) != null && itemstack.itemId == ((Enchantment) TileEntityEnchanter.enchantments.get(i)).enchantFrom.itemId)
                {
                    if (this.enchanterItemStacks[2] == null)
                    {
                        this.currentEnchantment = (Enchantment) TileEntityEnchanter.enchantments.get(i);
                        this.enchantTimeForItem = this.currentEnchantment.enchantPowerNeeded;
                    }
                    else if (this.enchanterItemStacks[2].itemId == ((Enchantment) TileEntityEnchanter.enchantments.get(i)).enchantTo.itemId && ((Enchantment) TileEntityEnchanter.enchantments.get(i)).enchantTo.getItem().getMaxCount() > this.enchanterItemStacks[2].count)
                    {
                        this.currentEnchantment = (Enchantment) TileEntityEnchanter.enchantments.get(i);
                        this.enchantTimeForItem = this.currentEnchantment.enchantPowerNeeded;
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

    public static void addEnchantment(final ItemStack from, final ItemStack to, final int i)
    {
        TileEntityEnchanter.enchantments.add(new Enchantment(from, to, i));
    }

    static
    {
        TileEntityEnchanter.enchantments = new ArrayList<Enchantment>();
        addEnchantment(new ItemStack(AetherBlocks.GRAVITITE_ORE, 1), new ItemStack(AetherBlocks.ENCHANTED_GRAVITITE, 1), 1000);
        addEnchantment(new ItemStack(AetherItems.PickSkyroot, 1), new ItemStack(AetherItems.PickSkyroot, 1), 250);
        addEnchantment(new ItemStack(AetherItems.SwordSkyroot, 1), new ItemStack(AetherItems.SwordSkyroot, 1), 250);
        addEnchantment(new ItemStack(AetherItems.ShovelSkyroot, 1), new ItemStack(AetherItems.ShovelSkyroot, 1), 200);
        addEnchantment(new ItemStack(AetherItems.AxeSkyroot, 1), new ItemStack(AetherItems.AxeSkyroot, 1), 200);
        addEnchantment(new ItemStack(AetherItems.PickHolystone, 1), new ItemStack(AetherItems.PickHolystone, 1), 600);
        addEnchantment(new ItemStack(AetherItems.SwordHolystone, 1), new ItemStack(AetherItems.SwordHolystone, 1), 600);
        addEnchantment(new ItemStack(AetherItems.ShovelHolystone, 1), new ItemStack(AetherItems.ShovelHolystone, 1), 500);
        addEnchantment(new ItemStack(AetherItems.AxeHolystone, 1), new ItemStack(AetherItems.AxeHolystone, 1), 500);
        addEnchantment(new ItemStack(AetherItems.PickZanite, 1), new ItemStack(AetherItems.PickZanite, 1), 2500);
        addEnchantment(new ItemStack(AetherItems.SwordZanite, 1), new ItemStack(AetherItems.SwordZanite, 1), 2500);
        addEnchantment(new ItemStack(AetherItems.ShovelZanite, 1), new ItemStack(AetherItems.ShovelZanite, 1), 2000);
        addEnchantment(new ItemStack(AetherItems.AxeZanite, 1), new ItemStack(AetherItems.AxeZanite, 1), 2000);
        addEnchantment(new ItemStack(AetherItems.PickGravitite, 1), new ItemStack(AetherItems.PickGravitite, 1), 6000);
        addEnchantment(new ItemStack(AetherItems.SwordGravitite, 1), new ItemStack(AetherItems.SwordGravitite, 1), 6000);
        addEnchantment(new ItemStack(AetherItems.ShovelGravitite, 1), new ItemStack(AetherItems.ShovelGravitite, 1), 5000);
        addEnchantment(new ItemStack(AetherItems.AxeGravitite, 1), new ItemStack(AetherItems.AxeGravitite, 1), 5000);
        addEnchantment(new ItemStack(AetherItems.Dart, 1, 0), new ItemStack(AetherItems.Dart, 1, 2), 250);
        addEnchantment(new ItemStack(AetherItems.Bucket, 1, 2), new ItemStack(AetherItems.Bucket, 1, 3), 1000);
        //addEnchantment(new ItemInstance(ItemBase.record13, 1), new ItemInstance(AetherItems.BlueMusicDisk, 1), 2500);
        //(new ItemInstance(ItemBase.recordCat, 1), new ItemInstance(AetherItems.BlueMusicDisk, 1), 2500);
        addEnchantment(new ItemStack(Item.LEATHER_HELMET, 1), new ItemStack(Item.LEATHER_HELMET, 1), 400);
        addEnchantment(new ItemStack(Item.LEATHER_CHESTPLATE, 1), new ItemStack(Item.LEATHER_CHESTPLATE, 1), 500);
        addEnchantment(new ItemStack(Item.LEATHER_LEGGINGS, 1), new ItemStack(Item.LEATHER_LEGGINGS, 1), 500);
        addEnchantment(new ItemStack(Item.LEATHER_BOOTS, 1), new ItemStack(Item.LEATHER_BOOTS, 1), 400);
        addEnchantment(new ItemStack(Item.WOODEN_PICKAXE, 1), new ItemStack(Item.WOODEN_PICKAXE, 1), 500);
        addEnchantment(new ItemStack(Item.WOODEN_SHOVEL, 1), new ItemStack(Item.WOODEN_SHOVEL, 1), 400);
        addEnchantment(new ItemStack(Item.WOODEN_SWORD, 1), new ItemStack(Item.WOODEN_SWORD, 1), 500);
        addEnchantment(new ItemStack(Item.WOODEN_AXE, 1), new ItemStack(Item.WOODEN_AXE, 1), 400);
        addEnchantment(new ItemStack(Item.WOODEN_HOE, 1), new ItemStack(Item.WOODEN_HOE, 1), 300);
        addEnchantment(new ItemStack(Item.STONE_PICKAXE, 1), new ItemStack(Item.STONE_PICKAXE, 1), 1000);
        addEnchantment(new ItemStack(Item.STONE_SHOVEL, 1), new ItemStack(Item.STONE_SHOVEL, 1), 750);
        addEnchantment(new ItemStack(Item.STONE_SWORD, 1), new ItemStack(Item.STONE_SWORD, 1), 1000);
        addEnchantment(new ItemStack(Item.STONE_HATCHET, 1), new ItemStack(Item.STONE_HATCHET, 1), 750);
        addEnchantment(new ItemStack(Item.STONE_HOE, 1), new ItemStack(Item.STONE_HOE, 1), 750);
        addEnchantment(new ItemStack(Item.IRON_HELMET, 1), new ItemStack(Item.IRON_HELMET, 1), 1500);
        addEnchantment(new ItemStack(Item.IRON_CHESTPLATE, 1), new ItemStack(Item.IRON_CHESTPLATE, 1), 2000);
        addEnchantment(new ItemStack(Item.IRON_LEGGINGS, 1), new ItemStack(Item.IRON_LEGGINGS, 1), 2000);
        addEnchantment(new ItemStack(Item.IRON_BOOTS, 1), new ItemStack(Item.IRON_BOOTS, 1), 1500);
        addEnchantment(new ItemStack(Item.IRON_PICKAXE, 1), new ItemStack(Item.IRON_PICKAXE, 1), 2500);
        addEnchantment(new ItemStack(Item.IRON_SHOVEL, 1), new ItemStack(Item.IRON_SHOVEL, 1), 2000);
        addEnchantment(new ItemStack(Item.IRON_SWORD, 1), new ItemStack(Item.IRON_SWORD, 1), 2500);
        addEnchantment(new ItemStack(Item.IRON_AXE, 1), new ItemStack(Item.IRON_AXE, 1), 1500);
        addEnchantment(new ItemStack(Item.IRON_HOE, 1), new ItemStack(Item.IRON_HOE, 1), 1500);
        addEnchantment(new ItemStack(Item.GOLDEN_HELMET, 1), new ItemStack(Item.GOLDEN_HELMET, 1), 1000);
        addEnchantment(new ItemStack(Item.GOLDEN_CHESTPLATE, 1), new ItemStack(Item.GOLDEN_CHESTPLATE, 1), 1200);
        addEnchantment(new ItemStack(Item.GOLDEN_LEGGINGS, 1), new ItemStack(Item.GOLDEN_LEGGINGS, 1), 1200);
        addEnchantment(new ItemStack(Item.GOLDEN_BOOTS, 1), new ItemStack(Item.GOLDEN_BOOTS, 1), 1000);
        addEnchantment(new ItemStack(Item.GOLDEN_PICKAXE, 1), new ItemStack(Item.GOLDEN_PICKAXE, 1), 1500);
        addEnchantment(new ItemStack(Item.GOLDEN_SHOVEL, 1), new ItemStack(Item.GOLDEN_SHOVEL, 1), 1000);
        addEnchantment(new ItemStack(Item.GOLDEN_SWORD, 1), new ItemStack(Item.GOLDEN_SWORD, 1), 1500);
        addEnchantment(new ItemStack(Item.GOLDEN_AXE, 1), new ItemStack(Item.GOLDEN_AXE, 1), 1000);
        addEnchantment(new ItemStack(Item.GOLDEN_HOE, 1), new ItemStack(Item.GOLDEN_HOE, 1), 1000);
        addEnchantment(new ItemStack(Item.DIAMOND_HELMET, 1), new ItemStack(Item.DIAMOND_HELMET, 1), 5000);
        addEnchantment(new ItemStack(Item.DIAMOND_CHESTPLATE, 1), new ItemStack(Item.DIAMOND_CHESTPLATE, 1), 6000);
        addEnchantment(new ItemStack(Item.DIAMOND_LEGGINGS, 1), new ItemStack(Item.DIAMOND_LEGGINGS, 1), 6000);
        addEnchantment(new ItemStack(Item.DIAMOND_BOOTS, 1), new ItemStack(Item.DIAMOND_BOOTS, 1), 5000);
        addEnchantment(new ItemStack(Item.DIAMOND_PICKAXE, 1), new ItemStack(Item.DIAMOND_PICKAXE, 1), 7000);
        addEnchantment(new ItemStack(Item.DIAMOND_SHOVEL, 1), new ItemStack(Item.DIAMOND_SHOVEL, 1), 6000);
        addEnchantment(new ItemStack(Item.DIAMOND_SWORD, 1), new ItemStack(Item.DIAMOND_SWORD, 1), 6500);
        addEnchantment(new ItemStack(Item.DIAMOND_AXE, 1), new ItemStack(Item.DIAMOND_AXE, 1), 6000);
        addEnchantment(new ItemStack(Item.DIAMOND_HOE, 1), new ItemStack(Item.DIAMOND_HOE, 1), 6000);
        addEnchantment(new ItemStack(Item.FISHING_ROD, 1), new ItemStack(Item.FISHING_ROD, 1), 500);
        addEnchantment(new ItemStack(AetherBlocks.QUICKSOIL, 1), new ItemStack(AetherBlocks.QUICKSOIL_GLASS, 1), 250);
        addEnchantment(new ItemStack(AetherBlocks.HOLYSTONE, 1), new ItemStack(AetherItems.HealingStone, 1), 750);
        addEnchantment(new ItemStack(AetherItems.GravititeHelmet, 1), new ItemStack(AetherItems.GravititeHelmet, 1), 12000);
        addEnchantment(new ItemStack(AetherItems.GravititeBodyplate, 1), new ItemStack(AetherItems.GravititeBodyplate, 1), 20000);
        addEnchantment(new ItemStack(AetherItems.GravititePlatelegs, 1), new ItemStack(AetherItems.GravititePlatelegs, 1), 15000);
        addEnchantment(new ItemStack(AetherItems.GravititeBoots, 1), new ItemStack(AetherItems.GravititeBoots, 1), 12000);
        addEnchantment(new ItemStack(AetherItems.GravititeGlove, 1), new ItemStack(AetherItems.GravititeGlove, 1), 10000);
        addEnchantment(new ItemStack(AetherItems.ZaniteHelmet, 1), new ItemStack(AetherItems.ZaniteHelmet, 1), 6000);
        addEnchantment(new ItemStack(AetherItems.ZaniteChestplate, 1), new ItemStack(AetherItems.ZaniteChestplate, 1), 10000);
        addEnchantment(new ItemStack(AetherItems.ZaniteLeggings, 1), new ItemStack(AetherItems.ZaniteLeggings, 1), 8000);
        addEnchantment(new ItemStack(AetherItems.ZaniteBoots, 1), new ItemStack(AetherItems.ZaniteBoots, 1), 5000);
        addEnchantment(new ItemStack(AetherItems.ZaniteGlove, 1), new ItemStack(AetherItems.ZaniteGlove, 1), 4000);
        addEnchantment(new ItemStack(AetherItems.ZaniteRing, 1), new ItemStack(AetherItems.ZaniteRing, 1), 2000);
        addEnchantment(new ItemStack(AetherItems.ZanitePendant, 1), new ItemStack(AetherItems.ZanitePendant, 1), 2000);
        addEnchantment(new ItemStack(AetherItems.LeatherGlove, 1), new ItemStack(AetherItems.LeatherGlove, 1), 300);
        addEnchantment(new ItemStack(AetherItems.IronGlove, 1), new ItemStack(AetherItems.IronGlove, 1), 1200);
        addEnchantment(new ItemStack(AetherItems.GoldGlove, 1), new ItemStack(AetherItems.GoldGlove, 1), 800);
        addEnchantment(new ItemStack(AetherItems.DiamondGlove, 1), new ItemStack(AetherItems.DiamondGlove, 1), 4000);
        addEnchantment(new ItemStack(AetherItems.DartShooter, 1, 0), new ItemStack(AetherItems.DartShooter, 1, 2), 2000);
    }
}
