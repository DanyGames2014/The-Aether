package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.client.render.model.ModelHomeShot;
import com.gildedgames.aether.entity.mobs.EntityHomeShot;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class RenderHomeShot extends LivingEntityRenderer
{
    private ModelHomeShot shotty;

    public RenderHomeShot(final EntityModel ms, final float f)
    {
        super(ms, f);
        this.shotty = (ModelHomeShot) ms;
    }

    public void method_823(final LivingEntity el, final float f)
    {
        final EntityHomeShot hs = (EntityHomeShot) el;
        for (int i = 0; i < 3; ++i)
        {
            this.shotty.sinage[i] = hs.sinage[i];
        }
    }
}
