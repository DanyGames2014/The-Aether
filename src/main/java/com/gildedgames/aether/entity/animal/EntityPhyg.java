package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityPhyg extends EntityAetherAnimal
{
    public boolean getSaddled;
    public float wingFold;
    public float wingAngle;
    private float aimingForFold;
    public int jumps;
    public int jrem;
    private boolean jpress;
    private int ticks;

    public EntityPhyg(final World level)
    {
        super(level);
        this.getSaddled = false;
        this.texture = "aether:textures/entity/FlyingPigBase.png";
        this.setBoundingBoxSpacing(0.9f, 1.3f);
        this.jrem = 0;
        this.jumps = 1;
        this.ticks = 0;
        this.field_1641 = 1.0f;
        this.field_1622 = true;
    }

    @Override
    protected boolean method_940()
    {
        return !this.getSaddled;
    }

    @Override
    protected boolean bypassesSteppingEffects()
    {
        return this.field_1623;
    }

    @Override
    protected void initDataTracker()
    {
        this.dataTracker.startTracking(16, 0);
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("Jumps", (short) this.jumps);
        tag.putShort("Remaining", (short) this.jrem);
        tag.putBoolean("getSaddled", this.getSaddled);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.jumps = tag.getShort("Jumps");
        this.jrem = tag.getShort("Remaining");
        this.getSaddled = tag.getBoolean("getSaddled");
        if (this.getSaddled)
        {
            this.texture = "aether:textures/entity/FlyingPigSaddle.png";
        }
    }

    @Override
    protected void method_944()
    {
        this.velocityY = 0.6;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.field_1623)
        {
            this.wingAngle *= 0.8f;
            this.aimingForFold = 0.1f;
            this.jpress = false;
            this.jrem = this.jumps;
        }
        else
        {
            this.aimingForFold = 1.0f;
        }
        ++this.ticks;
        this.wingAngle = this.wingFold * (float) Math.sin((double) (this.ticks / 31.830988f));
        this.wingFold += (this.aimingForFold - this.wingFold) / 5.0f;
        this.field_1636 = 0.0f;
        if (this.velocityY < -0.2)
        {
            this.velocityY = -0.2;
        }
    }

    public void method_910()
    {
        if (this.world.isRemote)
        {
            return;
        }
        if (this.field_1594 != null && this.field_1594 instanceof LivingEntity)
        {
            this.field_1029 = 0.0f;
            this.field_1060 = 0.0f;
            this.jumping = false;
            ((EntityBaseAccessor) this.field_1594).setFallDistance(0.0f);
            final float yaw = this.field_1594.yaw;
            this.yaw = yaw;
            this.prevYaw = yaw;
            final float pitch = this.field_1594.pitch;
            this.pitch = pitch;
            this.prevPitch = pitch;
            final LivingEntity entityliving = (LivingEntity) this.field_1594;
            final float f = 3.141593f;
            final float f2 = f / 180.0f;
            if (((LivingAccessor) entityliving).get1029() > 0.1f)
            {
                final float f3 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor) entityliving).get1029() * -Math.sin((double) f3) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) entityliving).get1029() * Math.cos((double) f3) * 0.17499999701976776;
            }
            else if (((LivingAccessor) entityliving).get1029() < -0.1f)
            {
                final float f4 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor) entityliving).get1029() * -Math.sin((double) f4) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) entityliving).get1029() * Math.cos((double) f4) * 0.17499999701976776;
            }
            if (((LivingAccessor) entityliving).get1060() > 0.1f)
            {
                final float f5 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor) entityliving).get1060() * Math.cos((double) f5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) entityliving).get1060() * Math.sin((double) f5) * 0.17499999701976776;
            }
            else if (((LivingAccessor) entityliving).get1060() < -0.1f)
            {
                final float f6 = entityliving.yaw * f2;
                this.velocityX += ((LivingAccessor) entityliving).get1060() * Math.cos((double) f6) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) entityliving).get1060() * Math.sin((double) f6) * 0.17499999701976776;
            }
            if (this.field_1623 && ((LivingAccessor) entityliving).getJumping())
            {
                this.field_1623 = false;
                this.velocityY = 1.4;
                this.jpress = true;
                --this.jrem;
            }
            else if (this.isSubmergedInWater() && ((LivingAccessor) entityliving).getJumping())
            {
                this.velocityY = 0.5;
                this.jpress = true;
                --this.jrem;
            }
            else if (this.jrem > 0 && !this.jpress && ((LivingAccessor) entityliving).getJumping())
            {
                this.velocityY = 1.2;
                this.jpress = true;
                --this.jrem;
            }
            if (this.jpress && !((LivingAccessor) entityliving).getJumping())
            {
                this.jpress = false;
            }
            final double d = Math.abs(Math.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ));
            if (d > 0.375)
            {
                final double d2 = 0.375 / d;
                this.velocityX *= d2;
                this.velocityZ *= d2;
            }
            return;
        }
        super.method_910();
    }

    @Override
    protected String method_911()
    {
        return "mob.pig";
    }

    @Override
    protected String method_912()
    {
        return "mob.pig";
    }

    @Override
    protected String method_913()
    {
        return "mob.pigdeath";
    }

    @Override
    public boolean method_1323(final PlayerEntity entityplayer)
    {
        if (!this.getSaddled && entityplayer.inventory.getSelectedItem() != null && entityplayer.inventory.getSelectedItem().itemId == Item.SADDLE.id)
        {
            entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            this.getSaddled = true;
            this.texture = "aether:textures/entity/FlyingPigSaddle.png";
            return true;
        }
        if (this.getSaddled && !this.world.isRemote && (this.field_1594 == null || this.field_1594 == entityplayer))
        {
            entityplayer.method_1376(this);
            AetherMod.giveAchievement(AetherAchievements.flyingPig, entityplayer);
            return true;
        }
        return false;
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
        this.method_1339(this.random.nextBoolean() ? Item.FEATHER.id : Item.RAW_PORKCHOP.id, count);
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Phyg");
    }
}
