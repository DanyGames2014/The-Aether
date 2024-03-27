package com.gildedgames.aether.entity.base;

import net.minecraft.class_65;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public abstract class EntityDungeonMob extends MobEntity implements class_65, MobSpawnDataProvider
{
    protected int attackStrength;

    public EntityDungeonMob(final World level)
    {
        super(level);
        this.attackStrength = 2;
        this.health = 20;
    }

    @Override
    public void method_937()
    {
        final float f = this.method_1394(1.0f);
        if (f > 0.5f)
        {
            this.field_1059 += 2;
        }
        super.method_937();
    }

    @Override
    protected Entity method_638()
    {
        final PlayerEntity entityplayer = this.world.method_186(this, 16.0);
        if (entityplayer != null && this.method_928(entityplayer))
        {
            return entityplayer;
        }
        return null;
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        if (!super.damage(target, amount))
        {
            return false;
        }
        if (this.field_1594 == target || this.field_1595 == target)
        {
            return true;
        }
        if (target != this)
        {
            this.target = target;
        }
        return true;
    }

    @Override
    protected void method_637(final Entity target, final float f)
    {
        if (this.field_1042 <= 0 && f < 2.0f && target.boundingBox.maxY > this.boundingBox.minY && target.boundingBox.minY < this.boundingBox.maxY)
        {
            this.field_1042 = 20;
            target.damage(this, this.attackStrength);
        }
    }

    @Override
    protected float method_641(final int x, final int y, final int z)
    {
        return 0.5f - this.world.method_1782(x, y, z);
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
    }

    @Override
    public boolean canSpawn()
    {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        if (this.world.method_164(LightType.SKY, i, j, k) > this.random.nextInt(32))
        {
            return false;
        }
        int l = this.world.method_255(i, j, k);
        if (this.world.method_269())
        {
            final int i2 = this.world.field_202;
            this.world.field_202 = 10;
            l = this.world.method_255(i, j, k);
            this.world.field_202 = i2;
        }
        return l <= this.random.nextInt(8) && super.canSpawn();
    }
}
