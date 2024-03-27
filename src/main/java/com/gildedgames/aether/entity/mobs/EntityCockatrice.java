package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.projectile.EntityPoisonNeedle;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityCockatrice extends MonsterEntity implements MobSpawnDataProvider
{
    public float field_752_b;
    public float destPos;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h;
    public int timeUntilNextEgg;
    public int jumps;
    public int jrem;
    public boolean jpress;
    public boolean gotrider;

    public EntityCockatrice(final World level)
    {
        super(level);
        this.destPos = 0.0f;
        this.field_755_h = 1.0f;
        this.field_1641 = 1.0f;
        this.jrem = 0;
        this.jumps = 3;
        this.texture = "aether:textures/entity/Cockatrice.png";
        this.setBoundingBoxSpacing(1.0f, 2.0f);
        this.health = 20;
        this.timeUntilNextEgg = this.random.nextInt(6000) + 6000;
    }

    @Override
    public boolean canSpawn()
    {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        return this.random.nextInt(25) == 0 && this.method_641(i, j, k) >= 0.0f && this.world.canSpawnEntity(this.boundingBox) && this.world.method_190(this, this.boundingBox).size() == 0 && !this.world.method_218(this.boundingBox) && this.world.getBlockId(i, j - 1, k) != AetherBlocks.DUNGEON_STONE.id && this.world.getBlockId(i, j - 1, k) != AetherBlocks.LIGHT_DUNGEON_STONE.id
                && this.world.getBlockId(i, j - 1, k) != AetherBlocks.LOCKED_DUNGEON_STONE.id && this.world.getBlockId(i, j - 1, k) != AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id && this.world.getBlockId(i, j - 1, k) != AetherBlocks.HOLYSTONE.id && this.world.field_213 > 0;
    }

    @Override
    public void tick()
    {
        super.tick();
        this.field_1622 = (this.field_1594 instanceof PlayerEntity); // todo: make local player
        if (!this.world.isRemote && this.gotrider)
        {
            if (this.field_1594 != null)
            {
                return;
            }
            final List list = this.world.getEntities(this, this.boundingBox.expand(0.5, 0.75, 0.5));
            final int i = 0;
            if (i < list.size())
            {
                final Entity entity = (Entity) list.get(i);
                entity.method_1376(this);
            }
            this.gotrider = false;
        }
        if (!this.world.isRemote && this.world.field_213 == 0)
        {
            this.markDead();
        }
    }

    @Override
    protected void method_637(final Entity target, final float f)
    {
        if (f < 10.0f)
        {
            final double d = target.x - this.x;
            final double d2 = target.z - this.z;
            if (this.field_1042 == 0)
            {
                final EntityPoisonNeedle entityPoisonNeedle;
                final EntityPoisonNeedle entityarrow = entityPoisonNeedle = new EntityPoisonNeedle(this.world, this);
                entityPoisonNeedle.y += 1.399999976158142;
                final double d3 = target.y + target.method_1378() - 0.20000000298023224 - entityarrow.y;
                final float f2 = MathHelper.sqrt(d * d + d2 * d2) * 0.2f;
                this.world.playSound((Entity) this, "aether:aether.sound.other.dartshooter.shootdart", 1.0f, 1.0f / (this.random.nextFloat() * 0.4f + 0.8f));
                this.world.method_210(entityarrow);
                entityarrow.setArrowHeading(d, d3 + f2, d2, 0.6f, 12.0f);
                this.field_1042 = 30;
            }
            this.yaw = (float) (Math.atan2(d2, d) * 180.0 / 3.1415927410125732) - 90.0f;
            this.field_663 = true;
        }
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
            this.jrem = this.jumps;
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
        if (!this.world.isRemote && --this.timeUntilNextEgg <= 0)
        {
            this.timeUntilNextEgg = this.random.nextInt(6000) + 6000;
        }
    }

    @Override
    protected void method_1389(final float height)
    {
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        if (target != null && this.field_1594 != null && target == this.field_1594)
        {
            return false;
        }
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
        tag.putShort("Jumps", (short) this.jumps);
        tag.putShort("Remaining", (short) this.jrem);
        if (this.field_1594 != null)
        {
            this.gotrider = true;
        }
        tag.putBoolean("GotRider", this.gotrider);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.jumps = tag.getShort("Jumps");
        this.jrem = tag.getShort("Remaining");
        this.gotrider = tag.getBoolean("GotRider");
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

    public boolean method_1323(final PlayerEntity entityplayer)
    {
        return true;
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
        return AetherMod.MODID.id("Cockatrice");
    }
}
