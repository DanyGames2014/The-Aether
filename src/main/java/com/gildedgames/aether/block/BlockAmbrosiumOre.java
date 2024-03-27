package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockAmbrosiumOre extends TemplateBlock {
    public BlockAmbrosiumOre(final Identifier id) {
        super(id, Material.STONE);
    }

    @Override
    public int getTexture(int side, int meta) {
        return TextureListener.sprAmbrosiumOre;
    }

    @Override
    public int getDroppedItemId(final int meta, final Random rand) {
        return AetherItems.AmbrosiumShard.id;
    }

    @Override
    public void onPlaced(final World level, final int x, final int y, final int z, final int facing) {
        level.setBlock(x, y, z, this.id);
        level.method_215(x, y, z, 1);
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
