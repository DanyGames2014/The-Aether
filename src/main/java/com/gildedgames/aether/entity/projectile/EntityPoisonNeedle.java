package com.gildedgames.aether.entity.projectile;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.effect.AetherPoison;
import com.gildedgames.aether.entity.base.EntityProjectileBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityPoisonNeedle extends EntityProjectileBase implements MobSpawnDataProvider
{
    public LivingEntity victim;
    public int poisonTime;
    public static int texfxindex;

    public EntityPoisonNeedle(final World level)
    {
        super(level);
    }

    public EntityPoisonNeedle(final World world, final double x, final double y, final double z)
    {
        super(world, x, y, z);
    }

    public EntityPoisonNeedle(final World world, final LivingEntity ent)
    {
        super(world, ent);
    }

    public void initDataTracker()
    {
        super.initDataTracker();
        this.dmg = 0;
        this.speed = 1.5f;
    }

    @Override
    public boolean isSubmergedInWater()
    {
        return this.victim == null && super.isSubmergedInWater();
    }

    @Override
    public boolean onHitTarget(final Entity entity)
    {
        if (!(entity instanceof LivingEntity) || !AetherPoison.canPoison(entity))
        {
            return super.onHitTarget(entity);
        }
        final LivingEntity ent = (LivingEntity) entity;
        if (ent instanceof PlayerEntity)
        {
            AetherPoison.afflictPoison();
            return super.onHitTarget(entity);
        }
        final List list = this.world.getEntities(this, ent.boundingBox.expand(2.0, 2.0, 2.0));
        for (int i = 0; i < list.size(); ++i)
        {
            final Entity lr2 = (Entity) list.get(i);
            if (lr2 instanceof EntityPoisonNeedle)
            {
                final EntityPoisonNeedle arr = (EntityPoisonNeedle) lr2;
                if (arr.victim == ent)
                {
                    arr.poisonTime = 500;
                    arr.dead = false;
                    this.markDead();
                    return false;
                }
            }
        }
        (this.victim = ent).damage(this.shooter, this.dmg);
        this.poisonTime = 500;
        return false;
    }

    @Override
    public void markDead()
    {
        this.victim = null;
        super.markDead();
    }

    @Override
    public boolean onHitBlock()
    {
        return this.victim == null;
    }

    @Override
    public boolean canBeShot(final Entity ent)
    {
        return super.canBeShot(ent) && this.victim == null;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.dead)
        {
            return;
        }
        if (this.victim != null)
        {
            if (this.victim.dead || this.poisonTime == 0)
            {
                this.markDead();
                return;
            }
            /* todo:
            final ParticleBase fx = new Poof(this.level, this.x, this.y, this.z, ItemBase.slimeball);
            fx.renderDistanceMultiplier = 10.0;
            ((ParticleBaseAccessor)fx).set2635(EntityPoisonNeedle.texfxindex);*/
            //AetherPoison.mc.particleManager.addParticle(fx);
            this.dead = false;
            this.inGround = false;
            this.x = this.victim.x;
            this.y = this.victim.boundingBox.minY + this.victim.spacingY * 0.8;
            this.z = this.victim.z;
            AetherPoison.distractEntity(this.victim);
            --this.poisonTime;
        }
    }

    static
    {
        EntityPoisonNeedle.texfxindex = 94;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("PoisonNeedle");
    }
}
