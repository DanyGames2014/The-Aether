package com.gildedgames.aether.entity.projectile;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityProjectileBase;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityDartGolden extends EntityProjectileBase implements MobSpawnDataProvider
{
    public LivingEntity victim;
    public static int texfxindex;

    public EntityDartGolden(final World level)
    {
        super(level);
    }

    public EntityDartGolden(final World world, final double x, final double y, final double z)
    {
        super(world, x, y, z);
    }

    public EntityDartGolden(final World world, final LivingEntity ent)
    {
        super(world, ent);
    }

    public void initDataTracker()
    {
        super.initDataTracker();
        this.item = new ItemStack(AetherItems.Dart, 1, 0);
        this.curvature = 0.0f;
        this.dmg = 4;
        this.speed = 1.5f;
    }

    @Override
    public boolean isSubmergedInWater()
    {
        return this.victim == null && super.isSubmergedInWater();
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
        this.curvature = 0.03f;
        this.world.playSound((Entity) this, "random.drr", 1.0f, 1.2f / (this.random.nextFloat() * 0.2f + 0.9f));
        return this.victim == null;
    }

    @Override
    public boolean canBeShot(final Entity ent)
    {
        return super.canBeShot(ent) && this.victim == null;
    }

    static
    {
        EntityDartGolden.texfxindex = 94;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("GoldenDart");
    }
}
