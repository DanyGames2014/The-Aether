package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import org.lwjgl.opengl.GL11;

public class ModelHomeShot extends EntityModel
{
    public ModelPart[] head;
    public float[] sinage;
    private static final float sponge = 57.295773f;

    public ModelHomeShot()
    {
        this(0.0f);
    }

    public ModelHomeShot(final float f)
    {
        this(f, 0.0f);
    }

    public ModelHomeShot(final float f, final float f1)
    {
        this.sinage = new float[3];
        (this.head = new ModelPart[3])[0] = new ModelPart(0, 0);
        this.head[1] = new ModelPart(32, 0);
        this.head[2] = new ModelPart(0, 16);
        for (int i = 0; i < 3; ++i)
        {
            this.head[i].addCuboid(-4.0f, -4.0f, -4.0f, 8, 8, 8, f);
            this.head[i].setPivot(0.0f, 0.0f + f1, 0.0f);
        }
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        GL11.glTranslatef(0.0f, 0.75f, 0.0f);
        GL11.glEnable(2977);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPushMatrix();
        GL11.glRotatef(this.sinage[0] * 57.295773f, 1.0f, 0.0f, 0.0f);
        this.head[0].render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glRotatef(this.sinage[1] * 57.295773f, 0.0f, 1.0f, 0.0f);
        this.head[1].render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glRotatef(this.sinage[2] * 57.295773f, 0.0f, 0.0f, 1.0f);
        this.head[2].render(f5);
        GL11.glPopMatrix();
        GL11.glEnable(3008);
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        for (int i = 0; i < 3; ++i)
        {
            this.head[i].yaw = f3 / 57.29578f;
            this.head[i].pitch = f4 / 57.29578f;
        }
    }
}
