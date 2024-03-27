package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.projectile.EntityFiroBall;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityMiniCloud extends FlyingEntity implements MobSpawnDataProvider
{
    public int shotTimer;
    public int lifeSpan;
    public boolean gotPlayer;
    public boolean toLeft;
    public LivingEntity dude;
    public double targetX;
    public double targetY;
    public double targetZ;

    public EntityMiniCloud(final World level)
    {
        super(level);
        this.texture = "aether:textures/entity/minicloud.png";
        this.setBoundingBoxSpacing(0.0f, 0.0f);
        this.field_1642 = true;
        this.field_1643 = 1.75f;
    }

    public EntityMiniCloud(final World world, final PlayerEntity ep, final boolean flag)
    {
        super(world);
        this.texture = "aether:textures/entity/minicloud.png";
        this.setBoundingBoxSpacing(0.5f, 0.45f);
        this.dude = ep;
        this.toLeft = flag;
        this.lifeSpan = 3600;
        this.getTargetPos();
        this.method_1340(this.targetX, this.targetY, this.targetZ);
        this.pitch = this.dude.pitch;
        this.yaw = this.dude.yaw;
        this.field_1643 = 1.75f;
        this.method_919();
    }

    @Override
    public boolean method_1364(final double d)
    {
        return true;
    }

    public void getTargetPos()
    {
        if (this.method_1351(this.dude) > 2.0f)
        {
            this.targetX = this.dude.x;
            this.targetY = this.dude.y - 0.10000000149011612;
            this.targetZ = this.dude.z;
        }
        else
        {
            double angle = this.dude.yaw;
            if (this.toLeft)
            {
                angle -= 90.0;
            }
            else
            {
                angle += 90.0;
            }
            angle /= -57.29577319531843;
            this.targetX = this.dude.x + Math.sin(angle) * 1.05;
            this.targetY = this.dude.y - 0.10000000149011612;
            this.targetZ = this.dude.z + Math.cos(angle) * 1.05;
        }
    }

    public boolean atShoulder()
    {
        final double a = this.x - this.targetX;
        final double b = this.y - this.targetY;
        final double c = this.z - this.targetZ;
        return Math.sqrt(a * a + b * b + c * c) < 0.3;
    }

    public void approachTarget()
    {
        final double a = this.targetX - this.x;
        final double b = this.targetY - this.y;
        final double c = this.targetZ - this.z;
        final double d = Math.sqrt(a * a + b * b + c * c) * 3.25;
        this.velocityX = (this.velocityX + a / d) / 2.0;
        this.velocityY = (this.velocityY + b / d) / 2.0;
        this.velocityZ = (this.velocityZ + c / d) / 2.0;
        final double angle = Math.atan2(a, c);
    }

    protected Entity findPlayer()
    {
        final PlayerEntity entityplayer = this.world.method_186(this, 16.0);
        if (entityplayer != null && this.method_928(entityplayer))
        {
            return entityplayer;
        }
        return null;
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("LifeSpan", (short) this.lifeSpan);
        tag.putShort("ShotTimer", (short) this.shotTimer);
        tag.putBoolean("GotPlayer", this.gotPlayer = (this.dude != null));
        tag.putBoolean("ToLeft", this.toLeft);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.lifeSpan = tag.getShort("LifeSpan");
        this.shotTimer = tag.getShort("ShotTimer");
        this.gotPlayer = tag.getBoolean("GotPlayer");
        this.toLeft = tag.getBoolean("ToLeft");
    }

    public void method_910()
    {
        super.method_910();
        --this.lifeSpan;
        if (this.lifeSpan <= 0)
        {
            this.method_919();
            this.dead = true;
            return;
        }
        if (this.shotTimer > 0)
        {
            --this.shotTimer;
        }
        if (this.gotPlayer && this.dude == null)
        {
            this.gotPlayer = false;
            this.dude = (LivingEntity) this.findPlayer();
        }
        if (this.dude == null || this.dude.dead)
        {
            this.method_919();
            this.dead = true;
            return;
        }
        this.getTargetPos();
        if (this.atShoulder())
        {
            this.velocityX *= 0.65;
            this.velocityY *= 0.65;
            this.velocityZ *= 0.65;
            this.yaw = this.dude.yaw + (this.toLeft ? 1.0f : -1.0f);
            this.pitch = this.dude.pitch;
            if (this.shotTimer <= 0 && this.dude instanceof PlayerEntity && ((PlayerEntity) this.dude).handSwinging)
            {
                final float spanish = this.yaw - (this.toLeft ? 1.0f : -1.0f);
                final double a = this.x + Math.sin(spanish / -57.29577319531843) * 1.6;
                final double b = this.y - 0.25;
                final double c = this.z + Math.cos(spanish / -57.29577319531843) * 1.6;
                final EntityFiroBall eh = new EntityFiroBall(this.world, a, b, c, true, true);
                this.world.method_210(eh);
                final Vec3d vec3d = this.method_1320();
                if (vec3d != null)
                {
                    eh.smotionX = vec3d.x * 1.5;
                    eh.smotionY = vec3d.y * 1.5;
                    eh.smotionZ = vec3d.z * 1.5;
                }
                eh.smacked = true;
                this.world.playSound((Entity) this, "aether:aether.sound.mobs.zephyr.zephyrshoot", 0.75f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
                this.shotTimer = 40;
            }
        }
        else
        {
            this.approachTarget();
        }
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        return (target == null || target != this.dude) && super.damage(target, amount);
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Minicloud");
    }
}
