package com.gildedgames.aether.block;

import net.minecraft.block.Block;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockEnchantedGravitite extends BlockFloating {
    public BlockEnchantedGravitite(final Identifier i, final boolean bool) {
        super(i, bool);
        this.textureId = Block.IRON_BLOCK.textureId;
    }

    @Override
    public int getColor(final int i) {
        return 16755455;
    }

    @Override
    public int getColorMultiplier(final BlockView tileView, final int x, final int y, final int z) {
        return this.getColor(tileView.getBlockMeta(x, y, z));
    }
}
