package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class EntitySwet extends EntityAetherAnimal
{
    public int ticker;
    public int flutter;
    public int hops;
    public int textureNum;
    public boolean textureSet;
    public boolean gotrider;
    public boolean kickoff;
    public boolean friendly;

    public EntitySwet(final World level, int textureNum)
    {
        super(level);
        this.textureNum = textureNum;
        textureSet = true;
        setStartVariables();
    }

    public EntitySwet(final World level)
    {
        super(level);
        setStartVariables();
    }

    private void setStartVariables()
    {
        health = 25;
        if (!textureSet)
        {
            if (random.nextInt(2) == 0)
            {
                textureNum = 2;
                textureSet = true;
            }
            else
            {
                textureNum = 1;
                textureSet = true;
            }
        }
        if (textureNum == 1)
        {
            texture = "aether:textures/entity/swets.png";
            movementSpeed = 1.5f;
        }
        else
        {
            texture = "aether:textures/entity/goldswets.png";
            movementSpeed = 3.0f;
        }
        setBoundingBoxSpacing(0.8f, 0.8f);
        method_1340(x, y, z);
        hops = 0;
        gotrider = false;
        flutter = 0;
        ticker = 0;
    }

    @Override
    public void method_1384()
    {
        super.method_1384();
        if (field_1594 != null && kickoff)
        {
            field_1594.method_1376(this);
            kickoff = false;
        }
    }

    @Override
    public void method_1382()
    {
        field_1594.method_1340(x, boundingBox.minY - 0.30000001192092896 + field_1594.eyeHeight, z);
    }

    @Override
    public void tick()
    {
        if (target != null)
        {
            for (int i = 0; i < 3; ++i)
            {
                final float f = 0.01745278f;
                final double d = (float) x + (random.nextFloat() - random.nextFloat()) * 0.3f;
                final double d2 = (float) y + spacingY;
                final double d3 = (float) z + (random.nextFloat() - random.nextFloat()) * 0.3f;
                world.addParticle("splash", d, d2 - 0.25, d3, 0.0, 0.0, 0.0);
            }
        }
        super.tick();
        if (gotrider)
        {
            if (field_1594 != null)
            {
                return;
            }
            final List list = world.getEntities(this, boundingBox.expand(0.5, 0.75, 0.5));
            final int j = 0;
            if (j < list.size())
            {
                final Entity entity = (Entity) list.get(j);
                capturePrey(entity);
            }
            gotrider = false;
        }
        if (isSubmergedInWater())
        {
            dissolve();
        }
    }

    @Override
    protected boolean method_940()
    {
        return true;
    }

    public void method_1389(final float height)
    {
        if (friendly)
        {
            return;
        }
        super.method_1389(height);
        if (hops >= 3 && health > 0)
        {
            dissolve();
        }
    }

    @Override
    public void method_925(final Entity entity, final int i, final double d, final double d1)
    {
        if (field_1594 != null && entity == field_1594)
        {
            return;
        }
        super.method_925(entity, i, d, d1);
    }

    public void dissolve()
    {
        for (int i = 0; i < 50; ++i)
        {
            final float f = random.nextFloat() * 3.141593f * 2.0f;
            final float f2 = random.nextFloat() * 0.5f + 0.25f;
            final float f3 = MathHelper.sin(f) * f2;
            final float f4 = MathHelper.cos(f) * f2;
            world.addParticle("splash", x + f3, boundingBox.minY + 1.25, z + f4, f3 * 1.5 + velocityX, 4.0, f4 * 1.5 + velocityZ);
        }
        if (field_1594 != null)
        {
            final Entity passenger = this.field_1594;
            passenger.y += this.field_1594.eyeHeight - 0.3f;
            this.field_1594.method_1376(this);
        }
        this.markDead();
    }

    public void capturePrey(final Entity entity)
    {
        this.splorch();
        final double x = entity.x;
        this.x = x;
        this.prevX = x;
        final double n = entity.y + 0.009999999776482582;
        this.y = n;
        this.prevY = n;
        final double z = entity.z;
        this.z = z;
        this.prevZ = z;
        final float yaw = entity.yaw;
        this.yaw = yaw;
        this.prevYaw = yaw;
        final float pitch = entity.pitch;
        this.pitch = pitch;
        this.prevPitch = pitch;
        this.velocityX = entity.velocityX;
        this.velocityY = entity.velocityY;
        this.velocityZ = entity.velocityZ;
        this.setBoundingBoxSpacing(entity.spacingXZ, entity.spacingY);
        this.method_1340(this.x, this.y, this.z);
        entity.method_1376(this);
        this.yaw = this.random.nextFloat() * 360.0f;
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        if (this.hops == 3 && target == null && this.health > 1)
        {
            this.health = 1;
        }
        final boolean flag = super.damage(target, amount);
        if (flag && this.field_1594 != null && this.field_1594 instanceof LivingEntity)
        {
            if (target != null && this.field_1594 == target)
            {
                if (this.random.nextInt(3) == 0)
                {
                    this.kickoff = true;
                }
            }
            else
            {
                this.field_1594.damage(null, amount);
                if (this.health <= 0)
                {
                    this.kickoff = true;
                }
            }
        }
        if (flag && this.health <= 0)
        {
            this.dissolve();
        }
        else if (flag && target instanceof LivingEntity)
        {
            final LivingEntity entityliving = (LivingEntity) target;
            if (entityliving.health > 0 && (this.field_1594 == null || entityliving != this.field_1594))
            {
                this.method_924(this.target = target, 180.0f, 180.0f);
                this.kickoff = true;
            }
        }
        if (this.friendly && this.target instanceof PlayerEntity)
        {
            this.target = null;
        }
        return flag;
    }

    public void d_2()
    {
        if (this.field_1594 != null && this.field_1594 instanceof LivingEntity)
        {
            this.field_1029 = 0.0f;
            this.field_1060 = 0.0f;
            this.jumping = false;
            ((EntityBaseAccessor) this.field_1594).setFallDistance(0.0f);
            final float yaw = this.field_1594.yaw;
            this.yaw = yaw;
            this.prevYaw = yaw;
            final float n = 0.0f;
            this.pitch = n;
            this.prevPitch = n;
            final LivingEntity entityliving = (LivingEntity) this.field_1594;
            final float f = 3.141593f;
            final float f2 = f / 180.0f;
            final float f3 = entityliving.yaw * f2;
            if (this.field_1623)
            {
                if (((LivingAccessor) entityliving).getJumping())
                {
                    if (this.hops == 0)
                    {
                        this.field_1623 = false;
                        this.velocityY = 0.8500000238418579;
                        this.hops = 1;
                        this.flutter = 5;
                    }
                    else if (this.hops == 1)
                    {
                        this.field_1623 = false;
                        this.velocityY = 1.0499999523162842;
                        this.hops = 2;
                        this.flutter = 5;
                    }
                    else if (this.hops == 2)
                    {
                        this.field_1623 = false;
                        this.velocityY = 1.25;
                        this.flutter = 5;
                    }
                }
                else if (((LivingAccessor) entityliving).get1029() > 0.125f || ((LivingAccessor) entityliving).get1029() < -0.125f || ((LivingAccessor) entityliving).get1060() > 0.125f || ((LivingAccessor) entityliving).get1060() < -0.125f)
                {
                    this.field_1623 = false;
                    this.velocityY = 0.3499999940395355;
                    this.hops = 0;
                    this.flutter = 0;
                }
                else if (this.hops > 0)
                {
                    this.hops = 0;
                }
                ((LivingAccessor) entityliving).set1029(0.0f);
                ((LivingAccessor) entityliving).set1060(0.0f);
            }
            else
            {
                if (((LivingAccessor) entityliving).get1029() > 0.1f)
                {
                    if (this.textureNum == 1)
                    {
                        this.velocityX += ((LivingAccessor) entityliving).get1029() * -Math.sin((double) f3) * 0.125;
                        this.velocityZ += ((LivingAccessor) entityliving).get1029() * Math.cos((double) f3) * 0.125;
                    }
                    else
                    {
                        this.velocityX += ((LivingAccessor) entityliving).get1029() * -Math.sin((double) f3) * 0.325;
                        this.velocityZ += ((LivingAccessor) entityliving).get1029() * Math.cos((double) f3) * 0.125;
                    }
                }
                else if (((LivingAccessor) entityliving).get1029() < -0.1f)
                {
                    if (this.textureNum == 1)
                    {
                        this.velocityX += ((LivingAccessor) entityliving).get1029() * -Math.sin((double) f3) * 0.125;
                        this.velocityZ += ((LivingAccessor) entityliving).get1029() * Math.cos((double) f3) * 0.125;
                    }
                    else
                    {
                        this.velocityX += ((LivingAccessor) entityliving).get1029() * -Math.sin((double) f3) * 0.325;
                        this.velocityZ += ((LivingAccessor) entityliving).get1029() * Math.cos((double) f3) * 0.125;
                    }
                }
                if (((LivingAccessor) entityliving).get1060() > 0.1f)
                {
                    if (this.textureNum == 1)
                    {
                        this.velocityX += ((LivingAccessor) entityliving).get1060() * Math.cos((double) f3) * 0.125;
                        this.velocityZ += ((LivingAccessor) entityliving).get1060() * Math.sin((double) f3) * 0.125;
                    }
                    else
                    {
                        this.velocityX += ((LivingAccessor) entityliving).get1060() * Math.cos((double) f3) * 0.325;
                        this.velocityZ += ((LivingAccessor) entityliving).get1060() * Math.sin((double) f3) * 0.125;
                    }
                }
                else if (((LivingAccessor) entityliving).get1060() < -0.1f)
                {
                    if (this.textureNum == 1)
                    {
                        this.velocityX += ((LivingAccessor) entityliving).get1060() * Math.cos((double) f3) * 0.125;
                        this.velocityZ += ((LivingAccessor) entityliving).get1060() * Math.sin((double) f3) * 0.125;
                    }
                    else
                    {
                        this.velocityX += ((LivingAccessor) entityliving).get1060() * Math.cos((double) f3) * 0.325;
                        this.velocityZ += ((LivingAccessor) entityliving).get1060() * Math.sin((double) f3) * 0.125;
                    }
                }
                if (this.velocityY < 0.05000000074505806 && this.flutter > 0 && ((LivingAccessor) entityliving).getJumping())
                {
                    this.velocityY += 0.07000000029802322;
                    --this.flutter;
                }
            }
            final double d = Math.abs(Math.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ));
            if (d > 0.2750000059604645)
            {
                final double d2 = 0.275 / d;
                this.velocityX *= d2;
                this.velocityZ *= d2;
            }
        }
    }

    public void method_910()
    {
        ++this.field_1059;
        this.method_920();
        if (this.friendly && this.field_1594 != null)
        {
            this.d_2();
            return;
        }
        if (!this.field_1623 && this.jumping)
        {
            this.jumping = false;
        }
        else if (this.field_1623)
        {
            if (this.field_1029 > 0.05f)
            {
                this.field_1029 *= 0.75f;
            }
            else
            {
                this.field_1029 = 0.0f;
            }
        }
        if (this.target != null && this.field_1594 == null && this.health > 0)
        {
            this.method_924(this.target, 10.0f, 10.0f);
        }
        if (this.target != null && this.target.dead)
        {
            this.target = null;
        }
        if (!this.field_1623 && this.velocityY < 0.05000000074505806 && this.flutter > 0)
        {
            this.velocityY += 0.07000000029802322;
            --this.flutter;
        }
        if (this.ticker < 4)
        {
            ++this.ticker;
        }
        else
        {
            if (this.field_1623 && this.field_1594 == null && this.hops != 0 && this.hops != 3)
            {
                this.hops = 0;
            }
            if (this.target == null && this.field_1594 == null)
            {
                final Entity entity = this.getPrey();
                if (entity != null)
                {
                    this.target = entity;
                }
            }
            else if (this.target != null && this.field_1594 == null)
            {
                if (this.method_1351(this.target) <= 9.0f)
                {
                    if (this.field_1623 && this.method_928(this.target))
                    {
                        this.splotch();
                        this.flutter = 10;
                        this.jumping = true;
                        this.field_1029 = 1.0f;
                        this.yaw += 5.0f * (this.random.nextFloat() - this.random.nextFloat());
                    }
                }
                else
                {
                    this.target = null;
                    this.jumping = false;
                    this.field_1029 = 0.0f;
                }
            }
            else if (this.field_1594 != null && this.field_1623)
            {
                if (this.hops == 0)
                {
                    this.splotch();
                    this.field_1623 = false;
                    this.velocityY = 0.3499999940395355;
                    this.field_1029 = 0.8f;
                    this.hops = 1;
                    this.flutter = 5;
                    this.yaw += 20.0f * (this.random.nextFloat() - this.random.nextFloat());
                }
                else if (this.hops == 1)
                {
                    this.splotch();
                    this.field_1623 = false;
                    this.velocityY = 0.44999998807907104;
                    this.field_1029 = 0.9f;
                    this.hops = 2;
                    this.flutter = 5;
                    this.yaw += 20.0f * (this.random.nextFloat() - this.random.nextFloat());
                }
                else if (this.hops == 2)
                {
                    this.splotch();
                    this.field_1623 = false;
                    this.velocityY = 1.25;
                    this.field_1029 = 1.25f;
                    this.hops = 3;
                    this.flutter = 5;
                    this.yaw += 20.0f * (this.random.nextFloat() - this.random.nextFloat());
                }
            }
            this.ticker = 0;
        }
        if (this.field_1623 && this.hops >= 3)
        {
            this.dissolve();
        }
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("Hops", (short) this.hops);
        tag.putShort("Flutter", (short) this.flutter);
        if (this.field_1594 != null)
        {
            this.gotrider = true;
        }
        tag.putBoolean("GotRider", this.gotrider);
        tag.putBoolean("Friendly", this.friendly);
        tag.putBoolean("textureSet", this.textureSet);
        tag.putShort("textureNum", (short) this.textureNum);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.hops = tag.getShort("Hops");
        this.flutter = tag.getShort("Flutter");
        this.gotrider = tag.getBoolean("GotRider");
        this.friendly = tag.getBoolean("Friendly");
        this.textureSet = tag.getBoolean("textureSet");
        this.textureNum = tag.getShort("textureNum");
        if (this.textureNum == 1)
        {
            this.texture = "aether:textures/entity/swets.png";
            this.movementSpeed = 1.5f;
        }
        else
        {
            this.texture = "aether:textures/entity/goldswets.png";
            this.movementSpeed = 3.0f;
        }
    }

    public void splorch()
    {
        this.world.playSound((Entity) this, "mob.slimeattack", 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
    }

    public void splotch()
    {
        this.world.playSound((Entity) this, "mob.slime", 0.5f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
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
    public void method_1353(final Entity entity)
    {
        if (this.hops == 0 && this.field_1594 == null && this.target != null && entity != null && entity == this.target && (entity.field_1595 == null || !(entity.field_1595 instanceof EntitySwet)))
        {
            if (entity.field_1594 != null)
            {
                entity.field_1594.method_1376(entity);
            }
            this.capturePrey(entity);
        }
        super.method_1353(entity);
    }

    @Override
    public boolean method_1323(final PlayerEntity entityplayer)
    {
        if (!this.world.isRemote)
        {
            if (!this.friendly)
            {
                this.friendly = true;
                this.target = null;
                return true;
            }
            if ((this.friendly && this.field_1594 == null) || this.field_1594 == entityplayer)
            {
                this.capturePrey(entityplayer);
            }
        }
        return true;
    }

    protected Entity getPrey()
    {
        final List list = this.world.getEntities(this, this.boundingBox.expand(6.0, 6.0, 6.0));
        for (int i = 0; i < list.size(); ++i)
        {
            final Entity entity = (Entity) list.get(i);
            if (entity instanceof LivingEntity && !(entity instanceof EntitySwet))
            {
                if (this.friendly)
                {
                    if (entity instanceof PlayerEntity)
                    {
                        continue;
                    }
                }
                else if (entity instanceof MonsterEntity)
                {
                    continue;
                }
                return entity;
            }
        }
        return null;
    }

    @Override
    protected void method_933()
    {
        final ItemStack stack = new ItemStack((this.textureNum == 1) ? AetherBlocks.AERCLOUD.id : Block.GLOWSTONE.id, 3, (this.textureNum == 1) ? 1 : 0);

        PlayerEntity player = this.world.method_186(this, 10);
        if (player != null)
            if (player.getHand() != null)
                if (player.getHand().getItem() == AetherItems.SwordSkyroot)
                    stack.count *= 2;
        this.method_1327(stack, 0.0f);
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Swet");
    }
}
