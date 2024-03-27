package com.gildedgames.aether.entity.projectile;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.boss.EntityFireMonster;
import com.gildedgames.aether.entity.mobs.EntityFireMinion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityFiroBall extends FlyingEntity implements MobSpawnDataProvider
{
    public float[] sinage;
    public double smotionX;
    public double smotionY;
    public double smotionZ;
    public int life;
    public int lifeSpan;
    public boolean frosty;
    public boolean smacked;
    public boolean fromCloud;
    private static final double topSpeed = 0.125;
    private static final float sponge = 57.295773f;

    public EntityFiroBall(final World level)
    {
        super(level);
        this.texture = "aether:textures/entity/firoball.png";
        this.lifeSpan = 300;
        this.life = this.lifeSpan;
        this.setBoundingBoxSpacing(0.9f, 0.9f);
        this.sinage = new float[3];
        this.fireImmune = true;
        for (int i = 0; i < 3; ++i)
        {
            this.sinage[i] = this.random.nextFloat() * 6.0f;
        }
    }

    public EntityFiroBall(final World world, final double x, final double y, final double z, final boolean flag)
    {
        super(world);
        this.texture = "aether:textures/entity/firoball.png";
        this.lifeSpan = 300;
        this.life = this.lifeSpan;
        this.setBoundingBoxSpacing(0.9f, 0.9f);
        this.method_1338(x, y, z, this.yaw, this.pitch);
        this.sinage = new float[3];
        this.fireImmune = true;
        for (int i = 0; i < 3; ++i)
        {
            this.sinage[i] = this.random.nextFloat() * 6.0f;
        }
        this.smotionX = (0.2 + this.random.nextFloat() * 0.15) * ((this.random.nextInt(2) == 0) ? 1.0 : -1.0);
        this.smotionY = (0.2 + this.random.nextFloat() * 0.15) * ((this.random.nextInt(2) == 0) ? 1.0 : -1.0);
        this.smotionZ = (0.2 + this.random.nextFloat() * 0.15) * ((this.random.nextInt(2) == 0) ? 1.0 : -1.0);
        if (flag)
        {
            this.frosty = true;
            this.texture = "aether:textures/entity/iceyball.png";
            this.smotionX /= 3.0;
            this.smotionY = 0.0;
            this.smotionZ /= 3.0;
        }
    }

    public EntityFiroBall(final World world, final double x, final double y, final double z, final boolean flag, final boolean flag2)
    {
        super(world);
        this.texture = "aether:textures/entity/firoball.png";
        this.lifeSpan = 300;
        this.life = this.lifeSpan;
        this.setBoundingBoxSpacing(0.9f, 0.9f);
        this.method_1338(x, y, z, this.yaw, this.pitch);
        this.sinage = new float[3];
        this.fireImmune = true;
        for (int i = 0; i < 3; ++i)
        {
            this.sinage[i] = this.random.nextFloat() * 6.0f;
        }
        this.smotionX = (0.2 + this.random.nextFloat() * 0.15) * ((this.random.nextInt(2) == 0) ? 1.0 : -1.0);
        this.smotionY = (0.2 + this.random.nextFloat() * 0.15) * ((this.random.nextInt(2) == 0) ? 1.0 : -1.0);
        this.smotionZ = (0.2 + this.random.nextFloat() * 0.15) * ((this.random.nextInt(2) == 0) ? 1.0 : -1.0);
        if (flag)
        {
            this.frosty = true;
            this.texture = "aether:textures/entity/iceyball.png";
            this.smotionX /= 3.0;
            this.smotionY = 0.0;
            this.smotionZ /= 3.0;
        }
        this.fromCloud = flag2;
    }

    @Override
    public void tick()
    {
        super.tick();
        --this.life;
        if (this.life <= 0)
        {
            this.fizzle();
            this.dead = true;
        }
    }

    public void fizzle()
    {
        if (this.frosty)
        {
            this.world.playSound((Entity) this, "random.glass", 2.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.2f);
        }
        else
        {
            this.world.playSound((Entity) this, "random.fizz", 2.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.2f);
        }
        for (int j = 0; j < 16; ++j)
        {
            final double a = this.x + (this.random.nextFloat() - this.random.nextFloat()) * 0.25;
            final double b = this.y + (this.random.nextFloat() - this.random.nextFloat()) * 0.25;
            final double c = this.z + (this.random.nextFloat() - this.random.nextFloat()) * 0.25;
            if (!this.frosty)
            {
                this.world.addParticle("largesmoke", a, b, c, 0.0, 0.0, 0.0);
            }
        }
    }

    public void splode()
    {
        if (this.frosty)
        {
            this.world.playSound((Entity) this, "random.glass", 2.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.2f);
        }
        else
        {
            this.world.playSound((Entity) this, "random.explode", 2.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.2f);
        }
        for (int j = 0; j < 40; ++j)
        {
            double a = (this.random.nextFloat() - 0.5f) * 0.5f;
            double b = (this.random.nextFloat() - 0.5f) * 0.5f;
            double c = (this.random.nextFloat() - 0.5f) * 0.5f;
            if (this.frosty)
            {
                a *= 0.5;
                b *= 0.5;
                c *= 0.5;
                this.world.addParticle("snowshovel", this.x, this.y, this.z, a, b + 0.125, c);
            }
            else
            {
                this.world.addParticle("flame", this.x, this.y, this.z, a, b, c);
            }
        }
    }

    public void updateAnims()
    {
        if (!this.frosty)
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
    }

    public void method_910()
    {
        this.velocityX = this.smotionX;
        this.velocityY = this.smotionY;
        this.velocityZ = this.smotionZ;
        if (this.field_1626)
        {
            if (this.frosty && this.smacked)
            {
                this.splode();
                this.fizzle();
                this.dead = true;
            }
            else
            {
                final int i = MathHelper.floor(this.x);
                final int j = MathHelper.floor(this.boundingBox.minY);
                final int k = MathHelper.floor(this.z);
                if (this.smotionX > 0.0 && this.world.getBlockId(i + 1, j, k) != 0)
                {
                    final double n = -this.smotionX;
                    this.smotionX = n;
                    this.velocityX = n;
                }
                else if (this.smotionX < 0.0 && this.world.getBlockId(i - 1, j, k) != 0)
                {
                    final double n2 = -this.smotionX;
                    this.smotionX = n2;
                    this.velocityX = n2;
                }
                if (this.smotionY > 0.0 && this.world.getBlockId(i, j + 1, k) != 0)
                {
                    final double n3 = -this.smotionY;
                    this.smotionY = n3;
                    this.velocityY = n3;
                }
                else if (this.smotionY < 0.0 && this.world.getBlockId(i, j - 1, k) != 0)
                {
                    final double n4 = -this.smotionY;
                    this.smotionY = n4;
                    this.velocityY = n4;
                }
                if (this.smotionZ > 0.0 && this.world.getBlockId(i, j, k + 1) != 0)
                {
                    final double n5 = -this.smotionZ;
                    this.smotionZ = n5;
                    this.velocityZ = n5;
                }
                else if (this.smotionZ < 0.0 && this.world.getBlockId(i, j, k - 1) != 0)
                {
                    final double n6 = -this.smotionZ;
                    this.smotionZ = n6;
                    this.velocityZ = n6;
                }
            }
        }
        this.updateAnims();
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("LifeLeft", (short) this.life);
        tag.put("SeriousKingVampire", (NbtElement) this.method_1329(this.smotionX, this.smotionY, this.smotionZ));
        tag.putBoolean("Frosty", this.frosty);
        tag.putBoolean("FromCloud", this.fromCloud);
        tag.putBoolean("Smacked", this.smacked);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.life = tag.getShort("LifeLeft");
        this.frosty = tag.getBoolean("Frosty");
        this.fromCloud = tag.getBoolean("FromCloud");
        if (this.frosty)
        {
            this.texture = "aether:textures/entity/iceyball.png";
        }
        this.smacked = tag.getBoolean("Smacked");
        final NbtList nbttaglist = tag.getList("SeriousKingVampire");
        this.smotionX = (float) ((NbtDouble) nbttaglist.get(0)).value;
        this.smotionY = (float) ((NbtDouble) nbttaglist.get(1)).value;
        this.smotionZ = (float) ((NbtDouble) nbttaglist.get(2)).value;
    }

    @Override
    public void method_1353(final Entity entity)
    {
        super.method_1353(entity);
        boolean flag = false;
        if (entity != null && entity instanceof LivingEntity && !(entity instanceof EntityFiroBall))
        {
            if (this.frosty && (!(entity instanceof EntityFireMonster) || (this.smacked && !this.fromCloud)) && !(entity instanceof EntityFireMinion))
            {
                flag = entity.damage(this, 5);
            }
            else if (!this.frosty && !(entity instanceof EntityFireMonster) && !(entity instanceof EntityFireMinion))
            {
                flag = entity.damage(this, 5);
                if (flag)
                {
                    entity.fire = 100;
                }
            }
        }
        if (flag)
        {
            this.splode();
            this.fizzle();
            this.dead = true;
        }
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        if (target != null)
        {
            final Vec3d vec3d = target.method_1320();
            if (vec3d != null)
            {
                this.smotionX = vec3d.x;
                this.smotionY = vec3d.y;
                this.smotionZ = vec3d.z;
            }
            return this.smacked = true;
        }
        return false;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Fireball");
    }
}
