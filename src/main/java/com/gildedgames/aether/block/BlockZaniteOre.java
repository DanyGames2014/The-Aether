package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockZaniteOre extends TemplateBlock {
    public BlockZaniteOre(final Identifier id) {
        super(id, Material.STONE);
    }

    public int getTexture(int side, int meta) {
        return TextureListener.sprZaniteOre;
    }

    @Override
    public int getDroppedItemId(final int meta, final Random rand) {
        return AetherItems.Zanite.id;
    }
}
