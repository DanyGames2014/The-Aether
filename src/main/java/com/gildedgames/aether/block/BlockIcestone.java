package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockIcestone extends TemplateBlock {
    public BlockIcestone(final Identifier id) {
        super(id, Material.STONE);
    }

    public int getTexture(int side, int meta) {
        return TextureListener.sprIceStone;
    }

    @Override
    public void onPlaced(final World level, final int x, final int y, final int z, final int facing) {
        level.setBlock(x, y, z, this.id);
        level.method_215(x, y, z, 1);
        for (int x2 = x - 3; x2 < x + 4; ++x2) {
            for (int y2 = y - 1; y2 < y + 2; ++y2) {
                for (int z2 = z - 3; z2 < z + 4; ++z2) {
                    if ((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y) + (z2 - z) * (z2 - z) < 8 && level.getBlockId(x2, y2, z2) == Block.WATER.id) {
                        level.setBlock(x2, y2, z2, Block.ICE.id);
                    }
                    if ((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y) + (z2 - z) * (z2 - z) < 8 && level.getBlockId(x2, y2, z2) == Block.LAVA.id) {
                        level.setBlock(x2, y2, z2, Block.OBSIDIAN.id);
                    }
                }
            }
        }
    }

    @Override
    public void afterBreak(final World level, final PlayerEntity playerBase, final int x, final int y, final int z, final int meta) {
        super.afterBreak(level, playerBase, x, y, z, meta);
        if (playerBase.getHand() != null)
            if (meta == 0 && playerBase.getHand().getItem() == AetherItems.PickSkyroot) {
                this.dropStacks(level, x, y, z, meta);
            }
    }
}
