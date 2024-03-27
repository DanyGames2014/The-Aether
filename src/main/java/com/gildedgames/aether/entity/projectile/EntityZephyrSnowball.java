package com.gildedgames.aether.entity.projectile; /*name "projectile" was copied from divinerpg =D*/

import com.gildedgames.aether.AetherMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

// todo: zephyr snowball fixing
public class EntityZephyrSnowball extends Entity implements MobSpawnDataProvider
{
    private int field_9402_e;
    private int field_9401_f;
    private int field_9400_g;
    private int field_9399_h;
    private boolean field_9398_i;
    public int field_9406_a;
    private LivingEntity field_9397_j;
    private int field_9396_k;
    private int field_9395_l;
    public double field_9405_b;
    public double field_9404_c;
    public double field_9403_d;

    public EntityZephyrSnowball(final World level)
    {
        super(level);
        this.field_9402_e = -1;
        this.field_9401_f = -1;
        this.field_9400_g = -1;
        this.field_9399_h = 0;
        this.field_9398_i = false;
        this.field_9406_a = 0;
        this.field_9395_l = 0;
        this.setBoundingBoxSpacing(1.0f, 1.0f);
    }

    @Override
    protected void initDataTracker()
    {
    }

    @Override
    public boolean method_1364(final double d)
    {
        double d2 = this.boundingBox.getAverageSideLength() * 4.0;
        d2 *= 64.0;
        return d < d2 * d2;
    }

