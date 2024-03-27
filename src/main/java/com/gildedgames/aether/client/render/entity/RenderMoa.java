package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.entity.animal.EntityMoa;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderMoa extends LivingEntityRenderer
{
    public RenderMoa(final EntityModel modelbase, final float f)
    {
        super(modelbase, f);
    }

    public void renderChicken(final EntityMoa entitymoa, final double d, final double d1, final double d2, final float f, final float f1)
    {
        super.render(entitymoa, d, d1, d2, f, f1);
    }

    protected float getWingRotation(final EntityMoa entitymoa, final float f)
    {
        final float f2 = entitymoa.field_756_e + (entitymoa.field_752_b - entitymoa.field_756_e) * f;
        final float f3 = entitymoa.field_757_d + (entitymoa.destPos - entitymoa.field_757_d) * f;
        return (MathHelper.sin(f2) + 1.0f) * f3;
    }

    @Override
    protected float method_828(final LivingEntity entityliving, final float f)
    {
        return this.getWingRotation((EntityMoa) entityliving, f);
    }

    @Override
    public void render(final LivingEntity entity, final double x, final double y, final double z, final float f, final float f1)
    {
        this.renderChicken((EntityMoa) entity, x, y, z, f, f1);
    }

    @Override
    public void render(final Entity entity, final double x, final double y, final double z, final float f, final float f1)
    {
        this.renderChicken((EntityMoa) entity, x, y, z, f, f1);
    }

    protected void scalemoa()
    {
        GL11.glScalef(1.8f, 1.8f, 1.8f);
    }

    @Override
    protected void method_823(final LivingEntity entityliving, final float f)
    {
        if (entityliving instanceof EntityMoa && ((EntityMoa) entityliving).baby)
        {
            return;
        }
        this.scalemoa();
    }
}
