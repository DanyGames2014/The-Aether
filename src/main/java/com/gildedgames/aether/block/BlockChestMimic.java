package com.gildedgames.aether.block;

import com.gildedgames.aether.entity.mobs.EntityMimic;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockChestMimic extends TemplateBlock {
    public BlockChestMimic(final Identifier id) {
        super(id, Material.WOOD);
        this.textureId = 26;
    }

    @Override
    public int getTextureId(final BlockView tileView, final int x, final int y, final int z, final int meta) {
        if (meta == 1) {
            return this.textureId - 1;
        }
        if (meta == 0) {
            return this.textureId - 1;
        }
        final int i1 = tileView.getBlockId(x, y, z - 1);
        final int j1 = tileView.getBlockId(x, y, z + 1);
        final int k1 = tileView.getBlockId(x - 1, y, z);
        final int l1 = tileView.getBlockId(x + 1, y, z);
        if (i1 == this.id || j1 == this.id) {
            if (meta == 2 || meta == 3) {
                return this.textureId;
            }
            int i2 = 0;
            if (i1 == this.id) {
                i2 = -1;
            }
            final int k2 = tileView.getBlockId(x - 1, y, (i1 != this.id) ? (z + 1) : (z - 1));
            final int i3 = tileView.getBlockId(x + 1, y, (i1 != this.id) ? (z + 1) : (z - 1));
            if (meta == 4) {
                i2 = -1 - i2;
            }
            byte byte1 = 5;
            if ((Block.BLOCKS_OPAQUE[k1] || Block.BLOCKS_OPAQUE[k2]) && !Block.BLOCKS_OPAQUE[l1] && !Block.BLOCKS_OPAQUE[i3]) {
                byte1 = 5;
            }
            if ((Block.BLOCKS_OPAQUE[l1] || Block.BLOCKS_OPAQUE[i3]) && !Block.BLOCKS_OPAQUE[k1] && !Block.BLOCKS_OPAQUE[k2]) {
                byte1 = 4;
            }
            return ((meta != byte1) ? (this.textureId + 32) : (this.textureId + 16)) + i2;
        } else {
            if (k1 != this.id && l1 != this.id) {
                byte byte2 = 3;
                if (Block.BLOCKS_OPAQUE[i1] && !Block.BLOCKS_OPAQUE[j1]) {
                    byte2 = 3;
                }
                if (Block.BLOCKS_OPAQUE[j1] && !Block.BLOCKS_OPAQUE[i1]) {
                    byte2 = 2;
                }
                if (Block.BLOCKS_OPAQUE[k1] && !Block.BLOCKS_OPAQUE[l1]) {
                    byte2 = 5;
                }
                if (Block.BLOCKS_OPAQUE[l1] && !Block.BLOCKS_OPAQUE[k1]) {
                    byte2 = 4;
                }
                return (meta != byte2) ? this.textureId : (this.textureId + 1);
            }
            if (meta == 4 || meta == 5) {
                return this.textureId;
            }
            int j2 = 0;
            if (k1 == this.id) {
                j2 = -1;
            }
            final int l2 = tileView.getBlockId((k1 != this.id) ? (x + 1) : (x - 1), y, z - 1);
            final int j3 = tileView.getBlockId((k1 != this.id) ? (x + 1) : (x - 1), y, z + 1);
            if (meta == 3) {
                j2 = -1 - j2;
            }
            byte byte3 = 3;
            if ((Block.BLOCKS_OPAQUE[i1] || Block.BLOCKS_OPAQUE[l2]) && !Block.BLOCKS_OPAQUE[j1] && !Block.BLOCKS_OPAQUE[j3]) {
                byte3 = 3;
            }
            if ((Block.BLOCKS_OPAQUE[j1] || Block.BLOCKS_OPAQUE[j3]) && !Block.BLOCKS_OPAQUE[i1] && !Block.BLOCKS_OPAQUE[l2]) {
                byte3 = 2;
            }
            return ((meta != byte3) ? (this.textureId + 32) : (this.textureId + 16)) + j2;
        }
    }

    @Override
    public int getTexture(final int side) {
        if (side == 1) {
            return this.textureId - 1;
        }
        if (side == 0) {
            return this.textureId - 1;
        }
        if (side == 3) {
            return this.textureId + 1;
        }
        return this.textureId;
    }

    @Override
    public boolean canPlaceAt(final World level, final int x, final int y, final int z) {
        int l = 0;
        if (level.getBlockId(x - 1, y, z) == this.id) {
            ++l;
        }
        if (level.getBlockId(x + 1, y, z) == this.id) {
            ++l;
        }
        if (level.getBlockId(x, y, z - 1) == this.id) {
            ++l;
        }
        if (level.getBlockId(x, y, z + 1) == this.id) {
            ++l;
        }
        return l <= 1 && !this.isThereANeighborChest(level, x - 1, y, z) && !this.isThereANeighborChest(level, x + 1, y, z) && !this.isThereANeighborChest(level, x, y, z - 1) && !this.isThereANeighborChest(level, x, y, z + 1);
    }

    private boolean isThereANeighborChest(final World world, final int i, final int j, final int k) {
        return world.getBlockId(i, j, k) == this.id && (world.getBlockId(i - 1, j, k) == this.id || world.getBlockId(i + 1, j, k) == this.id || world.getBlockId(i, j, k - 1) == this.id || world.getBlockId(i, j, k + 1) == this.id);
    }

    @Override
    public void onBreak(final World level, final int x, final int y, final int z) {
        //level.setTile(x, y, z, 0);
        final EntityMimic mimic = new EntityMimic(level);
        mimic.method_1340(x + 0.5, y + 1.5, z + 0.5);
        level.method_210(mimic);
    }

    @Override
    public boolean onUse(final World level, final int x, final int y, final int z, final PlayerEntity player) {
        level.setBlock(x, y, z, 0);
        return true;
    }

    @Override
    public int getDroppedItemCount(final Random rand) {
        return 0;
    }
}
