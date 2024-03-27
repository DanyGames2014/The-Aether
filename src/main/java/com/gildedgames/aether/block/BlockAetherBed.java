package com.gildedgames.aether.block;

import com.gildedgames.aether.mixin.access.PlayerBaseAccessor;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.class_141;
import net.minecraft.class_213;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBedBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockAetherBed extends TemplateBedBlock {
    public static final int[][] headBlockToFootBlockMap;

    public BlockAetherBed(Identifier identifier) {
        super(identifier);
        this.setBounds();
    }


    @Override
    public boolean onUse(final World level, int x, final int y, int z, final PlayerEntity player) {
        //if (level.isServerSide) {
        //    return true;
        //}
        int l = level.getBlockMeta(x, y, z);
        if (!isBlockFootOfBed(l)) {
            final int i1 = getDirectionFromMetadata(l);
            x += BlockAetherBed.headBlockToFootBlockMap[i1][0];
            z += BlockAetherBed.headBlockToFootBlockMap[i1][1];
            if (level.getBlockId(x, y, z) != this.id) {
                return true;
            }
            l = level.getBlockMeta(x, y, z);
        }
        if (isBedOccupied(l)) {
            PlayerEntity entityplayer1 = null;
            for (final Object objpl : level.field_200) {
                PlayerEntity entityplayer2 = (PlayerEntity) objpl;
                if (entityplayer2.method_943()) {
                    final Vec3i chunkcoordinates = entityplayer2.spawnPos;
                    if (chunkcoordinates.x != x || chunkcoordinates.y != y || chunkcoordinates.z != z) {
                        continue;
                    }
                    entityplayer1 = entityplayer2;
                }
            }
            if (entityplayer1 != null) {
                player.method_490("tile.bed.occupied");
                return true;
            }
            setBedOccupied(level, x, y, z, false);
        }
        final class_141 enumstatus = this.sleepInBedAt(player, x, y, z);
        if (enumstatus == class_141.OK) {
            setBedOccupied(level, x, y, z, true);
            return true;
        }
        if (enumstatus == class_141.NOT_POSSIBLE_NOW) {
            player.method_490("tile.bed.noSleep");
        }
        return true;
    }

    public class_141 sleepInBedAt(final PlayerEntity player, final int i, final int j, final int k) {
        final World worldObj = player.world;
        //if (!worldObj.isServerSide) {
        if (player.method_943() || !player.isAlive()) {
            return class_141.OTHER_PROBLEM;
        }
        if (worldObj.method_220()) {
            return class_141.NOT_POSSIBLE_NOW;
        }
        if (Math.abs(player.x - i) > 3.0 || Math.abs(player.y - j) > 2.0 || Math.abs(player.z - k) > 3.0) {
            return class_141.NOT_POSSIBLE_HERE;
        }
        //}
        player.spacingXZ = 0.2f;
        player.spacingY = 0.2f;
        player.eyeHeight = 0.2f;
        if (worldObj.method_239(i, j, k)) {
            final int l = worldObj.getBlockMeta(i, j, k);
            final int i2 = BedBlock.method_1658(l);
            float f = 0.5f;
            float f2 = 0.5f;
            switch (i2) {
                case 0: {
                    f2 = 0.9f;
                    break;
                }
                case 2: {
                    f2 = 0.1f;
                    break;
                }
                case 1: {
                    f = 0.1f;
                    break;
                }
                case 3: {
                    f = 0.9f;
                    break;
                }
            }
            this.func_22052_e(player, i2);
            player.method_1340(i + f, j + 0.9375f, k + f2);
        } else {
            player.method_1340(i + 0.5f, j + 0.9375f, k + 0.5f);
        }
        ((PlayerBaseAccessor) player).setSleeping(true);
        final double velocityX = 0.0;
        player.velocityY = velocityX;
        player.velocityZ = velocityX;
        player.velocityX = velocityX;
        //if (!worldObj.isServerSide) {
        worldObj.method_264();
        //}
        return class_141.OK;
    }

    private void func_22052_e(final PlayerEntity player, final int i) {
        player.field_509 = 0.0f;
        player.field_510 = 0.0f;
        switch (i) {
            case 0: {
                player.field_510 = -1.8f;
                break;
            }
            case 2: {
                player.field_510 = 1.8f;
                break;
            }
            case 1: {
                player.field_509 = 1.8f;
                break;
            }
            case 3: {
                player.field_509 = -1.8f;
                break;
            }
        }
    }

    @Override
    public int getTexture(final int side, final int meta) {
        if (side == 0) {
            return Block.PLANKS.textureId;
        }
        final int k = getDirectionFromMetadata(meta);
        final int l = class_213.field_794[k][side];
        if (isBlockFootOfBed(meta)) {
            if (l == 2) {
                return this.textureId + 2 + 16;
            }
            if (l == 5 || l == 4) {
                return this.textureId + 1 + 16;
            }
            return this.textureId + 1;
        } else {
            if (l == 3) {
                return this.textureId - 1 + 16;
            }
            if (l == 5 || l == 4) {
                return this.textureId + 16;
            }
            return this.textureId;
        }
    }

    @Override
    public int getRenderType() {
        return 14;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public void updateBoundingBox(final BlockView tileView, final int x, final int y, final int z) {
        this.setBounds();
    }

    @Override
    public void neighborUpdate(final World level, final int x, final int y, final int z, final int id) {
        final int i1 = level.getBlockMeta(x, y, z);
        final int j1 = getDirectionFromMetadata(i1);
        if (isBlockFootOfBed(i1)) {
            if (level.getBlockId(x - BlockAetherBed.headBlockToFootBlockMap[j1][0], y, z - BlockAetherBed.headBlockToFootBlockMap[j1][1]) != this.id) {
                level.setBlock(x, y, z, 0);
            }
        } else if (level.getBlockId(x + BlockAetherBed.headBlockToFootBlockMap[j1][0], y, z + BlockAetherBed.headBlockToFootBlockMap[j1][1]) != this.id) {
            level.setBlock(x, y, z, 0);
            //if (!level.isServerSide) {
            this.dropStacks(level, x, y, z, i1);
            //}
        }
    }

    @Override
    public int getDroppedItemId(final int meta, final Random rand) {
        if (isBlockFootOfBed(meta)) {
            return 0;
        }
        return Item.BED.id;
    }

    private void setBounds() {
        this.setBoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.5625f, 1.0f);
    }

    public static int getDirectionFromMetadata(final int i) {
        return i & 0x3;
    }

    public static boolean isBlockFootOfBed(final int i) {
        return (i & 0x8) != 0x0;
    }

    public static boolean isBedOccupied(final int i) {
        return (i & 0x4) != 0x0;
    }

    public static void setBedOccupied(final World world, final int i, final int j, final int k, final boolean flag) {
        int l = world.getBlockMeta(i, j, k);
        if (flag) {
            l |= 0x4;
        } else {
            l &= 0xFFFFFFFB;
        }
        world.method_215(i, j, k, l);
    }

    public static Vec3i getNearestEmptyChunkCoordinates(final World world, final int i, final int j, final int k, int l) {
        final int i2 = world.getBlockMeta(i, j, k);
        final int j2 = getDirectionFromMetadata(i2);
        for (int k2 = 0; k2 <= 1; ++k2) {
            final int l2 = i - BlockAetherBed.headBlockToFootBlockMap[j2][0] * k2 - 1;
            final int i3 = k - BlockAetherBed.headBlockToFootBlockMap[j2][1] * k2 - 1;
            final int j3 = l2 + 2;
            final int k3 = i3 + 2;
            for (int l3 = l2; l3 <= j3; ++l3) {
                for (int i4 = i3; i4 <= k3; ++i4) {
                    if (world.method_1780(l3, j - 1, i4) && world.method_234(l3, j, i4)) {
                        if (world.method_234(l3, j + 1, i4)) {
                            if (l <= 0) {
                                return new Vec3i(l3, j, i4);
                            }
                            --l;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void dropStacks(final World level, final int x, final int y, final int z, final int meta, final float dropChance) {
        if (!isBlockFootOfBed(meta)) {
            super.dropStacks(level, x, y, z, meta, dropChance);
        }
    }

    @Override
    public int getPistonBehavior() {
        return 1;
    }

    static {
        headBlockToFootBlockMap = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    }
}
