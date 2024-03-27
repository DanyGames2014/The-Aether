package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;

public class ModelAerwhale extends EntityModel
{
    ModelPart body;
    ModelPart body2;
    ModelPart body3;
    ModelPart fin1;
    ModelPart fin2;
    ModelPart fin3;
    ModelPart fin4;

    public ModelAerwhale()
    {
        (this.body2 = new ModelPart(0, 0)).addCuboid(-2.5f, -2.5f, -2.5f, 5, 5, 5);
        (this.body3 = new ModelPart(0, 10)).addCuboid(-1.5f, -1.5f, 2.5f, 3, 3, 4);
        (this.fin1 = new ModelPart(0, 17)).addCuboid(-7.5f, -0.5f, 2.5f, 8, 1, 4);
        (this.fin2 = new ModelPart(0, 17)).addCuboid(-0.5f, -0.5f, 2.5f, 8, 1, 4);
        (this.fin3 = new ModelPart(0, 22)).addCuboid(-7.5f, 1.5f, -6.5f, 4, 1, 2);
        (this.fin4 = new ModelPart(0, 22)).addCuboid(3.5f, 1.5f, -6.5f, 4, 1, 2);
        (this.body = new ModelPart(20, 0)).addCuboid(-3.5f, -3.5f, -12.5f, 7, 6, 10);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.body.render(f5);
        this.body2.render(f5);
        this.body3.render(f5);
        this.fin1.render(f5);
        this.fin2.render(f5);
        this.fin3.render(f5);
        this.fin4.render(f5);
    }
}
