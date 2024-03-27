package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.client.render.model.ModelAechorPlant;
import com.gildedgames.aether.entity.animal.EntityAechorPlant;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public class RenderAechorPlant extends LivingEntityRenderer
{
    public ModelAechorPlant xd;

    public RenderAechorPlant(final ModelAechorPlant mb, final float f)
    {
        super(mb, f);
        this.method_815(mb);
        this.xd = mb;
    }

    @Override
    protected void method_823(final LivingEntity entityliving, final float f)
    {
        final EntityAechorPlant b1 = (EntityAechorPlant) entityliving;
        float f2 = (float) Math.sin((double) b1.sinage);
        float f3;
        if (b1.hurtTime > 0)
        {
            f2 *= 0.45f;
            f2 -= 0.125f;
            f3 = 1.75f + (float) Math.sin((double) (b1.sinage + 2.0f)) * 1.5f;
        }
        else if (b1.seeprey)
        {
            f2 *= 0.25f;
            f3 = 1.75f + (float) Math.sin((double) (b1.sinage + 2.0f)) * 1.5f;
        }
        else
        {
            f2 *= 0.125f;
            f3 = 1.75f;
        }
        this.xd.sinage = f2;
        this.xd.sinage2 = f3;
        final float f4 = 0.625f + b1.size / 6.0f;
        this.xd.size = f4;
        this.field_2678 = f4 - 0.25f;
    }

    protected boolean a(final EntityAechorPlant entityaechorplant, final int i, final float f)
    {
        if (i != 0)
        {
            return false;
        }
        if (i != 0)
        {
            return false;
        }
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.325f);
        return true;
    }

    @Override
    protected boolean method_825(final LivingEntity entityliving, final int i, final float f)
    {
        return this.a((EntityAechorPlant) entityliving, i, f);
    }
}
