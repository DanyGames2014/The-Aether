package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.entity.projectile.EntityFloatingBlock;
import net.minecraft.block.Block;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderFloatingBlock extends EntityRenderer
{
    private static BlockRenderManager renderBlocks;

    public RenderFloatingBlock()
    {
        RenderFloatingBlock.renderBlocks = new BlockRenderManager();
        this.field_2678 = 0.5f;
    }

    public void func_156_a(final EntityFloatingBlock entityFloatingBlock, final double d, final double d1, final double d2, final float f, final float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        this.bindTexture("/terrain.png");
        final Block block = Block.BLOCKS[entityFloatingBlock.blockID];
        final World world = entityFloatingBlock.getWorld();
        GL11.glDisable(2896);
        renderBlockFallingSand(block, world, MathHelper.floor(entityFloatingBlock.x), MathHelper.floor(entityFloatingBlock.y), MathHelper.floor(entityFloatingBlock.z), entityFloatingBlock.metadata);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }

    public static void renderBlockFallingSand(final Block block, final World world, final int i, final int j, final int k, final int meta)
    {
        final int l = block.getColor(meta);
        final float red = (l >> 16 & 0xFF) / 255.0f;
        final float green = (l >> 8 & 0xFF) / 255.0f;
        final float blue = (l & 0xFF) / 255.0f;
        final float f = 0.5f;
        final float f2 = 1.0f;
        final float f3 = 0.8f;
        final float f4 = 0.6f;
        final Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.startQuads();
        final float f5 = block.getLuminance(world, i, j, k);
        float f6 = block.getLuminance(world, i, j - 1, k);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.color(f * f6 * red, f * f6 * green, f * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderBottomFace(block, -0.5, -0.5, -0.5, block.getTexture(0, meta));
        f6 = block.getLuminance(world, i, j + 1, k);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.color(f2 * f6 * red, f2 * f6 * green, f2 * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderTopFace(block, -0.5, -0.5, -0.5, block.getTexture(1, meta));
        f6 = block.getLuminance(world, i, j, k - 1);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.color(f3 * f6 * red, f3 * f6 * green, f3 * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderEastFace(block, -0.5, -0.5, -0.5, block.getTexture(2, meta));
        f6 = block.getLuminance(world, i, j, k + 1);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.color(f3 * f6 * red, f3 * f6 * green, f3 * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderWestFace(block, -0.5, -0.5, -0.5, block.getTexture(3, meta));
        f6 = block.getLuminance(world, i - 1, j, k);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.color(f4 * f6 * red, f4 * f6 * green, f4 * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderNorthFace(block, -0.5, -0.5, -0.5, block.getTexture(4, meta));
        f6 = block.getLuminance(world, i + 1, j, k);
        if (f6 < f5)
        {
            f6 = f5;
        }
        tessellator.color(f4 * f6 * red, f4 * f6 * green, f4 * f6 * blue);
        RenderFloatingBlock.renderBlocks.renderSouthFace(block, -0.5, -0.5, -0.5, block.getTexture(5, meta));
        tessellator.draw();
    }

    @Override
    public void render(final Entity entity, final double x, final double y, final double z, final float f, final float f1)
    {
        this.func_156_a((EntityFloatingBlock) entity, x, y, z, f, f1);
    }
}
