package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;

public class ModelZephyr extends EntityModel
{
    ModelPart body;

    public ModelZephyr()
    {
        final byte byte0 = -16;
        (this.body = new ModelPart(0, 0)).addCuboid(-8.0f, -4.0f, -8.0f, 10, 7, 12);
        final ModelPart body = this.body;
        body.pivotY += 24 + byte0;
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.body.render(f5);
    }
}
