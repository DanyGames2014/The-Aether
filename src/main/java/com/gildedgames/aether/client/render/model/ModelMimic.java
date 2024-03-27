package com.gildedgames.aether.client.render.model;

import com.gildedgames.aether.entity.mobs.EntityMimic;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;

public class ModelMimic extends EntityModel
{
    ModelPart box;
    ModelPart boxLid;
    ModelPart leftLeg;
    ModelPart rightLeg;

    public ModelMimic()
    {
        (this.box = new ModelPart(0, 0)).addCuboid(-8.0f, 0.0f, -8.0f, 16, 10, 16);
        this.box.setPivot(0.0f, -24.0f, 0.0f);
        (this.boxLid = new ModelPart(16, 10)).addCuboid(0.0f, 0.0f, 0.0f, 16, 6, 16);
        this.boxLid.setPivot(-8.0f, -24.0f, 8.0f);
        (this.leftLeg = new ModelPart(0, 0)).addCuboid(-3.0f, 0.0f, -3.0f, 6, 15, 6);
        this.leftLeg.setPivot(-4.0f, -15.0f, 0.0f);
        (this.rightLeg = new ModelPart(0, 0)).addCuboid(-3.0f, 0.0f, -3.0f, 6, 15, 6);
        this.rightLeg.setPivot(4.0f, -15.0f, 0.0f);
    }

    public void render1(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final EntityMimic mimic)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.boxLid.pitch = 3.1415927f - mimic.mouth;
        this.rightLeg.pitch = mimic.legs;
        this.leftLeg.pitch = -mimic.legs;
        this.box.render(f5);
    }

    public void render2(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final EntityMimic mimic)
    {
        this.boxLid.render(f5);
        this.leftLeg.render(f5);
        this.rightLeg.render(f5);
    }
}
