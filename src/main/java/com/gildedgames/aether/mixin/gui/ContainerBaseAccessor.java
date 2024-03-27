package com.gildedgames.aether.mixin.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HandledScreen.class)
public interface ContainerBaseAccessor
{

    @Accessor("backgroundWidth")
    int getContainerWidth();

    @Accessor("backgroundHeight")
    int getContainerHeight();

}
