package com.gildedgames.aether.mixin;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.player.AetherPlayerHandler;
import net.minecraft.class_556;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_556.class)
public class Mixinclass_556
{

    @Shadow
    private Minecraft field_2401;

    @Inject(method = "method_1860(F)V", at = @At("HEAD"), cancellable = true)
    private void cancelHand(float f, CallbackInfo ci)
    {
        PlayerEntity player = field_2401.player;
        AetherPlayerHandler handler = AetherMod.getPlayerHandler(player);
        if (!handler.visible && player.getHand() == null)
            ci.cancel();
    }
}
