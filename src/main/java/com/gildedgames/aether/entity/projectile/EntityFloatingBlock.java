package com.gildedgames.aether.entity.projectile;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.block.BlockFloating;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityFloatingBlock extends Entity implements MobSpawnDataProvider
{
    public int blockID;
    public int metadata;
    public int flytime;

    public EntityFloatingBlock(final World level)
    {
        super(level);
        this.flytime = 0;
    }

    public EntityFloatingBlock(final World world, final double d, final double d1, final double d2, final int i, final int j)
    {
        super(world);
        this.flytime = 0;
        this.blockID = i;
        this.metadata = j;
        this.field_1593 = true;
        this.setBoundingBoxSpacing(0.98f, 0.98f);
        this.eyeHeight = this.spacingY / 2.0f;
        this.method_1340(d, d1, d2);
        this.velocityX = 0.0;
        this.velocityY = 0.0;
        this.velocityZ = 0.0;
        this.prevX = d;
        this.prevY = d1;
        this.prevZ = d2;
    }

    public EntityFloatingBlock(final World world, final double d, final double d1, final double d2, final int i)
    {
        this(world, d, d1, d2, i, 0);
    }

    @Override
    protected boolean bypassesSteppingEffects()
    {
        return false;
    }

    @Override
    protected void initDataTracker()
    {
    }

    @Override
    public boolean method_1356()
    {
        return !this.dead;
    }

    @Override
    public void tick()
    {
        if (this.blockID == 0)
        {
            this.markDead();
            return;
        }
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        ++this.flytime;
        this.velocityY += 0.04;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        this.velocityX *= 0.9800000190734863;
        this.velocityY *= 0.9800000190734863;
        this.velocityZ *= 0.9800000190734863;
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.y);
        final int k = MathHelper.floor(this.z);
        if (this.world.getBlockId(i, j, k) == this.blockID || (this.world.getBlockId(i, j, k) == AetherBlocks.AETHER_GRASS_BLOCK.id && this.blockID == AetherBlocks.AETHER_DIRT.id))
        {
            this.world.setBlock(i, j, k, 0);
        }
        final List list = this.world.getEntities(this, this.boundingBox.expand(0.0, 1.0, 0.0));
        for (int n = 0; n < list.size(); ++n)
        {
            if (list.get(n) instanceof FallingBlockEntity && this.world.method_156(this.blockID, i, j, k, true, 1))
            {
                this.world.method_201(i, j, k, this.blockID, this.metadata);
                this.markDead();
            }
        }
        if (this.field_1625 && !this.field_1623)
        {
            this.velocityX *= 0.699999988079071;
            this.velocityZ *= 0.699999988079071;
            this.velocityY *= -0.5;
            this.markDead();
            if ((!this.world.method_156(this.blockID, i, j, k, true, 1) || BlockFloating.canFallAbove(this.world, i, j + 1, k) || !this.world.method_201(i, j, k, this.blockID, this.metadata)) && !this.world.isRemote)
            {
            }
        }
        else if (this.flytime > 100 && !this.world.isRemote)
        {
            this.markDead();
        }
    }

    @Override
    protected void writeNbt(final NbtCompound tag)
    {
        tag.putByte("Tile", (byte) this.blockID);
    }

    @Override
    protected void readNbt(final NbtCompound tag)
    {
        this.blockID = (tag.getByte("Tile") & 0xFF);
    }

    @Override
    public float method_1366()
    {
        return 0.0f;
    }

    public World getWorld()
    {
        return this.world;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("FloatingBlock");
    }
}
