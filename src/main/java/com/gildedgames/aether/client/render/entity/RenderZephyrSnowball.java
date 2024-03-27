package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.entity.projectile.EntityZephyrSnowball;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class RenderZephyrSnowball extends EntityRenderer
{ /*WARNING: THIS IS VERY DANGEROUS WEAPON(and annoying too =<)*/
    public void func_4012_a(final EntityZephyrSnowball entityZephyrSnowball, final double d, final double d1, final double d2, final float f, final float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glEnable(32826);
        final float f2 = 2.0f;
        GL11.glScalef(f2 / 1.0f, f2 / 1.0f, f2 / 1.0f);
        final int i = Item.SNOWBALL.getTextureId(0);
        this.bindTexture("/gui/items.png");
        final Tessellator tessellator = Tessellator.INSTANCE;
        final float f3 = (i % 16 * 16 + 0) / 256.0f;
        final float f4 = (i % 16 * 16 + 16) / 256.0f;
        final float f5 = (i / 16 * 16 + 0) / 256.0f;
        final float f6 = (i / 16 * 16 + 16) / 256.0f;
        final float f7 = 1.0f;
        final float f8 = 0.5f;
        final float f9 = 0.25f;
        GL11.glRotatef(180.0f - this.dispatcher.field_2497, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-this.dispatcher.field_2498, 1.0f, 0.0f, 0.0f);
        tessellator.startQuads();
        tessellator.normal(0.0f, 1.0f, 0.0f);
        tessellator.vertex(0.0f - f8, 0.0f - f9, 0.0, f3, f6);
        tessellator.vertex(f7 - f8, 0.0f - f9, 0.0, f4, f6);
        tessellator.vertex(f7 - f8, 1.0f - f9, 0.0, f4, f5);
        tessellator.vertex(0.0f - f8, 1.0f - f9, 0.0, f3, f5);
        tessellator.draw();
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    @Override
    public void render(final Entity entity, final double x, final double y, final double z, final float f, final float f1)
    {
        this.func_4012_a((EntityZephyrSnowball) entity, x, y, z, f, f1);
    }
}
