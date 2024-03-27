package com.gildedgames.aether.block;

import com.gildedgames.aether.entity.boss.EntityValkyrie;
import com.gildedgames.aether.entity.mobs.EntitySentry;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateTranslucentBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockTrap extends TemplateTranslucentBlock {

    public BlockTrap(final Identifier id) {
        super(id, TextureListener.sprBronze, Material.STONE, false);
        this.setTickRandomly(true);
    }

    @Override
    public boolean isOpaque() {
        return true;
    }

    @Override
    public int getRenderLayer() {
        return 1;
    }

    @Override
    public int getTexture(final int side, final int meta) {
        if (meta == 2) {
            return TextureListener.sprGold;
        }
        if (meta == 1) {
            return TextureListener.sprSilver;
        }
        return TextureListener.sprBronze;
    }

    @Override
    public int getDroppedItemCount(final Random rand) {
        return 1;
    }

    @Override
    public void onSteppedOn(final World level, final int x, final int y, final int z, final Entity entityBase) {
        if (entityBase instanceof PlayerEntity) {
            level.playSound(x + 0.5f, y + 0.5f, z + 0.5f, "aether:aether.sound.other.dungeontrap.activatetrap", 1.0f, 1.0f);
            final int x2 = MathHelper.floor((double) x);
            final int y2 = MathHelper.floor((double) y);
            final int z2 = MathHelper.floor((double) z);
            switch (level.getBlockMeta(x, y, z)) {
                case 0: {
                    final EntitySentry entitysentry = new EntitySentry(level, x2 + 0.5, y2 + 1.5, z2 + 0.5);
                    level.method_210(entitysentry);
                    break;
                }
                case 1: {
                    final EntityValkyrie entityvalk = new EntityValkyrie(level);
                    entityvalk.method_1340(x2 + 0.5, y2 + 1.5, z2 + 0.5);
                    level.method_210(entityvalk);
                    break;
                }
            }
            level.method_201(x, y, z, AetherBlocks.LOCKED_DUNGEON_STONE.id, level.getBlockMeta(x, y, z));
        }
    }

    @Override
    protected int getDroppedItemMeta(final int meta) {
        return meta;
    }

    static {

    }
}
