package com.gildedgames.aether.mixin.gui;

import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ButtonWidget.class)
public interface ButtonAccessor
{
    @Accessor(value = "width")
    int getWidth();

    @Accessor(value = "height")
    int getHeight();
}
