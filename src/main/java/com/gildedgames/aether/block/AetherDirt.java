package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class AetherDirt extends TemplateBlock {

    public AetherDirt(Identifier identifier) {
        super(identifier, Material.SOIL);
    }

    @Override
    public int getTexture(int side, int meta) {
        return TextureListener.sprDirt;
    }

    @Override
    public boolean isOpaque() {
        return true;
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
            if (meta == 0 && playerBase.getHand().getItem() == AetherItems.ShovelSkyroot) {
                this.dropStacks(level, x, y, z, meta);
            }
    }
}
