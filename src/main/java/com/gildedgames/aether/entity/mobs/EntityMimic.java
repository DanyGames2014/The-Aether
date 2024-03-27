package com.gildedgames.aether.entity.mobs;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityDungeonMob;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityMimic extends EntityDungeonMob
{
    public float mouth;
    public float legs;
    private float legsDirection;

    public EntityMimic(final World level)
    {
        super(level);
        this.legsDirection = 1.0f;
        this.texture = "aether:textures/entity/Mimic.png";
        this.eyeHeight = 0.0f;
        this.setBoundingBoxSpacing(1.0f, 2.0f);
        this.health = 40;
        this.attackStrength = 5;
        this.target = level.method_186(this, 64.0);
    }

    @Override
    public void tick()
    {
        super.tick();
        this.mouth = (float) (Math.cos((double) (this.field_1645 / 10.0f * 3.1415927f)) + 1.0) * 0.6f;
        this.legs *= 0.9f;
        if (this.velocityX > 0.001 || this.velocityX < -0.001 || this.velocityZ > 0.001 || this.velocityZ < -0.001)
        {
            this.legs += this.legsDirection * 0.2f;
            if (this.legs > 1.0f)
            {
                this.legsDirection = -1.0f;
            }
            if (this.legs < -1.0f)
            {
                this.legsDirection = 1.0f;
            }
        }
    }

    public void method_1353(final Entity entity)
    {
        if (!this.dead && entity != null)
        {
            entity.damage(this, 4);
        }
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        if (target instanceof PlayerEntity)
        {
            this.method_924(target, 10.0f, 10.0f);
            this.target = target;
        }
        return super.damage(target, amount);
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
    protected float method_915()
    {
        return 0.6f;
    }

    @Override
    protected int method_914()
    {
        return Block.CHEST.id;
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Mimic");
    }
}
