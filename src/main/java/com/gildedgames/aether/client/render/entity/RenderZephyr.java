package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.client.render.model.ModelZephyr;
import com.gildedgames.aether.entity.mobs.EntityZephyr;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public class RenderZephyr extends LivingEntityRenderer
{
    public RenderZephyr()
    {
        super(new ModelZephyr(), 0.5f);
    }

    protected void func_4014_a(final EntityZephyr entityzephyr, final float f)
    {
        final EntityZephyr entityzephyr2 = entityzephyr;
        float f2 = (entityzephyr2.prevAttackCounter + (entityzephyr2.attackCounter - entityzephyr2.prevAttackCounter) * f) / 20.0f;
        if (f2 < 0.0f)
        {
            f2 = 0.0f;
        }
        f2 = 1.0f / (f2 * f2 * f2 * f2 * f2 * 2.0f + 1.0f);
        final float f3 = (8.0f + f2) / 2.0f;
        final float f4 = (8.0f + 1.0f / f2) / 2.0f;
        GL11.glScalef(f4, f3, f4);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    protected void scalewhale()
    {
        GL11.glScalef(6.0f, 6.0f, 6.0f);
    }

    @Override
    protected void method_823(final LivingEntity entityliving, final float f)
    {
        this.func_4014_a((EntityZephyr) entityliving, f);
    }
}
