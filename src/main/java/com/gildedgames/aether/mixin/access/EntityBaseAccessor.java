package com.gildedgames.aether.mixin.access;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Random;

@Mixin(Entity.class)
public interface EntityBaseAccessor
{

    /*GETTERS*/
    @Accessor("field_1612")
    boolean get1612();

    @Accessor("field_1636")
    float getFallDistance();

    @Accessor("field_1611")
    int getNextStepDistance();

    @Accessor("random")
    public Random getRand();

    @Accessor("fireImmune")
    public boolean getImmunityToFire();

    /*SETTERS*/
    @Accessor("spacingY")
    void setHeight(float f);

    @Accessor("spacingXZ")
    void setWidth(float f);

    @Accessor("field_1636")
    void setFallDistance(float f);

    @Accessor("field_1611")
    void setNextStepDistance(int i);

    @Accessor("fireImmune")
    public void setImmunityToFire(boolean b); //did i spell it correctly?
}
