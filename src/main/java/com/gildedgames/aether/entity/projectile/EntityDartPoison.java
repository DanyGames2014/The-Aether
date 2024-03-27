package com.gildedgames.aether.entity.projectile;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.effect.AetherPoison;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class EntityDartPoison extends EntityDartGolden
{
    public LivingEntity victim;
    public int poisonTime;
    public static int texfxindex;

    public EntityDartPoison(final World level)
    {
        super(level);
    }

    public EntityDartPoison(final World world, final double x, final double y, final double z)
    {
        super(world, x, y, z);
    }

    public EntityDartPoison(final World world, final LivingEntity ent)
    {
        super(world, ent);
    }

    @Override
    public void initDataTracker()
    {
        super.initDataTracker();
        this.item = new ItemStack(AetherItems.Dart, 1, 1);
        this.dmg = 2;
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
            if (lr2 instanceof EntityDartPoison)
            {
                final EntityDartPoison arr = (EntityDartPoison) lr2;
                if (arr.victim == ent)
                {
                    arr.poisonTime = 500;
                    arr.dead = false;
                    ent.damage(this.shooter, this.dmg);
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
            /* todo: particle
            final ParticleBase fx = new Poof(this.level, this.x, this.y, this.z, ItemBase.slimeball);
            fx.renderDistanceMultiplier = 10.0;
            ((ParticleBaseAccessor)fx).set2635(EntityDartPoison.texfxindex);*/
            //AetherPoison.mc.particleManager.addParticle(fx);
            this.dead = false;
            this.inGround = false;
            this.x = this.victim.x;
            this.y = this.victim.boundingBox.minY + this.victim.spacingY * 0.8;
            this.z = this.victim.z;
            AetherPoison.distractEntity(this.victim);
            --this.poisonTime;
            if (this.poisonTime % 50 == 0)
            {
                this.victim.damage(this.shooter, 1);
            }
        }
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("PoisonDart");
    }

    static
    {
        EntityDartPoison.texfxindex = 94;
    }
}
