package com.gildedgames.aether.entity.projectile;

import com.gildedgames.aether.AetherMod;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityFlamingArrow extends Entity implements MobSpawnDataProvider
{
    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private int field_28019_h;
    private boolean inGround;
    public boolean doesArrowBelongToPlayer;
    public int arrowShake;
    public LivingEntity owner;
    private int ticksInGround;
    private int ticksInAir;

    public EntityFlamingArrow(final World level)
    {
        super(level);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = 0;
        this.field_28019_h = 0;
        this.inGround = false;
        this.doesArrowBelongToPlayer = false;
        this.arrowShake = 0;
        this.ticksInAir = 0;
        this.setBoundingBoxSpacing(0.5f, 0.5f);
        this.fire = 1;
    }

    public EntityFlamingArrow(final World world, final double d, final double d1, final double d2)
    {
        super(world);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = 0;
        this.field_28019_h = 0;
        this.inGround = false;
        this.doesArrowBelongToPlayer = false;
        this.arrowShake = 0;
        this.ticksInAir = 0;
        this.setBoundingBoxSpacing(0.5f, 0.5f);
        this.method_1338(d, d1, d2, this.yaw, this.pitch);
        this.eyeHeight = 0.0f;
    }

    public EntityFlamingArrow(final World world, final LivingEntity entityliving)
    {
        super(world);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = 0;
        this.field_28019_h = 0;
        this.inGround = false;
        this.doesArrowBelongToPlayer = false;
        this.arrowShake = 0;
        this.ticksInAir = 0;
        this.owner = entityliving;
        this.doesArrowBelongToPlayer = (entityliving instanceof PlayerEntity);
        this.setBoundingBoxSpacing(0.5f, 0.5f);
        this.method_1341(entityliving.x, entityliving.y + entityliving.method_1378(), entityliving.z, entityliving.yaw, entityliving.pitch);
        this.x -= MathHelper.cos(this.yaw / 180.0f * 3.141593f) * 0.16f;
        this.y -= 0.10000000149011612;
        this.z -= MathHelper.sin(this.yaw / 180.0f * 3.141593f) * 0.16f;
        this.method_1338(this.x, this.y, this.z, this.yaw, this.pitch);
        this.eyeHeight = 0.0f;
        this.velocityX = -MathHelper.sin(this.yaw / 180.0f * 3.141593f) * MathHelper.cos(this.pitch / 180.0f * 3.141593f);
        this.velocityZ = MathHelper.cos(this.yaw / 180.0f * 3.141593f) * MathHelper.cos(this.pitch / 180.0f * 3.141593f);
        this.velocityY = -MathHelper.sin(this.pitch / 180.0f * 3.141593f);
        this.setArrowHeading(this.velocityX, this.velocityY, this.velocityZ, 1.5f, 1.0f);
    }

    @Override
    protected void initDataTracker()
    {
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
            this.prevPitch = this.pitch;
            this.prevYaw = this.yaw;
            this.method_1341(this.x, this.y, this.z, this.yaw, this.pitch);
            this.ticksInGround = 0;
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
        final int i = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
        if (i > 0)
        {
            Block.BLOCKS[i].updateBoundingBox(this.world, this.xTile, this.yTile, this.zTile);
            final Box axisalignedbb = Block.BLOCKS[i].getCollisionShape(this.world, this.xTile, this.yTile, this.zTile);
            if (axisalignedbb != null && axisalignedbb.contains(Vec3d.createCached(this.x, this.y, this.z)))
            {
                this.inGround = true;
            }
        }
        if (this.arrowShake > 0)
        {
            --this.arrowShake;
        }
        if (!this.inGround)
        {
            this.world.addParticle(this.random.nextBoolean() ? "flame" : "smoke", this.x, this.y, this.z, 0.0, 0.0, 0.0);
            ++this.ticksInAir;
            Vec3d vec3d = Vec3d.createCached(this.x, this.y, this.z);
            Vec3d vec3d2 = Vec3d.createCached(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
            HitResult movingobjectposition = this.world.method_162(vec3d, vec3d2, false, true);
            vec3d = Vec3d.createCached(this.x, this.y, this.z);
            vec3d2 = Vec3d.createCached(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
            if (movingobjectposition != null)
            {
                vec3d2 = Vec3d.createCached(movingobjectposition.pos.x, movingobjectposition.pos.y, movingobjectposition.pos.z);
            }
            Entity entity = null;
            final List list = this.world.getEntities(this, this.boundingBox.stretch(this.velocityX, this.velocityY, this.velocityZ).expand(1.0, 1.0, 1.0));
            double d = 0.0;
            for (int l = 0; l < list.size(); ++l)
            {
                final Entity entity2 = (Entity) list.get(l);
                if (entity2.method_1356())
                {
                    if (entity2 != this.owner || this.ticksInAir >= 5)
                    {
                        final float f2 = 0.3f;
                        final Box axisalignedbb2 = entity2.boundingBox.expand(f2, f2, f2);
                        final HitResult movingobjectposition2 = axisalignedbb2.raycast(vec3d, vec3d2);
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
                    if (movingobjectposition.entity.damage(this.owner, 4))
                    {
                        this.world.playSound((Entity) this, "random.drr", 1.0f, 1.2f / (this.random.nextFloat() * 0.2f + 0.9f));
                        movingobjectposition.entity.fire = 100;
                        final int x = MathHelper.floor(movingobjectposition.entity.boundingBox.minX);
                        final int y = MathHelper.floor(movingobjectposition.entity.boundingBox.minY);
                        final int z = MathHelper.floor(movingobjectposition.entity.boundingBox.minZ);
                        this.world.setBlock(x, y, z, 51);
                        this.markDead();
                    }
                    else
                    {
                        this.velocityX *= -0.10000000149011612;
                        this.velocityY *= -0.10000000149011612;
                        this.velocityZ *= -0.10000000149011612;
                        this.yaw += 180.0f;
                        this.prevYaw += 180.0f;
                        this.ticksInAir = 0;
                    }
                }
                else
                {
                    this.xTile = movingobjectposition.blockX;
                    this.yTile = movingobjectposition.blockY;
                    this.zTile = movingobjectposition.blockZ;
                    this.inTile = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
                    this.field_28019_h = this.world.getBlockMeta(this.xTile, this.yTile, this.zTile);
                    this.velocityX = (float) (movingobjectposition.pos.x - this.x);
                    this.velocityY = (float) (movingobjectposition.pos.y - this.y);
                    this.velocityZ = (float) (movingobjectposition.pos.z - this.z);
                    final float f3 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityY * this.velocityY + this.velocityZ * this.velocityZ);
                    this.x -= this.velocityX / f3 * 0.05000000074505806;
                    this.y -= this.velocityY / f3 * 0.05000000074505806;
                    this.z -= this.velocityZ / f3 * 0.05000000074505806;
                    this.world.playSound((Entity) this, "random.drr", 1.0f, 1.2f / (this.random.nextFloat() * 0.2f + 0.9f));
                    final int xPos = MathHelper.floor(this.x);
                    final int yPos = MathHelper.floor(this.y);
                    final int zPos = MathHelper.floor(this.z);
                    this.world.setBlock(xPos, yPos, zPos, 51);
                    this.inGround = true;
                    this.arrowShake = 7;
                }
            }
            this.x += this.velocityX;
            this.y += this.velocityY;
            this.z += this.velocityZ;
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
            float f5 = 0.99f;
            final float f6 = 0.03f;
            if (this.method_1334())
            {
                for (int i2 = 0; i2 < 4; ++i2)
                {
                    final float f7 = 0.25f;
                    this.world.addParticle("bubble", this.x - this.velocityX * f7, this.y - this.velocityY * f7, this.z - this.velocityZ * f7, this.velocityX, this.velocityY, this.velocityZ);
                }
                f5 = 0.8f;
            }
            this.velocityX *= f5;
            this.velocityY *= f5;
            this.velocityZ *= f5;
            this.velocityY -= f6;
            this.method_1338(this.x, this.y, this.z, this.yaw, this.pitch);
            return;
        }
        final int j = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
        final int k = this.world.getBlockMeta(this.xTile, this.yTile, this.zTile);
        if (j != this.inTile || k != this.field_28019_h)
        {
            this.inGround = false;
            this.velocityX *= this.random.nextFloat() * 0.2f;
            this.velocityY *= this.random.nextFloat() * 0.2f;
            this.velocityZ *= this.random.nextFloat() * 0.2f;
            this.ticksInGround = 0;
            this.ticksInAir = 0;
            return;
        }
        ++this.ticksInGround;
        if (this.ticksInGround == 1200)
        {
            this.markDead();
        }
    }

    public void writeNbt(final NbtCompound tag)
    {
        tag.putShort("xTile", (short) this.xTile);
        tag.putShort("yTile", (short) this.yTile);
        tag.putShort("zTile", (short) this.zTile);
        tag.putByte("inTile", (byte) this.inTile);
        tag.putByte("inData", (byte) this.field_28019_h);
        tag.putByte("shake", (byte) this.arrowShake);
        tag.putByte("inGround", (byte) (byte) (this.inGround ? 1 : 0));
        tag.putBoolean("player", this.doesArrowBelongToPlayer);
    }

    public void readNbt(final NbtCompound tag)
    {
        this.xTile = tag.getShort("xTile");
        this.yTile = tag.getShort("yTile");
        this.zTile = tag.getShort("zTile");
        this.inTile = (tag.getByte("inTile") & 0xFF);
        this.field_28019_h = (tag.getByte("inData") & 0xFF);
        this.arrowShake = (tag.getByte("shake") & 0xFF);
        this.inGround = (tag.getByte("inGround") == 1);
        this.doesArrowBelongToPlayer = tag.getBoolean("player");
    }

    @Override
    public void onPlayerInteraction(final PlayerEntity entityplayer)
    {
        if (this.world.isRemote)
        {
            return;
        }
        if (this.inGround && this.doesArrowBelongToPlayer && this.arrowShake <= 0 && entityplayer.inventory.method_671(new ItemStack(Item.ARROW, 1)))
        {
            this.world.playSound((Entity) this, "random.pop", 0.2f, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            entityplayer.method_491(this, 1);
            this.markDead();
        }
    }

    @Override
    public float method_1366()
    {
        return 0.0f;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("FlamingArrow");
    }
}
