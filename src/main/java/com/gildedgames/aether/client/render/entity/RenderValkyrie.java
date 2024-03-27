package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.client.render.model.ModelValkyrie;
import com.gildedgames.aether.entity.boss.EntityValkyrie;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class RenderValkyrie extends SkeletonEntityRenderer
{
    public ModelValkyrie mv1;

    public RenderValkyrie(final BipedEntityModel model, final float f)
    {
        super(model, f);
        this.mv1 = (ModelValkyrie) model;
    }

    @Override
    protected void method_823(final LivingEntity entityliving, final float f)
    {
        final EntityValkyrie v1 = (EntityValkyrie) entityliving;
        this.mv1.sinage = v1.sinage;
        this.mv1.gonRound = v1.field_1623;
        this.mv1.halow = !v1.otherDimension();
    }
}
