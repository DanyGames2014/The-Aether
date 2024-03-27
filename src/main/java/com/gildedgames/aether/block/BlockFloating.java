package com.gildedgames.aether.block;

import com.gildedgames.aether.entity.projectile.EntityFloatingBlock;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockFloating extends TemplateBlock {
    public static boolean fallInstantly;
    private final boolean enchanted;

    public BlockFloating(final Identifier id, final boolean bool) {
        super(id, Material.STONE);
        this.enchanted = bool;
    }

    public int getTexture(int side, int meta) {
        if (this.id == AetherBlocks.GRAVITITE_ORE.id) {
            return TextureListener.sprGravititeOre;
        }
        return super.getTexture(side, meta);
    }

    @Override
    public void onPlaced(final World level, final int x, final int y, final int z) {
        level.method_216(x, y, z, this.id, this.getTickRate());
    }

    @Override
    public void neighborUpdate(final World level, final int x, final int y, final int z, final int id) {
        level.method_216(x, y, z, this.id, this.getTickRate());
    }

    @Override
    public void onTick(final World level, final int x, final int y, final int z, final Random rand) {
        if (!this.enchanted || (this.enchanted && level.method_263(x, y, z))) {
            this.tryToFall(level, x, y, z);
        }
    }

    private void tryToFall(final World world, final int i, int j, final int k) {
        final int l = i;
        final int i2 = j;
        final int j2 = k;
        if (canFallAbove(world, l, i2 + 1, j2) && i2 < 128) {
            final byte byte0 = 32;
            if (BlockFloating.fallInstantly || !world.method_155(i - byte0, j - byte0, k - byte0, i + byte0, j + byte0, k + byte0)) {
                world.setBlock(i, j, k, 0);
                while (canFallAbove(world, i, j + 1, k) && j < 128) {
                    ++j;
                }
                if (j > 0) {
                    world.setBlock(i, j, k, this.id);
                }
            } else {
                final EntityFloatingBlock floating = new EntityFloatingBlock(world, i + 0.5f, j + 0.5f, k + 0.5f, this.id);
                world.method_210(floating);
            }
        }
    }

    @Override
    public int getTickRate() {
        return 3;
    }

    public static boolean canFallAbove(final World world, final int i, final int j, final int k) {
        final int l = world.getBlockId(i, j, k);
        if (l == 0) {
            return true;
        }
        if (l == Block.FIRE.id) {
            return true;
        }
        final Material material = Block.BLOCKS[l].material;
        return material == Material.WATER || material == Material.LAVA;
    }

    static {
        BlockFloating.fallInstantly = false;
    }
}
