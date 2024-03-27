package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelValkyrie extends BipedEntityModel
{
    public ModelPart bipedBody2;
    public ModelPart bipedRightArm2;
    public ModelPart bipedLeftArm2;
    public ModelPart wingLeft;
    public ModelPart wingRight;
    public ModelPart[] skirt;
    public ModelPart[] sword;
    public ModelPart[] strand;
    public ModelPart[] halo;
    public static final int swordParts = 5;
    public static final int skirtParts = 6;
    public static final int strandParts = 22;
    public static final int haloParts = 4;
    public float sinage;
    public boolean gonRound;
    public boolean halow;

    public ModelValkyrie()
    {
        this(0.0f);
    }

    public ModelValkyrie(final float f)
    {
        this(f, 0.0f);
    }

    public ModelValkyrie(final float f, final float f1)
    {
        this.leftArmPose = false;
        this.rightArmPose = false;
        this.sneaking = false;
        (this.head = new ModelPart(0, 0)).addCuboid(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        this.head.setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.body = new ModelPart(12, 16)).addCuboid(-3.0f, 0.0f, -1.5f, 6, 12, 3, f);
        this.body.setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.bipedBody2 = new ModelPart(12, 16)).addCuboid(-3.0f, 0.5f, -1.25f, 6, 5, 3, f + 0.75f);
        this.bipedBody2.setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.rightArm = new ModelPart(30, 16)).addCuboid(-3.0f, -1.5f, -1.5f, 3, 12, 3, f);
        this.rightArm.setPivot(-4.0f, 1.5f + f1, 0.0f);
        this.leftArm = new ModelPart(30, 16);
        this.leftArm.mirror = true;
        this.leftArm.addCuboid(-1.0f, -1.5f, -1.5f, 3, 12, 3, f);
        this.leftArm.setPivot(5.0f, 1.5f + f1, 0.0f);
        (this.bipedRightArm2 = new ModelPart(30, 16)).addCuboid(-3.0f, -1.5f, -1.5f, 3, 3, 3, f + 0.75f);
        this.bipedRightArm2.setPivot(-4.0f, 1.5f + f1, 0.0f);
        this.bipedLeftArm2 = new ModelPart(30, 16);
        this.bipedLeftArm2.mirror = true;
        this.bipedLeftArm2.addCuboid(-1.0f, -1.5f, -1.5f, 3, 3, 3, f + 0.75f);
        this.bipedLeftArm2.setPivot(5.0f, 1.5f + f1, 0.0f);
        (this.rightLeg = new ModelPart(0, 16)).addCuboid(-2.0f, 0.0f, -1.5f, 3, 12, 3, f);
        this.rightLeg.setPivot(-1.0f, 12.0f + f1, 0.0f);
        this.leftLeg = new ModelPart(0, 16);
        this.leftLeg.mirror = true;
        this.leftLeg.addCuboid(-2.0f, 0.0f, -1.5f, 3, 12, 3, f);
        this.leftLeg.setPivot(2.0f, 12.0f + f1, 0.0f);
        this.sword = new ModelPart[5];
        (this.sword[0] = new ModelPart(9, 16)).addCuboid(-2.5f, 8.0f, 1.5f, 2, 2, 1, f);
        this.sword[0].setPivot(-4.0f, 1.5f + f1, 0.0f);
        (this.sword[1] = new ModelPart(32, 10)).addCuboid(-3.0f, 6.5f, -2.75f, 3, 5, 1, f + 0.5f);
        this.sword[1].setPivot(-4.0f, 1.5f + f1, 0.0f);
        (this.sword[2] = new ModelPart(42, 18)).addCuboid(-2.0f, 7.5f, -12.5f, 1, 3, 10, f);
        this.sword[2].setPivot(-4.0f, 1.5f + f1, 0.0f);
        (this.sword[3] = new ModelPart(42, 18)).addCuboid(-2.0f, 7.5f, -22.5f, 1, 3, 10, f);
        this.sword[3].setPivot(-4.0f, 1.5f + f1, 0.0f);
        (this.sword[4] = new ModelPart(28, 17)).addCuboid(-2.0f, 8.5f, -23.5f, 1, 1, 1, f);
        this.sword[4].setPivot(-4.0f, 1.5f + f1, 0.0f);
        (this.wingLeft = new ModelPart(24, 31)).addCuboid(0.0f, -4.5f, 0.0f, 19, 8, 1, f);
        this.wingLeft.setPivot(0.5f, 4.5f + f1, 2.625f);
        this.wingRight = new ModelPart(24, 31);
        this.wingRight.mirror = true;
        this.wingRight.addCuboid(-19.0f, -4.5f, 0.0f, 19, 8, 1, f);
        this.wingRight.setPivot(-0.5f, 4.5f + f1, 2.625f);
        this.skirt = new ModelPart[6];
        (this.skirt[0] = new ModelPart(0, 0)).addCuboid(0.0f, 0.0f, -1.0f, 3, 6, 1, f);
        this.skirt[0].setPivot(-3.0f, 9.0f + f1, -1.5f);
        (this.skirt[1] = new ModelPart(0, 0)).addCuboid(0.0f, 0.0f, -1.0f, 3, 6, 1, f);
        this.skirt[1].setPivot(0.0f, 9.0f + f1, -1.5f);
        (this.skirt[2] = new ModelPart(0, 0)).addCuboid(0.0f, 0.0f, 0.0f, 3, 6, 1, f);
        this.skirt[2].setPivot(-3.0f, 9.0f + f1, 1.5f);
        (this.skirt[3] = new ModelPart(0, 0)).addCuboid(0.0f, 0.0f, 0.0f, 3, 6, 1, f);
        this.skirt[3].setPivot(0.0f, 9.0f + f1, 1.5f);
        (this.skirt[4] = new ModelPart(55, 19)).addCuboid(-1.0f, 0.0f, 0.0f, 1, 6, 3, f);
        this.skirt[4].setPivot(-3.0f, 9.0f + f1, -1.5f);
        (this.skirt[5] = new ModelPart(55, 19)).addCuboid(0.0f, 0.0f, 0.0f, 1, 6, 3, f);
        this.skirt[5].setPivot(3.0f, 9.0f + f1, -1.5f);
        this.strand = new ModelPart[22];
        for (int i = 0; i < 22; ++i)
        {
            this.strand[i] = new ModelPart(42 + i % 7, 17);
        }
        this.strand[0].addCuboid(-5.0f, -7.0f, -4.0f, 1, 3, 1, f);
        this.strand[0].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[1].addCuboid(4.0f, -7.0f, -4.0f, 1, 3, 1, f);
        this.strand[1].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[2].addCuboid(-5.0f, -7.0f, -3.0f, 1, 4, 1, f);
        this.strand[2].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[3].addCuboid(4.0f, -7.0f, -3.0f, 1, 4, 1, f);
        this.strand[3].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[4].addCuboid(-5.0f, -7.0f, -2.0f, 1, 4, 1, f);
        this.strand[4].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[5].addCuboid(4.0f, -7.0f, -2.0f, 1, 4, 1, f);
        this.strand[5].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[6].addCuboid(-5.0f, -7.0f, -1.0f, 1, 5, 1, f);
        this.strand[6].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[7].addCuboid(4.0f, -7.0f, -1.0f, 1, 5, 1, f);
        this.strand[7].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[8].addCuboid(-5.0f, -7.0f, 0.0f, 1, 5, 1, f);
        this.strand[8].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[9].addCuboid(4.0f, -7.0f, 0.0f, 1, 5, 1, f);
        this.strand[9].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[10].addCuboid(-5.0f, -7.0f, 1.0f, 1, 6, 1, f);
        this.strand[10].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[11].addCuboid(4.0f, -7.0f, 1.0f, 1, 6, 1, f);
        this.strand[11].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[12].addCuboid(-5.0f, -7.0f, 2.0f, 1, 7, 1, f);
        this.strand[12].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[13].addCuboid(4.0f, -7.0f, 2.0f, 1, 7, 1, f);
        this.strand[13].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[14].addCuboid(-5.0f, -7.0f, 3.0f, 1, 8, 1, f);
        this.strand[14].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[15].addCuboid(4.0f, -7.0f, 3.0f, 1, 8, 1, f);
        this.strand[15].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[16].addCuboid(-4.0f, -7.0f, 4.0f, 1, 9, 1, f);
        this.strand[16].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[17].addCuboid(3.0f, -7.0f, 4.0f, 1, 9, 1, f);
        this.strand[17].setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.strand[18] = new ModelPart(42, 17)).addCuboid(-3.0f, -7.0f, 4.0f, 3, 10, 1, f);
        this.strand[18].setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.strand[19] = new ModelPart(43, 17)).addCuboid(0.0f, -7.0f, 4.0f, 3, 10, 1, f);
        this.strand[19].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[20].addCuboid(-1.0f, -7.0f, -5.0f, 1, 2, 1, f);
        this.strand[20].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.strand[21].addCuboid(0.0f, -7.0f, -5.0f, 1, 3, 1, f);
        this.strand[21].setPivot(0.0f, 0.0f + f1, 0.0f);
        this.halo = new ModelPart[4];
        (this.halo[0] = new ModelPart(43, 9)).addCuboid(-2.5f, -11.0f, -3.5f, 5, 1, 1, f);
        this.halo[0].setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.halo[1] = new ModelPart(43, 9)).addCuboid(-2.5f, -11.0f, 2.5f, 5, 1, 1, f);
        this.halo[1].setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.halo[2] = new ModelPart(42, 11)).addCuboid(-3.5f, -11.0f, -2.5f, 1, 1, 5, f);
        this.halo[2].setPivot(0.0f, 0.0f + f1, 0.0f);
        (this.halo[3] = new ModelPart(42, 11)).addCuboid(2.5f, -11.0f, -2.5f, 1, 1, 5, f);
        this.halo[3].setPivot(0.0f, 0.0f + f1, 0.0f);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.body.render(f5);
        this.rightArm.render(f5);
        this.leftArm.render(f5);
        this.rightLeg.render(f5);
        this.leftLeg.render(f5);
        this.bipedBody2.render(f5);
        this.bipedRightArm2.render(f5);
        this.bipedLeftArm2.render(f5);
        this.wingLeft.render(f5);
        this.wingRight.render(f5);
        for (int i = 0; i < 5; ++i)
        {
            this.sword[i].render(f5);
        }
        for (int i = 0; i < 6; ++i)
        {
            this.skirt[i].render(f5);
        }
        for (int i = 0; i < 22; ++i)
        {
            this.strand[i].render(f5);
        }
        if (this.halow)
        {
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glDisable(3008);
            GL11.glBlendFunc(770, 771);
            for (int i = 0; i < 4; ++i)
            {
                this.halo[i].render(f5);
            }
            GL11.glEnable(3008);
        }
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.head.yaw = f3 / 57.29578f;
        this.head.pitch = f4 / 57.29578f;
        this.rightArm.pitch = MathHelper.cos(f * 0.6662f + 3.141593f) * 2.0f * f1 * 0.5f;
        this.leftArm.pitch = MathHelper.cos(f * 0.6662f) * 2.0f * f1 * 0.5f;
        this.rightArm.roll = 0.05f;
        this.leftArm.roll = -0.05f;
        this.rightLeg.pitch = MathHelper.cos(f * 0.6662f) * 1.4f * f1;
        this.leftLeg.pitch = MathHelper.cos(f * 0.6662f + 3.141593f) * 1.4f * f1;
        this.rightLeg.yaw = 0.0f;
        this.leftLeg.yaw = 0.0f;
        for (int i = 0; i < 22; ++i)
        {
            this.strand[i].yaw = this.head.yaw;
            this.strand[i].pitch = this.head.pitch;
        }
        for (int i = 0; i < 4; ++i)
        {
            this.halo[i].yaw = this.head.yaw;
            this.halo[i].pitch = this.head.pitch;
        }
        if (this.riding)
        {
            final ModelPart field_622 = this.rightArm;
            field_622.pitch -= 0.6283185f;
            final ModelPart field_623 = this.leftArm;
            field_623.pitch -= 0.6283185f;
            this.rightLeg.pitch = -1.256637f;
            this.leftLeg.pitch = -1.256637f;
            this.rightLeg.yaw = 0.3141593f;
            this.leftLeg.yaw = -0.3141593f;
        }
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
            final ModelPart bipedBody2 = this.bipedBody2;
            final ModelPart field_624 = this.body;
            final float n = MathHelper.sin(MathHelper.sqrt(f6) * 3.141593f * 2.0f) * 0.2f;
            field_624.yaw = n;
            bipedBody2.yaw = n;
            final ModelPart field_625 = this.rightArm;
            field_625.yaw += this.body.yaw;
            final ModelPart field_626 = this.leftArm;
            field_626.yaw += this.body.yaw;
            final ModelPart field_627 = this.leftArm;
            field_627.pitch += this.body.yaw;
            f6 = 1.0f - this.handWingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            final float f7 = MathHelper.sin(f6 * 3.141593f);
            final float f8 = MathHelper.sin(this.handWingProgress * 3.141593f) * -(this.head.pitch - 0.7f) * 0.75f;
            final ModelPart field_628 = this.rightArm;
            field_628.pitch -= (float) (f7 * 1.2 + f8);
            final ModelPart field_629 = this.rightArm;
            field_629.yaw += this.body.yaw * 2.0f;
            this.rightArm.roll = MathHelper.sin(this.handWingProgress * 3.141593f) * -0.4f;
        }
        final ModelPart field_630 = this.rightArm;
        field_630.roll += MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        final ModelPart field_631 = this.leftArm;
        field_631.roll -= MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        final ModelPart field_632 = this.rightArm;
        field_632.pitch += MathHelper.sin(f2 * 0.067f) * 0.05f;
        final ModelPart field_633 = this.leftArm;
        field_633.pitch -= MathHelper.sin(f2 * 0.067f) * 0.05f;
        for (int i = 0; i < 5; ++i)
        {
            this.sword[i].roll = this.rightArm.roll;
            this.sword[i].yaw = this.rightArm.yaw;
            this.sword[i].pitch = this.rightArm.pitch;
        }
        this.bipedRightArm2.roll = this.rightArm.roll;
        this.bipedRightArm2.yaw = this.rightArm.yaw;
        this.bipedRightArm2.pitch = this.rightArm.pitch;
        this.bipedLeftArm2.roll = this.leftArm.roll;
        this.bipedLeftArm2.pitch = this.leftArm.pitch;
        this.wingLeft.yaw = -0.2f;
        this.wingRight.yaw = 0.2f;
        this.wingLeft.roll = -0.125f;
        this.wingRight.roll = 0.125f;
        final ModelPart wingLeft = this.wingLeft;
        wingLeft.yaw += (float) (Math.sin((double) this.sinage) / 6.0);
        final ModelPart wingRight = this.wingRight;
        wingRight.yaw -= (float) (Math.sin((double) this.sinage) / 6.0);
        final ModelPart wingLeft2 = this.wingLeft;
        wingLeft2.roll += (float) (Math.cos((double) this.sinage) / (this.gonRound ? 8.0f : 3.0f));
        final ModelPart wingRight2 = this.wingRight;
        wingRight2.roll -= (float) (Math.cos((double) this.sinage) / (this.gonRound ? 8.0f : 3.0f));
        this.skirt[0].pitch = -0.2f;
        this.skirt[1].pitch = -0.2f;
        this.skirt[2].pitch = 0.2f;
        this.skirt[3].pitch = 0.2f;
        this.skirt[4].roll = 0.2f;
        this.skirt[5].roll = -0.2f;
        if (this.leftLeg.pitch < -0.3f)
        {
            final ModelPart cuboid = this.skirt[1];
            cuboid.pitch += this.leftLeg.pitch + 0.3f;
            final ModelPart cuboid2 = this.skirt[2];
            cuboid2.pitch -= this.leftLeg.pitch + 0.3f;
        }
        if (this.leftLeg.pitch > 0.3f)
        {
            final ModelPart cuboid3 = this.skirt[3];
            cuboid3.pitch += this.leftLeg.pitch - 0.3f;
            final ModelPart cuboid4 = this.skirt[0];
            cuboid4.pitch -= this.leftLeg.pitch - 0.3f;
        }
    }
}
