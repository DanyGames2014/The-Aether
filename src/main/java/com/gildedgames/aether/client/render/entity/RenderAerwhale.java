package com.gildedgames.aether.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.gildedgames.aether.client.render.model.ModelAerwhale;

import net.minecraft.entity.EntityBase;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.client.render.entity.EntityRenderer;

public class RenderAerwhale extends EntityRenderer {
    private EntityModelBase model;
    
    public RenderAerwhale() {
        this.model = new ModelAerwhale();
    }
    
    @Override
    public void render(final EntityBase entity, final double x, final double y, final double z, final float f, final float f1) {
        GL11.glPushMatrix();
        this.bindTexture("aether:textures/entity/Aerwhale.png");
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glRotatef(90.0f - entity.yaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180.0f - entity.pitch, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(5.0f, 5.0f, 5.0f);
        this.model.render(0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
}
