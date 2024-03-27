package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.entity.EntityCloudParachute;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderCloudParachute extends EntityRenderer
{
    private BlockRenderManager renderBlocks;

    public RenderCloudParachute()
    {
        this.renderBlocks = new BlockRenderManager();
        this.field_2678 = 0.5f;
    }

    public void renderCloud(final EntityCloudParachute entitycloud, final double d, final double d1, final double d2, final float f, final float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glRotatef(180.0f - f, 0.0f, 1.0f, 0.0f);
        this.bindTexture("/terrain.png");
        GL11.glDisable(2896);
        RenderFloatingBlock.renderBlockFallingSand(AetherBlocks.AERCLOUD, entitycloud.getWorld(), MathHelper.floor(entitycloud.x), MathHelper.floor(entitycloud.y), MathHelper.floor(entitycloud.z), entitycloud.gold ? 2 : 0);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }

    @Override
    public void render(final Entity entity, final double x, final double y, final double z, final float f, final float f1)
    {
        this.renderCloud((EntityCloudParachute) entity, x, y, z, f, f1);
    }
}
