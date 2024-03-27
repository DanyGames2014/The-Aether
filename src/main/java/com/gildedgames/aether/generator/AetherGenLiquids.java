package com.gildedgames.aether.generator;

import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class AetherGenLiquids extends Feature
{
    private int liquidBlockId;

    public AetherGenLiquids(final int i)
    {
        this.liquidBlockId = i;
    }

    @Override
    public boolean generate(final World level, final Random rand, final int x, final int y, final int z)
    {
        if (level.getBlockId(x, y + 1, z) != AetherBlocks.HOLYSTONE.id || level.getBlockMeta(x, y + 1, z) >= 2)
        {
            return false;
        }
        if (level.getBlockId(x, y - 1, z) != AetherBlocks.HOLYSTONE.id || level.getBlockMeta(x, y - 1, z) >= 2)
        {
            return false;
        }
        if (level.getBlockId(x, y, z) != 0 && (level.getBlockId(x, y, z) != AetherBlocks.HOLYSTONE.id || level.getBlockMeta(x, y, z) >= 2))
        {
            return false;
        }
        int l = 0;
        if (level.getBlockId(x - 1, y, z) == AetherBlocks.HOLYSTONE.id || level.getBlockMeta(x - 1, y, z) >= 2)
        {
            ++l;
        }
        if (level.getBlockId(x + 1, y, z) == AetherBlocks.HOLYSTONE.id || level.getBlockMeta(x + 1, y, z) >= 2)
        {
            ++l;
        }
        if (level.getBlockId(x, y, z - 1) == AetherBlocks.HOLYSTONE.id || level.getBlockMeta(x, y, z - 1) >= 2)
        {
            ++l;
        }
        if (level.getBlockId(x, y, z + 1) == AetherBlocks.HOLYSTONE.id || level.getBlockMeta(x, y, z + 1) >= 2)
        {
            ++l;
        }
        int i1 = 0;
        if (level.method_234(x - 1, y, z))
        {
            ++i1;
        }
        if (level.method_234(x + 1, y, z))
        {
            ++i1;
        }
        if (level.method_234(x, y, z - 1))
        {
            ++i1;
        }
        if (level.method_234(x, y, z + 1))
        {
            ++i1;
        }
        if (l == 3 && i1 == 1)
        {
            level.setBlock(x, y, z, this.liquidBlockId);
            level.field_197 = true;
            Block.BLOCKS[this.liquidBlockId].onTick(level, x, y, z, rand);
            level.field_197 = false;
        }
        return true;
    }
}
