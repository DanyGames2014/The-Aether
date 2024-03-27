package com.gildedgames.aether.mixin.access;

import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(World.class)
public interface LevelAccessor
{

    @Accessor("dimensionData")
    DimensionData getDimData();

    @Accessor("field_212")
    void set212(int i);
}
