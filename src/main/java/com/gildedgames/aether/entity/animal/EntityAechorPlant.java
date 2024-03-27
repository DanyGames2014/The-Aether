package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.entity.projectile.EntityPoisonNeedle;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class EntityAechorPlant extends EntityAetherAnimal
{
    public LivingEntity target;
    public int size;
    public int attTime;
    public int smokeTime;
    public boolean seeprey;
    public boolean grounded;
    public boolean noDespawn;
    public float sinage;
    private int poisonLeft;

    public EntityAechorPlant(final World level)
    {
        super(level);
        this.texture = "aether:textures/entity/aechorplant.png";
        this.size = this.random.nextInt(4) + 1;
        this.health = 10 + this.size * 2;
        this.sinage = this.random.nextFloat() * 6.0f;
        final int n = 0;
        this.attTime = n;
        this.smokeTime = n;
        this.seeprey = false;
        this.setBoundingBoxSpacing(0.75f + this.size * 0.125f, 0.5f + this.size * 0.075f);
        this.method_1340(this.x, this.y, this.z);
        this.poisonLeft = 2;
    }

    @Override
    public int method_916()
    {
        return 3;
    }

    @Override
    public boolean canSpawn()
    {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        return this.world.getBlockId(i, j - 1, k) == AetherBlocks.AETHER_GRASS_BLOCK.id && this.world.method_255(i, j, k) > 8 && super.canSpawn();
    }

    @Override
    public void method_937()
    {
        if (this.health <= 0 || !this.grounded)
        {
            super.method_937();
            if (this.health <= 0)
            {
                return;
            }
        }
        else
        {
            ++this.field_1059;
            this.method_920();
        }
        if (this.field_1623)
        {
            this.grounded = true;
        }
        if (this.hurtTime > 0)
        {
            this.sinage += 0.9f;
        }
        else if (this.seeprey)
        {
            this.sinage += 0.3f;
        }
        else
        {
            this.sinage += 0.1f;
        }
        if (this.sinage > 6.283186f)
        {
            this.sinage -= 6.283186f;
        }
        if (this.target == null)
        {
            final List list = this.world.getEntities(this, this.boundingBox.expand(10.0, 10.0, 10.0));
            for (int j = 0; j < list.size(); ++j)
            {
                final Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof LivingEntity && !(entity1 instanceof EntityAechorPlant) && !(entity1 instanceof CreeperEntity))
                {
                    if (entity1 instanceof PlayerEntity)
                    {
                        final PlayerEntity player1 = (PlayerEntity) entity1;
                        final boolean flag = false;
                        if (flag)
                        {
                            continue;
                        }
                    }
                    this.target = (LivingEntity) entity1;
                    break;
                }
            }
        }
        if (this.target != null)
        {
            if (this.target.dead || this.target.method_1351(this) > 12.0)
            {
                this.target = null;
                this.attTime = 0;
            }
            else if (this.target instanceof PlayerEntity)
            {
                final PlayerEntity player2 = (PlayerEntity) this.target;
                final boolean flag2 = false;
                if (flag2)
                {
                    this.target = null;
                    this.attTime = 0;
                }
            }
            if (this.target != null && this.attTime >= 20 && this.method_928(this.target) && this.target.method_1351(this) < 5.5 + this.size / 2.0)
            {
                this.shootTarget();
                this.attTime = -10;
            }
            if (this.attTime < 20)
            {
                ++this.attTime;
            }
        }
        ++this.smokeTime;
        if (this.smokeTime >= (this.seeprey ? 3 : 8))
        {
            this.smokeTime = 0;
            final int i = MathHelper.floor(this.x);
            final int j = MathHelper.floor(this.boundingBox.minY);
            final int k = MathHelper.floor(this.z);
            if (this.world.getBlockId(i, j - 1, k) != AetherBlocks.AETHER_GRASS_BLOCK.id && this.grounded)
            {
                this.dead = true;
            }
        }
        this.seeprey = (this.target != null);
    }

    @Override
    public void markDead()
    {
        if (!this.noDespawn || this.health <= 0)
        {
            super.markDead();
        }
    }

    public void shootTarget()
    {
        if (this.world.field_213 == 0)
        {
            return;
        }
        double d1 = this.target.x - this.x;
        double d2 = this.target.z - this.z;
        final double d3 = 1.5 / Math.sqrt(d1 * d1 + d2 * d2 + 0.1);
        final double d4 = 0.1 + Math.sqrt(d1 * d1 + d2 * d2 + 0.1) * 0.5 + (this.y - this.target.y) * 0.25;
        d1 *= d3;
        d2 *= d3;
        final EntityPoisonNeedle entityarrow = new EntityPoisonNeedle(this.world, this);
        entityarrow.y = this.y + 0.5;
        this.world.playSound((Entity) this, "aether:aether.sound.other.dartshooter.shootdart", 2.0f, 1.0f / (this.random.nextFloat() * 0.4f + 0.8f));
        this.world.method_210(entityarrow);
        entityarrow.setArrowHeading(d1, d4, d2, 0.285f + (float) d4 * 0.05f, 1.0f);
    }

    @Override
    protected String method_912()
    {
        return "damage.hurtflesh";
    }

    @Override
    protected String method_913()
    {
        return "damage.fallbig";
    }

    @Override
    public void method_925(final Entity entity, final int ii, final double dd, final double dd1)
    {
        for (int i = 0; i < 8; ++i)
        {
            final double d1 = this.x + (this.random.nextFloat() - this.random.nextFloat()) * 0.5;
            final double d2 = this.y + 0.25 + (this.random.nextFloat() - this.random.nextFloat()) * 0.5;
            final double d3 = this.z + (this.random.nextFloat() - this.random.nextFloat()) * 0.5;
            final double d4 = (this.random.nextFloat() - this.random.nextFloat()) * 0.5;
            final double d5 = (this.random.nextFloat() - this.random.nextFloat()) * 0.5;
            this.world.addParticle("portal", d1, d2, d3, d4, 0.25, d5);
        }
        if (this.health > 0)
        {
            return;
        }
        super.method_925(entity, ii, dd, dd1);
    }

    @Override
    public boolean method_1323(final PlayerEntity entityplayer)
    {
        final boolean flag = false;
        final ItemStack stack = entityplayer.inventory.getSelectedItem();
        if (stack != null && stack.itemId == AetherItems.Bucket.id && this.poisonLeft > 0)
        {
            --this.poisonLeft;
            entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, null);
            entityplayer.inventory.setStack(entityplayer.inventory.selectedSlot, new ItemStack(AetherItems.Bucket, 1, 2));
            return true;
        }
        if (flag)
        {
            this.noDespawn = true;
            final String s = "heart";
            for (int i = 0; i < 7; ++i)
            {
                final double d = this.random.nextGaussian() * 0.02;
                final double d2 = this.random.nextGaussian() * 0.02;
                final double d3 = this.random.nextGaussian() * 0.02;
                this.world.addParticle(s, this.x + this.random.nextFloat() * this.spacingXZ * 2.0f - this.spacingXZ, this.y + 0.5 + this.random.nextFloat() * this.spacingY, this.z + this.random.nextFloat() * this.spacingXZ * 2.0f - this.spacingXZ, d, d2, d3);
            }
        }
        return false;
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putBoolean("Grounded", this.grounded);
        tag.putBoolean("NoDespawn", this.noDespawn);
        tag.putShort("AttTime", (short) this.attTime);
        tag.putShort("Size", (short) this.size);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.grounded = tag.getBoolean("Grounded");
        this.noDespawn = tag.getBoolean("NoDespawn");
        this.attTime = tag.getShort("AttTime");
        this.size = tag.getShort("Size");
        this.setBoundingBoxSpacing(0.75f + this.size * 0.125f, 0.5f + this.size * 0.075f);
        this.method_1340(this.x, this.y, this.z);
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
        this.method_1339(AetherItems.AechorPetal.id, count);
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("AechorPlant");
    }
}
