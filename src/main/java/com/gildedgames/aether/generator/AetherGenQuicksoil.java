package com.gildedgames.aether.generator;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class AetherGenQuicksoil extends Feature
{
    private int minableBlockId;

    public AetherGenQuicksoil(final int i)
    {
        this.minableBlockId = i;
    }

    @Override
    public boolean generate(final World level, final Random rand, final int x, final int y, final int z)
    {
        for (int x2 = x - 3; x2 < x + 4; ++x2)
        {
            for (int z2 = z - 3; z2 < z + 4; ++z2)
            {
                if (level.getBlockId(x2, y, z2) == 0 && (x2 - x) * (x2 - x) + (z2 - z) * (z2 - z) < 12)
                {
                    level.method_200(x2, y, z2, this.minableBlockId);
                }
            }
        }
        return true;
    }
}
