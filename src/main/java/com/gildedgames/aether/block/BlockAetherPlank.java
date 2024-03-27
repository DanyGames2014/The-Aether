package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import net.minecraft.block.Material;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockAetherPlank extends TemplateBlock {

    public BlockAetherPlank(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public int getTexture(int side, int meta) {
        return TextureListener.sprPlank;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    public boolean isSideVisible(final BlockView tileView, final int x, final int y, final int z, final int side) {
        return super.isSideVisible(tileView, x, y, z, 1 - side);
    }

}
