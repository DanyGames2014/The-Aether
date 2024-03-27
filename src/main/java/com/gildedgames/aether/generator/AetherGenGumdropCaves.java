package com.gildedgames.aether.generator;

import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class AetherGenGumdropCaves extends Feature
{
    private int field_100003_a;
    private int field_100002_b;

    public AetherGenGumdropCaves(final int i, final int j)
    {
        this.field_100003_a = i;
        this.field_100002_b = j;
    }

    @Override
    public boolean generate(final World level, final Random rand, final int x, final int y, final int z)
    {
        final float f = rand.nextFloat() * 3.141593f;
        final double d = x + 8 + MathHelper.sin(f) * this.field_100002_b / 8.0f;
        final double d2 = x + 8 - MathHelper.sin(f) * this.field_100002_b / 8.0f;
        final double d3 = z + 8 + MathHelper.cos(f) * this.field_100002_b / 8.0f;
        final double d4 = z + 8 - MathHelper.cos(f) * this.field_100002_b / 8.0f;
        final double d5 = y + rand.nextInt(3) + 2;
        final double d6 = y + rand.nextInt(3) + 2;
        for (int l = 0; l <= this.field_100002_b; ++l)
        {
            final double d7 = d + (d2 - d) * l / this.field_100002_b;
            final double d8 = d5 + (d6 - d5) * l / this.field_100002_b;
            final double d9 = d3 + (d4 - d3) * l / this.field_100002_b;
            final double d10 = rand.nextDouble() * this.field_100002_b / 16.0;
            final double d11 = (MathHelper.sin(l * 3.141593f / this.field_100002_b) + 1.0f) * d10 + 1.0;
            final double d12 = (MathHelper.sin(l * 3.141593f / this.field_100002_b) + 1.0f) * d10 + 1.0;
            final int i1 = (int) (d7 - d11 / 2.0);
            final int j1 = (int) (d8 - d12 / 2.0);
            final int k1 = (int) (d9 - d11 / 2.0);
            final int l2 = (int) (d7 + d11 / 2.0);
            final int i2 = (int) (d8 + d12 / 2.0);
            final int j2 = (int) (d9 + d11 / 2.0);
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
                                final int bID = level.getBlockId(k2, l3, i3);
                                final int meta = level.getBlockMeta(k2, l3, i3);
                                if (d13 * d13 + d14 * d14 + d15 * d15 < 1.0 && ((bID == AetherBlocks.HOLYSTONE.id && meta <= 1) || bID == AetherBlocks.AETHER_GRASS_BLOCK.id || bID == AetherBlocks.AETHER_DIRT.id))
                                {
                                    level.setBlock(k2, l3, i3, this.field_100003_a);
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
