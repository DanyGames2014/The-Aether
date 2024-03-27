package com.gildedgames.aether.generator;

import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class AetherGenMinable extends Feature
{
    private int minableBlockId;
    private int numberOfBlocks;

    public AetherGenMinable(final int i, final int j)
    {
        this.minableBlockId = i;
        this.numberOfBlocks = j;
    }

    @Override
    public boolean generate(final World level, final Random rand, final int x, final int y, final int z)
    {
        final float f = rand.nextFloat() * 3.141593f;
        final double d = x + 8 + MathHelper.sin(f) * this.numberOfBlocks / 8.0f;
        final double d2 = x + 8 - MathHelper.sin(f) * this.numberOfBlocks / 8.0f;
        final double d3 = z + 8 + MathHelper.cos(f) * this.numberOfBlocks / 8.0f;
        final double d4 = z + 8 - MathHelper.cos(f) * this.numberOfBlocks / 8.0f;
        final double d5 = y + rand.nextInt(3) + 2;
        final double d6 = y + rand.nextInt(3) + 2;
        for (int l = 0; l <= this.numberOfBlocks; ++l)
        {
            final double d7 = d + (d2 - d) * l / this.numberOfBlocks;
            final double d8 = d5 + (d6 - d5) * l / this.numberOfBlocks;
            final double d9 = d3 + (d4 - d3) * l / this.numberOfBlocks;
            final double d10 = rand.nextDouble() * this.numberOfBlocks / 16.0;
            final double d11 = (MathHelper.sin(l * 3.141593f / this.numberOfBlocks) + 1.0f) * d10 + 1.0;
            final double d12 = (MathHelper.sin(l * 3.141593f / this.numberOfBlocks) + 1.0f) * d10 + 1.0;
            final int i1 = MathHelper.floor(d7 - d11 / 2.0);
            final int j1 = MathHelper.floor(d8 - d12 / 2.0);
            final int k1 = MathHelper.floor(d9 - d11 / 2.0);
            final int l2 = MathHelper.floor(d7 + d11 / 2.0);
            final int i2 = MathHelper.floor(d8 + d12 / 2.0);
            final int j2 = MathHelper.floor(d9 + d11 / 2.0);
            for (int k2 = i1; k2 <= l2; ++k2)
            {
                final double d13 = (k2 + 0.5 - d7) / (d11 / 2.0);
                if (d13 * d13 < 1.0)
                {
                    for (int l3 = j1; l3 <= i2; ++l3)
                    {
                        final double d14 = (l3 + 0.5 - d8) / (d12 / 2.0);
                        if (d13 * d13 + d14 * d14 < 1.0)
                        {
                            for (int i3 = k1; i3 <= j2; ++i3)
                            {
                                final double d15 = (i3 + 0.5 - d9) / (d11 / 2.0);
                                if (d13 * d13 + d14 * d14 + d15 * d15 < 1.0 && level.getBlockId(k2, l3, i3) == AetherBlocks.HOLYSTONE.id && level.getBlockMeta(k2, l3, i3) <= 1)
                                {
                                    level.method_200(k2, l3, i3, this.minableBlockId);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
