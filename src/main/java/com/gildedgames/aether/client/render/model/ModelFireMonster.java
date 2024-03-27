package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFireMonster extends BipedEntityModel
{
    public ModelPart bipedBody2;
    public ModelPart bipedBody3;
    public ModelPart bipedBody4;
    public ModelPart bipedRightArm2;
    public ModelPart bipedLeftArm2;
    public ModelPart bipedRightArm3;
    public ModelPart bipedLeftArm3;

    public ModelFireMonster()
    {
        this(0.0f);
    }

    public ModelFireMonster(final float f)
    {
        this(f, 0.0f);
    }

    public ModelFireMonster(final float f, final float f1)
    {
        this.leftArmPose = false;
        this.rightArmPose = false;
        this.sneaking = false;
        (this.head = new ModelPart(0, 0)).addCuboid(-4.0f, -8.0f, -3.0f, 8, 5, 7, f);
        this.head.setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.hat = new ModelPart(32, 0)).addCuboid(-4.0f, -3.0f, -4.0f, 8, 3, 8, f);
        this.hat.setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.body = new ModelPart(0, 12)).addCuboid(-5.0f, 0.0f, -2.5f, 10, 6, 5, f);
        this.body.setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.bipedBody2 = new ModelPart(0, 23)).addCuboid(-4.5f, 6.0f, -2.0f, 9, 5, 4, f);
        this.bipedBody2.setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.bipedBody3 = new ModelPart(30, 27)).addCuboid(-4.5f, 11.0f, -2.0f, 5, 1, 4, f + 0.5f);
        this.bipedBody3.setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.bipedBody4 = new ModelPart(30, 27)).addCuboid(-0.5f, 11.0f, -2.0f, 5, 1, 4, f + 0.5f);
        this.bipedBody4.setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.rightArm = new ModelPart(30, 11)).addCuboid(-2.5f, -2.5f, -2.5f, 5, 5, 5, f + 0.5f);
        this.rightArm.setPivot(-8.0f, 2.0f + f1, 0.0f);
        (this.bipedRightArm2 = new ModelPart(30, 11)).addCuboid(-2.5f, 2.5f, -2.5f, 5, 10, 5, f);
        this.bipedRightArm2.setPivot(-8.0f, 2.0f + f1, 0.0f);
        (this.bipedRightArm3 = new ModelPart(30, 26)).addCuboid(-2.5f, 7.5f, -2.5f, 5, 1, 5, f + 0.25f);
        this.bipedRightArm3.setPivot(-8.0f, 2.0f + f1, 0.0f);
        this.leftArm = new ModelPart(30, 11);
        this.leftArm.mirror = true;
        this.leftArm.addCuboid(-2.5f, -2.5f, -2.5f, 5, 5, 5, f + 0.5f);
        this.leftArm.setPivot(8.0f, 2.0f + f1, 0.0f);
        this.bipedLeftArm2 = new ModelPart(30, 11);
        this.bipedLeftArm2.mirror = true;
        this.bipedLeftArm2.addCuboid(-2.5f, 2.5f, -2.5f, 5, 10, 5, f);
        this.bipedLeftArm2.setPivot(8.0f, 2.0f + f1, 0.0f);
        this.bipedLeftArm3 = new ModelPart(30, 26);
        this.bipedLeftArm3.mirror = true;
        this.bipedLeftArm3.addCuboid(-2.5f, 7.5f, -2.5f, 5, 1, 5, f + 0.25f);
        this.bipedLeftArm3.setPivot(8.0f, 2.0f + f1, 0.0f);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glScalef(2.25f, 2.25f, 2.25f);
        GL11.glTranslatef(0.0f, -0.25f, 0.0f);
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.hat.render(f5);
        this.body.render(f5);
        this.bipedBody2.render(f5);
        this.bipedBody3.render(f5);
        this.bipedBody4.render(f5);
        this.rightArm.render(f5);
        this.bipedRightArm2.render(f5);
        this.bipedRightArm3.render(f5);
        this.leftArm.render(f5);
        this.bipedLeftArm2.render(f5);
        this.bipedLeftArm3.render(f5);
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.head.yaw = f3 / 57.29578f;
        this.head.pitch = f4 / 57.29578f;
        this.hat.yaw = this.head.yaw;
        this.hat.pitch = this.head.pitch;
        this.rightArm.pitch = 0.0f;
        this.leftArm.pitch = 0.0f;
        this.rightArm.roll = 0.0f;
        this.leftArm.roll = 0.0f;
        if (this.leftArmPose)
        {
            this.leftArm.pitch = this.leftArm.pitch * 0.5f - 0.3141593f;
        }
        if (this.rightArmPose)
        {
            this.rightArm.pitch = this.rightArm.pitch * 0.5f - 0.3141593f;
        }
        this.rightArm.yaw = 0.0f;
        this.leftArm.yaw = 0.0f;
        if (this.handWingProgress > -9990.0f)
        {
            float f6 = this.handWingProgress;
            this.body.yaw = MathHelper.sin(MathHelper.sqrt(f6) * 3.141593f * 2.0f) * 0.2f;
            final ModelPart field_622 = this.rightArm;
            field_622.yaw += this.body.yaw;
            final ModelPart field_623 = this.leftArm;
            field_623.yaw += this.body.yaw;
            final ModelPart field_624 = this.leftArm;
            field_624.pitch += this.body.yaw;
            f6 = 1.0f - this.handWingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            final float f7 = MathHelper.sin(f6 * 3.141593f);
            final float f8 = MathHelper.sin(this.handWingProgress * 3.141593f) * -(this.head.pitch - 0.7f) * 0.75f;
            final ModelPart field_625 = this.rightArm;
            field_625.pitch -= (float) (f7 * 1.2 + f8);
            final ModelPart field_626 = this.rightArm;
            field_626.yaw += this.body.yaw * 2.0f;
            this.rightArm.roll = MathHelper.sin(this.handWingProgress * 3.141593f) * -0.4f;
        }
        final ModelPart field_627 = this.rightArm;
        field_627.roll += MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        final ModelPart field_628 = this.leftArm;
        field_628.roll -= MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        final ModelPart field_629 = this.rightArm;
        field_629.pitch += MathHelper.sin(f2 * 0.067f) * 0.05f;
        final ModelPart field_630 = this.leftArm;
        field_630.pitch -= MathHelper.sin(f2 * 0.067f) * 0.05f;
        final ModelPart bipedBody4 = this.bipedBody4;
        final ModelPart bipedBody5 = this.bipedBody3;
        final ModelPart bipedBody6 = this.bipedBody2;
        final float pitch = this.body.pitch;
        bipedBody6.pitch = pitch;
        bipedBody5.pitch = pitch;
        bipedBody4.pitch = pitch;
        final ModelPart bipedBody7 = this.bipedBody4;
        final ModelPart bipedBody8 = this.bipedBody3;
        final ModelPart bipedBody9 = this.bipedBody2;
        final float yaw = this.body.yaw;
        bipedBody9.yaw = yaw;
        bipedBody8.yaw = yaw;
        bipedBody7.yaw = yaw;
        final ModelPart bipedLeftArm3 = this.bipedLeftArm3;
        final ModelPart bipedLeftArm4 = this.bipedLeftArm2;
        final float pitch2 = this.leftArm.pitch;
        bipedLeftArm4.pitch = pitch2;
        bipedLeftArm3.pitch = pitch2;
        final ModelPart bipedLeftArm5 = this.bipedLeftArm3;
        final ModelPart bipedLeftArm6 = this.bipedLeftArm2;
        final float yaw2 = this.leftArm.yaw;
        bipedLeftArm6.yaw = yaw2;
        bipedLeftArm5.yaw = yaw2;
        final ModelPart bipedLeftArm7 = this.bipedLeftArm3;
        final ModelPart bipedLeftArm8 = this.bipedLeftArm2;
        final float roll = this.leftArm.roll;
        bipedLeftArm8.roll = roll;
        bipedLeftArm7.roll = roll;
        final ModelPart bipedRightArm3 = this.bipedRightArm3;
        final ModelPart bipedRightArm4 = this.bipedRightArm2;
        final float pitch3 = this.rightArm.pitch;
        bipedRightArm4.pitch = pitch3;
        bipedRightArm3.pitch = pitch3;
        final ModelPart bipedRightArm5 = this.bipedRightArm3;
        final ModelPart bipedRightArm6 = this.bipedRightArm2;
        final float yaw3 = this.rightArm.yaw;
        bipedRightArm6.yaw = yaw3;
        bipedRightArm5.yaw = yaw3;
        final ModelPart bipedRightArm7 = this.bipedRightArm3;
        final ModelPart bipedRightArm8 = this.bipedRightArm2;
        final float roll2 = this.rightArm.roll;
        bipedRightArm8.roll = roll2;
        bipedRightArm7.roll = roll2;
    }
}
