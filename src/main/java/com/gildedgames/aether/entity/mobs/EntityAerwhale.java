package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.class_65;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResultType;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityAerwhale extends FlyingEntity implements class_65, MobSpawnDataProvider
{
    private long checkTime;
    private final long checkTimeInterval = 3000L;
    private double checkX;
    private double checkY;
    private double checkZ;
    private final double minTraversalDist = 3.0;
    private boolean isStuckWarning;
    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity;
    private int aggroCooldown;
    public int prevAttackCounter;
    public int attackCounter;
    public double motionYaw;
    public double motionPitch;

    public EntityAerwhale(final World level)
    {
        super(level);
        this.checkTime = 0L;
        this.checkX = 0.0;
        this.checkY = 0.0;
        this.checkZ = 0.0;
        this.isStuckWarning = false;
        this.courseChangeCooldown = 0;
        this.targetedEntity = null;
        this.fireImmune = true;
        this.aggroCooldown = 0;
        this.prevAttackCounter = 0;
        this.attackCounter = 0;
        this.texture = "aether:textures/entity/Aerwhale.png";
        this.setBoundingBoxSpacing(4.0f, 4.0f);
        this.movementSpeed = 0.5f;
        this.health = 20;
        this.yaw = 360.0f * this.random.nextFloat();
        this.pitch = 90.0f * this.random.nextFloat() - 45.0f;
        this.field_1622 = true;
    }

    @Override
    protected void initDataTracker()
    {
        super.initDataTracker();
        this.dataTracker.startTracking(16, 0);
    }

    @Override
    public void method_937()
    {
    }

    @Override
    public void tick()
    {
        final int byte0 = this.dataTracker.getInt(16);
        this.texture = ((byte0 != 1) ? "aether:textures/entity/Aerwhale.png" : "aether:textures/entity/Aerwhale.png");
        final double[] distances = {this.openSpace(0.0f, 0.0f), this.openSpace(45.0f, 0.0f), this.openSpace(0.0f, 45.0f), this.openSpace(-45.0f, 0.0f), this.openSpace(0.0f, -45.0f)};
        int longest = 0;
        for (int i = 1; i < 5; ++i)
        {
            if (distances[i] > distances[longest])
            {
                longest = i;
            }
        }
        switch (longest)
        {
            case 0:
            {
                if (distances[0] != 50.0)
                {
                    this.pitch = -this.pitch;
                    this.yaw = -this.yaw;
                    break;
                }
                this.motionYaw *= 0.8999999761581421;
                this.motionPitch *= 0.8999999761581421;
                if (this.y > 100.0)
                {
                    this.motionPitch -= 2.0;
                }
                if (this.y < 20.0)
                {
                    this.motionPitch += 2.0;
                    break;
                }
                break;
            }
            case 1:
            {
                this.motionYaw += 5.0;
                break;
            }
            case 2:
            {
                this.motionPitch -= 5.0;
                break;
            }
            case 3:
            {
                this.motionYaw -= 5.0;
                break;
            }
            case 4:
            {
                this.motionPitch += 5.0;
                break;
            }
        }
        this.motionYaw += 2.0f * this.random.nextFloat() - 1.0f;
        this.motionPitch += 2.0f * this.random.nextFloat() - 1.0f;
        this.pitch += (float) (0.1 * this.motionPitch);
        this.yaw += (float) (0.1 * this.motionYaw);
        if (this.pitch < -60.0f)
        {
            this.pitch = -60.0f;
        }
        if (this.pitch > 60.0f)
        {
            this.pitch = 60.0f;
        }
        this.pitch *= (float) 0.99;
        this.velocityX += 0.005 * Math.cos(this.yaw / 180.0 * 3.141592653589793) * Math.cos(this.pitch / 180.0 * 3.141592653589793);
        this.velocityY += 0.005 * Math.sin(this.pitch / 180.0 * 3.141592653589793);
        this.velocityZ += 0.005 * Math.sin(this.yaw / 180.0 * 3.141592653589793) * Math.cos(this.pitch / 180.0 * 3.141592653589793);
        this.velocityX *= 0.98;
        this.velocityY *= 0.98;
        this.velocityZ *= 0.98;
        int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        if (this.velocityX > 0.0 && this.world.getBlockId(i + 1, j, k) != 0)
        {
            this.velocityX = -this.velocityX;
            this.motionYaw -= 10.0;
        }
        else if (this.velocityX < 0.0 && this.world.getBlockId(i - 1, j, k) != 0)
        {
            this.velocityX = -this.velocityX;
            this.motionYaw += 10.0;
        }
        if (this.velocityY > 0.0 && this.world.getBlockId(i, j + 1, k) != 0)
        {
            this.velocityY = -this.velocityY;
            this.motionPitch -= 10.0;
        }
        else if (this.velocityY < 0.0 && this.world.getBlockId(i, j - 1, k) != 0)
        {
            this.velocityY = -this.velocityY;
            this.motionPitch += 10.0;
        }
        if (this.velocityZ > 0.0 && this.world.getBlockId(i, j, k + 1) != 0)
        {
            this.velocityZ = -this.velocityZ;
            this.motionYaw -= 10.0;
        }
        else if (this.velocityZ < 0.0 && this.world.getBlockId(i, j, k - 1) != 0)
        {
            this.velocityZ = -this.velocityZ;
            this.motionYaw += 10.0;
        }
        this.fire = 0;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        this.checkForBeingStuck();
    }

    public double getSpeed()
    {
        return Math.sqrt(this.velocityX * this.velocityX + this.velocityY * this.velocityY + this.velocityZ * this.velocityZ);
    }

    private double openSpace(final float rotationYawOffset, final float rotationPitchOffset)
    {
        final float yaw = this.yaw + rotationYawOffset;
        final float pitch = this.yaw + rotationYawOffset;
        final Vec3d vec3d = Vec3d.createCached(this.x, this.y, this.z);
        final float f3 = MathHelper.cos(-yaw * 0.01745329f - 3.141593f);
        final float f4 = MathHelper.sin(-yaw * 0.01745329f - 3.141593f);
        final float f5 = MathHelper.cos(-pitch * 0.01745329f);
        final float f6 = MathHelper.sin(-pitch * 0.01745329f);
        final float f7 = f4 * f5;
        final float f8 = f6;
        final float f9 = f3 * f5;
        final double d3 = 50.0;
        final Vec3d vec3d2 = vec3d.add(f7 * d3, f8 * d3, f9 * d3);
        final HitResult movingobjectposition = this.world.method_161(vec3d, vec3d2, true);
        if (movingobjectposition == null)
        {
            return 50.0;
        }
        // field_789 is TILE, field_790 is ENTITY
        if (movingobjectposition.type == HitResultType.BLOCK)
        {
            final double i = movingobjectposition.blockX - this.x;
            final double j = movingobjectposition.blockY - this.y;
            final double k = movingobjectposition.blockZ - this.z;
            return Math.sqrt(i * i + j * j + k * k);
        }
        return 50.0;
    }

    @Override
    protected void method_910()
    {
    }

    private void checkForBeingStuck()
    {
        final long curtime = System.currentTimeMillis();
        if (curtime > this.checkTime + 3000L)
        {
            final double diffx = this.x - this.checkX;
            final double diffy = this.y - this.checkY;
            final double diffz = this.z - this.checkZ;
            final double distanceTravelled = Math.sqrt(diffx * diffx + diffy * diffy + diffz * diffz);
            if (distanceTravelled < 3.0)
            {
                if (!this.isStuckWarning)
                {
                    this.isStuckWarning = true;
                }
                else
                {
                    this.markDead();
                }
            }
            this.checkX = this.x;
            this.checkY = this.y;
            this.checkZ = this.z;
            this.checkTime = curtime;
        }
    }

    private boolean isCourseTraversable(final double d, final double d1, final double d2, final double d3)
    {
        final double d4 = (this.waypointX - this.x) / d3;
        final double d5 = (this.waypointY - this.y) / d3;
        final double d6 = (this.waypointZ - this.z) / d3;
        final Box axisalignedbb = this.boundingBox.copy();
        for (int i = 1; i < d3; ++i)
        {
            axisalignedbb.translate(d4, d5, d6);
            if (this.world.method_190(this, axisalignedbb).size() > 0)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    protected String method_911()
    {
        return "aether:aether.sound.mobs.aerwhale.aerwhalecall";
    }

    @Override
    protected String method_912()
    {
        return "aether:aether.sound.mobs.aerwhale.aerwhaledeath";
    }

    @Override
    protected String method_913()
    {
        return "aether:aether.sound.mobs.aerwhale.aerwhaledeath";
    }

    @Override
    protected float method_915()
    {
        return 3.0f;
    }

    @Override
    public int method_916()
    {
        return 1;
    }

    public boolean method_940()
    {
        return true;
    }

    @Override
    public boolean canSpawn()
    {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        return this.random.nextInt(65) == 0 && this.world.canSpawnEntity(this.boundingBox) && this.world.method_190(this, this.boundingBox).size() == 0 && !this.world.method_218(this.boundingBox) && this.world.getBlockId(i, j - 1, k) != AetherBlocks.DUNGEON_STONE.id && this.world.getBlockId(i, j - 1, k) != AetherBlocks.LIGHT_DUNGEON_STONE.id && this.world.getBlockId(i, j - 1, k) != AetherBlocks.LOCKED_DUNGEON_STONE.id && this.world.getBlockId(i, j - 1, k) != AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id && this.world.getBlockId(i, j - 1, k) != AetherBlocks.HOLYSTONE.id;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Aerwhale");
    }
}
