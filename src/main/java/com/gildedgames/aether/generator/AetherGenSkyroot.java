package com.gildedgames.aether.generator;

import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class AetherGenSkyroot extends Feature
{
    @Override
    public boolean generate(final World level, final Random rand, final int x, final int y, final int z)
    {
        final int l = rand.nextInt(3) + 4;
        boolean flag = true;
        if (y < 1 || y + l + 1 > 128)
        {
            return false;
        }
        for (int i1 = y; i1 <= y + 1 + l; ++i1)
        {
            byte byte0 = 1;
            if (i1 == y)
            {
                byte0 = 0;
            }
            if (i1 >= y + 1 + l - 2)
            {
                byte0 = 2;
            }
            for (int i2 = x - byte0; i2 <= x + byte0 && flag; ++i2)
            {
                for (int l2 = z - byte0; l2 <= z + byte0 && flag; ++l2)
                {
                    if (i1 >= 0 && i1 < 128)
                    {
                        final int j3 = level.getBlockId(i2, i1, l2);
                        if (j3 != 0 && j3 != AetherBlocks.SKYROOT_LEAVES.id)
                        {
                            flag = false;
                        }
                    }
                    else
                    {
                        flag = false;
                    }
                }
            }
        }
        if (!flag)
        {
            return false;
        }
        final int j4 = level.getBlockId(x, y - 1, z);
        if ((j4 != AetherBlocks.AETHER_GRASS_BLOCK.id && j4 != AetherBlocks.AETHER_DIRT.id) || y >= 128 - l - 1)
        {
            return false;
        }
        level.method_200(x, y - 1, z, AetherBlocks.AETHER_DIRT.id);
        for (int k1 = y - 3 + l; k1 <= y + l; ++k1)
        {
            final int j5 = k1 - (y + l);
            for (int i3 = 1 - j5 / 2, k2 = x - i3; k2 <= x + i3; ++k2)
            {
                final int l3 = k2 - x;
                for (int i4 = z - i3; i4 <= z + i3; ++i4)
                {
                    final int j6 = i4 - z;
                    if ((Math.abs(l3) != i3 || Math.abs(j6) != i3 || (rand.nextInt(2) != 0 && j5 != 0)) && !Block.BLOCKS_OPAQUE[level.getBlockId(k2, k1, i4)])
                    {
                        level.method_200(k2, k1, i4, AetherBlocks.SKYROOT_LEAVES.id);
                    }
                }
            }
        }
        for (int l4 = 0; l4 < l; ++l4)
        {
            final int k3 = level.getBlockId(x, y + l4, z);
            if (k3 == 0 || k3 == AetherBlocks.SKYROOT_LEAVES.id)
            {
                level.method_154(x, y + l4, z, AetherBlocks.SKYROOT_LOG.id, 0);
            }
        }
        return true;
    }
}
