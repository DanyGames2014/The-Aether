package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityFlyingCow extends EntityAetherAnimal
{
    public boolean getSaddled;
    public float wingFold;
    public float wingAngle;
    private float aimingForFold;
    public int jumps;
    public int jrem;
    private boolean jpress;
    private int ticks;

    public EntityFlyingCow(World level)
    {
        super(level);
        this.getSaddled = false;
        this.texture = "aether:textures/entity/Mob_FlyingCowBase.png";
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
    public void writeNbt(NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("Jumps", (short) this.jumps);
        tag.putShort("Remaining", (short) this.jrem);
        tag.putBoolean("getSaddled", this.getSaddled);
    }

    @Override
    public void readNbt(NbtCompound tag)
    {
        super.readNbt(tag);
        this.jumps = tag.getShort("Jumps");
        this.jrem = tag.getShort("Remaining");
        this.getSaddled = tag.getBoolean("getSaddled");
        if (this.getSaddled)
        {
            this.texture = "aether:textures/entity/Mob_FlyingCowSaddle.png";
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
        this.wingAngle = this.wingFold * (float) Math.sin(this.ticks / 31.830988f);
        this.wingFold += (this.aimingForFold - this.wingFold) / 5.0f;
        this.field_1636 = 0.0f;
        if (this.velocityY < -0.2)
        {
            this.velocityY = -0.2;
        }
    }

    public void method_910()
    {
        //if (this.level.isServerSide) {
        //   return;
        //}
        if (this.field_1594 != null && this.field_1594 instanceof LivingEntity)
        {
            this.field_1029 = 0.0f;
            this.field_1060 = 0.0f;
            this.jumping = false;
            ((EntityBaseAccessor) this.field_1594).setFallDistance(0.0f);
            float field_1606 = this.field_1594.yaw;
            this.yaw = field_1606;
            this.prevYaw = field_1606;
            float field_1607 = this.field_1594.pitch;
            this.pitch = field_1607;
            this.prevPitch = field_1607;
            LivingEntity living2 = (LivingEntity) this.field_1594;
            float float3 = 3.141593f;
            float float4 = float3 / 180.0f;
            if (((LivingAccessor) living2).get1029() > 0.1f)
            {
                float float5 = living2.yaw * float4;
                this.velocityX += ((LivingAccessor) living2).get1029() * -Math.sin(float5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) living2).get1029() * Math.cos(float5) * 0.17499999701976776;
            }
            else if (((LivingAccessor) living2).get1029() < -0.1f)
            {
                float float5 = living2.yaw * float4;
                this.velocityX += ((LivingAccessor) living2).get1029() * -Math.sin(float5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) living2).get1029() * Math.cos(float5) * 0.17499999701976776;
            }
            if (((LivingAccessor) living2).get1060() > 0.1f)
            {
                float float5 = living2.yaw * float4;
                this.velocityX += ((LivingAccessor) living2).get1060() * Math.cos(float5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) living2).get1060() * Math.sin(float5) * 0.17499999701976776;
            }
            else if (((LivingAccessor) living2).get1060() < -0.1f)
            {
                float float5 = living2.yaw * float4;
                this.velocityX += ((LivingAccessor) living2).get1060() * Math.cos(float5) * 0.17499999701976776;
                this.velocityZ += ((LivingAccessor) living2).get1060() * Math.sin(float5) * 0.17499999701976776;
            }
            if (this.field_1623 && ((LivingAccessor) living2).getJumping())
            {
                this.field_1623 = false;
                this.velocityY = 1.4;
                this.jpress = true;
                --this.jrem;
            }
            else if (this.isSubmergedInWater() && ((LivingAccessor) living2).getJumping())
            {
                this.velocityY = 0.5;
                this.jpress = true;
                --this.jrem;
            }
            else if (this.jrem > 0 && !this.jpress && ((LivingAccessor) living2).getJumping())
            {
                this.velocityY = 1.2;
                this.jpress = true;
                --this.jrem;
            }
            if (this.jpress && !((LivingAccessor) living2).getJumping())
            {
                this.jpress = false;
            }
            double double5 = Math.abs(Math.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ));
            if (double5 > 0.375)
            {
                double double7 = 0.375 / double5;
                this.velocityX *= double7;
                this.velocityZ *= double7;
            }
            return;
        }
        super.method_910();
    }

    @Override
    protected String method_911()
    {
        return "mob.cow";
    }

    @Override
    protected String method_912()
    {
        return "mob.cowhurt";
    }

    @Override
    protected String method_913()
    {
        return "mob.cowhurt";
    }

    @Override
    protected float method_915()
    {
        return 0.4f;
    }

    @Override
    public boolean method_1323(PlayerEntity playerBase)
    {
        if (!this.getSaddled && playerBase.inventory.getSelectedItem() != null && playerBase.inventory.getSelectedItem().itemId == Item.SADDLE.id)
        {
            playerBase.inventory.setStack(playerBase.inventory.selectedSlot, null);
            this.getSaddled = true;
            this.texture = "aether:textures/entity/Mob_FlyingCowSaddle.png";
            return true;
        }
        if (this.getSaddled /*&& !this.level.isServerSide*/ && (this.field_1594 == null || this.field_1594 == playerBase))
        {
            playerBase.method_1376(this);
            return true;
        }
        return false;
    }

    @Override
    protected void method_933()
    {
        PlayerEntity player = this.world.method_186(this, 10);
        int count = 2;
        if (player != null)
            if (player.getHand() != null)
                if (player.getHand().getItem() == AetherItems.SwordSkyroot)
                    count *= 2;
        this.method_1339(Item.LEATHER.id, count);
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("FlyingCow");
    }
}
