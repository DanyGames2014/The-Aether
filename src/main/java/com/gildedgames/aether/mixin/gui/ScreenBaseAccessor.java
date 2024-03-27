package com.gildedgames.aether.mixin.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(Screen.class)
public interface ScreenBaseAccessor
{

    @Accessor("minecraft")
    Minecraft getMinecraftInstance();

    @Accessor("buttons")
    public List getButtons();

    @Invoker("render")
    public void invokeRender(int mouseX, int mouseY, float delta);
}
