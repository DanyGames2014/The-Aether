package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockAetherFlower extends TemplateBlock {
    public BlockAetherFlower(final Identifier id, final int var2) {
        super(id, Material.field_988);
        this.setTickRandomly(true);
        final float var3 = 0.2f;
        this.setBoundingBox(0.5f - var3, 0.0f, 0.5f - var3, 0.5f + var3, var3 * 3.0f, 0.5f + var3);
    }

    @Override
    public int getTexture(int side, int meta) {
        if (this.id == AetherBlocks.WHITE_FLOWER.id) {
            return TextureListener.sprWhiteFlower;
        }
        return TextureListener.sprPurpleFlower;
    }

    @Override
    public boolean canPlaceAt(final World level, final int x, final int y, final int z) {
        return super.canPlaceAt(level, x, y, z) && this.canThisPlantGrowOnThisBlockID(level.getBlockId(x, y - 1, z));
    }

    protected boolean canThisPlantGrowOnThisBlockID(final int var1) {
        return var1 == AetherBlocks.AETHER_GRASS_BLOCK.id || var1 == AetherBlocks.AETHER_DIRT.id;
    }

    @Override
    public void neighborUpdate(final World level, final int x, final int y, final int z, final int id) {
        super.neighborUpdate(level, x, y, z, id);
        this.func_268_h(level, x, y, z);
    }

    @Override
    public void onTick(final World level, final int x, final int y, final int z, final Random rand) {
        this.func_268_h(level, x, y, z);
    }

    protected final void func_268_h(final World var1, final int var2, final int var3, final int var4) {
        if (!this.canGrow(var1, var2, var3, var4)) {
            this.dropStacks(var1, var2, var3, var4, var1.getBlockMeta(var2, var3, var4));
            var1.setBlock(var2, var3, var4, 0);
        }
    }

    @Override
    public boolean canGrow(final World level, final int x, final int y, final int z) {
        return (level.method_252(x, y, z) >= 8 || level.method_249(x, y, z)) && this.canThisPlantGrowOnThisBlockID(level.getBlockId(x, y - 1, z));
    }

    @Override
    public Box getCollisionShape(final World level, final int x, final int y, final int z) {
        return null;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 1;
    }
}
