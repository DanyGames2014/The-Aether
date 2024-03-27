package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockHolystone extends TemplateBlock {

    public BlockHolystone(Identifier identifier) {
        super(identifier, Material.STONE);
    }

    @Override
    public void afterBreak(World level, PlayerEntity playerBase, int x, int y, int z, int meta) {
        int i;
        playerBase.increaseStat(Stats.MINE_BLOCK[this.id], 1);
        int i2 = this.id;
        if (meta <= 1) {
            i = 0;
        } else {
            i = 2;
        }
        ItemStack stack = new ItemStack(i2, 1, i);
        if (playerBase.getHand() != null)
            if (meta == 0 && playerBase.getHand().getItem() == AetherItems.PickSkyroot) {
                stack.count *= 2;
            }
        this.dropStack(level, x, y, z, stack);
    }

    @Override
    public int getTexture(int side, int meta) {
        return TextureListener.sprHolystone;
    }
}
