package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.generator.AetherGenGoldenOak;
import com.gildedgames.aether.generator.AetherGenSkyroot;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.template.block.TemplatePlantBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockAetherSapling extends TemplatePlantBlock {
    public BlockAetherSapling(final Identifier id, boolean isGold) {
        super(id, isGold ? TextureListener.sprGoldenOak : TextureListener.sprSkyroot);
        final float f = 0.4f;
        this.setBoundingBox(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 2.0f, 0.5f + f);
    }

    @Override
    public void onTick(final World level, final int x, final int y, final int z, final Random rand) {
        super.onTick(level, x, y, z, rand);
        if (level.method_255(x, y + 1, z) >= 9 && rand.nextInt(30) == 0) {
            this.growTree(level, x, y, z, rand);
        }
    }

    @Override
    public int getTexture(final int side, final int meta) {
        if (this.id == AetherBlocks.GOLDEN_OAK_SAPLING.id) {
            if (this.textureId != TextureListener.sprGoldenSapling) {
                this.textureId = TextureListener.sprGoldenSapling;
            }
            return TextureListener.sprGoldenSapling;
        }
        if (this.textureId != TextureListener.sprOakSapling) {
            this.textureId = TextureListener.sprOakSapling;
        }
        return TextureListener.sprOakSapling;
    }

    @Override
    public boolean canPlaceAt(final World level, final int x, final int y, final int z) {
        return super.canPlaceAt(level, x, y, z) && this.method_1683(level.getBlockId(x, y - 1, z));
    }

    @Override
    protected boolean method_1683(final int id) {
        return id == AetherBlocks.AETHER_GRASS_BLOCK.id || id == AetherBlocks.AETHER_DIRT.id || id == Block.DIRT.id || id == Block.GRASS_BLOCK.id;
    }

    @Override
    public boolean onUse(final World level, final int x, final int y, final int z, final PlayerEntity player) {
        if (player == null) {
            return false;
        }
        final ItemStack itemStack = player.getHand();
        if (itemStack == null) {
            return false;
        }
        if (itemStack.itemId != Item.DYE.id) {
            return false;
        }
        if (itemStack.getDamage() != 15) {
            return false;
        }
        this.growTree(level, x, y, z, level.field_214);
        final ItemStack itemInstance = itemStack;
        --itemInstance.count;
        return true;
    }

    public void growTree(final World world, final int i, final int j, final int k, final Random random) {
        world.method_200(i, j, k, 0);
        Object obj = null;
        if (this.id == AetherBlocks.GOLDEN_OAK_SAPLING.id) {
            obj = new AetherGenGoldenOak();
        } else {
            obj = new AetherGenSkyroot();
        }
        if (!((Feature) obj).generate(world, random, i, j, k)) {
            world.method_200(i, j, k, this.id);
        }
    }

    static {
        //BlockAetherSapling.sprSkyroot = ModLoader.addOverride("/terrain.png", "/aether/blocks/SkyrootSapling.png");
        //BlockAetherSapling.sprGoldenOak = ModLoader.addOverride("/terrain.png", "/aether/blocks/GoldenOakSapling.png");
    }
}
