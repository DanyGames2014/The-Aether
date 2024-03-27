package com.gildedgames.aether.client.render.model;

import com.gildedgames.aether.entity.animal.EntityPhyg;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;

public class ModelFlyingPig2 extends EntityModel
{
    private ModelPart leftWingInner;
    private ModelPart leftWingOuter;
    private ModelPart rightWingInner;
    private ModelPart rightWingOuter;
    public static EntityPhyg pig;

    public ModelFlyingPig2()
    {
        this.leftWingInner = new ModelPart(0, 0);
        this.leftWingOuter = new ModelPart(20, 0);
        this.rightWingInner = new ModelPart(0, 0);
        this.rightWingOuter = new ModelPart(40, 0);
        this.leftWingInner.addCuboid(-1.0f, -8.0f, -4.0f, 2, 16, 8, 0.0f);
        this.leftWingOuter.addCuboid(-1.0f, -8.0f, -4.0f, 2, 16, 8, 0.0f);
        this.rightWingInner.addCuboid(-1.0f, -8.0f, -4.0f, 2, 16, 8, 0.0f);
        this.rightWingOuter.addCuboid(-1.0f, -8.0f, -4.0f, 2, 16, 8, 0.0f);
        this.rightWingOuter.yaw = 3.1415927f;
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        final float wingBend = -(float) Math.acos((double) ModelFlyingPig2.pig.wingFold);
        float x = 32.0f * ModelFlyingPig2.pig.wingFold / 4.0f;
        final float y = -32.0f * (float) Math.sqrt((double) (1.0f - ModelFlyingPig2.pig.wingFold * ModelFlyingPig2.pig.wingFold)) / 4.0f;
        final float z = 0.0f;
        float x2 = x * (float) Math.cos((double) ModelFlyingPig2.pig.wingAngle) - y * (float) Math.sin((double) ModelFlyingPig2.pig.wingAngle);
        float y2 = x * (float) Math.sin((double) ModelFlyingPig2.pig.wingAngle) + y * (float) Math.cos((double) ModelFlyingPig2.pig.wingAngle);
        this.leftWingInner.setPivot(4.0f + x2, y2 + 12.0f, z);
        this.rightWingInner.setPivot(-4.0f - x2, y2 + 12.0f, z);
        x *= 3.0f;
        x2 = x * (float) Math.cos((double) ModelFlyingPig2.pig.wingAngle) - y * (float) Math.sin((double) ModelFlyingPig2.pig.wingAngle);
        y2 = x * (float) Math.sin((double) ModelFlyingPig2.pig.wingAngle) + y * (float) Math.cos((double) ModelFlyingPig2.pig.wingAngle);
        this.leftWingOuter.setPivot(4.0f + x2, y2 + 12.0f, z);
        this.rightWingOuter.setPivot(-4.0f - x2, y2 + 12.0f, z);
        this.leftWingInner.roll = ModelFlyingPig2.pig.wingAngle + wingBend + 1.5707964f;
        this.leftWingOuter.roll = ModelFlyingPig2.pig.wingAngle - wingBend + 1.5707964f;
        this.rightWingInner.roll = -(ModelFlyingPig2.pig.wingAngle + wingBend - 1.5707964f);
        this.rightWingOuter.roll = -(ModelFlyingPig2.pig.wingAngle - wingBend + 1.5707964f);
        this.leftWingOuter.render(f5);
        this.leftWingInner.render(f5);
        this.rightWingOuter.render(f5);
        this.rightWingInner.render(f5);
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
    }
}
