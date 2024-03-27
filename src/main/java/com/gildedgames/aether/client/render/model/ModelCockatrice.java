package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class ModelCockatrice extends EntityModel
{
    public ModelPart head;
    public ModelPart body;
    public ModelPart legs;
    public ModelPart legs2;
    public ModelPart wings;
    public ModelPart wings2;
    public ModelPart jaw;
    public ModelPart neck;
    public ModelPart feather1;
    public ModelPart feather2;
    public ModelPart feather3;
    public Random random;

    public ModelCockatrice()
    {
        final byte byte0 = 16;
        this.random = new Random();
        (this.head = new ModelPart(0, 13)).addCuboid(-2.0f, -4.0f, -6.0f, 4, 4, 8, 0.0f);
        this.head.setPivot(0.0f, (float) (-8 + byte0), -4.0f);
        (this.jaw = new ModelPart(24, 13)).addCuboid(-2.0f, -1.0f, -6.0f, 4, 1, 8, -0.1f);
        this.jaw.setPivot(0.0f, (float) (-8 + byte0), -4.0f);
        (this.body = new ModelPart(0, 0)).addCuboid(-3.0f, -3.0f, 0.0f, 6, 8, 5, 0.0f);
        this.body.setPivot(0.0f, (float) (0 + byte0), 0.0f);
        (this.legs = new ModelPart(22, 0)).addCuboid(-1.0f, -1.0f, -1.0f, 2, 9, 2);
        this.legs.setPivot(-2.0f, (float) (0 + byte0), 1.0f);
        (this.legs2 = new ModelPart(22, 0)).addCuboid(-1.0f, -1.0f, -1.0f, 2, 9, 2);
        this.legs2.setPivot(2.0f, (float) (0 + byte0), 1.0f);
        (this.wings = new ModelPart(52, 0)).addCuboid(-1.0f, -0.0f, -1.0f, 1, 8, 4);
        this.wings.setPivot(-3.0f, (float) (-4 + byte0), 0.0f);
        (this.wings2 = new ModelPart(52, 0)).addCuboid(0.0f, -0.0f, -1.0f, 1, 8, 4);
        this.wings2.setPivot(3.0f, (float) (-4 + byte0), 0.0f);
        (this.neck = new ModelPart(44, 0)).addCuboid(-1.0f, -6.0f, -1.0f, 2, 6, 2);
        this.neck.setPivot(0.0f, (float) (-2 + byte0), -4.0f);
        (this.feather1 = new ModelPart(30, 0)).addCuboid(-1.0f, -5.0f, 5.0f, 2, 1, 5, -0.3f);
        this.feather1.setPivot(0.0f, (float) (1 + byte0), 1.0f);
        (this.feather2 = new ModelPart(30, 0)).addCuboid(-1.0f, -5.0f, 5.0f, 2, 1, 5, -0.3f);
        this.feather2.setPivot(0.0f, (float) (1 + byte0), 1.0f);
        (this.feather3 = new ModelPart(30, 0)).addCuboid(-1.0f, -5.0f, 5.0f, 2, 1, 5, -0.3f);
        this.feather3.setPivot(0.0f, (float) (1 + byte0), 1.0f);
        final ModelPart feather1 = this.feather1;
        feather1.pivotY += 0.5f;
        final ModelPart feather2 = this.feather2;
        feather2.pivotY += 0.5f;
        final ModelPart feather3 = this.feather3;
        feather3.pivotY += 0.5f;
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.jaw.render(f5);
        this.body.render(f5);
        this.legs.render(f5);
        this.legs2.render(f5);
        this.wings.render(f5);
        this.wings2.render(f5);
        this.neck.render(f5);
        this.feather1.render(f5);
        this.feather2.render(f5);
        this.feather3.render(f5);
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        final float f6 = 3.141593f;
        this.head.pitch = f4 / 57.29578f;
        this.head.yaw = f3 / 57.29578f;
        this.jaw.pitch = this.head.pitch;
        this.jaw.yaw = this.head.yaw;
        this.body.pitch = 1.570796f;
        this.legs.pitch = MathHelper.cos(f * 0.6662f) * 1.4f * f1;
        this.legs2.pitch = MathHelper.cos(f * 0.6662f + 3.141593f) * 1.4f * f1;
        if (f2 > 0.001f)
        {
            this.wings.pivotZ = -1.0f;
            this.wings2.pivotZ = -1.0f;
            this.wings.pivotY = 12.0f;
            this.wings2.pivotY = 12.0f;
            this.wings.pitch = 0.0f;
            this.wings2.pitch = 0.0f;
            this.wings.roll = f2;
            this.wings2.roll = -f2;
            this.legs.pitch = 0.6f;
            this.legs2.pitch = 0.6f;
        }
        else
        {
            this.wings.pivotZ = -3.0f;
            this.wings2.pivotZ = -3.0f;
            this.wings.pivotY = 14.0f;
            this.wings2.pivotY = 14.0f;
            this.wings.pitch = f6 / 2.0f;
            this.wings2.pitch = f6 / 2.0f;
            this.wings.roll = 0.0f;
            this.wings2.roll = 0.0f;
        }
        this.feather1.yaw = -0.375f;
        this.feather2.yaw = 0.0f;
        this.feather3.yaw = 0.375f;
        this.feather1.pitch = 0.25f;
        this.feather2.pitch = 0.25f;
        this.feather3.pitch = 0.25f;
        this.neck.pitch = 0.0f;
        this.neck.yaw = this.head.yaw;
        final ModelPart jaw = this.jaw;
        jaw.pitch += 0.35f;
    }
}
