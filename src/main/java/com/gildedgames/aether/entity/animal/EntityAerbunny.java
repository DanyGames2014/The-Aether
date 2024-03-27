package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Block;
import net.minecraft.class_61;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class EntityAerbunny extends EntityAetherAnimal
{
    public int age;
    public int mate;
    public boolean grab;
    public boolean fear;
    public boolean gotrider;
    public Entity runFrom;
    public float puffiness;

    public EntityAerbunny(final World level)
    {
        super(level);
        this.movementSpeed = 2.5f;
        this.texture = "aether:textures/entity/aerbunny.png";
        this.eyeHeight = -0.16f;
        this.setBoundingBoxSpacing(0.4f, 0.4f);
        this.health = 6;
        if (this.field_1592 < 5.0)
        {
            this.field_1592 = 5.0;
        }
        this.age = this.random.nextInt(64);
        this.mate = 0;
    }

    @Override
    public boolean method_1387()
    {
        return false; // stops them from dying while on your head, going through doors etc
    }

    @Override
    public void tick()
    {
        if (this.gotrider)
        {
            this.gotrider = false;
            if (this.field_1595 == null)
            {
                final PlayerEntity entityplayer = (PlayerEntity) this.findPlayerToRunFrom();
                if (entityplayer != null && this.method_1351(entityplayer) < 2.0f && entityplayer.field_1594 == null)
                {
                    this.method_1376(entityplayer);
                }
            }
        }
        if (this.age < 1023)
        {
            ++this.age;
        }
        else if (this.mate < 127)
        {
            ++this.mate;
        }
        else
        {
            int i = 0;
            final List list = this.world.getEntities(this, this.boundingBox.expand(16.0, 16.0, 16.0));
            for (int j = 0; j < list.size(); ++j)
            {
                final Entity entity = (Entity) list.get(j);
                if (entity instanceof EntityAerbunny)
                {
                    ++i;
                }
            }
            if (i > 12)
            {
                this.proceed();
                return;
            }
            final List list2 = this.world.getEntities(this, this.boundingBox.expand(1.0, 1.0, 1.0));
            boolean flag = false;
            for (int k = 0; k < list.size(); ++k)
            {
                final Entity entity2 = (Entity) list2.get(k);
                if (entity2 instanceof EntityAerbunny)
                {
                    if (entity2 != this)
                    {
                        final EntityAerbunny entitybunny = (EntityAerbunny) entity2;
                        if (entitybunny.field_1595 == null)
                        {
                            if (entitybunny.age >= 1023)
                            {
                                final EntityAerbunny entitybunny2 = new EntityAerbunny(this.world);
                                entitybunny2.method_1340(this.x, this.y, this.z);
                                this.world.method_210(entitybunny2);
                                this.world.playSound((Entity) this, "mob.chickenplop", 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
                                this.proceed();
                                entitybunny.proceed();
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!flag)
            {
                this.mate = this.random.nextInt(16);
            }
        }
        if (this.puffiness > 0.0f)
        {
            this.puffiness -= 0.1f;
        }
        else
        {
            this.puffiness = 0.0f;
        }
        super.tick();
    }

    @Override
    protected void method_1389(final float height)
    {
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putBoolean("Fear", this.fear);
        if (this.field_1594 != null)
        {
            this.gotrider = true;
        }
        tag.putBoolean("GotRider", this.gotrider);
        tag.putShort("RepAge", (short) this.age);
        tag.putShort("RepMate", (short) this.mate);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.fear = tag.getBoolean("Fear");
        this.gotrider = tag.getBoolean("GotRider");
        this.age = tag.getShort("RepAge");
        this.mate = tag.getShort("RepMate");
    }

    @Override
    protected void method_910()
    {
        if (this.field_1623)
        {
            if (this.field_1029 != 0.0f)
            {
                this.method_944();
            }
        }
        else if (this.field_1595 != null)
        {
            if (this.field_1595.dead)
            {
                this.method_1376(this.field_1595);
            }
            else if (!this.field_1595.field_1623 && !this.field_1595.isSubmergedInWater())
            {
                ((EntityBaseAccessor) this.field_1595).setFallDistance(0.0f);
                final Entity vehicle = this.field_1595;
                vehicle.velocityY += 0.05000000074505806;
                if (this.field_1595.velocityY < -0.22499999403953552 && this.field_1595 instanceof LivingEntity && ((LivingAccessor) this.field_1595).getJumping())
                {
                    this.field_1595.velocityY = 0.125;
                    this.cloudPoop();
                    this.puffiness = 1.15f;
                }
            }
        }
        else if (!this.grab)
        {
            if (this.field_1029 != 0.0f)
            {
                final int j = MathHelper.floor(this.x);
                final int i1 = MathHelper.floor(this.boundingBox.minY);
                final int k1 = MathHelper.floor(this.boundingBox.minY - 0.5);
                final int l1 = MathHelper.floor(this.z);
                if ((this.world.getBlockId(j, i1 - 1, l1) != 0 || this.world.getBlockId(j, k1 - 1, l1) != 0) && this.world.getBlockId(j, i1 + 2, l1) == 0 && this.world.getBlockId(j, i1 + 1, l1) == 0)
                {
                    if (this.velocityY < 0.0)
                    {
                        this.cloudPoop();
                        this.puffiness = 0.9f;
                    }
                    this.velocityY = 0.2;
                }
            }
            if (this.velocityY < -0.1)
            {
                this.velocityY = -0.1;
            }
        }
        if (!this.grab)
        {
            super.method_910();
            if (this.fear && this.random.nextInt(4) == 0)
            {
                if (this.runFrom != null)
                {
                    this.runLikeHell();
                    this.world.addParticle("splash", this.x, this.y, this.z, 0.0, 0.0, 0.0);
                    if (!this.method_633())
                    {
                        this.method_924(this.runFrom, 30.0f, 30.0f);
                    }
                    if (this.runFrom.dead || this.method_1351(this.runFrom) > 16.0f)
                    {
                        this.runFrom = null;
                    }
                }
                else
                {
                    this.runFrom = this.findPlayerToRunFrom();
                }
            }
        }
        else if (this.field_1623)
        {
            this.grab = false;
            this.world.playSound((Entity) this, "aether:aether.sound.mobs.aerbunny.aerbunnyland", 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
            final List list = this.world.getEntities(this, this.boundingBox.expand(12.0, 12.0, 12.0));
            for (int m = 0; m < list.size(); ++m)
            {
                final Entity entity = (Entity) list.get(m);
                if (entity instanceof MonsterEntity)
                {
                    final MonsterEntity entitymobs = (MonsterEntity) entity;
                    entitymobs.setTarget(this);
                }
            }
        }
        if (this.isSubmergedInWater())
        {
            this.method_944();
        }
    }

    public void cloudPoop()
    {
        final double a = this.random.nextFloat() - 0.5f;
        final double c = this.random.nextFloat() - 0.5f;
        final double d = this.x + a * 0.4000000059604645;
        final double e = this.boundingBox.minY;
        final double f = this.z + a * 0.4000000059604645;
        this.world.addParticle("explode", d, e, f, 0.0, -0.07500000298023224, 0.0);
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        final boolean flag = super.damage(target, amount);
        if (flag && target instanceof PlayerEntity)
        {
            this.fear = true;
        }
        return flag;
    }

    @Override
    public boolean method_932()
    {
        return this.field_1029 != 0.0f;
    }

    protected Entity findPlayerToRunFrom()
    {
        final PlayerEntity entityplayer = this.world.method_186(this, 12.0);
        if (entityplayer != null && this.method_928(entityplayer))
        {
            return entityplayer;
        }
        return null;
    }

    public void runLikeHell()
    {
        final double a = this.x - this.runFrom.x;
        final double b = this.z - this.runFrom.z;
        double crazy = Math.atan2(a, b);
        crazy += (this.random.nextFloat() - this.random.nextFloat()) * 0.75;
        final double c = this.x + Math.sin(crazy) * 8.0;
        final double d = this.z + Math.cos(crazy) * 8.0;
        final int x = MathHelper.floor(c);
        final int y = MathHelper.floor(this.boundingBox.minY);
        final int z = MathHelper.floor(d);
        for (int q = 0; q < 16; ++q)
        {
            final int i = x + this.random.nextInt(4) - this.random.nextInt(4);
            final int j = y + this.random.nextInt(4) - this.random.nextInt(4) - 1;
            final int k = z + this.random.nextInt(4) - this.random.nextInt(4);
            if (j > 4 && (this.world.getBlockId(i, j, k) == 0 || this.world.getBlockId(i, j, k) == Block.SNOW.id) && this.world.getBlockId(i, j - 1, k) != 0)
            {
                final class_61 dogs = this.world.method_189(this, i, j, k, 16.0f);
                this.method_635(dogs);
                break;
            }
        }
    }

    @Override
    public boolean method_1323(final PlayerEntity entityplayer)
    {
        this.yaw = entityplayer.yaw;
        if (this.field_1595 != null)
        {
            this.field_1012 = this.field_1595.yaw;
            this.yaw = this.field_1595.yaw;
        }
        this.method_1376(entityplayer);
        if (this.field_1595 == null)
        {
            this.grab = true;
        }
        else
        {
            this.world.playSound((Entity) this, "aether:aether.sound.mobs.aerbunny.aerbunnylift", 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
        }
        this.jumping = false;
        this.field_1029 = 0.0f;
        this.field_1060 = 0.0f;
        this.method_635(null);
        this.velocityX = entityplayer.velocityX * 5.0;
        this.velocityY = entityplayer.velocityY / 2.0 + 0.5;
        this.velocityZ = entityplayer.velocityZ * 5.0;
        return true;
    }

    @Override
    public double method_1385()
    {
        if (this.field_1595 instanceof PlayerEntity)
        {
            return this.eyeHeight - 1.15f;
        }
        return this.eyeHeight;
    }

    @Override
    protected String method_911()
    {
        return null;
    }

    @Override
    protected void method_933()
    {
        PlayerEntity player = this.world.method_186(this, 10);
        int count = 1;
        if (player != null)
            if (player.getHand() != null)
                if (player.getHand().getItem() == AetherItems.SwordSkyroot)
                    count *= 2;
        this.method_1339(Item.STRING.id, count);
    }

    public void proceed()
    {
        this.mate = 0;
        this.age = this.random.nextInt(64);
    }

    @Override
    protected boolean bypassesSteppingEffects()
    {
        return this.field_1623;
    }

    @Override
    protected String method_912()
    {
        return "aether:aether.sound.mobs.aerbunny.aerbunnyhurt";
    }

    @Override
    protected String method_913()
    {
        return "aether:aether.sound.mobs.aerbunny.aerbunnydeath";
    }

    @Override
    public boolean canSpawn()
    {
        return super.canSpawn();
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Aerbunny");
    }
}
