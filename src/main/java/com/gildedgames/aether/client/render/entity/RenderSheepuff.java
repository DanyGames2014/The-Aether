package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.entity.animal.EntitySheepuff;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import org.lwjgl.opengl.GL11;

public class RenderSheepuff extends LivingEntityRenderer
{
    private EntityModel wool;
    private EntityModel puffed;

    public RenderSheepuff(final EntityModel modelbase, final EntityModel modelbase1, final EntityModel modelbase2, final float f)
    {
        super(modelbase1, f);
        this.method_815(modelbase);
        this.wool = modelbase;
        this.puffed = modelbase2;
    }

    protected boolean setWoolColorAndRender(final EntitySheepuff entitysheep, final int i, final float f)
    {
        if (i == 0 && !entitysheep.getSheared())
        {
            if (entitysheep.getPuffed())
            {
                this.method_815(this.puffed);
                this.bindTexture("aether:textures/entity/sheepuff_fur.png");
            }
            else
            {
                this.method_815(this.wool);
                this.bindTexture("aether:textures/entity/sheepuff_fur.png");
            }
            final float f2 = entitysheep.method_1394(f);
            final int j = entitysheep.getFleeceColor();
            GL11.glColor3f(f2 * SheepEntity.field_2698[j][0], f2 * SheepEntity.field_2698[j][1], f2 * SheepEntity.field_2698[j][2]);
            return true;
        }
        return false;
    }

    @Override
    protected boolean method_825(final LivingEntity entityliving, final int i, final float f)
    {
        return this.setWoolColorAndRender((EntitySheepuff) entityliving, i, f);
    }
}
