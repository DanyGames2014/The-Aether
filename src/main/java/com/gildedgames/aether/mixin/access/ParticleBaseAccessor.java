package com.gildedgames.aether.mixin.access;

import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Particle.class)
public interface ParticleBaseAccessor
{
    @Accessor("field_2635")
    void set2635(int i);
}
