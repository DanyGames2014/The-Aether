package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.client.render.model.ModelFlyingCow2;
import com.gildedgames.aether.entity.animal.EntityFlyingCow;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class RenderFlyingCow extends LivingEntityRenderer
{
    private EntityModel wingmodel;

    public RenderFlyingCow(EntityModel entityModelBase2, EntityModel entityModelBase3, float float4)
    {
        super(entityModelBase2, float4);
        this.method_815(entityModelBase3);
        this.wingmodel = entityModelBase3;
    }

    protected boolean setWoolColorAndRender(EntityFlyingCow entityFlyingCow, int integer, float float4)
    {
        if (integer == 0)
        {
            this.bindTexture("aether:textures/entity/FlyingPigWings.png");
            ModelFlyingCow2.flyingcow = entityFlyingCow;
            return true;
        }
        return false;
    }

    @Override
    protected boolean method_825(LivingEntity living, int integer, float float4)
    {
        return this.setWoolColorAndRender((EntityFlyingCow) living, integer, float4);
    }
}
