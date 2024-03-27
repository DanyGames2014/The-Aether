package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import org.lwjgl.opengl.GL11;

public class ModelSlider extends EntityModel
{
    public ModelPart head;

    public ModelSlider()
    {
        this(0.0f);
    }

    public ModelSlider(final float f)
    {
        this(f, 0.0f);
    }

    public ModelSlider(final float f, final float f1)
    {
        (this.head = new ModelPart(0, 0)).addCuboid(-8.0f, -16.0f, -8.0f, 16, 16, 16, f);
        this.head.setPivot(0.0f, 0.0f + f1, 0.0f);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        GL11.glPushMatrix();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        this.head.render(f5);
        GL11.glPopMatrix();
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.head.yaw = 0.0f;
        this.head.pitch = 0.0f;
    }
}
