package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelSheepuff1 extends QuadrupedEntityModel
{
    public ModelSheepuff1()
    {
        super(12, 0.0f);
        (this.head = new ModelPart(0, 0)).addCuboid(-3.0f, -4.0f, -4.0f, 6, 6, 6, 0.6f);
        this.head.setPivot(0.0f, 6.0f, -8.0f);
        (this.body = new ModelPart(28, 8)).addCuboid(-4.0f, -10.0f, -7.0f, 8, 16, 6, 1.75f);
        this.body.setPivot(0.0f, 5.0f, 2.0f);
        final float f = 0.5f;
        (this.rightHindLeg = new ModelPart(0, 16)).addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.rightHindLeg.setPivot(-3.0f, 12.0f, 7.0f);
        (this.leftHindLeg = new ModelPart(0, 16)).addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.leftHindLeg.setPivot(3.0f, 12.0f, 7.0f);
        (this.rightFrontLeg = new ModelPart(0, 16)).addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.rightFrontLeg.setPivot(-3.0f, 12.0f, -5.0f);
        (this.leftFrontLeg = new ModelPart(0, 16)).addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.leftFrontLeg.setPivot(3.0f, 12.0f, -5.0f);
    }
}
