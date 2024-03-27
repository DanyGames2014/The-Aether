package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityDungeonMob;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntitySentry extends EntityDungeonMob
{
    public float field_100021_a;
    public float field_100020_b;
    private int jcount;
    public int size;
    public int counter;
    public int lostyou;
    public boolean active;

    public EntitySentry(final World level)
    {
        super(level);
        this.texture = "aether:textures/entity/Sentry.png";
        this.size = 2;
        this.eyeHeight = 0.0f;
        this.movementSpeed = 1.0f;
        this.field_100021_a = 1.0f;
        this.field_100020_b = 1.0f;
        this.jcount = this.random.nextInt(20) + 10;
        this.func_100019_e(this.size);
    }

    public EntitySentry(final World world, final double x, final double y, final double z)
    {
        super(world);
        this.texture = "aether:textures/entity/Sentry.png";
        this.size = 2;
        this.eyeHeight = 0.0f;
        this.movementSpeed = 1.0f;
        this.field_100021_a = 1.0f;
        this.field_100020_b = 1.0f;
        this.jcount = this.random.nextInt(20) + 10;
        this.func_100019_e(this.size);
        this.yaw = this.random.nextInt(4) * 1.5707965f;
        this.method_1340(x, y, z);
    }

    public void func_100019_e(final int i)
    {
        this.health = 10;
        this.spacingXZ = 0.85f;
        this.spacingY = 0.85f;
        this.method_1340(this.x, this.y, this.z);
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putInt("Size", this.size - 1);
        tag.putInt("LostYou", this.lostyou);
        tag.putInt("Counter", this.counter);
        tag.putBoolean("Active", this.active);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.size = tag.getInt("Size") + 1;
        this.lostyou = tag.getInt("LostYou");
        this.counter = tag.getInt("Counter");
        this.active = tag.getBoolean("Active");
    }

    @Override
    public void tick()
    {
        final boolean flag = this.field_1623;
        super.tick();
        if (this.field_1623 && !flag)
        {
            this.world.playSound((Entity) this, "mob.slime", this.method_915(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f) / 0.8f);
        }
        else if (!this.field_1623 && flag && this.target != null)
        {
            this.velocityX *= 3.0;
            this.velocityZ *= 3.0;
        }
        if (this.target != null && this.target.dead)
        {
            this.target = null;
        }
    }

    public void markDead()
    {
        if (this.health <= 0 || this.dead)
        {
            super.markDead();
        }
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        final boolean flag = super.damage(target, amount);
        if (flag && target instanceof LivingEntity)
        {
            this.active = true;
            this.lostyou = 0;
            this.target = target;
            this.texture = "aether:textures/entity/SentryLit.png";
        }
        return flag;
    }

    public void shutdown()
    {
        this.counter = -64;
        this.active = false;
        this.target = null;
        this.texture = "aether:textures/entity/Sentry.png";
        this.method_635(null);
        this.field_1060 = 0.0f;
        this.field_1029 = 0.0f;
        this.jumping = false;
        this.velocityX = 0.0;
        this.velocityZ = 0.0;
    }

    public void method_1353(final Entity entity)
    {
        if (!this.dead && this.target != null && entity != null && this.target == entity)
        {
            this.world.method_187(this, this.x, this.y, this.z, 0.1f);
            entity.damage(null, 2);
            if (entity instanceof LivingEntity entityliving)
            {
                double d;
                double d2;
                for (d = entityliving.x - this.x, d2 = entityliving.z - this.z; d * d + d2 * d2 < 1.0E-4; d = (Math.random() - Math.random()) * 0.01, d2 = (Math.random() - Math.random()) * 0.01)
                {
                }
                entityliving.method_925(this, 5, -d, -d2);
                entityliving.velocityX *= 4.0;
                entityliving.velocityY *= 4.0;
                entityliving.velocityZ *= 4.0;
            }
            final float f = 0.01745329f;
            for (int i = 0; i < 40; ++i)
            {
                final double d3 = (float) this.x + this.random.nextFloat() * 0.25f;
                final double d4 = (float) this.y + 0.5f;
                final double d5 = (float) this.z + this.random.nextFloat() * 0.25f;
                final float f2 = this.random.nextFloat() * 360.0f;
                this.world.addParticle("explode", d3, d4, d5, -Math.sin(f * f2) * 0.75, 0.125, Math.cos(f * f2) * 0.75);
            }
            this.health = 0;
            this.markDead();
        }
    }

    @Override
    protected void method_910()
    {
        final PlayerEntity entityplayer = this.world.method_186(this, 8.0);
        if (!this.active && this.counter >= 8)
        {
            if (entityplayer != null && this.method_928(entityplayer))
            {
                this.method_924(entityplayer, 10.0f, 10.0f);
                this.target = entityplayer;
                this.active = true;
                this.lostyou = 0;
                this.texture = "aether:textures/entity/SentryLit.png";
            }
            this.counter = 0;
        }
        else if (this.active && this.counter >= 8)
        {
            if (this.target == null)
            {
                if (entityplayer != null && this.method_928(entityplayer))
                {
                    this.target = entityplayer;
                    this.active = true;
                    this.lostyou = 0;
                }
                else
                {
                    ++this.lostyou;
                    if (this.lostyou >= 4)
                    {
                        this.shutdown();
                    }
                }
            }
            else if (!this.method_928(this.target) || this.method_1351(this.target) >= 16.0f)
            {
                ++this.lostyou;
                if (this.lostyou >= 4)
                {
                    this.shutdown();
                }
            }
            else
            {
                this.lostyou = 0;
            }
            this.counter = 0;
        }
        else
        {
            ++this.counter;
        }
        if (!this.active)
        {
            return;
        }
        if (this.target != null)
        {
            this.method_924(this.target, 10.0f, 10.0f);
        }
        if (this.field_1623 && this.jcount-- <= 0)
        {
            this.jcount = this.random.nextInt(20) + 10;
            this.jumping = true;
            this.field_1060 = 0.5f - this.random.nextFloat();
            this.field_1029 = 1.0f;
            this.world.playSound(this, "mob.slime", this.method_915(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f) * 0.8f);
            if (this.target != null)
            {
                this.jcount /= 2;
                this.field_1029 = 1.0f;
            }
        }
        else
        {
            this.jumping = false;
            if (this.field_1623)
            {
                final float n = 0.0f;
                this.field_1029 = n;
                this.field_1060 = n;
            }
        }
    }

    @Override
    protected String method_912()
    {
        return "mob.slime";
    }

    @Override
    protected String method_913()
    {
        return "mob.slime";
    }

    @Override
    public boolean canSpawn()
    {
        return super.canSpawn();
    }

    @Override
    protected float method_915()
    {
        return 0.6f;
    }

    @Override
    protected int method_914()
    {
        if (this.random.nextInt(5) == 0)
        {
            return AetherBlocks.LIGHT_DUNGEON_STONE.id;
        }
        return AetherBlocks.DUNGEON_STONE.id;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Sentry");
    }
}
