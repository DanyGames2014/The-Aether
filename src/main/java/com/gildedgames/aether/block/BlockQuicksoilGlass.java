package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import net.minecraft.block.Material;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.template.block.TemplateTranslucentBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockQuicksoilGlass extends TemplateTranslucentBlock {
    public BlockQuicksoilGlass(final Identifier id) {
        super(id, 0, Material.field_994, false);
        this.slipperiness = 1.05f;
    }

    @Override
    public int getTexture(int side, int meta) {
        return TextureListener.sprQuicksoilGlass;
    }

    @Override
    public int getDroppedItemCount(final Random rand) {
        return 0;
    }

    @Override
    public int getRenderLayer() {
        return 1;
    }

    @Override
    public boolean isSideVisible(final BlockView tileView, final int x, final int y, final int z, final int side) {
        return super.isSideVisible(tileView, x, y, z, 1 - side);
    }

    @Override
    public int getColor(final int i) {
        return 16776960;
    }

    @Override
    public int getColorMultiplier(final BlockView tileView, final int x, final int y, final int z) {
        return this.getColor(tileView.getBlockMeta(x, y, z));
    }
}
