package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.client.render.model.ModelAerbunny;
import com.gildedgames.aether.entity.animal.EntityAerbunny;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public class RenderAerbunny extends LivingEntityRenderer
{
    public ModelAerbunny mb;

    public RenderAerbunny(final EntityModel modelbase, final float f)
    {
        super(modelbase, f);
        this.mb = (ModelAerbunny) modelbase;
    }

    protected void rotAerbunny(final EntityAerbunny entitybunny)
    {
        if (!entitybunny.field_1623 && entitybunny.field_1595 == null)
        {
            if (entitybunny.velocityY > 0.5)
            {
                GL11.glRotatef(15.0f, -1.0f, 0.0f, 0.0f);
            }
            else if (entitybunny.velocityY < -0.5)
            {
                GL11.glRotatef(-15.0f, -1.0f, 0.0f, 0.0f);
            }
            else
            {
                GL11.glRotatef((float) (entitybunny.velocityY * 30.0), -1.0f, 0.0f, 0.0f);
            }
        }
        this.mb.puffiness = entitybunny.puffiness;
    }

    @Override
    protected void method_823(final LivingEntity entityliving, final float f)
    {
        this.rotAerbunny((EntityAerbunny) entityliving);
    }
}
