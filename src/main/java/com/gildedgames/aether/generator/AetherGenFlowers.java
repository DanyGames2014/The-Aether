package com.gildedgames.aether.generator;

import com.gildedgames.aether.block.BlockAetherFlower;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class AetherGenFlowers extends Feature
{
    private int plantBlockId;

    public AetherGenFlowers(final int i)
    {
        this.plantBlockId = i;
    }

    @Override
    public boolean generate(final World level, final Random rand, final int x, final int y, final int z)
    {
        for (int l = 0; l < 64; ++l)
        {
            final int i1 = x + rand.nextInt(8) - rand.nextInt(8);
            final int j1 = y + rand.nextInt(4) - rand.nextInt(4);
            final int k1 = z + rand.nextInt(8) - rand.nextInt(8);
            if (level.method_234(i1, j1, k1) && ((BlockAetherFlower) Block.BLOCKS[this.plantBlockId]).canGrow(level, i1, j1, k1))
            {
                level.method_200(i1, j1, k1, this.plantBlockId);
            }
        }
        return true;
    }
}
