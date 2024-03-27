package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockLog extends TemplateBlock {
    private static Random rand;

    public BlockLog(final Identifier id) {
        super(id, TextureListener.sprSkyrootLogSide, Material.WOOD);
    }

    @Override
    public void onPlaced(World level, int x, int y, int z) {
        level.method_215(x, y, z, 1);
        super.onPlaced(level, x, y, z);
    }

    @Override
    public int getTexture(final int side) {

        if (side <= 1) {
            return TextureListener.sprSkyrootLogTop;
        }
        if (this.id == AetherBlocks.SKYROOT_LOG.id) return TextureListener.sprSkyrootLogSide;
        else return TextureListener.sprGoldenLogSide;
    }

    @Override
    public int getDroppedItemCount(final Random rand) {
        return 0;
    }

    @Override
    public void afterBreak(final World level, final PlayerEntity playerBase, final int x, final int y, final int z, final int meta) {
        ItemStack stack = new ItemStack(this.id, 1, 0);
        playerBase.increaseStat(Stats.MINE_BLOCK[this.id], 1);
        if (this.id == AetherBlocks.SKYROOT_LOG.id) {
            // regular log
            super.afterBreak(level, playerBase, x, y, z, meta);
            if (playerBase.getHand() != null)
                if (meta != 0 && playerBase.getHand().getItem() == AetherItems.AxeSkyroot) {
                    stack.count = 2;
                }
        } else {
            final ItemStack itemstack = playerBase.inventory.getSelectedItem();
            if (itemstack != null) {
                if ((itemstack.itemId == AetherItems.AxeZanite.id || itemstack.itemId == AetherItems.AxeGravitite.id)) {
                    ItemStack amberStack;
                    if (rand.nextBoolean()) {
                        amberStack = new ItemStack(AetherItems.GoldenAmber.id, 2 + rand.nextInt(2), 0);
                        this.dropStack(level, x, y, z, amberStack);
                    }
                    stack.count = 1;
                    stack.itemId = AetherBlocks.SKYROOT_LOG.id;
                }
            }
        }
        this.dropStack(level, x, y, z, stack);
    }

    static {
        BlockLog.rand = new Random();
    }
}
