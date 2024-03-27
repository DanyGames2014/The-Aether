package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class EntityFireMinion extends MonsterEntity implements MobSpawnDataProvider
{
    public EntityFireMinion(final World level)
    {
        super(level);
        this.texture = "aether:textures/entity/firemonster.png";
        this.movementSpeed = 1.5f;
        this.field_547 = 5;
        this.health = 40;
        this.fireImmune = true;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.health > 0)
        {
            for (int j = 0; j < 4; ++j)
            {
                final double a = this.random.nextFloat() - 0.5f;
                final double b = this.random.nextFloat();
                final double c = this.random.nextFloat() - 0.5f;
                final double d = this.x + a * b;
                final double e = this.boundingBox.minY + b - 0.5;
                final double f = this.z + c * b;
                this.world.addParticle("flame", d, e, f, 0.0, -0.07500000298023224, 0.0);
            }
        }
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("FireMinion");
    }
}
