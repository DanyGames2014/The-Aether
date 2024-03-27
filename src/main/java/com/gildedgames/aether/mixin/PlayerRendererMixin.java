package com.gildedgames.aether.mixin;

import com.gildedgames.aether.AetherMod;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerRendererMixin
{
    @Inject(method = "render(Lnet/minecraft/entity/Entity;DDDFF)V", at = @At(value = "HEAD"), cancellable = true)
    private void renderEntityCustomHEAD(Entity d, double e, double f, double g, float h, float par6, CallbackInfo ci)
    {
        if (d instanceof PlayerEntity player)
        {
            if (!AetherMod.getPlayerHandler(player).visible)
                ci.cancel();
        }
    }
}
