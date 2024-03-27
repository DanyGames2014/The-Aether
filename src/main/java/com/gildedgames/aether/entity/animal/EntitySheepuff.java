package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.WoolBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class EntitySheepuff extends EntityAetherAnimal
{
    public static final float[][] fleeceColorTable;
    private int amountEaten;

    public EntitySheepuff(final World level, final int color)
    {
        super(level);
        this.setFleeceColor(color);
        setStartVariables();
    }

    public EntitySheepuff(final World level)
    {
        super(level);
        this.setFleeceColor(getRandomFleeceColor(this.random));
        setStartVariables();
    }

    private void setStartVariables()
    {
        this.texture = "aether:textures/entity/sheepuff.png";
        this.setBoundingBoxSpacing(0.9f, 1.3f);
        this.amountEaten = 0;
    }

    @Override
    protected void initDataTracker()
    {
        super.initDataTracker();
        this.dataTracker.startTracking(16, (byte) 0);
    }

    @Override
    protected void method_933()
    {
        if (!this.getSheared())
        {
            this.method_1327(new ItemStack(Block.WOOL.id, 1 + this.random.nextInt(2), this.getFleeceColor()), 0.0f);
        }
    }

    @Override
    public boolean method_1323(final PlayerEntity entityplayer)
    {
        final ItemStack itemstack = entityplayer.inventory.getSelectedItem();
        if (itemstack != null && itemstack.itemId == Item.SHEARS.id && !this.getSheared())
        {
            if (!this.world.isRemote)
            {
                if (this.getPuffed())
                {
                    this.setPuffed(false);
                    for (int i = 2 + this.random.nextInt(3), j = 0; j < i; ++j)
                    {
                        final ItemEntity dropItem;
                        final ItemEntity entityitem = dropItem = this.method_1327(new ItemStack(Block.WOOL.id, 1, this.getFleeceColor()), 1.0f);
                        dropItem.velocityY += this.random.nextFloat() * 0.05f;
                        final ItemEntity item = entityitem;
                        item.velocityX += (this.random.nextFloat() - this.random.nextFloat()) * 0.1f;
                        final ItemEntity item2 = entityitem;
                        item2.velocityZ += (this.random.nextFloat() - this.random.nextFloat()) * 0.1f;
                    }
                }
                else
                {
                    this.setSheared(true);
                    for (int i = 2 + this.random.nextInt(3), j = 0; j < i; ++j)
                    {
                        final ItemEntity dropItem2;
                        final ItemEntity entityitem = dropItem2 = this.method_1327(new ItemStack(Block.WOOL.id, 1, this.getFleeceColor()), 1.0f);
                        dropItem2.velocityY += this.random.nextFloat() * 0.05f;
                        final ItemEntity item3 = entityitem;
                        item3.velocityX += (this.random.nextFloat() - this.random.nextFloat()) * 0.1f;
                        final ItemEntity item4 = entityitem;
                        item4.velocityZ += (this.random.nextFloat() - this.random.nextFloat()) * 0.1f;
                    }
                }
            }
            itemstack.damage(1, entityplayer);
        }
        if (itemstack != null && itemstack.itemId == Item.DYE.id && !this.getSheared())
        {
            final int colour = WoolBlock.method_1(itemstack.getDamage());
            if (this.getFleeceColor() != colour)
            {
                if (this.getPuffed() && itemstack.count >= 2)
                {
                    this.setFleeceColor(colour);
                    final ItemStack itemInstance = itemstack;
                    itemInstance.count -= 2;
                }
                else if (!this.getPuffed())
                {
                    this.setFleeceColor(colour);
                    final ItemStack itemInstance2 = itemstack;
                    --itemInstance2.count;
                }
            }
        }
        return false;
    }

    @Override
    protected void method_944()
    {
        if (this.getPuffed())
        {
            this.velocityY = 1.8;
            this.velocityX += this.random.nextGaussian() * 0.5;
            this.velocityZ += this.random.nextGaussian() * 0.5;
        }
        else
        {
            this.velocityY = 0.41999998688697815;
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.getPuffed())
        {
            this.field_1636 = 0.0f;
            if (this.velocityY < -0.05)
            {
                this.velocityY = -0.05;
            }
        }
        if (this.random.nextInt(100) == 0)
        {
            final int x = MathHelper.floor(this.x);
            final int y = MathHelper.floor(this.y);
            final int z = MathHelper.floor(this.z);
            if (this.world.getBlockId(x, y - 1, z) == AetherBlocks.AETHER_GRASS_BLOCK.id)
            {
                this.world.setBlock(x, y - 1, z, AetherBlocks.AETHER_DIRT.id);
                ++this.amountEaten;
            }
        }
        if (this.amountEaten == 5 && !this.getSheared() && !this.getPuffed())
        {
            this.setPuffed(true);
            this.amountEaten = 0;
        }
        if (this.amountEaten == 10 && this.getSheared() && !this.getPuffed())
        {
            this.setSheared(false);
            this.setFleeceColor(0);
            this.amountEaten = 0;
        }
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putBoolean("Sheared", this.getSheared());
        tag.putBoolean("Puffed", this.getPuffed());
        tag.putByte("Color", (byte) this.getFleeceColor());
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.setSheared(tag.getBoolean("Sheared"));
        this.setPuffed(tag.getBoolean("Puffed"));
        this.setFleeceColor(tag.getByte("Color"));
    }

    @Override
    protected String method_911()
    {
        return "mob.sheep";
    }

    @Override
    protected String method_912()
    {
        return "mob.sheep";
    }

    @Override
    protected String method_913()
    {
        return "mob.sheep";
    }

    public int getFleeceColor()
    {
        return this.dataTracker.getByte(16) & 0xF;
    }

    public void setFleeceColor(final int i)
    {
        final byte byte0 = this.dataTracker.getByte(16);
        this.dataTracker.set(16, (byte) ((byte0 & 0xF0) | (i & 0xF)));
    }

    public boolean getSheared()
    {
        return (this.dataTracker.getByte(16) & 0x10) != 0x0;
    }

    public void setSheared(final boolean flag)
    {
        final byte byte0 = this.dataTracker.getByte(16);
        if (flag)
        {
            this.dataTracker.set(16, (byte) (byte0 | 0x10));
        }
        else
        {
            this.dataTracker.set(16, (byte) (byte0 & 0xFFFFFFEF));
        }
    }

    public boolean getPuffed()
    {
        return (this.dataTracker.getByte(16) & 0x20) != 0x0;
    }

    public void setPuffed(final boolean flag)
    {
        final byte byte0 = this.dataTracker.getByte(16);
        if (flag)
        {
            this.dataTracker.set(16, (byte) (byte0 | 0x20));
        }
        else
        {
            this.dataTracker.set(16, (byte) (byte0 & 0xFFFFFFDF));
        }
    }

    public static int getRandomFleeceColor(final Random random)
    {
        final int i = random.nextInt(100);
        if (i < 5)
        {
            return 3;
        }
        if (i < 10)
        {
            return 9;
        }
        if (i < 15)
        {
            return 5;
        }
        if (i < 18)
        {
            return 6;
        }
        return (random.nextInt(500) != 0) ? 0 : 10;
    }

    static
    {
        fleeceColorTable = new float[][]{{1.0f, 1.0f, 1.0f}, {0.975f, 0.85f, 0.6f}, {0.95f, 0.75f, 0.925f}, {0.8f, 0.85f, 0.975f}, {0.95f, 0.95f, 0.6f}, {0.75f, 0.9f, 0.55f}, {0.975f, 0.85f, 0.9f}, {0.65f, 0.65f, 0.65f}, {0.8f, 0.8f, 0.8f}, {0.65f, 0.8f, 0.85f}, {0.85f, 0.7f, 0.95f}, {0.6f, 0.7f, 0.9f}, {0.75f, 0.7f, 0.65f}, {0.7f, 0.75f, 0.6f}, {0.9f, 0.65f, 0.65f}, {0.55f, 0.55f, 0.55f}};
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Sheepuff");
    }
}
