package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class AetherGrassBlock extends TemplateBlock {

    public AetherGrassBlock(Identifier identifier) {
        super(identifier, Material.SOIL);
        setTickRandomly(true);
    }

    @Override
    public int getTexture(int side, int meta) {
        switch (side) {
            case 0:
                return TextureListener.sprDirt;
            case 1:
                return TextureListener.sprGrassTop;
            default:
                return TextureListener.sprGrassSide;
        }
    }

    @Override
    public int getDroppedItemId(final int meta, final Random rand) {
        return AetherBlocks.AETHER_DIRT.getDroppedItemId(0, rand);
    }

    @Override
    public void afterBreak(final World level, final PlayerEntity playerBase, final int x, final int y, final int z, final int meta) {
        super.afterBreak(level, playerBase, x, y, z, meta);
        if (playerBase.getHand() != null)
            if (meta == 0 && playerBase.getHand().getItem() == AetherItems.ShovelSkyroot) {
                this.dropStacks(level, x, y, z, meta);
            }
    }


    @Override
    public void onTick(World level, int x, int y, int z, Random rand) {
        if (!level.isRemote) {
            if (level.method_252(x, y + 1, z) < 4 && level.method_1779(x, y + 1, z).method_906()) {
                if (rand.nextInt(4) == 0) {
                    level.setBlock(x, y, z, AetherBlocks.AETHER_DIRT.id);
                }
            } else if (level.method_252(x, y + 1, z) >= 9) {
                int l = (x + rand.nextInt(3)) - 1;
                int i1 = (y + rand.nextInt(5)) - 3;
                int j1 = (z + rand.nextInt(3)) - 1;
                if (level.getBlockId(l, i1, j1) == AetherBlocks.AETHER_DIRT.id && level.method_252(l, i1 + 1, j1) >= 4 && !level.method_1779(l, i1 + 1, j1).method_906()) {
                    level.setBlock(l, i1, j1, AetherBlocks.AETHER_GRASS_BLOCK.id);
                }
            }
        }
    }
}
