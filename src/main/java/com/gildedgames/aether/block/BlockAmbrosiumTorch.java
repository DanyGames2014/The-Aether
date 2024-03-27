package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateTorchBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockAmbrosiumTorch extends TemplateTorchBlock {
    public BlockAmbrosiumTorch(final Identifier id) {
        super(id, TextureListener.sprAmbrosiumTorch);
        this.setTickRandomly(true);
    }

    @Override
    public int getTexture(int side, int meta) {
        return TextureListener.sprAmbrosiumTorch;
    }

    @Override
    public Box getCollisionShape(final World level, final int x, final int y, final int z) {
        return null;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override // net.minecraft.block.BlockBase
    @Environment(EnvType.CLIENT)
    public int getRenderType() {
        return 2;
    }

    @Override
    public boolean canPlaceAt(final World level, final int x, final int y, final int z) {
        return level.method_1783(x - 1, y, z) || level.method_1783(x + 1, y, z) || level.method_1783(x, y, z - 1) || level.method_1783(x, y, z + 1) || level.method_1783(x, y - 1, z);
    }

    @Override
    public void onPlaced(final World level, final int x, final int y, final int z, final int facing) {
        int i1 = level.getBlockMeta(x, y, z);
        if (facing == 1 && level.method_1783(x, y - 1, z)) {
            i1 = 5;
        }
        if (facing == 2 && level.method_1783(x, y, z + 1)) {
            i1 = 4;
        }
        if (facing == 3 && level.method_1783(x, y, z - 1)) {
            i1 = 3;
        }
        if (facing == 4 && level.method_1783(x + 1, y, z)) {
            i1 = 2;
        }
        if (facing == 5 && level.method_1783(x - 1, y, z)) {
            i1 = 1;
        }
        level.method_215(x, y, z, i1);
    }

    @Override
    public void onTick(final World level, final int x, final int y, final int z, final Random rand) {
        super.onTick(level, x, y, z, rand);
        if (level.getBlockMeta(x, y, z) == 0) {
            this.onPlaced(level, x, y, z);
        }
    }

    @Override
    public void onPlaced(final World level, final int x, final int y, final int z) {
        if (level.method_1783(x - 1, y, z)) {
            level.method_215(x, y, z, 1);
        } else if (level.method_1783(x + 1, y, z)) {
            level.method_215(x, y, z, 2);
        } else if (level.method_1783(x, y, z - 1)) {
            level.method_215(x, y, z, 3);
        } else if (level.method_1783(x, y, z + 1)) {
            level.method_215(x, y, z, 4);
        } else if (level.method_1783(x, y - 1, z)) {
            level.method_215(x, y, z, 5);
        }
        this.dropTorchIfCantStay(level, x, y, z);
    }

    @Override
    public void neighborUpdate(final World level, final int x, final int y, final int z, final int id) {
        if (this.dropTorchIfCantStay(level, x, y, z)) {
            final int i1 = level.getBlockMeta(x, y, z);
            boolean flag = !level.method_1783(x - 1, y, z) && i1 == 1;
            if (!level.method_1783(x + 1, y, z) && i1 == 2) {
                flag = true;
            }
            if (!level.method_1783(x, y, z - 1) && i1 == 3) {
                flag = true;
            }
            if (!level.method_1783(x, y, z + 1) && i1 == 4) {
                flag = true;
            }
            if (!level.method_1783(x, y - 1, z) && i1 == 5) {
                flag = true;
            }
            if (flag) {
                this.dropStacks(level, x, y, z, level.getBlockMeta(x, y, z));
                level.setBlock(x, y, z, 0);
            }
        }
    }

    private boolean dropTorchIfCantStay(final World world, final int i, final int j, final int k) {
        if (!this.canPlaceAt(world, i, j, k)) {
            this.dropStacks(world, i, j, k, world.getBlockMeta(i, j, k));
            world.setBlock(i, j, k, 0);
            return false;
        }
        return true;
    }

    @Override
    public HitResult raycast(final World world, final int x, final int y, final int z, final Vec3d vec3d, final Vec3d vec3d1) {
        final int l = world.getBlockMeta(x, y, z) & 0x7;
        final float f = 0.15f;
        if (l == 1) {
            this.setBoundingBox(0.0f, 0.2f, 0.5f - f, f * 2.0f, 0.8f, 0.5f + f);
        } else if (l == 2) {
            this.setBoundingBox(1.0f - f * 2.0f, 0.2f, 0.5f - f, 1.0f, 0.8f, 0.5f + f);
        } else if (l == 3) {
            this.setBoundingBox(0.5f - f, 0.2f, 0.0f, 0.5f + f, 0.8f, f * 2.0f);
        } else if (l == 4) {
            this.setBoundingBox(0.5f - f, 0.2f, 1.0f - f * 2.0f, 0.5f + f, 0.8f, 1.0f);
        } else {
            final float f2 = 0.1f;
            this.setBoundingBox(0.5f - f2, 0.0f, 0.5f - f2, 0.5f + f2, 0.6f, 0.5f + f2);
        }
        return super.raycast(world, x, y, z, vec3d, vec3d1);
    }

    @Override
    public void randomDisplayTick(final World level, final int x, final int y, final int z, final Random rand) {
        final int l = level.getBlockMeta(x, y, z);
        final double d = x + 0.5f;
        final double d2 = y + 0.7f;
        final double d3 = z + 0.5f;
        final double d4 = 0.2199999988079071;
        final double d5 = 0.27000001072883606;
        if (l == 1) {
            level.addParticle("flame", d - d5, d2 + d4, d3, 0.0, 0.0, 0.0);
        } else if (l == 2) {
            level.addParticle("flame", d + d5, d2 + d4, d3, 0.0, 0.0, 0.0);
        } else if (l == 3) {
            level.addParticle("flame", d, d2 + d4, d3 - d5, 0.0, 0.0, 0.0);
        } else if (l == 4) {
            level.addParticle("flame", d, d2 + d4, d3 + d5, 0.0, 0.0, 0.0);
        } else {
            level.addParticle("flame", d, d2, d3, 0.0, 0.0, 0.0);
        }
    }
}
