package com.gildedgames.aether.mixin.access;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingAccessor
{
    @Accessor("field_1029")
    float get1029();

    @Accessor("field_1060")
    float get1060();

    @Accessor("jumping")
    boolean getJumping();

    @Accessor("field_1058")
    void set1058(int i);

    @Accessor("field_1029")
    void set1029(float f);

    @Accessor("field_1060")
    void set1060(float f);

    // TODO : Not sure about this one, getDrops ???
    @Invoker("method_933")
    void invokeGetDrops();
    //@Invoker("setSize")
    //void setSize(float f, float f1);
}
