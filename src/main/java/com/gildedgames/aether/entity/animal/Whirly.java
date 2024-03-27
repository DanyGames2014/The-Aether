package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LevelAccessor;
import com.gildedgames.aether.mixin.data.DimensionFileAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.class_75;
import net.minecraft.class_81;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ExplosionParticle;
import net.minecraft.client.particle.FireSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionData;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.SideUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Whirly extends EntityAetherAnimal
{
    public int entcount;
    public int Life;
    public List fluffies;
    public class_75 Enty;
    public static final float pie = 3.141593f;
    public static final float pia = 0.01745329f;
    public float Angle;
    public float Speed;
    public float Curve;
    public boolean evil;

    public Whirly(final World level)
    {
        super(level);
        System.out.println("whirly");
        this.entcount = 0;
        this.setBoundingBoxSpacing(0.6f, 0.8f);
        this.method_1340(this.x, this.y, this.z);
        this.movementSpeed = 0.6f;
        this.Angle = this.random.nextFloat() * 360.0f;
        this.Speed = this.random.nextFloat() * 0.025f + 0.025f;
        this.Curve = (this.random.nextFloat() - this.random.nextFloat()) * 0.1f;
        this.Life = this.random.nextInt(512) + 512;
        if (this.random.nextInt(10) == 0 && !this.shouldStopEvil())
        {
            this.evil = true;
            this.Life /= 2;
        }
        this.fluffies = (List) new ArrayList();
        if (FabricLoader.getInstance().getGameInstance() instanceof Minecraft mc)
        {
            this.Enty = mc.field_2808;
        }
    }

    public boolean bypassesSteppingEffects()
    {
        return false;
    }

    public boolean shouldStopEvil()
    {
        if (!(((LevelAccessor) this.world).getDimData() instanceof class_81))
        {
            return false;
        }
        DimensionData dd = (((LevelAccessor) this.world).getDimData()); //i hate casts
        final File file = new File(((DimensionFileAccessor) dd).getSaveFolder(), "stopevil.txt");
        return file.exists();
    }

    public void method_910()
    {
        boolean flag = false;
        if (this.evil)
        {
            final PlayerEntity entityplayer = (PlayerEntity) this.getPlayer();
            if (entityplayer != null && entityplayer.field_1623)
            {
                this.target = entityplayer;
                flag = true;
            }
        }
        if (this.target == null)
        {
            this.velocityX = Math.cos((double) (0.01745329f * this.Angle)) * this.Speed;
            this.velocityZ = -Math.sin((double) (0.01745329f * this.Angle)) * this.Speed;
            this.Angle += this.Curve;
        }
        else
        {
            super.method_910();
        }
        final List list = this.world.getEntities(this, this.boundingBox.expand(2.5, 2.5, 2.5));
        if (this.Life-- <= 0 || this.isSubmergedInWater())
        {
            this.markDead();
        }
        if (this.getPlayer() != null)
        {
            ++this.entcount;
        }
        if (this.entcount >= 128)
        {
            if (this.evil && this.target != null)
            {
                final CreeperEntity entitycreeper = new CreeperEntity(this.world);
                entitycreeper.method_1341(this.x, this.y - 0.75, this.z, this.random.nextFloat() * 360.0f, 0.0f);
                entitycreeper.velocityX = (this.random.nextFloat() - this.random.nextFloat()) * 0.125;
                entitycreeper.velocityZ = (this.random.nextFloat() - this.random.nextFloat()) * 0.125;
                this.world.method_210(entitycreeper);
                this.entcount = 0;
            }
            else
            {
                final int i = this.loot();
                if (i != 0)
                {
                    this.method_1339(i, 1);
                    this.entcount = 0;
                }
            }
        }
        if (!flag && this.target == null)
        {
            for (int j = 0; j < 2; ++j)
            {
                final double d1 = (float) this.x + this.random.nextFloat() * 0.25f;
                final double d2 = (float) this.y + this.spacingY + 0.125f;
                final double d3 = (float) this.z + this.random.nextFloat() * 0.25f;
                final float f = this.random.nextFloat() * 360.0f;
                final ExplosionParticle entityexplodefx = new ExplosionParticle(this.world, -Math.sin((double) (0.01745329f * f)) * 0.75, d2 - 0.25, Math.cos((double) (0.01745329f * f)) * 0.75, d1, 0.125, d3);
                this.Enty.method_325(entityexplodefx);
                this.fluffies.add(entityexplodefx);
                entityexplodefx.field_1592 = 10.0;
                entityexplodefx.field_1642 = true;
                ((EntityBaseAccessor) entityexplodefx).setWidth(0.25f);
                ((EntityBaseAccessor) entityexplodefx).setHeight(0.25f);
                entityexplodefx.method_1340(this.x, this.y, this.z);
                entityexplodefx.y = d2;
            }
        }
        else
        {
            for (int k = 0; k < 3; ++k)
            {
                final double d4 = (float) this.x + this.random.nextFloat() * 0.25f;
                final double d5 = (float) this.y + this.spacingY + 0.125f;
                final double d6 = (float) this.z + this.random.nextFloat() * 0.25f;
                final float f2 = this.random.nextFloat() * 360.0f;
                final FireSmokeParticle entitysmokefx = new FireSmokeParticle(this.world, -Math.sin((double) (0.01745329f * f2)) * 0.75, d5 - 0.25, Math.cos((double) (0.01745329f * f2)) * 0.75, d4, 0.125, d6, 3.5f);
                this.Enty.method_325(entitysmokefx);
                this.fluffies.add(entitysmokefx);
                entitysmokefx.field_1592 = 10.0;
                entitysmokefx.field_1642 = true;
                ((EntityBaseAccessor) entitysmokefx).setWidth(0.25f);
                ((EntityBaseAccessor) entitysmokefx).setHeight(0.25f);
                entitysmokefx.method_1340(this.x, this.y, this.z);
                entitysmokefx.y = d5;
            }
        }
        final double d7 = (float) this.x;
        final double d8 = (float) this.y;
        final double d9 = (float) this.z;
        for (int l = 0; l < list.size(); ++l)
        {
            final Entity entity = (Entity) list.get(l);
            final double d10 = (float) entity.x;
            final double d11 = (float) entity.y - entity.eyeHeight * 0.6f;
            final double d12 = (float) entity.z;
            double d13 = this.method_1351(entity);
            final double d14 = d11 - d8;
            if (d13 <= 1.5 + d14)
            {
                entity.velocityY = 0.15000000596046448;
                ((EntityBaseAccessor) entity).setFallDistance(0.0f);
                if (d14 > 1.5)
                {
                    entity.velocityY = -0.44999998807907104 + d14 * 0.3499999940395355;
                    d13 += d14 * 1.5;
                    if (entity == this.target)
                    {
                        this.target = null;
                        this.method_635(null);
                    }
                }
                else
                {
                    entity.velocityY = 0.125;
                }
                double d15 = Math.atan2(d7 - d10, d9 - d12) / 0.01745329424738884;
                d15 += 160.0;
                entity.velocityX = -Math.cos(0.01745329424738884 * d15) * (d13 + 0.25) * 0.10000000149011612;
                entity.velocityZ = Math.sin(0.01745329424738884 * d15) * (d13 + 0.25) * 0.10000000149011612;
                if (entity instanceof Whirly)
                {
                    entity.markDead();
                    if (!this.shouldStopEvil() && !this.evil)
                    {
                        this.evil = true;
                        this.Life /= 2;
                    }
                }
            }
            else
            {
                final double d16 = Math.atan2(d7 - d10, d9 - d12) / 0.01745329424738884;
                final Entity entityBase = entity;
                entityBase.velocityX += Math.sin(0.01745329424738884 * d16) * 0.009999999776482582;
                final Entity entityBase2 = entity;
                entityBase2.velocityZ += Math.cos(0.01745329424738884 * d16) * 0.009999999776482582;
            }
            final int j2 = MathHelper.floor(this.x);
            final int k2 = MathHelper.floor(this.y);
            final int l2 = MathHelper.floor(this.z);
            if (this.world.getBlockId(j2, k2 + 1, l2) != 0)
            {
                this.Life -= 50;
            }
            final int i2 = j2 - 1 + this.random.nextInt(3);
            final int j3 = k2 + this.random.nextInt(5);
            final int k3 = l2 - 1 + this.random.nextInt(3);
            if (this.world.getBlockId(i2, j3, k3) == Block.LEAVES.id)
            {
                this.world.setBlock(i2, j3, k3, 0);
            }
        }
        if (!world.isRemote)
            return;
        if (this.fluffies.size() > 0)
        {
            for (int i3 = 0; i3 < this.fluffies.size(); ++i3)
            {
                final Particle entityfx = (Particle) this.fluffies.get(i3);
                if (entityfx.dead)
                {
                    this.fluffies.remove(entityfx);
                }
                else
                {
                    final double d17 = (float) entityfx.x;
                    final double d18 = (float) entityfx.boundingBox.minY;
                    final double d19 = (float) entityfx.z;
                    final double d20 = this.method_1351(entityfx);
                    final double d21 = d18 - d8;
                    entityfx.velocityY = 0.11500000208616257;
                    double d22 = Math.atan2(d7 - d17, d9 - d19) / 0.01745329424738884;
                    d22 += 160.0;
                    entityfx.velocityX = -Math.cos(0.01745329424738884 * d22) * (d20 * 2.5 - d21) * 0.10000000149011612;
                    entityfx.velocityZ = Math.sin(0.01745329424738884 * d22) * (d20 * 2.5 - d21) * 0.10000000149011612;
                }
            }
        }
    }

    public int loot()
    {
        final int i = this.random.nextInt(100) + 1;
        if (i == 100)
        {
            return Item.DIAMOND.id;
        }
        if (i >= 96)
        {
            return Item.IRON_INGOT.id;
        }
        if (i >= 91)
        {
            return Item.GOLD_INGOT.id;
        }
        if (i >= 82)
        {
            return Item.COAL.id;
        }
        if (i >= 75)
        {
            return Block.GRAVEL.id;
        }
        if (i >= 64)
        {
            return Block.CLAY.id;
        }
        if (i >= 52)
        {
            return Item.STICK.id;
        }
        if (i >= 38)
        {
            return Item.FLINT.id;
        }
        if (i > 20)
        {
            return Block.LOG.id;
        }
        return Block.SAND.id;
    }

    @Override
    public void markDead()
    {
        SideUtil.run(this::clientRemove, this::serverRemove);
        super.markDead();
    }

    public void serverRemove()
    {
        if (!this.fluffies.isEmpty())
        {
            for (int i = 0; i < this.fluffies.size(); ++i)
            {
                this.fluffies.remove(i);
            }
        }
    }

    public void clientRemove()
    {
        if (!this.fluffies.isEmpty())
        {
            for (int i = 0; i < this.fluffies.size(); ++i)
            {
                final Particle particleBase;
                final Particle entityfx = particleBase = (Particle) this.fluffies.get(i);
                particleBase.velocityX *= 0.5;
                final Particle particleBase2 = entityfx;
                particleBase2.velocityY *= 0.75;
                final Particle particleBase3 = entityfx;
                particleBase3.velocityZ *= 0.5;
                this.fluffies.remove(entityfx);
            }
        }
    }

    @Override
    public boolean canSpawn()
    {
        if (this.y < 64.0)
        {
            this.y += 64.0;
        }
        if (!this.world.canSpawnEntity(this.boundingBox) || this.world.method_190(this, this.boundingBox).size() != 0 || this.world.method_218(this.boundingBox))
        {
            return false;
        }
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        boolean flag = true;
        for (int l = 1; l < 20; ++l)
        {
            if (l + j >= 125)
            {
                break;
            }
            if (this.world.method_255(i, j + l, k) <= 12 || this.world.getBlockId(i, j + l, k) != 0)
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public Entity getPlayer()
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
        tag.putFloat("Angle", this.Angle);
        tag.putFloat("Speed", this.Speed);
        tag.putFloat("Curve", this.Curve);
        tag.putShort("Life", (short) this.Life);
        tag.putShort("Counter", (short) this.entcount);
        tag.putBoolean("Evil", this.evil);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.Angle = tag.getFloat("Angle");
        this.Speed = tag.getFloat("Speed");
        this.Curve = tag.getFloat("Curve");
        this.Life = tag.getShort("Life");
        this.entcount = tag.getShort("Counter");
        this.evil = tag.getBoolean("Evil");
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        return false;
    }

    @Override
    public void method_1353(final Entity entity)
    {
    }

    @Override
    public int method_916()
    {
        return 1;
    }

    @Override
    public boolean method_932()
    {
        return this.field_1624;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Whirly");
    }
}
