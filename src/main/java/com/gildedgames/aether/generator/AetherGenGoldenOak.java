package com.gildedgames.aether.generator;

import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class AetherGenGoldenOak extends Feature
{
    public boolean branch(final World world, final Random random, int i, int j, int k, final int slant)
    {
        final int directionX = random.nextInt(3) - 1;
        final int directionY = slant;
        final int directionZ = random.nextInt(3) - 1;
        for (int n = 0; n < random.nextInt(2) + 1; ++n)
        {
            i += directionX;
            j += directionY;
            k += directionZ;
            if (world.getBlockId(i, j, k) == AetherBlocks.GOLDEN_OAK_LEAVES.id)
            {
                world.setBlock(i, j, k, AetherBlocks.GOLDEN_OAK_LOG.id);
            }
        }
        return true;
    }

    @Override
    public boolean generate(final World level, final Random rand, final int x, final int y, final int z)
    {
        if (level.getBlockId(x, y - 1, z) != AetherBlocks.AETHER_GRASS_BLOCK.id && level.getBlockId(x, y - 1, z) != AetherBlocks.AETHER_DIRT.id)
        {
            return false;
        }
        final int height = rand.nextInt(5) + 6;
        for (int x2 = x - 3; x2 < x + 4; ++x2)
        {
            for (int y2 = y + 5; y2 < y + 12; ++y2)
            {
                for (int z2 = z - 3; z2 < z + 4; ++z2)
                {
                    if ((x2 - x) * (x2 - x) + (y2 - y - 8) * (y2 - y - 8) + (z2 - z) * (z2 - z) < 12 + rand.nextInt(5) && level.getBlockId(x2, y2, z2) == 0)
                    {
                        level.method_200(x2, y2, z2, AetherBlocks.GOLDEN_OAK_LEAVES.id);
                    }
                }
            }
        }
        for (int n = 0; n < height; ++n)
        {
            if (n > 4 && rand.nextInt(3) > 0)
            {
                this.branch(level, rand, x, y + n, z, n / 4 - 1);
            }
            level.setBlock(x, y + n, z, AetherBlocks.GOLDEN_OAK_LOG.id);
        }
        return true;
    }
}
