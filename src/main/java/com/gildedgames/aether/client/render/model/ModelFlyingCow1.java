package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelFlyingCow1 extends QuadrupedEntityModel
{
    ModelPart udders;
    ModelPart horn1;
    ModelPart horn2;

    public ModelFlyingCow1()
    {
        super(12, 0.0f);
        (this.head = new ModelPart(0, 0)).addCuboid(-4.0f, -4.0f, -6.0f, 8, 8, 6, 0.0f);
        this.head.setPivot(0.0f, 4.0f, -8.0f);
        (this.horn1 = new ModelPart(22, 0)).addCuboid(-4.0f, -5.0f, -4.0f, 1, 3, 1, 0.0f);
        this.horn1.setPivot(0.0f, 3.0f, -7.0f);
        (this.horn2 = new ModelPart(22, 0)).addCuboid(3.0f, -5.0f, -4.0f, 1, 3, 1, 0.0f);
        this.horn2.setPivot(0.0f, 3.0f, -7.0f);
        (this.udders = new ModelPart(52, 0)).addCuboid(-2.0f, -3.0f, 0.0f, 4, 6, 2, 0.0f);
        this.udders.setPivot(0.0f, 14.0f, 6.0f);
        this.udders.pitch = 1.570796f;
        (this.body = new ModelPart(18, 4)).addCuboid(-6.0f, -10.0f, -7.0f, 12, 18, 10, 0.0f);
        this.body.setPivot(0.0f, 5.0f, 2.0f);
        final ModelPart cuboid3 = this.rightHindLeg;
        --cuboid3.pivotX;
        final ModelPart cuboid4 = this.leftHindLeg;
        ++cuboid4.pivotX;
        final ModelPart cuboid5 = this.rightHindLeg;
        cuboid5.pivotZ += 0.0f;
        final ModelPart cuboid6 = this.leftHindLeg;
        cuboid6.pivotZ += 0.0f;
        final ModelPart cuboid7 = this.rightFrontLeg;
        --cuboid7.pivotX;
        final ModelPart cuboid8 = this.leftFrontLeg;
        ++cuboid8.pivotX;
        final ModelPart cuboid9 = this.rightFrontLeg;
        --cuboid9.pivotZ;
        final ModelPart cuboid10 = this.leftFrontLeg;
        --cuboid10.pivotZ;
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        super.render(f, f1, f2, f3, f4, f5);
        this.horn1.render(f5);
        this.horn2.render(f5);
        this.udders.render(f5);
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        super.setAngles(f, f1, f2, f3, f4, f5);
        this.horn1.yaw = this.head.yaw;
        this.horn1.pitch = this.head.pitch;
        this.horn2.yaw = this.head.yaw;
        this.horn2.pitch = this.head.pitch;
    }
}
