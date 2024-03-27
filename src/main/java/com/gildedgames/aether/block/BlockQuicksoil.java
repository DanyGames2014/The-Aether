package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockQuicksoil extends TemplateBlock {
    public BlockQuicksoil(final Identifier id) {
        super(id, Material.SAND);
        this.slipperiness = 1.1f;
    }

    public int getTexture(int side, int meta) {
        return TextureListener.sprQuicksoil;
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
