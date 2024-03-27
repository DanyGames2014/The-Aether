package com.gildedgames.aether.client.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import org.lwjgl.opengl.GL11;

public class ModelAechorPlant extends EntityModel
{
    private ModelPart[] petal;
    private ModelPart[] leaf;
    private ModelPart[] stamen;
    private ModelPart[] stamen2;
    private ModelPart[] thorn;
    private ModelPart stem;
    private ModelPart head;
    private static int petals;
    private static int thorns;
    private static int stamens;
    public float sinage;
    public float sinage2;
    public float size;
    private float pie;

    public ModelAechorPlant()
    {
        this(0.0f);
    }

    public ModelAechorPlant(final float f)
    {
        this(f, 0.0f);
    }

    public ModelAechorPlant(final float f, final float f1)
    {
        this.pie = 6.283186f;
        this.size = 1.0f;
        this.petal = new ModelPart[ModelAechorPlant.petals];
        this.leaf = new ModelPart[ModelAechorPlant.petals];
        for (int i = 0; i < ModelAechorPlant.petals; ++i)
        {
            this.petal[i] = new ModelPart(0, 0);
            if (i % 2 == 0)
            {
                (this.petal[i] = new ModelPart(29, 3)).addCuboid(-4.0f, -1.0f, -12.0f, 8, 1, 9, f - 0.25f);
                this.petal[i].setPivot(0.0f, 1.0f + f1, 0.0f);
            }
            else
            {
                this.petal[i].addCuboid(-4.0f, -1.0f, -13.0f, 8, 1, 10, f - 0.125f);
                this.petal[i].setPivot(0.0f, 1.0f + f1, 0.0f);
            }
            (this.leaf[i] = new ModelPart(38, 13)).addCuboid(-2.0f, -1.0f, -9.5f, 4, 1, 8, f - 0.15f);
            this.leaf[i].setPivot(0.0f, 1.0f + f1, 0.0f);
        }
        this.stamen = new ModelPart[ModelAechorPlant.stamens];
        this.stamen2 = new ModelPart[ModelAechorPlant.stamens];
        for (int i = 0; i < ModelAechorPlant.stamens; ++i)
        {
            (this.stamen[i] = new ModelPart(36, 13)).addCuboid(0.0f, -9.0f, -1.5f, 1, 6, 1, f - 0.25f);
            this.stamen[i].setPivot(0.0f, 1.0f + f1, 0.0f);
        }
        for (int i = 0; i < ModelAechorPlant.stamens; ++i)
        {
            (this.stamen2[i] = new ModelPart(32, 15)).addCuboid(0.0f, -10.0f, -1.5f, 1, 1, 1, f + 0.125f);
            this.stamen2[i].setPivot(0.0f, 1.0f + f1, 0.0f);
        }
        (this.head = new ModelPart(0, 12)).addCuboid(-3.0f, -3.0f, -3.0f, 6, 2, 6, f + 0.75f);
        this.head.setPivot(0.0f, 1.0f + f1, 0.0f);
        (this.stem = new ModelPart(24, 13)).addCuboid(-1.0f, 0.0f, -1.0f, 2, 6, 2, f);
        this.stem.setPivot(0.0f, 1.0f + f1, 0.0f);
        this.thorn = new ModelPart[ModelAechorPlant.thorns];
        for (int i = 0; i < ModelAechorPlant.thorns; ++i)
        {
            (this.thorn[i] = new ModelPart(32, 13)).setPivot(0.0f, 1.0f + f1, 0.0f);
        }
        this.thorn[0].addCuboid(-1.75f, 1.25f, -1.0f, 1, 1, 1, f - 0.25f);
        this.thorn[1].addCuboid(-1.0f, 2.25f, 0.75f, 1, 1, 1, f - 0.25f);
        this.thorn[2].addCuboid(0.75f, 1.25f, 0.0f, 1, 1, 1, f - 0.25f);
        this.thorn[3].addCuboid(0.0f, 2.25f, -1.75f, 1, 1, 1, f - 0.25f);
    }

    @Override
    public void render(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.setAngles(f, f1, f2, f3, f4, f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 1.2f, 0.0f);
        GL11.glScalef(this.size, this.size, this.size);
        for (int i = 0; i < ModelAechorPlant.petals; ++i)
        {
            this.petal[i].render(f5);
            this.leaf[i].render(f5);
        }
        for (int i = 0; i < ModelAechorPlant.stamens; ++i)
        {
            this.stamen[i].render(f5);
            this.stamen2[i].render(f5);
        }
        this.head.render(f5);
        this.stem.render(f5);
        for (int i = 0; i < ModelAechorPlant.thorns; ++i)
        {
            this.thorn[i].render(f5);
        }
        GL11.glPopMatrix();
    }

    @Override
    public void setAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        this.head.pitch = 0.0f;
        this.head.yaw = f4 / 57.29578f;
        final float boff = this.sinage2;
        this.stem.yaw = this.head.yaw;
        this.stem.pivotY = boff * 0.5f;
        for (int i = 0; i < ModelAechorPlant.thorns; ++i)
        {
            this.thorn[i].yaw = this.head.yaw;
            this.thorn[i].pivotY = boff * 0.5f;
        }
        for (int i = 0; i < ModelAechorPlant.petals; ++i)
        {
            this.petal[i].pitch = ((i % 2 == 0) ? -0.25f : -0.4125f);
            final ModelPart cuboid = this.petal[i];
            cuboid.pitch += this.sinage;
            this.petal[i].yaw = this.head.yaw;
            final ModelPart cuboid2 = this.petal[i];
            cuboid2.yaw += this.pie / ModelAechorPlant.petals * i;
            this.leaf[i].pitch = ((i % 2 == 0) ? 0.1f : 0.2f);
            final ModelPart cuboid3 = this.leaf[i];
            cuboid3.pitch += this.sinage * 0.75f;
            this.leaf[i].yaw = this.head.yaw + this.pie / ModelAechorPlant.petals / 2.0f;
            final ModelPart cuboid4 = this.leaf[i];
            cuboid4.yaw += this.pie / ModelAechorPlant.petals * i;
            this.petal[i].pivotY = boff;
            this.leaf[i].pivotY = boff;
        }
        for (int i = 0; i < ModelAechorPlant.stamens; ++i)
        {
            this.stamen[i].pitch = 0.2f + i / 15.0f;
            this.stamen[i].yaw = this.head.yaw + 0.1f;
            final ModelPart cuboid5 = this.stamen[i];
            cuboid5.yaw += this.pie / ModelAechorPlant.stamens * i;
            final ModelPart cuboid6 = this.stamen[i];
            cuboid6.pitch += this.sinage * 0.4f;
            this.stamen2[i].pitch = 0.2f + i / 15.0f;
            this.stamen2[i].yaw = this.head.yaw + 0.1f;
            final ModelPart cuboid7 = this.stamen2[i];
            cuboid7.yaw += this.pie / ModelAechorPlant.stamens * i;
            final ModelPart cuboid8 = this.stamen2[i];
            cuboid8.pitch += this.sinage * 0.4f;
            this.stamen[i].pivotY = boff + this.sinage * 2.0f;
            this.stamen2[i].pivotY = boff + this.sinage * 2.0f;
        }
        this.head.pivotY = boff + this.sinage * 2.0f;
    }

    static
    {
        ModelAechorPlant.petals = 10;
        ModelAechorPlant.thorns = 4;
        ModelAechorPlant.stamens = 3;
    }
}
