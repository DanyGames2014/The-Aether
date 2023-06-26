package com.gildedgames.aether.mixin.gui;

import net.minecraft.client.gui.widgets.Button;
import net.minecraft.level.Level;
import net.minecraft.level.dimension.DimensionData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Button.class)
public interface ButtonAccessor
{
	@Accessor(value = "width")
	int getWidth();
	@Accessor(value = "height")
	int getHeight();
}