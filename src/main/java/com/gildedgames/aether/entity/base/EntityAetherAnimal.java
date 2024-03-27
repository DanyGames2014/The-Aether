package com.gildedgames.aether.entity.base;

import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public abstract class EntityAetherAnimal extends AnimalEntity implements MobSpawnDataProvider
{
    public EntityAetherAnimal(World level)
    {
        super(level);
    }

    @Override
    protected float method_641(int x, int y, int z)
    {
        if (this.world.getBlockId(x, y - 1, z) == AetherBlocks.AETHER_GRASS_BLOCK.id)
        {
            return 10.0f;
        }
        return this.world.method_1782(x, y, z) - 0.5f;
    }

    @Override
    public void writeNbt(NbtCompound tag)
    {
        super.writeNbt(tag);
    }

    @Override
    public void readNbt(NbtCompound tag)
    {
        super.readNbt(tag);
    }

    @Override
    public boolean canSpawn()
    {
        int integer2 = MathHelper.floor(this.x);
        int integer3 = MathHelper.floor(this.boundingBox.minY);
        int integer4 = MathHelper.floor(this.z);
        return this.world.canSpawnEntity(this.boundingBox) && this.world.method_190(this, this.boundingBox).size() == 0 && !this.world.method_218(this.boundingBox) && this.world.getBlockId(integer2, integer3 - 1, integer4) == AetherBlocks.AETHER_GRASS_BLOCK.id && this.world.method_252(integer2, integer3, integer4) > 8 && this.method_641(integer2, integer3, integer4) >= 0.0f;
    }

    @Override
    public int getMinAmbientSoundDelay()
    {
        return 120;
    }
}
