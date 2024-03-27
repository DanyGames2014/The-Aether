package com.gildedgames.aether.entity;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityCloudParachute extends Entity implements MobSpawnDataProvider
{
    private LivingEntity entityUsing;
    private boolean justServerSpawned;
    private static Map<LivingEntity, EntityCloudParachute> cloudMap;
    private static final float MAX_FALL_SPEED = 0.25f;
    private static final double ANIM_RADIUS = 0.75;
    public boolean gold;

    public EntityCloudParachute(final World level)
    {
        super(level);
        this.justServerSpawned = false;
        this.setBoundingBoxSpacing(1.0f, 1.0f);
    }

    public EntityCloudParachute(final World world, final double d, final double d1, final double d2)
    {
        this(world);
        this.method_1338(d, d1, d2, this.yaw, this.pitch);
        this.justServerSpawned = true;
    }

    public EntityCloudParachute(final World world, final LivingEntity entityliving, final boolean bool)
    {
        this(world);
        if (entityliving == null)
        {
            throw new IllegalArgumentException("entityliving cannot be null.");
        }
        this.entityUsing = entityliving;
        EntityCloudParachute.cloudMap.put(entityliving, this);
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        this.moveToEntityUsing();
        this.gold = bool;
    }

    public static EntityCloudParachute getCloudBelongingToEntity(final LivingEntity entity)
    {
        return (EntityCloudParachute) EntityCloudParachute.cloudMap.get(entity);
    }

    public World getWorld()
    {
        return this.world;
    }

    public void die()
    {
        if (this.entityUsing != null)
        {
            if (EntityCloudParachute.cloudMap.containsKey(this.entityUsing))
            {
                EntityCloudParachute.cloudMap.remove(this.entityUsing);
            }
            for (int i = 0; i < 32; ++i)
            {
                doCloudSmoke(this.world, this.entityUsing);
            }
            this.world.playSound((Entity) this.entityUsing, "cloud", 1.0f, 1.0f / (this.random.nextFloat() * 0.1f + 0.95f));
        }
        this.entityUsing = null;
        this.dead = true;
    }

    public static void doCloudSmoke(final World world, final LivingEntity entityliving)
    {
        final double x = entityliving.x + ((EntityBaseAccessor) entityliving).getRand().nextDouble() * 0.75 * 2.0 - 0.75;
        final double y = entityliving.boundingBox.minY - 0.5 + ((EntityBaseAccessor) entityliving).getRand().nextDouble() * 0.75 * 2.0 - 0.75;
        final double z = entityliving.z + ((EntityBaseAccessor) entityliving).getRand().nextDouble() * 0.75 * 2.0 - 0.75;
        // todo: particle MinecraftClientAccessor.getMCinstance().particleManager.addParticle(new EntityCloudSmokeFX(world, x, y, z, 0.0, 0.0, 0.0, 2.5f, 1.0f, 1.0f, 1.0f));
    }

    public static boolean entityHasRoomForCloud(final World world, final LivingEntity entityliving)
    {
        final Box boundingBox = Box.create(entityliving.x - 0.5, entityliving.boundingBox.minY - 1.0, entityliving.z - 0.5, entityliving.x + 0.5, entityliving.boundingBox.minY, entityliving.z + 0.5);
        return world.method_190(entityliving, boundingBox).size() == 0 && !world.method_207(boundingBox, Material.WATER);
    }

    @Override
    protected void initDataTracker()
    {
    }

    @Override
    public boolean method_1364(final double d)
    {
        if (this.entityUsing != null)
        {
            return this.entityUsing.method_1364(d);
        }
        return super.method_1364(d);
    }

    @Override
    public boolean method_1356()
    {
        return true;
    }

    @Override
    public Box method_1381()
    {
        return this.boundingBox;
    }

    @Override
    public void tick()
    {
        if (this.dead)
        {
            return;
        }
        if (this.entityUsing == null)
        {
            if (this.world.isRemote && !this.justServerSpawned)
            {
                this.die();
                return;
            }
            this.justServerSpawned = false;
            this.entityUsing = this.findUser();
            if (this.entityUsing == null)
            {
                this.die();
                return;
            }
            EntityCloudParachute.cloudMap.put(this.entityUsing, this);
        }
        if (this.entityUsing.velocityY < -0.25)
        {
            this.entityUsing.velocityY = -0.25;
        }
        ((EntityBaseAccessor) this.entityUsing).setFallDistance(0.0f);
        doCloudSmoke(this.world, this.entityUsing);
        this.moveToEntityUsing();
    }

    private LivingEntity findUser()
    {
        final List entities = this.world.method_175(LivingEntity.class, this.boundingBox.copy().translate(0.0, 1.0, 0.0));
        double minDeltaSquared = -1.0;
        LivingEntity entityliving = null;
        for (int i = 0; i < entities.size(); ++i)
        {
            final LivingEntity entitylivingtemp = (LivingEntity) entities.get(i);
            if (entitylivingtemp.isAlive())
            {
                final double xDelta = this.x - entitylivingtemp.x;
                final double yDelta = this.boundingBox.maxY - entitylivingtemp.boundingBox.minY;
                final double zDelta = this.z - entitylivingtemp.z;
                final double deltaSquared = xDelta * xDelta + yDelta * yDelta + zDelta * zDelta;
                if (minDeltaSquared == -1.0 || deltaSquared < minDeltaSquared)
                {
                    minDeltaSquared = deltaSquared;
                    entityliving = entitylivingtemp;
                }
            }
        }
        return entityliving;
    }

    private void moveToEntityUsing()
    {
        this.method_1338(this.entityUsing.x, this.entityUsing.boundingBox.minY - this.spacingY / 2.0f, this.entityUsing.z, this.entityUsing.yaw, this.entityUsing.pitch);
        this.velocityX = this.entityUsing.velocityX;
        this.velocityY = this.entityUsing.velocityY;
        this.velocityZ = this.entityUsing.velocityZ;
        this.yaw = this.entityUsing.yaw;
        if (this.isCollided())
        {
            this.die();
        }
    }

    private boolean isCollided()
    {
        return this.world.method_190(this, this.boundingBox).size() > 0 || this.world.method_207(this.boundingBox, Material.WATER);
    }

    @Override
    public void onPlayerInteraction(final PlayerEntity entityplayer)
    {
    }

    @Override
    protected void readNbt(final NbtCompound tag)
    {
    }

    @Override
    protected void writeNbt(final NbtCompound tag)
    {
    }

    static
    {
        EntityCloudParachute.cloudMap = new HashMap<LivingEntity, EntityCloudParachute>();
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Parachute");
    }
}
