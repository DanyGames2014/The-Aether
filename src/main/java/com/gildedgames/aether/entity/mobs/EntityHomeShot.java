package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityHomeShot extends FlyingEntity implements MobSpawnDataProvider
{
    public float[] sinage;
    public LivingEntity target;
    public boolean firstRun;
    public int life;
    public int lifeSpan;
    private static final double topSpeed = 0.125;
    private static final float sponge = 57.295773f;

    public EntityHomeShot(final World level)
    {
        super(level);
        this.texture = "aether:textures/entity/electroball.png";
        this.lifeSpan = 200;
        this.life = this.lifeSpan;
        this.setBoundingBoxSpacing(0.7f, 0.7f);
        this.firstRun = true;
        this.sinage = new float[3];
        this.fireImmune = true;
        for (int i = 0; i < 3; ++i)
        {
            this.sinage[i] = this.random.nextFloat() * 6.0f;
        }
    }

    public EntityHomeShot(final World world, final double x, final double y, final double z, final LivingEntity ep)
    {
        super(world);
        this.texture = "aether:textures/entity/electroball.png";
        this.lifeSpan = 200;
        this.life = this.lifeSpan;
        this.setBoundingBoxSpacing(0.7f, 0.7f);
        this.method_1340(x, y, z);
        this.target = ep;
        this.sinage = new float[3];
        this.fireImmune = true;
        for (int i = 0; i < 3; ++i)
        {
            this.sinage[i] = this.random.nextFloat() * 6.0f;
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        --this.life;
        if (this.firstRun && this.target == null)
        {
            this.target = (LivingEntity) this.findPlayerToAttack();
            this.firstRun = false;
        }
        if (this.target == null || this.target.dead || this.target.health <= 0)
        {
            this.dead = true;
        }
        else if (this.life <= 0)
        {
            final LightningEntity thunder = new LightningEntity(this.world, this.x, this.y, this.z);
            this.world.method_210(thunder);
            this.dead = true;
        }
        else
        {
            this.updateAnims();
            this.faceIt();
            this.moveIt(this.target, 0.02);
        }
    }

    public void moveIt(final Entity e1, final double sped)
    {
        final double angle1 = this.yaw / 57.295773f;
        this.velocityX -= Math.sin(angle1) * sped;
        this.velocityZ += Math.cos(angle1) * sped;
        final double a = e1.y - 0.75;
        if (a < this.boundingBox.minY - 0.5)
        {
            this.velocityY -= sped / 2.0;
        }
        else if (a > this.boundingBox.minY + 0.5)
        {
            this.velocityY += sped / 2.0;
        }
        else
        {
            this.velocityY += (a - this.boundingBox.minY) * (sped / 2.0);
        }
        if (this.field_1623)
        {
            this.field_1623 = false;
            this.velocityY = 0.10000000149011612;
        }
    }

    public void faceIt()
    {
        this.method_924(this.target, 10.0f, 10.0f);
    }

    public void updateAnims()
    {
        for (int i = 0; i < 3; ++i)
        {
            final float[] sinage = this.sinage;
            final int n = i;
            sinage[n] += 0.3f + i * 0.13f;
            if (this.sinage[i] > 6.283186f)
            {
                final float[] sinage2 = this.sinage;
                final int n2 = i;
                sinage2[n2] -= 6.283186f;
            }
        }
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("LifeLeft", (short) this.life);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.life = tag.getShort("LifeLeft");
    }

    public void checkOverLimit()
    {
        final double a = this.target.x - this.x;
        final double b = this.target.y - this.y;
        final double c = this.target.z - this.z;
        final double d = Math.sqrt(a * a + b * b + c * c);
        if (d > 0.125)
        {
            final double e = 0.125 / d;
            this.velocityX *= e;
            this.velocityY *= e;
            this.velocityZ *= e;
        }
    }

    public Entity findPlayerToAttack()
    {
        final PlayerEntity entityplayer = this.world.method_186(this, 16.0);
        if (entityplayer != null && this.method_928(entityplayer))
        {
            return entityplayer;
        }
        return null;
    }

    @Override
    public void method_1353(final Entity entity)
    {
        super.method_1353(entity);
        if (entity != null && this.target != null && entity == this.target)
        {
            final boolean flag = entity.damage(this, 1);
            if (flag)
            {
                this.moveIt(entity, -0.1);
            }
        }
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        if (target != null)
        {
            this.moveIt(target, -0.15 - amount / 8.0);
            return true;
        }
        return false;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Homeshot");
    }
}
