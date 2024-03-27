package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import net.minecraft.block.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockPillar extends TemplateBlock {

    public BlockPillar(final Identifier id) {
        super(id, Material.STONE);
    }

    @Override
    public int getTexture(final int side, final int meta) {
        if (side == 0 || side == 1) {
            return TextureListener.sprPillarTop;
        }
        if (meta == 0) {
            return TextureListener.sprPillarSide;
        }
        return TextureListener.sprPillarTopSide;
    }

    @Override
    protected int getDroppedItemMeta(final int meta) {
        return meta;
    }

    static {

    }
}
