package com.gildedgames.aether.mixin.data;

import net.minecraft.class_81;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.io.File;

@Mixin(class_81.class)
public interface DimensionFileAccessor
{
    @Accessor("field_279")
    public File getSaveFolder();
}
