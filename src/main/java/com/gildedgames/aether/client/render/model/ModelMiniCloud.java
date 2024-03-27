package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;

public class ModelMiniCloud extends EntityModel
{
    public ModelPart[] head;

    public ModelMiniCloud()
    {
        this(0.0f);
    }

    public ModelMiniCloud(final float f)
    {
        this(f, 0.0f);
    }

    public ModelMiniCloud(final float f, final float f1)
    {
        (this.head = new ModelPart[5])[0] = new ModelPart(0, 0);
        this.head[1] = new ModelPart(36, 0);
        this.head[2] = new ModelPart(36, 0);
        this.head[3] = new ModelPart(36, 8);
        this.head[4] = new ModelPart(36, 8);
        this.head[0].addCuboid(-4.5f, -4.5f, -4.5f, 9, 9, 9, f);
        this.head[0].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.head[1].addCuboid(-3.5f, -3.5f, -5.5f, 7, 7, 1, f);
        this.head[1].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.head[2].addCuboid(-3.5f, -3.5f, 4.5f, 7, 7, 1, f);
        this.head[2].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.head[3].addCuboid(-5.5f, -3.5f, -3.5f, 1, 7, 7, f);
        this.head[3].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.head[4].addCuboid(4.5f, -3.5f, -3.5f, 1, 7, 7, f);
        this.head[4].setPivot(0.0f, 0.0f + f1, 0.0f);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        for (int i = 0; i < 5; ++i)
        {
            this.head[i].render(f5);
        }
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        for (int i = 0; i < 5; ++i)
        {
            this.head[i].yaw = f3 / 57.29578f;
            this.head[i].pitch = f4 / 57.29578f;
        }
    }
}
