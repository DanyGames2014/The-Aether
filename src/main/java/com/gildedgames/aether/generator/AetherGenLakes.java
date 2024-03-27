package com.gildedgames.aether.generator;

import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class AetherGenLakes extends Feature
{
    private int field_15235_a;

    public AetherGenLakes(final int i)
    {
        this.field_15235_a = i;
    }

    @Override
    public boolean generate(final World level, final Random rand, int x, int y, int z)
    {
        for (x -= 8, z -= 8; y > 0 && level.method_234(x, y, z); --y)
        {
        }
        y -= 4;
        final boolean[] aflag = new boolean[2048];
        for (int l = rand.nextInt(4) + 4, i1 = 0; i1 < l; ++i1)
        {
            final double d = rand.nextDouble() * 6.0 + 3.0;
            final double d2 = rand.nextDouble() * 4.0 + 2.0;
            final double d3 = rand.nextDouble() * 6.0 + 3.0;
            final double d4 = rand.nextDouble() * (16.0 - d - 2.0) + 1.0 + d / 2.0;
            final double d5 = rand.nextDouble() * (8.0 - d2 - 4.0) + 2.0 + d2 / 2.0;
            final double d6 = rand.nextDouble() * (16.0 - d3 - 2.0) + 1.0 + d3 / 2.0;
            for (int j4 = 1; j4 < 15; ++j4)
            {
                for (int k4 = 1; k4 < 15; ++k4)
                {
                    for (int l2 = 1; l2 < 7; ++l2)
                    {
                        final double d7 = (j4 - d4) / (d / 2.0);
                        final double d8 = (l2 - d5) / (d2 / 2.0);
                        final double d9 = (k4 - d6) / (d3 / 2.0);
                        final double d10 = d7 * d7 + d8 * d8 + d9 * d9;
                        if (d10 < 1.0)
                        {
                            aflag[(j4 * 16 + k4) * 8 + l2] = true;
                        }
                    }
                }
            }
        }
        for (int j5 = 0; j5 < 16; ++j5)
        {
            for (int j6 = 0; j6 < 16; ++j6)
            {
                for (int j7 = 0; j7 < 8; ++j7)
                {
                    final boolean flag = !aflag[(j5 * 16 + j6) * 8 + j7] && ((j5 < 15 && aflag[((j5 + 1) * 16 + j6) * 8 + j7]) || (j5 > 0 && aflag[((j5 - 1) * 16 + j6) * 8 + j7]) || (j6 < 15 && aflag[(j5 * 16 + (j6 + 1)) * 8 + j7]) || (j6 > 0 && aflag[(j5 * 16 + (j6 - 1)) * 8 + j7]) || (j7 < 7 && aflag[(j5 * 16 + j6) * 8 + (j7 + 1)]) || (j7 > 0 && aflag[(j5 * 16 + j6) * 8 + (j7 - 1)]));
                    if (flag)
                    {
                        final Material material = level.method_1779(x + j5, y + j7, z + j6);
                        if (j7 >= 4 && material.method_893())
                        {
                            return false;
                        }
                        if (j7 < 4 && !material.method_905() && level.getBlockId(x + j5, y + j7, z + j6) != this.field_15235_a)
                        {
                            return false;
                        }
                    }
                }
            }
        }
        for (int k5 = 0; k5 < 16; ++k5)
        {
            for (int k6 = 0; k6 < 16; ++k6)
            {
                for (int k7 = 0; k7 < 8; ++k7)
                {
                    if (aflag[(k5 * 16 + k6) * 8 + k7])
                    {
                        level.method_200(x + k5, y + k7, z + k6, (k7 < 4) ? this.field_15235_a : 0);
                    }
                }
            }
        }
        for (int l3 = 0; l3 < 16; ++l3)
        {
            for (int l4 = 0; l4 < 16; ++l4)
            {
                for (int l5 = 4; l5 < 8; ++l5)
                {
                    // field_2757 is SKY
                    if (aflag[(l3 * 16 + l4) * 8 + l5] && level.getBlockId(x + l3, y + l5 - 1, z + l4) == AetherBlocks.AETHER_DIRT.id && level.method_164(LightType.SKY, x + l3, y + l5, z + l4) > 0)
                    {
                        level.method_200(x + l3, y + l5 - 1, z + l4, AetherBlocks.AETHER_GRASS_BLOCK.id);
                    }
                }
            }
        }
        if (Block.BLOCKS[this.field_15235_a].material == Material.LAVA)
        {
            for (int i2 = 0; i2 < 16; ++i2)
            {
                for (int i3 = 0; i3 < 16; ++i3)
                {
                    for (int i4 = 0; i4 < 8; ++i4)
                    {
                        final boolean flag2 = !aflag[(i2 * 16 + i3) * 8 + i4] && ((i2 < 15 && aflag[((i2 + 1) * 16 + i3) * 8 + i4]) || (i2 > 0 && aflag[((i2 - 1) * 16 + i3) * 8 + i4]) || (i3 < 15 && aflag[(i2 * 16 + (i3 + 1)) * 8 + i4]) || (i3 > 0 && aflag[(i2 * 16 + (i3 - 1)) * 8 + i4]) || (i4 < 7 && aflag[(i2 * 16 + i3) * 8 + (i4 + 1)]) || (i4 > 0 && aflag[(i2 * 16 + i3) * 8 + (i4 - 1)]));
                        if (flag2 && (i4 < 4 || rand.nextInt(2) != 0) && level.method_1779(x + i2, y + i4, z + i3).method_905())
                        {
                            level.method_154(x + i2, y + i4, z + i3, AetherBlocks.HOLYSTONE.id, 0);
                        }
                    }
                }
            }
        }
        return true;
    }
}
