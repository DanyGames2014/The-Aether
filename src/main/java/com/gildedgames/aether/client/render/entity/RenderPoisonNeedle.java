package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.entity.projectile.EntityPoisonNeedle;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.EntityBase;
import net.minecraft.util.maths.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderPoisonNeedle extends EntityRenderer
{
    public void renderPoisonNeedle(final EntityPoisonNeedle entityarrow, final double d, final double d1, final double d2, final float f, final float f1)
    {
        if (entityarrow.victim != null)
        {
            return;
        }
        this.bindTexture("aether:textures/entity/entitypoisonneedle.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glRotatef(entityarrow.prevYaw + (entityarrow.yaw - entityarrow.prevYaw) * f1 - 90.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(entityarrow.prevPitch + (entityarrow.pitch - entityarrow.prevPitch) * f1, 0.0f, 0.0f, 1.0f);
        final Tessellator tessellator = Tessellator.INSTANCE;
        final int i = 1;
        final float f2 = 0.0f;
        final float f3 = 0.5f;
        final float f4 = (0 + i * 10) / 32.0f;
        final float f5 = (5 + i * 10) / 32.0f;
        final float f6 = 0.0f;
        final float f7 = 0.15625f;
        final float f8 = (5 + i * 10) / 32.0f;
        final float f9 = (10 + i * 10) / 32.0f;
        final float f10 = 0.05625f;
        GL11.glEnable(32826);
        final float f11 = entityarrow.arrowShake - f1;
        if (f11 > 0.0f)
        {
            final float f12 = -MathHelper.sin(f11 * 3.0f) * f11;
            GL11.glRotatef(f12, 0.0f, 0.0f, 1.0f);
        }
        GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(f10, f10, f10);
        GL11.glTranslatef(-4.0f, 0.0f, 0.0f);
        GL11.glNormal3f(f10, 0.0f, 0.0f);
        tessellator.start();
        tessellator.vertex(-7.0, -2.0, -2.0, f6, f8);
        tessellator.vertex(-7.0, -2.0, 2.0, f7, f8);
        tessellator.vertex(-7.0, 2.0, 2.0, f7, f9);
        tessellator.vertex(-7.0, 2.0, -2.0, f6, f9);
        tessellator.draw();
        GL11.glNormal3f(-f10, 0.0f, 0.0f);
        tessellator.start();
        tessellator.vertex(-7.0, 2.0, -2.0, f6, f8);
        tessellator.vertex(-7.0, 2.0, 2.0, f7, f8);
        tessellator.vertex(-7.0, -2.0, 2.0, f7, f9);
        tessellator.vertex(-7.0, -2.0, -2.0, f6, f9);
        tessellator.draw();
        for (int j = 0; j < 5; ++j)
        {
            GL11.glRotatef(72.0f, 1.0f, 0.0f, 0.0f);
            GL11.glNormal3f(0.0f, 0.0f, f10);
            tessellator.start();
            tessellator.vertex(-8.0, -2.0, 0.0, f2, f4);
            tessellator.vertex(8.0, -2.0, 0.0, f3, f4);
            tessellator.vertex(8.0, 2.0, 0.0, f3, f5);
            tessellator.vertex(-8.0, 2.0, 0.0, f2, f5);
            tessellator.draw();
        }
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    @Override
    public void render(final EntityBase entity, final double x, final double y, final double z, final float f, final float f1)
    {
        this.renderPoisonNeedle((EntityPoisonNeedle) entity, x, y, z, f, f1);
    }
}
