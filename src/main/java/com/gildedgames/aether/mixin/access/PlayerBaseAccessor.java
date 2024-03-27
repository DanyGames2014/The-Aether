package com.gildedgames.aether.mixin.access;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlayerEntity.class)
public interface PlayerBaseAccessor
{
    @Accessor("sleeping")
    public void setSleeping(boolean b);
}
