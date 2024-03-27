package com.gildedgames.aether.mixin.access;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntityRenderer.class)
public interface LivingEntityRendererAccessor
{
    @Invoker("method_828")
    float invoke828(LivingEntity living, float f);

    @Invoker("method_820")
    float invoke820(LivingEntity living, float f);

    @Invoker("method_826")
    void invoke826(LivingEntity arg, double d, double d1, double d2);

    @Invoker("method_824")
    void invoke824(LivingEntity arg, float f, float f1, float f2);

    @Invoker("method_823")
    void invoke823(LivingEntity living, float f);
}
