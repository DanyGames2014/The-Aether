package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelSheepuff2 extends QuadrupedEntityModel
{
    public ModelSheepuff2()
    {
        super(12, 0.0f);
        (this.head = new ModelPart(0, 0)).addCuboid(-3.0f, -4.0f, -6.0f, 6, 6, 8, 0.0f);
        this.head.setPivot(0.0f, 6.0f, -8.0f);
        (this.body = new ModelPart(28, 8)).addCuboid(-4.0f, -10.0f, -7.0f, 8, 16, 6, 0.0f);
        this.body.setPivot(0.0f, 5.0f, 2.0f);
    }
}
