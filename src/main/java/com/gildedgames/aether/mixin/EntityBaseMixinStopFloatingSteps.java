package com.gildedgames.aether.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class EntityBaseMixinStopFloatingSteps
{
    @Shadow
    protected abstract boolean bypassesSteppingEffects();

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;bypassesSteppingEffects()Z"), require = 0)
    public boolean canClimbOrAir(Entity instance) {
        boolean originalCanClimb = bypassesSteppingEffects();
        return instance.field_1623 && originalCanClimb;
    }
}
