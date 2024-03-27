package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockMossyHolystone extends BlockHolystone {
    public BlockMossyHolystone(Identifier identifier) {
        super(identifier);
    }

    @Override
    public int getTexture(int side, int meta) {
        return TextureListener.sprMossyHolystone;
    }

}
