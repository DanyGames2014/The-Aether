package com.gildedgames.aether.block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.block.HasMetaBlockItem;
import net.modificationstation.stationapi.api.block.MetaNamedBlockItemProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;

import net.minecraft.block.BlockBase;
@HasMetaBlockItem
public class BlockDungeon extends TemplateBlockBase implements MetaNamedBlockItemProvider{

    
    public BlockDungeon(final Identifier id) {
        super(id, Material.STONE);
    }
    
    @Override
    public int getTextureForSide(final int side, final int meta) {
        if (meta == 2) {
            return this.isLit() ? TextureListener.sprGoldLit : TextureListener.sprGold;
        }
        if (meta == 1) {
            return this.isLit() ? TextureListener.sprSilverLit : TextureListener.sprSilver;
        }
        return this.isLit() ? TextureListener.sprBronzeLit : TextureListener.sprBronze;
    }
    
    private boolean isLit() {
        return this.id == AetherBlocks.LIGHT_DUNGEON_STONE.id || this.id == AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id;
    }
    
    @Override
    protected int droppedMeta(final int meta) {
        return meta;
    }
    
    public static int func_21034_c(final int i) {
        return ~i & 0xF;
    }
    
    public static int func_21035_d(final int i) {
        return ~i & 0xF;
    }
}