    public EntityZephyrSnowball(final World world, final LivingEntity entityliving, double d, double d1, double d2)
    {
        super(world);
        this.field_9402_e = -1;
        this.field_9401_f = -1;
        this.field_9400_g = -1;
        this.field_9399_h = 0;
        this.field_9398_i = false;
        this.field_9406_a = 0;
        this.field_9395_l = 0;
        this.field_9397_j = entityliving;
        this.setBoundingBoxSpacing(1.0f, 1.0f);
        this.method_1341(entityliving.x, entityliving.y, entityliving.z, entityliving.yaw, entityliving.pitch);
        this.method_1338(this.x, this.y, this.z, this.yaw, this.pitch);
        this.eyeHeight = 0.0f;
        final double velocityX = 0.0;
        this.velocityZ = velocityX;
        this.velocityY = velocityX;
        this.velocityX = velocityX;
        d += this.random.nextGaussian() * 0.4;
        d1 += this.random.nextGaussian() * 0.4;
        d2 += this.random.nextGaussian() * 0.4;
        final double d3 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);
        this.field_9405_b = d / d3 * 0.1;
        this.field_9404_c = d1 / d3 * 0.1;
        this.field_9403_d = d2 / d3 * 0.1;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.field_9406_a > 0)
        {
            --this.field_9406_a;
        }
        if (this.field_9398_i)
        {
            final int i = this.world.getBlockId(this.field_9402_e, this.field_9401_f, this.field_9400_g);
            if (i == this.field_9399_h)
            {
                ++this.field_9396_k;
                if (this.field_9396_k == 1200)
                {
                    this.markDead();
                }
                return;
            }
            this.field_9398_i = false;
            this.velocityX *= this.random.nextFloat() * 0.2f;
            this.velocityY *= this.random.nextFloat() * 0.2f;
            this.velocityZ *= this.random.nextFloat() * 0.2f;
            this.field_9396_k = 0;
            this.field_9395_l = 0;
        }
        else
        {
            ++this.field_9395_l;
        }
        Vec3d vec3d = Vec3d.createCached(this.x, this.y, this.z);
        Vec3d vec3d2 = Vec3d.createCached(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
        HitResult movingobjectposition = this.world.method_160(vec3d, vec3d2);
        vec3d = Vec3d.createCached(this.x, this.y, this.z);
        vec3d2 = Vec3d.createCached(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
        if (movingobjectposition != null)
        {
            vec3d2 = Vec3d.createCached(movingobjectposition.pos.x, movingobjectposition.pos.y, movingobjectposition.pos.z);
        }
        Entity entity = null;
        final List list = this.world.getEntities(this, this.boundingBox.stretch(this.velocityX, this.velocityY, this.velocityZ).expand(1.0, 1.0, 1.0));
        double d = 0.0;
        for (int j = 0; j < list.size(); ++j)
        {
            final Entity entity2 = (Entity) list.get(j);
            if (entity2.method_1356())
            {
                if (entity2 != this.field_9397_j || this.field_9395_l >= 25)
                {
                    final float f2 = 0.3f;
                    final Box axisalignedbb = entity2.boundingBox.expand(f2, f2, f2);
                    final HitResult movingobjectposition2 = axisalignedbb.raycast(vec3d, vec3d2);
                    if (movingobjectposition2 != null)
                    {
                        final double d2 = vec3d.distanceTo(movingobjectposition2.pos);
                        if (d2 < d || d == 0.0)
                        {
                            entity = entity2;
                            d = d2;
                        }
                    }
                }
            }
        }
        if (entity != null)
        {
            movingobjectposition = new HitResult(entity);
        }
        if (movingobjectposition != null)
        {
            if (movingobjectposition.entity != null)
            {
                if (!movingobjectposition.entity.damage(this.field_9397_j, 0))
                {
                }
                final Entity field_1989 = movingobjectposition.entity;
                field_1989.velocityX += this.velocityX;
                final Entity field_1990 = movingobjectposition.entity;
                field_1990.velocityY += 0.2;
                final Entity field_1991 = movingobjectposition.entity;
                field_1991.velocityZ += this.velocityZ;
            }
            this.markDead();
        }
        this.x += this.velocityX;
        this.y += this.velocityY;
        this.z += this.velocityZ;
        final float f3 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
        this.yaw = (float) (Math.atan2(this.velocityX, this.velocityZ) * 180.0 / 3.1415927410125732);
        this.pitch = (float) (Math.atan2(this.velocityY, (double) f3) * 180.0 / 3.1415927410125732);
        while (this.pitch - this.prevPitch < -180.0f)
        {
            this.prevPitch -= 360.0f;
        }
        while (this.pitch - this.prevPitch >= 180.0f)
        {
            this.prevPitch += 360.0f;
        }
        while (this.yaw - this.prevYaw < -180.0f)
        {
            this.prevYaw -= 360.0f;
        }
        while (this.yaw - this.prevYaw >= 180.0f)
        {
            this.prevYaw += 360.0f;
        }
        this.pitch = this.prevPitch + (this.pitch - this.prevPitch) * 0.2f;
        this.yaw = this.prevYaw + (this.yaw - this.prevYaw) * 0.2f;
        float f4 = 0.95f;
        if (this.isSubmergedInWater())
        {
            for (int k = 0; k < 4; ++k)
            {
                final float f5 = 0.25f;
                this.world.addParticle("bubble", this.x - this.velocityX * f5, this.y - this.velocityY * f5, this.z - this.velocityZ * f5, this.velocityX, this.velocityY, this.velocityZ);
            }
            f4 = 0.8f;
        }
        this.velocityX += this.field_9405_b;
        this.velocityY += this.field_9404_c;
        this.velocityZ += this.field_9403_d;
        this.velocityX *= f4;
        this.velocityY *= f4;
        this.velocityZ *= f4;
        this.world.addParticle("smoke", this.x, this.y + 0.5, this.z, 0.0, 0.0, 0.0);
        this.method_1338(this.x, this.y, this.z, this.yaw, this.pitch);
    }

    public void writeNbt(final NbtCompound tag)
    {
        tag.putShort("xTile", (short) this.field_9402_e);
        tag.putShort("yTile", (short) this.field_9401_f);
        tag.putShort("zTile", (short) this.field_9400_g);
        tag.putByte("inTile", (byte) this.field_9399_h);
        tag.putByte("shake", (byte) this.field_9406_a);
        tag.putByte("inGround", (byte) (byte) (this.field_9398_i ? 1 : 0));
    }

    public void readNbt(final NbtCompound tag)
    {
        this.field_9402_e = tag.getShort("xTile");
        this.field_9401_f = tag.getShort("yTile");
        this.field_9400_g = tag.getShort("zTile");
        this.field_9399_h = (tag.getByte("inTile") & 0xFF);
        this.field_9406_a = (tag.getByte("shake") & 0xFF);
        this.field_9398_i = (tag.getByte("inGround") == 1);
    }

    @Override
    public float method_1369()
    {
        return 1.0f;
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        this.method_1336();
        if (target != null)
        {
            final Vec3d vec3d = target.method_1320();
            if (vec3d != null)
            {
                this.velocityX = vec3d.x;
                this.velocityY = vec3d.y;
                this.velocityZ = vec3d.z;
                this.field_9405_b = this.velocityX * 0.1;
                this.field_9404_c = this.velocityY * 0.1;
                this.field_9403_d = this.velocityZ * 0.1;
            }
            return true;
        }
        return false;
    }

    @Override
    public float method_1366()
    {
        return 0.0f;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("ZephyrSnowball");
    }
}
