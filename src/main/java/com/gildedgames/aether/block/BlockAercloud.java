package com.gildedgames.aether.block;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.registry.AetherAchievements;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.HasMetaBlockItem;
import net.modificationstation.stationapi.api.block.MetaNamedBlockItemProvider;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

@HasMetaBlockItem
public class BlockAercloud extends TemplateBlock implements MetaNamedBlockItemProvider {
    public static final int bouncingMeta = 1;

    public BlockAercloud(final Identifier id) {
        super(id, Material.field_997);
    }

    @Override
    public int getTexture(int side, int meta) {
        return TextureListener.sprAercloud;
    }

    @Override
    public void onEntityCollision(final World level, final int x, final int y, final int z, final Entity entityBase) {
        ((EntityBaseAccessor) entityBase).setFallDistance(0.0f);
        if (level.getBlockMeta(x, y, z) == 1) {
            entityBase.velocityY = 2.0;
            if (entityBase instanceof PlayerEntity) {
                AetherMod.giveAchievement(AetherAchievements.blueCloud, (PlayerEntity) entityBase);
            }
        } else if (entityBase.velocityY < 0.0) {
            entityBase.velocityY *= 0.005;
        }
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public int getRenderLayer() {
        return 1;
    }

    @Override
    protected int getDroppedItemMeta(int blockMeta) {
        return blockMeta;
    }

    @Override
    public int getColor(final int i) {
        if (i == 1) {
            return 3714284;
        }
        if (i == 2) {
            return 16777088;
        }
        return 16777215;
    }

    @Override
    public int getColorMultiplier(final BlockView tileView, final int x, final int y, final int z) {
        return this.getColor(tileView.getBlockMeta(x, y, z));
    }

    @Override
    public boolean isSideVisible(final BlockView tileView, final int x, final int y, final int z, final int side) {
        return super.isSideVisible(tileView, x, y, z, 1 - side);
    }

    @Override
    public Box getCollisionShape(final World level, final int x, final int y, final int z) {
        if (level.getBlockMeta(x, y, z) == 1) {
            return Box.createCached(x, y, z, x, y, z);
        }
        return Box.createCached(x, y, z, x + 1, y, z + 1);
    }
}