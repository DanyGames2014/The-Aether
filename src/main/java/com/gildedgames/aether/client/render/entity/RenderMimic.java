package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.client.render.model.ModelMimic;
import com.gildedgames.aether.entity.mobs.EntityMimic;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class RenderMimic extends EntityRenderer
{
    private ModelMimic model;

    public RenderMimic()
    {
        this.model = new ModelMimic();
    }

    public void render(final EntityMimic entityMimic, final double d, final double d1, final double d2, final float f, final float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glRotatef(180.0f - f, 0.0f, 1.0f, 0.0f);
        GL11.glScalef(-1.0f, -1.0f, 1.0f);
        this.bindTexture("aether:textures/entity/Mimic1.png");
        this.model.render1(0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f, entityMimic);
        this.bindTexture("aether:textures/entity/Mimic2.png");
        this.model.render2(0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f, entityMimic);
        GL11.glPopMatrix();
    }

    @Override
    public void render(final Entity entity, final double x, final double y, final double z, final float f, final float f1)
    {
        this.render((EntityMimic) entity, x, y, z, f, f1);
    }
}
