package com.gildedgames.aether.entity.base;

import com.gildedgames.aether.mixin.access.LivingAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public abstract class EntityProjectileBase extends Entity
{
    public float speed;
    public float slowdown;
    public float curvature;
    public float precision;
    public float hitBox;
    public int dmg;
    public ItemStack item;
    public int ttlInGround;
    public int xTile;
    public int yTile;
    public int zTile;
    public int inTile;
    public int inData;
    public boolean inGround;
    public int arrowShake;
    public LivingEntity shooter;
    public int ticksInGround;
    public int ticksFlying;
    public boolean shotByPlayer;

    public EntityProjectileBase(final World level)
    {
        super(level);
    }

    public EntityProjectileBase(final World world, final double d, final double d1, final double d2)
    {
        this(world);
        this.method_1338(d, d1, d2, this.yaw, this.pitch);
    }

    public EntityProjectileBase(final World world, final LivingEntity entityliving)
    {
        this(world);
        this.shooter = entityliving;
        this.shotByPlayer = (entityliving instanceof PlayerEntity);
        this.method_1341(entityliving.x, entityliving.y + entityliving.method_1378(), entityliving.z, entityliving.yaw, entityliving.pitch);
        this.x -= MathHelper.cos(this.yaw / 180.0f * 3.141593f) * 0.16f;
        this.y -= 0.10000000149011612;
        this.z -= MathHelper.sin(this.yaw / 180.0f * 3.141593f) * 0.16f;
        this.method_1338(this.x, this.y, this.z, this.yaw, this.pitch);
        this.velocityX = -MathHelper.sin(this.yaw / 180.0f * 3.141593f) * MathHelper.cos(this.pitch / 180.0f * 3.141593f);
        this.velocityZ = MathHelper.cos(this.yaw / 180.0f * 3.141593f) * MathHelper.cos(this.pitch / 180.0f * 3.141593f);
        this.velocityY = -MathHelper.sin(this.pitch / 180.0f * 3.141593f);
        this.setArrowHeading(this.velocityX, this.velocityY, this.velocityZ, this.speed, this.precision);
    }

    @Override
    protected void initDataTracker()
    {
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = 0;
        this.inGround = false;
        this.arrowShake = 0;
        this.ticksFlying = 0;
        this.setBoundingBoxSpacing(0.5f, 0.5f);
        this.eyeHeight = 0.0f;
        this.hitBox = 0.3f;
        this.speed = 1.0f;
        this.slowdown = 0.99f;
        this.curvature = 0.03f;
        this.dmg = 4;
        this.precision = 1.0f;
        this.ttlInGround = 1200;
        this.item = null;
    }

    @Override
    public void markDead()
    {
        this.shooter = null;
        super.markDead();
    }

    public void setArrowHeading(double d, double d1, double d2, final float f, final float f1)
    {
        final float f2 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += this.random.nextGaussian() * 0.007499999832361937 * f1;
        d1 += this.random.nextGaussian() * 0.007499999832361937 * f1;
        d2 += this.random.nextGaussian() * 0.007499999832361937 * f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        this.velocityX = d;
        this.velocityY = d1;
        this.velocityZ = d2;
        final float f3 = MathHelper.sqrt(d * d + d2 * d2);
        final float n = (float) (Math.atan2(d, d2) * 180.0 / 3.1415927410125732);
        this.yaw = n;
        this.prevYaw = n;
        final float n2 = (float) (Math.atan2(d1, (double) f3) * 180.0 / 3.1415927410125732);
        this.pitch = n2;
        this.prevPitch = n2;
        this.ticksInGround = 0;
    }

    @Override
    public void method_1365(final double velocityX, final double velocityY, final double velocityZ)
    {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        if (this.prevPitch == 0.0f && this.prevYaw == 0.0f)
        {
            final float f = MathHelper.sqrt(velocityX * velocityX + velocityZ * velocityZ);
            final float n = (float) (Math.atan2(velocityX, velocityZ) * 180.0 / 3.1415927410125732);
            this.yaw = n;
            this.prevYaw = n;
            final float n2 = (float) (Math.atan2(velocityY, (double) f) * 180.0 / 3.1415927410125732);
            this.pitch = n2;
            this.prevPitch = n2;
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.prevPitch == 0.0f && this.prevYaw == 0.0f)
        {
            final float f = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
            final float n = (float) (Math.atan2(this.velocityX, this.velocityZ) * 180.0 / 3.1415927410125732);
            this.yaw = n;
            this.prevYaw = n;
            final float n2 = (float) (Math.atan2(this.velocityY, (double) f) * 180.0 / 3.1415927410125732);
            this.pitch = n2;
            this.prevPitch = n2;
        }
        if (this.arrowShake > 0)
        {
            --this.arrowShake;
        }
        if (this.inGround)
        {
            final int i = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
            final int k = this.world.getBlockMeta(this.xTile, this.yTile, this.zTile);
            if (i == this.inTile && k == this.inData)
            {
                ++this.ticksInGround;
                this.tickInGround();
                if (this.ticksInGround == this.ttlInGround)
                {
                    this.markDead();
                }
                return;
            }
            this.inGround = false;
            this.velocityX *= this.random.nextFloat() * 0.2f;
            this.velocityY *= this.random.nextFloat() * 0.2f;
            this.velocityZ *= this.random.nextFloat() * 0.2f;
            this.ticksInGround = 0;
            this.ticksFlying = 0;
        }
        else
        {
            ++this.ticksFlying;
        }
        this.tickFlying();
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
            if (this.canBeShot(entity2))
            {
                final float f2 = this.hitBox;
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
        if (entity != null)
        {
            movingobjectposition = new HitResult(entity);
        }
        if (movingobjectposition != null && this.onHit())
        {
            final Entity ent = movingobjectposition.entity;
            if (ent != null)
            {
                if (this.onHitTarget(ent))
                {
                    if (ent instanceof LivingEntity && !(ent instanceof PlayerEntity))
                    {
                        ((LivingAccessor) ent).set1058(0);
                    }
                    ent.damage(this.shooter, this.dmg);
                    this.markDead();
                }
            }
            else
            {
                this.xTile = movingobjectposition.blockX;
                this.yTile = movingobjectposition.blockY;
                this.zTile = movingobjectposition.blockZ;
                this.inTile = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
                this.inData = this.world.getBlockMeta(this.xTile, this.yTile, this.zTile);
                if (this.onHitBlock(movingobjectposition))
                {
                    this.velocityX = (float) (movingobjectposition.pos.x - this.x);
                    this.velocityY = (float) (movingobjectposition.pos.y - this.y);
                    this.velocityZ = (float) (movingobjectposition.pos.z - this.z);
                    final float f3 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityY * this.velocityY + this.velocityZ * this.velocityZ);
                    this.x -= this.velocityX / f3 * 0.05000000074505806;
                    this.y -= this.velocityY / f3 * 0.05000000074505806;
                    this.z -= this.velocityZ / f3 * 0.05000000074505806;
                    this.inGround = true;
                    this.arrowShake = 7;
                }
                else
                {
                    this.inTile = 0;
                    this.inData = 0;
                }
            }
        }
        this.x += this.velocityX;
        this.y += this.velocityY;
        this.z += this.velocityZ;
        this.handleMotionUpdate();
        final float f4 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
        this.yaw = (float) (Math.atan2(this.velocityX, this.velocityZ) * 180.0 / 3.1415927410125732);
        this.pitch = (float) (Math.atan2(this.velocityY, (double) f4) * 180.0 / 3.1415927410125732);
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
        this.method_1338(this.x, this.y, this.z, this.yaw, this.pitch);
    }

    public void handleMotionUpdate()
    {
        float slow = this.slowdown;
        if (this.isSubmergedInWater())
        {
            for (int k = 0; k < 4; ++k)
            {
                final float f6 = 0.25f;
                this.world.addParticle("bubble", this.x - this.velocityX * f6, this.y - this.velocityY * f6, this.z - this.velocityZ * f6, this.velocityX, this.velocityY, this.velocityZ);
            }
            slow *= 0.8f;
        }
        this.velocityX *= slow;
        this.velocityY *= slow;
        this.velocityZ *= slow;
        this.velocityY -= this.curvature;
    }

    public void writeNbt(final NbtCompound tag)
    {
        tag.putShort("xTile", (short) this.xTile);
        tag.putShort("yTile", (short) this.yTile);
        tag.putShort("zTile", (short) this.zTile);
        tag.putByte("inTile", (byte) this.inTile);
        tag.putByte("inData", (byte) this.inData);
        tag.putByte("shake", (byte) this.arrowShake);
        tag.putByte("inGround", (byte) (byte) (this.inGround ? 1 : 0));
        tag.putBoolean("player", this.shotByPlayer);
    }

    public void readNbt(final NbtCompound tag)
    {
        this.xTile = tag.getShort("xTile");
        this.yTile = tag.getShort("yTile");
        this.zTile = tag.getShort("zTile");
        this.inTile = (tag.getByte("inTile") & 0xFF);
        this.inData = (tag.getByte("inData") & 0xFF);
        this.arrowShake = (tag.getByte("shake") & 0xFF);
        this.inGround = (tag.getByte("inGround") == 1);
        this.shotByPlayer = tag.getBoolean("player");
    }

    @Override
    public void onPlayerInteraction(final PlayerEntity entityplayer)
    {
        if (this.item == null)
        {
            return;
        }
        if (this.world.isRemote)
        {
            return;
        }
        if (this.inGround && this.shotByPlayer && this.arrowShake <= 0 && entityplayer.inventory.method_671(this.item.copy()))
        {
            this.world.playSound((Entity) this, "random.pop", 0.2f, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            entityplayer.method_491(this, 1);
            this.markDead();
        }
    }

    public boolean canBeShot(final Entity ent)
    {
        return ent.method_1356() && (ent != this.shooter || this.ticksFlying >= 5) && (!(ent instanceof LivingEntity) || ((LivingEntity) ent).field_1041 <= 0);
    }

    public boolean onHit()
    {
        return true;
    }

    public boolean onHitTarget(final Entity target)
    {
        this.world.playSound((Entity) this, "random.drr", 1.0f, 1.2f / (this.random.nextFloat() * 0.2f + 0.9f));
        return true;
    }

    public void tickFlying()
    {
    }

    public void tickInGround()
    {
    }

    public boolean onHitBlock(final HitResult mop)
    {
        return this.onHitBlock();
    }

    public boolean onHitBlock()
    {
        this.world.playSound((Entity) this, "random.drr", 1.0f, 1.2f / (this.random.nextFloat() * 0.2f + 0.9f));
        return true;
    }

    @Override
    public float method_1366()
    {
        return 0.0f;
    }
}
