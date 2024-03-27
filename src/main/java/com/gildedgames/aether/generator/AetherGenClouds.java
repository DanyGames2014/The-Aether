package com.gildedgames.aether.generator;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class AetherGenClouds extends Feature
{
    private int cloudBlockId;
    private int meta;
    private int numberOfBlocks;
    private boolean flat;

    public AetherGenClouds(final int i, final int meta, final int j, final boolean flag)
    {
        this.cloudBlockId = i;
        this.meta = meta;
        this.numberOfBlocks = j;
        this.flat = flag;
    }

    @Override
    public boolean generate(final World level, final Random rand, final int x, final int y, final int z)
    {
        int x2 = x;
        int y2 = y;
        int z2 = z;
        final int xTendency = rand.nextInt(3) - 1;
        final int zTendency = rand.nextInt(3) - 1;
        for (int n = 0; n < this.numberOfBlocks; ++n)
        {
            x2 += rand.nextInt(3) - 1 + xTendency;
            if ((rand.nextBoolean() && !this.flat) || (this.flat && rand.nextInt(10) == 0))
            {
                y2 += rand.nextInt(3) - 1;
            }
            z2 += rand.nextInt(3) - 1 + zTendency;
            for (int x3 = x2; x3 < x2 + rand.nextInt(4) + 3 * (this.flat ? 3 : 1); ++x3)
            {
                for (int y3 = y2; y3 < y2 + rand.nextInt(1) + 2; ++y3)
                {
                    for (int z3 = z2; z3 < z2 + rand.nextInt(4) + 3 * (this.flat ? 3 : 1); ++z3)
                    {
                        if (level.getBlockId(x3, y3, z3) == 0 && Math.abs(x3 - x2) + Math.abs(y3 - y2) + Math.abs(z3 - z2) < 4 * (this.flat ? 3 : 1) + rand.nextInt(2))
                        {
                            level.method_201(x3, y3, z3, this.cloudBlockId, this.meta);
                        }
                    }
                }
            }
        }
        return true;
    }
}
