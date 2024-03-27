package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.client.render.model.ModelFlyingPig2;
import com.gildedgames.aether.entity.animal.EntityPhyg;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class RenderPhyg extends LivingEntityRenderer
{
    private EntityModel wingmodel;

    public RenderPhyg(final EntityModel modelbase, final EntityModel modelbase1, final float f)
    {
        super(modelbase, f);
        this.method_815(modelbase1);
        this.wingmodel = modelbase1;
    }

    protected boolean setWoolColorAndRender(final EntityPhyg pig, final int i, final float f)
    {
        if (i == 0)
        {
            this.bindTexture("aether:textures/entity/FlyingPigWings.png");
            ModelFlyingPig2.pig = pig;
            return true;
        }
        return false;
    }

    @Override
    protected boolean method_825(final LivingEntity entityliving, final int i, final float f)
    {
        return this.setWoolColorAndRender((EntityPhyg) entityliving, i, f);
    }
}
