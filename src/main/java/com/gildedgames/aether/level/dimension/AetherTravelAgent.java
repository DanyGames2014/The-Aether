package com.gildedgames.aether.level.dimension;

import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.class_467;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class AetherTravelAgent extends class_467
{

    private final Random random = new Random();

    @Override
    public boolean method_1531(World level, Entity entity)
    {
        char c = '\200';
        double d = -1D;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = MathHelper.floor(entity.x);
        int i1 = MathHelper.floor(entity.z);
        for (int j1 = l - c; j1 <= l + c; j1++)
        {
            double d1 = ((double) j1 + 0.5D) - entity.x;
            for (int j2 = i1 - c; j2 <= i1 + c; j2++)
            {
                double d3 = ((double) j2 + 0.5D) - entity.z;
                for (int k2 = 127; k2 >= 0; k2--)
                {
                    if (level.getBlockId(j1, k2, j2) != AetherBlocks.AETHER_PORTAL.id)
                        continue;
                    while (level.getBlockId(j1, k2 - 1, j2) == AetherBlocks.AETHER_PORTAL.id)
                        k2--;
                    double d5 = ((double) k2 + 0.5D) - entity.y;
                    double d7 = d1 * d1 + d5 * d5 + d3 * d3;
                    if (d < 0.0D || d7 < d)
                    {
                        d = d7;
                        i = j1;
                        j = k2;
                        k = j2;
                    }
                }
            }
        }

        if (d >= 0.0D)
        {
            double d2 = (double) i + 0.5D;
            double d4 = (double) j + 0.5D;
            double d6 = (double) k + 0.5D;
            if (level.getBlockId(i - 1, j, k) == AetherBlocks.AETHER_PORTAL.id)
                d2 -= 0.5D;
            if (level.getBlockId(i + 1, j, k) == AetherBlocks.AETHER_PORTAL.id)
                d2 += 0.5D;
            if (level.getBlockId(i, j, k - 1) == AetherBlocks.AETHER_PORTAL.id)
                d6 -= 0.5D;
            if (level.getBlockId(i, j, k + 1) == AetherBlocks.AETHER_PORTAL.id)
                d6 += 0.5D;
            entity.method_1341(d2, d4, d6, entity.yaw, 0.0F);
            entity.velocityX = entity.velocityY = entity.velocityZ = 0.0D;
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean method_1532(World level, Entity entity)
    {
        byte byte0 = 16;
        double d = -1D;
        int i = MathHelper.floor(entity.x);
        int j = MathHelper.floor(entity.y);
        int k = MathHelper.floor(entity.z);
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = random.nextInt(4);
        for (int i2 = i - byte0; i2 <= i + byte0; i2++)
        {
            double d1 = ((double) i2 + 0.5D) - entity.x;
            for (int j3 = k - byte0; j3 <= k + byte0; j3++)
            {
                double d3 = ((double) j3 + 0.5D) - entity.z;
                for (int k4 = 127; k4 >= 0; k4--)
                {
                    if (!level.method_234(i2, k4, j3)) continue;
                    while (k4 > 0 && level.method_234(i2, k4 - 1, j3)) k4--;
                    label0:
                    for (int k5 = l1; k5 < l1 + 4; k5++)
                    {
                        int l6 = k5 % 2;
                        int i8 = 1 - l6;
                        if (k5 % 4 >= 2)
                        {
                            l6 = -l6;
                            i8 = -i8;
                        }
                        for (int j9 = 0; j9 < 3; j9++)
                            for (int k10 = 0; k10 < 4; k10++)
                                for (int l11 = -1; l11 < 4; l11++)
                                {
                                    int j12 = i2 + (k10 - 1) * l6 + j9 * i8;
                                    int l12 = k4 + l11;
                                    int j13 = (j3 + (k10 - 1) * i8) - j9 * l6;
                                    if (l11 < 0 && !blockIsGood(level.getBlockId(j12, l12, j13), level.getBlockMeta(j12, l12, j13)) || l11 >= 0 && !level.method_234(j12, l12, j13))
                                        break label0;
                                }

                        double d5 = ((double) k4 + 0.5D) - entity.y;
                        double d7 = d1 * d1 + d5 * d5 + d3 * d3;
                        if (d < 0.0D || d7 < d)
                        {
                            d = d7;
                            l = i2;
                            i1 = k4;
                            j1 = j3;
                            k1 = k5 % 4;
                        }
                    }
                }
            }
        }
        if (d < 0.0D) for (int j2 = i - byte0; j2 <= i + byte0; j2++)
        {
            double d2 = ((double) j2 + 0.5D) - entity.x;
            for (int k3 = k - byte0; k3 <= k + byte0; k3++)
            {
                double d4 = ((double) k3 + 0.5D) - entity.z;
                for (int l4 = 127; l4 >= 0; l4--)
                {
                    if (!level.method_234(j2, l4, k3)) continue;
                    while (level.method_234(j2, l4 - 1, k3) && l4 > 0)
                    {
                        l4--;
                    }
                    label1:
                    for (int l5 = l1; l5 < l1 + 2; l5++)
                    {
                        int i7 = l5 % 2;
                        int j8 = 1 - i7;
                        for (int k9 = 0; k9 < 4; k9++)
                            for (int l10 = -1; l10 < 4; l10++)
                            {
                                int i12 = j2 + (k9 - 1) * i7;
                                int k12 = l4 + l10;
                                int i13 = k3 + (k9 - 1) * j8;
                                if (l10 < 0 && !blockIsGood(level.getBlockId(i12, k12, i13), level.getBlockMeta(i12, k12, i13)) || l10 >= 0 && !level.method_234(i12, k12, i13))
                                    break label1;
                            }

                        double d6 = ((double) l4 + 0.5D) - entity.y;
                        double d8 = d2 * d2 + d6 * d6 + d4 * d4;
                        if (d < 0.0D || d8 < d)
                        {
                            d = d8;
                            l = j2;
                            i1 = l4;
                            j1 = k3;
                            k1 = l5 % 2;
                        }
                    }
                }
            }
        }
        int k2 = k1;
        int l2 = l;
        int i3 = i1;
        int l3 = j1;
        int i4 = k2 % 2;
        int j4 = 1 - i4;
        if (k2 % 4 >= 2)
        {
            i4 = -i4;
            j4 = -j4;
        }
        if (d < 0.0D)
        {
            if (i1 < 70) i1 = 70;
            if (i1 > 118) i1 = 118;
            i3 = i1;
            for (int i5 = -1; i5 <= 1; i5++)
                for (int i6 = 1; i6 < 3; i6++)
                    for (int j7 = -1; j7 < 3; j7++)
                    {
                        int k8 = l2 + (i6 - 1) * i4 + i5 * j4;
                        int l9 = i3 + j7;
                        int i11 = (l3 + (i6 - 1) * j4) - i5 * i4;
                        boolean flag = j7 < 0;
                        level.setBlock(k8, l9, i11, flag ? Block.GLOWSTONE.id : 0);
                    }
        }
        for (int j5 = 0; j5 < 4; j5++)
        {
            level.field_211 = true;
            for (int j6 = 0; j6 < 4; j6++)
                for (int k7 = -1; k7 < 4; k7++)
                {
                    int l8 = l2 + (j6 - 1) * i4;
                    int i10 = i3 + k7;
                    int j11 = l3 + (j6 - 1) * j4;
                    boolean flag1 = j6 == 0 || j6 == 3 || k7 == -1 || k7 == 3;
                    level.setBlock(l8, i10, j11, flag1 ? Block.GLOWSTONE.id : AetherBlocks.AETHER_PORTAL.id);
                }
            level.field_211 = false;
            for (int k6 = 0; k6 < 4; k6++)
                for (int l7 = -1; l7 < 4; l7++)
                {
                    int i9 = l2 + (k6 - 1) * i4;
                    int j10 = i3 + l7;
                    int k11 = l3 + (k6 - 1) * j4;
                    level.method_244(i9, j10, k11, level.getBlockId(i9, j10, k11));
                }
        }
        return true;
    }

    public boolean blockIsGood(int block, int meta)
    {
        if (block == 0)
            return false;
        if (!Block.BLOCKS[block].material.method_905())
            return false;
        return true/*block != AetherBlocks.Aercloud.blockID || meta != 0*/;
    }
}
