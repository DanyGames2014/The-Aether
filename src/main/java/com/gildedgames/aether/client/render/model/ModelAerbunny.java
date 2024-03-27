package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelAerbunny extends EntityModel
{
    public ModelPart a;
    public ModelPart b;
    public ModelPart b2;
    public ModelPart b3;
    public ModelPart e1;
    public ModelPart e2;
    public ModelPart ff1;
    public ModelPart ff2;
    public ModelPart g;
    public ModelPart g2;
    public ModelPart h;
    public ModelPart h2;
    public float puffiness;

    public ModelAerbunny()
    {
        final byte byte0 = 16;
        (this.a = new ModelPart(0, 0)).addCuboid(-2.0f, -1.0f, -4.0f, 4, 4, 6, 0.0f);
        this.a.setPivot(0.0f, (float) (-1 + byte0), -4.0f);
        (this.g = new ModelPart(14, 0)).addCuboid(-2.0f, -5.0f, -3.0f, 1, 4, 2, 0.0f);
        this.g.setPivot(0.0f, (float) (-1 + byte0), -4.0f);
        (this.g2 = new ModelPart(14, 0)).addCuboid(1.0f, -5.0f, -3.0f, 1, 4, 2, 0.0f);
        this.g2.setPivot(0.0f, (float) (-1 + byte0), -4.0f);
        (this.h = new ModelPart(20, 0)).addCuboid(-4.0f, 0.0f, -3.0f, 2, 3, 2, 0.0f);
        this.h.setPivot(0.0f, (float) (-1 + byte0), -4.0f);
        (this.h2 = new ModelPart(20, 0)).addCuboid(2.0f, 0.0f, -3.0f, 2, 3, 2, 0.0f);
        this.h2.setPivot(0.0f, (float) (-1 + byte0), -4.0f);
        (this.b = new ModelPart(0, 10)).addCuboid(-3.0f, -4.0f, -3.0f, 6, 8, 6, 0.0f);
        this.b.setPivot(0.0f, (float) (0 + byte0), 0.0f);
        (this.b2 = new ModelPart(0, 24)).addCuboid(-2.0f, 4.0f, -2.0f, 4, 3, 4, 0.0f);
        this.b2.setPivot(0.0f, (float) (0 + byte0), 0.0f);
        (this.b3 = new ModelPart(29, 0)).addCuboid(-3.5f, -3.5f, -3.5f, 7, 7, 7, 0.0f);
        this.b3.setPivot(0.0f, 0.0f, 0.0f);
        (this.e1 = new ModelPart(24, 16)).addCuboid(-2.0f, 0.0f, -1.0f, 2, 2, 2);
        this.e1.setPivot(3.0f, (float) (3 + byte0), -3.0f);
        (this.e2 = new ModelPart(24, 16)).addCuboid(0.0f, 0.0f, -1.0f, 2, 2, 2);
        this.e2.setPivot(-3.0f, (float) (3 + byte0), -3.0f);
        (this.ff1 = new ModelPart(16, 24)).addCuboid(-2.0f, 0.0f, -4.0f, 2, 2, 4);
        this.ff1.setPivot(3.0f, (float) (3 + byte0), 4.0f);
        (this.ff2 = new ModelPart(16, 24)).addCuboid(0.0f, 0.0f, -4.0f, 2, 2, 4);
        this.ff2.setPivot(-3.0f, (float) (3 + byte0), 4.0f);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.a.render(f5);
        this.g.render(f5);
        this.g2.render(f5);
        this.h.render(f5);
        this.h2.render(f5);
        this.b.render(f5);
        this.b2.render(f5);
        GL11.glPushMatrix();
        final float a = 1.0f + this.puffiness * 0.5f;
        GL11.glTranslatef(0.0f, 1.0f, 0.0f);
        GL11.glScalef(a, a, a);
        this.b3.render(f5);
        GL11.glPopMatrix();
        this.e1.render(f5);
        this.e2.render(f5);
        this.ff1.render(f5);
        this.ff2.render(f5);
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.a.pitch = -(f4 / 57.29578f);
        this.a.yaw = f3 / 57.29578f;
        this.g.pitch = this.a.pitch;
        this.g.yaw = this.a.yaw;
        this.g2.pitch = this.a.pitch;
        this.g2.yaw = this.a.yaw;
        this.h.pitch = this.a.pitch;
        this.h.yaw = this.a.yaw;
        this.h2.pitch = this.a.pitch;
        this.h2.yaw = this.a.yaw;
        this.b.pitch = 1.570796f;
        this.b2.pitch = 1.570796f;
        this.b3.pitch = 1.570796f;
        this.e1.pitch = MathHelper.cos(f * 0.6662f) * 1.0f * f1;
        this.ff1.pitch = MathHelper.cos(f * 0.6662f + 3.141593f) * 1.2f * f1;
        this.e2.pitch = MathHelper.cos(f * 0.6662f) * 1.0f * f1;
        this.ff2.pitch = MathHelper.cos(f * 0.6662f + 3.141593f) * 1.2f * f1;
    }
}
