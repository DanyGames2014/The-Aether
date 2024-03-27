package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.MoaColour;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityMoa extends EntityAetherAnimal
{
    public float field_752_b;
    public float destPos;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h;
    public int timeUntilNextEgg;
    public int jrem;
    int petalsEaten;
    boolean wellFed;
    boolean followPlayer;
    public boolean jpress;
    public boolean baby;
    public boolean grown;
    public boolean saddled;
    public MoaColour colour;

    public EntityMoa(final World level)
    {
        this(level, false, false, false);
    }

    public EntityMoa(final World world, final boolean babyBool, final boolean grownBool, final boolean saddledBool)
    {
        this(world, babyBool, grownBool, saddledBool, MoaColour.pickRandomMoa());
    }

    public EntityMoa(final World world, final boolean babyBool, final boolean grownBool, final boolean saddledBool, final MoaColour moaColour)
    {
        super(world);
        this.petalsEaten = 0;
        this.wellFed = false;
        this.followPlayer = false;
        this.baby = false;
        this.grown = false;
        this.saddled = false;
        this.destPos = 0.0f;
        this.field_755_h = 1.0f;
        this.field_1641 = 1.0f;
        this.jrem = 0;
        this.baby = babyBool;
        this.grown = grownBool;
        this.saddled = saddledBool;
        if (this.baby)
        {
            this.setBoundingBoxSpacing(0.4f, 0.5f);
        }
        this.colour = moaColour;
        this.texture = this.colour.getTexture(this.saddled);
        this.setBoundingBoxSpacing(1.0f, 2.0f);
        this.health = 40;
        this.timeUntilNextEgg = this.random.nextInt(6000) + 6000;
    }

    @Override
    public void tick()
    {
        super.tick();
        this.field_1622 = (this.field_1594 instanceof PlayerEntity); // todo: make local player
    }

    @Override
    public void method_937()
    {
        super.method_937();
        this.field_756_e = this.field_752_b;
        this.field_757_d = this.destPos;
        this.destPos += (float) ((this.field_1623 ? -1 : 4) * 0.05);
        if (this.destPos < 0.01f)
        {
            this.destPos = 0.01f;
        }
        if (this.destPos > 1.0f)
        {
            this.destPos = 1.0f;
        }
        if (this.field_1623)
        {
            this.destPos = 0.0f;
            this.jpress = false;
            this.jrem = this.colour.jumps;
        }
        if (!this.field_1623 && this.field_755_h < 1.0f)
        {
            this.field_755_h = 1.0f;
        }
        this.field_755_h *= (float) 0.9;
        if (!this.field_1623 && this.velocityY < 0.0)
        {
            if (this.field_1594 == null)
            {
                this.velocityY *= 0.6;
            }
            else
            {
                this.velocityY *= 0.6375;
            }
        }
        this.field_752_b += this.field_755_h * 2.0f;
        if (!this.world.isRemote && !this.baby && --this.timeUntilNextEgg <= 0)
        {
            this.world.playSound((Entity) this, "mob.chickenplop", 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
            this.method_1327(new ItemStack(AetherItems.MoaEgg, 1, this.colour.ID), 0.0f);
            this.timeUntilNextEgg = this.random.nextInt(6000) + 6000;
        }
        if (this.wellFed && this.random.nextInt(2000) == 0)
        {
            this.wellFed = false;
        }
        if (this.saddled && this.field_1594 == null)
        {
            this.movementSpeed = 0.0f;
        }
        else
        {
            this.movementSpeed = 0.7f;
        }
    }

    @Override
    protected void method_1389(final float height)
    {
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        final boolean flag = super.damage(target, amount);
        if (flag && this.field_1594 != null && (this.health <= 0 || this.random.nextInt(3) == 0))
        {
            this.field_1594.method_1376(this);
        }
        return flag;
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
                this.velocityY = 0.875;
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
                this.velocityY = 0.75;
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
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("Remaining", (short) this.jrem);
        tag.putShort("ColourNumber", (short) this.colour.ID);
        tag.putBoolean("Baby", this.baby);
        tag.putBoolean("Grown", this.grown);
        tag.putBoolean("Saddled", this.saddled);
        tag.putBoolean("wellFed", this.wellFed);
        tag.putInt("petalsEaten", this.petalsEaten);
        tag.putBoolean("followPlayer", this.followPlayer);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.jrem = tag.getShort("Remaining");
        this.colour = MoaColour.getColour(tag.getShort("ColourNumber"));
        this.baby = tag.getBoolean("Baby");
        this.grown = tag.getBoolean("Grown");
        this.saddled = tag.getBoolean("Saddled");
        this.wellFed = tag.getBoolean("wellFed");
        this.petalsEaten = tag.getInt("petalsEaten");
        this.followPlayer = tag.getBoolean("followPlayer");
        if (this.baby)
        {
            this.grown = false;
            this.saddled = false;
        }
        if (this.grown)
        {
            this.baby = false;
            this.saddled = false;
        }
        if (this.saddled)
        {
            this.baby = false;
            this.grown = false;
        }
        this.texture = this.colour.getTexture(this.saddled);
    }

    @Override
    protected String method_911()
    {
        return "aether:aether.sound.mobs.moa.idlecall";
    }

    @Override
    protected String method_912()
    {
        return "aether:aether.sound.mobs.moa.idlecall";
    }

    @Override
    protected String method_913()
    {
        return "aether:aether.sound.mobs.moa.idlecall";
    }

    @Override
    public boolean method_1323(final PlayerEntity entityplayer)
    {
        if (!this.saddled && this.grown && !this.baby && entityplayer.inventory.getSelectedItem() != null && entityplayer.inventory.getSelectedItem().itemId == Item.SADDLE.id)
        {
            entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            this.saddled = true;
            this.grown = false;
            this.texture = this.colour.getTexture(this.saddled);
            return true;
        }
        if (this.saddled && !this.world.isRemote && (this.field_1594 == null || this.field_1594 == entityplayer))
        {
            entityplayer.method_1376(this);
            final float yaw = this.yaw;
            entityplayer.yaw = yaw;
            entityplayer.prevYaw = yaw;
            return true;
        }
        if (!this.wellFed && !this.saddled && this.baby && !this.grown)
        {
            final ItemStack itemstack = entityplayer.inventory.getSelectedItem();
            if (itemstack != null && itemstack.itemId == AetherItems.AechorPetal.id)
            {
                ++this.petalsEaten;
                entityplayer.inventory.removeStack(entityplayer.inventory.selectedSlot, 1);
                if (this.petalsEaten > this.colour.jumps)
                {
                    this.grown = true;
                    this.baby = false;
                }
                this.wellFed = true;
            }
            return true;
        }
        if (!this.saddled && (this.baby || this.grown))
        {
            if (!this.followPlayer)
            {
                this.followPlayer = true;
                this.target = entityplayer;
            }
            else
            {
                this.followPlayer = false;
                this.target = null;
            }
        }
        return true;
    }

    public boolean method_940()
    {
        return !this.baby && !this.grown && !this.saddled;
    }

    @Override
    protected boolean bypassesSteppingEffects()
    {
        return this.field_1623;
    }

    @Override
    protected void method_933()
    {

        PlayerEntity player = this.world.method_186(this, 10);
        int count = 3;
        if (player != null)
            if (player.getHand() != null)
                if (player.getHand().getItem() == AetherItems.SwordSkyroot)
                    count *= 2;
        this.method_1339(Item.FEATHER.id, count);
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Moa");
    }
}
