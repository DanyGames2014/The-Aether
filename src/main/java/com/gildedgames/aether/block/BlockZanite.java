package com.gildedgames.aether.block;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockZanite extends TemplateBlock {
    public BlockZanite(final Identifier id) {
        super(id, Material.STONE);
        this.textureId = Block.IRON_BLOCK.textureId;
    }

    @Override
    public int getColor(final int i) {
        return 10066431;
    }

    @Override
    public int getColorMultiplier(final BlockView tileView, final int x, final int y, final int z) {
        return this.getColor(tileView.getBlockMeta(x, y, z));
    }
}
