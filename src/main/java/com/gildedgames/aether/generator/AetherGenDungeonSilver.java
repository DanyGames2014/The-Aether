package com.gildedgames.aether.generator;

import com.gildedgames.aether.block.BlockTreasureChest;
import com.gildedgames.aether.entity.boss.EntityValkyrie;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.Random;

public class AetherGenDungeonSilver extends AetherGenBuildings
{
    private int baseMeta1;
    private int baseMeta2;
    private int lockedBlockID1;
    private int lockedBlockID2;
    private int wallBlockID1;
    private int wallBlockID2;
    private int baseID1;
    private int baseID2;
    private int columnID;
    private int[][][] rooms;

    public AetherGenDungeonSilver(final int i, final int j, final int k, final int l, final int m, final int m1, final int o, final int o1, final int p)
    {
        this.rooms = new int[3][3][3];
        this.lockedBlockID1 = i;
        this.lockedBlockID2 = j;
        this.wallBlockID1 = k;
        this.wallBlockID2 = l;
        this.baseID1 = m;
        this.baseMeta1 = m1;
        this.baseID2 = o;
        this.baseMeta2 = o1;
        this.columnID = p;
    }

    @Override
    public boolean generate(final World level, final Random rand, final int x, final int y, final int z)
    {
        this.replaceAir = true;
        if (!this.isBoxEmpty(level, x, y, z, 55, 20, 30))
        {
            return false;
        }
        if (level.getBlockId(x, y - 5, z) == 0 || level.getBlockId(x + 55, y - 5, z) == 0 || level.getBlockId(x, y - 5, z + 30) == 0 || level.getBlockId(x + 55, y - 5, z + 30) == 0)
        {
            for (int n = 0; n < 100; ++n)
            {
                final int x2 = x - 11 + rand.nextInt(77);
                final int y2 = y - 7;
                final int z2 = z - 10 + rand.nextInt(50);
                new AetherGenClouds(AetherBlocks.AERCLOUD.id, 0, 10, false).generate(level, rand, x2, y2, z2);
            }
        }
        this.replaceSolid = true;
        this.setBlocks(this.baseID2, this.baseID1, 30);
        this.setMetadata(this.baseMeta2, this.baseMeta1);
        this.addSolidBox(level, rand, x, y - 5, z, 55, 5, 30);
        for (int x3 = x; x3 < x + 55; x3 += 4)
        {
            this.addColumn(level, rand, x3, y, z, 14);
            this.addColumn(level, rand, x3, y, z + 27, 14);
        }
        for (int z3 = z; z3 < z + 12; z3 += 4)
        {
            this.addColumn(level, rand, x, y, z3, 14);
            this.addColumn(level, rand, x + 52, y, z3, 14);
        }
        for (int z3 = z + 19; z3 < z + 30; z3 += 4)
        {
            this.addColumn(level, rand, x, y, z3, 14);
            this.addColumn(level, rand, x + 52, y, z3, 14);
        }
        this.setBlocks(this.lockedBlockID1, this.lockedBlockID2, 20);
        this.setMetadata(1, 1);
        this.addHollowBox(level, rand, x + 4, y - 1, z + 4, 47, 16, 22);
        this.addPlaneX(level, rand, x + 11, y, z + 5, 15, 20);
        this.addPlaneX(level, rand, x + 18, y, z + 5, 15, 20);
        this.addPlaneX(level, rand, x + 25, y, z + 5, 15, 20);
        this.addPlaneZ(level, rand, x + 5, y, z + 11, 20, 15);
        this.addPlaneZ(level, rand, x + 5, y, z + 18, 20, 15);
        this.setBlocks(this.lockedBlockID1, AetherBlocks.TRAPPED_DUNGEON_STONE.id, 15);
        this.setMetadata(1, 1);
        this.addPlaneY(level, rand, x + 5, y + 4, z + 5, 20, 20);
        this.addPlaneY(level, rand, x + 5, y + 9, z + 5, 20, 20);
        for (int y3 = y; y3 < y + 2; ++y3)
        {
            for (int z4 = z + 14; z4 < z + 16; ++z4)
            {
                level.method_200(x + 4, y3, z4, 0);
            }
        }
        this.setBlocks(0, 0, 1);
        this.addSolidBox(level, rand, x, y - 4, z + 14, 1, 4, 2);
        this.addSolidBox(level, rand, x + 1, y - 3, z + 14, 1, 3, 2);
        this.addSolidBox(level, rand, x + 2, y - 2, z + 14, 1, 2, 2);
        this.addSolidBox(level, rand, x + 3, y - 1, z + 14, 1, 1, 2);
        this.setBlocks(this.lockedBlockID1, this.lockedBlockID2, 20);
        this.setMetadata(1, 1);
        for (int y3 = 0; y3 < 7; ++y3)
        {
            this.addPlaneY(level, rand, x - 1, y + 15 + y3, z - 1 + 2 * y3, 57, 32 - 4 * y3);
        }
        int row = rand.nextInt(3);
        this.addStaircase(level, rand, x + 19, y, z + 5 + row * 7, 10);
        this.rooms[2][0][row] = 2;
        this.rooms[2][1][row] = 2;
        this.rooms[2][2][row] = 1;
        int x2 = x + 25;
        for (int y2 = y; y2 < y + 2; ++y2)
        {
            for (int z2 = z + 7 + 7 * row; z2 < z + 9 + 7 * row; ++z2)
            {
                level.method_200(x2, y2, z2, 0);
            }
        }
        row = rand.nextInt(3);
        this.addStaircase(level, rand, x + 12, y, z + 5 + row * 7, 5);
        this.rooms[1][0][row] = 1;
        this.rooms[1][1][row] = 1;
        row = rand.nextInt(3);
        this.addStaircase(level, rand, x + 5, y + 5, z + 5 + row * 7, 5);
        this.rooms[0][0][row] = 1;
        this.rooms[0][1][row] = 1;
        for (int p = 0; p < 3; ++p)
        {
            for (int q = 0; q < 3; ++q)
            {
                for (int r = 0; r < 3; ++r)
                {
                    int type = this.rooms[p][q][r];
                    if (p + 1 < 3 && (type == 0 || type == 1 || rand.nextBoolean()) && type != 2)
                    {
                        final int newType = this.rooms[p + 1][q][r];
                        if (newType != 2 && (newType != 1 || type != 1))
                        {
                            this.rooms[p][q][r] = 3;
                            type = 3;
                            x2 = x + 11 + 7 * p;
                            for (int y2 = y + 5 * q; y2 < y + 2 + 5 * q; ++y2)
                            {
                                for (int z2 = z + 7 + 7 * r; z2 < z + 9 + 7 * r; ++z2)
                                {
                                    level.method_200(x2, y2, z2, 0);
                                }
                            }
                        }
                    }
                    if (p - 1 > 0 && (type == 0 || type == 1 || rand.nextBoolean()) && type != 2)
                    {
                        final int newType = this.rooms[p - 1][q][r];
                        if (newType != 2 && (newType != 1 || type != 1))
                        {
                            this.rooms[p][q][r] = 4;
                            type = 4;
                            x2 = x + 4 + 7 * p;
                            for (int y2 = y + 5 * q; y2 < y + 2 + 5 * q; ++y2)
                            {
                                for (int z2 = z + 7 + 7 * r; z2 < z + 9 + 7 * r; ++z2)
                                {
                                    level.method_200(x2, y2, z2, 0);
                                }
                            }
                        }
                    }
                    if (r + 1 < 3 && (type == 0 || type == 1 || rand.nextBoolean()) && type != 2)
                    {
                        final int newType = this.rooms[p][q][r + 1];
                        if (newType != 2 && (newType != 1 || type != 1))
                        {
                            this.rooms[p][q][r] = 5;
                            type = 5;
                            final int z2 = z + 11 + 7 * r;
                            for (int y2 = y + 5 * q; y2 < y + 2 + 5 * q; ++y2)
                            {
                                for (int x3 = x + 7 + 7 * p; x3 < x + 9 + 7 * p; ++x3)
                                {
                                    level.method_200(x3, y2, z2, 0);
                                }
                            }
                        }
                    }
                    if (r - 1 > 0 && (type == 0 || type == 1 || rand.nextBoolean()) && type != 2)
                    {
                        final int newType = this.rooms[p][q][r - 1];
                        if (newType != 2 && (newType != 1 || type != 1))
                        {
                            this.rooms[p][q][r] = 6;
                            type = 6;
                            final int z2 = z + 4 + 7 * r;
                            for (int y2 = y + 5 * q; y2 < y + 2 + 5 * q; ++y2)
                            {
                                for (int x4 = x + 7 + 7 * p; x4 < x + 9 + 7 * p; ++x4)
                                {
                                    level.method_200(x4, y2, z2, 0);
                                }
                            }
                        }
                    }
                    final int roomType = rand.nextInt(3);
                    if (type >= 3)
                    {
                        switch (roomType)
                        {
                            case 0:
                            {
                                level.method_154(x + 7 + p * 7, y - 1 + q * 5, z + 7 + r * 7, AetherBlocks.TRAPPED_DUNGEON_STONE.id, 1);
                                break;
                            }
                            case 1:
                            {
                                this.addPlaneY(level, rand, x + 7 + 7 * p, y + 5 * q, z + 7 + 7 * r, 2, 2);
                                int u = x + 7 + 7 * p + rand.nextInt(2);
                                final int v = z + 7 + 7 * r + rand.nextInt(2);
                                if (level.getBlockId(u, y + 5 * q + 1, v) == 0)
                                {
                                    level.setBlock(u, y + 5 * q + 1, v, AetherBlocks.AETHER_CHEST.id);
                                    break;
                                }
                                break;
                            }
                            case 2:
                            {
                                this.addPlaneY(level, rand, x + 7 + 7 * p, y + 5 * q, z + 7 + 7 * r, 2, 2);
                                level.setBlock(x + 7 + 7 * p + rand.nextInt(2), y + 5 * q + 1, z + 7 + 7 * r + rand.nextInt(2), AetherBlocks.CHEST_MIMIC.id);
                                level.setBlock(x + 7 + 7 * p + rand.nextInt(2), y + 5 * q + 1, z + 7 + 7 * r + rand.nextInt(2), AetherBlocks.CHEST_MIMIC.id);
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (x2 = 0; x2 < 24; ++x2)
        {
            for (int z2 = 0; z2 < 20; ++z2)
            {
                final int distance = (int) (Math.sqrt((double) (x2 * x2 + (z2 - 7) * (z2 - 7))) + Math.sqrt((double) (x2 * x2 + (z2 - 12) * (z2 - 12))));
                if (distance == 21)
                {
                    level.method_154(x + 26 + x2, y, z + 5 + z2, this.lockedBlockID2, 1);
                }
                else if (distance > 21)
                {
                    level.method_154(x + 26 + x2, y, z + 5 + z2, this.lockedBlockID1, 1);
                }
            }
        }
        this.setBlocks(this.lockedBlockID1, this.lockedBlockID1, 1);
        this.setMetadata(1, 1);
        this.addPlaneY(level, rand, x + 44, y + 1, z + 11, 6, 8);
        this.addSolidBox(level, rand, x + 46, y + 2, z + 13, 4, 2, 4);
        this.addLineX(level, rand, x + 46, y + 4, z + 13, 4);
        this.addLineX(level, rand, x + 46, y + 4, z + 16, 4);
        this.addPlaneX(level, rand, x + 49, y + 4, z + 13, 4, 4);
        this.setBlocks(Block.WOOL.id, Block.WOOL.id, 1);
        this.setMetadata(11, 11);
        this.addPlaneY(level, rand, x + 47, y + 3, z + 14, 2, 2);
        for (x2 = 0; x2 < 2; ++x2)
        {
            for (int z2 = 0; z2 < 2; ++z2)
            {
                level.method_200(x + 44 + x2 * 5, y + 2, z + 11 + z2 * 7, AetherBlocks.AMBROSIUM_TORCH.id);
            }
        }
        this.setBlocks(Block.WATER.id, Block.WATER.id, 1);
        this.setMetadata(0, 0);
        this.addPlaneY(level, rand, x + 35, y + 1, z + 5, 6, 3);
        this.addPlaneY(level, rand, x + 35, y + 1, z + 22, 6, 3);
        this.setBlocks(this.lockedBlockID1, this.lockedBlockID1, 1);
        this.setMetadata(1, 1);
        this.addLineZ(level, rand, x + 34, y + 1, z + 5, 2);
        this.addLineZ(level, rand, x + 41, y + 1, z + 5, 2);
        this.addLineX(level, rand, x + 36, y + 1, z + 8, 4);
        level.method_154(x + 35, y + 1, z + 7, this.lockedBlockID1, 1);
        level.method_154(x + 40, y + 1, z + 7, this.lockedBlockID1, 1);
        this.addLineZ(level, rand, x + 34, y + 1, z + 23, 2);
        this.addLineZ(level, rand, x + 41, y + 1, z + 23, 2);
        this.addLineX(level, rand, x + 36, y + 1, z + 21, 4);
        level.method_154(x + 35, y + 1, z + 22, this.lockedBlockID1, 1);
        level.method_154(x + 40, y + 1, z + 22, this.lockedBlockID1, 1);
        for (x2 = x + 36; x2 < x + 40; x2 += 3)
        {
            for (int z2 = z + 8; z2 < z + 22; z2 += 13)
            {
                level.method_200(x2, y + 2, z2, AetherBlocks.AMBROSIUM_TORCH.id);
            }
        }
        this.addChandelier(level, x + 28, y, z + 10, 8);
        this.addChandelier(level, x + 43, y, z + 10, 8);
        this.addChandelier(level, x + 43, y, z + 19, 8);
        this.addChandelier(level, x + 28, y, z + 19, 8);
        this.addSapling(level, rand, x + 45, y + 1, z + 6);
        this.addSapling(level, rand, x + 45, y + 1, z + 21);
        final EntityValkyrie valk = new EntityValkyrie(level, x + 40.0, y + 1.5, z + 15.0, true);
        valk.method_1340(x + 40, y + 2, z + 15);
        valk.setDungeon(x + 26, y, z + 5);
        level.method_210(valk);
        this.setBlocks(this.lockedBlockID1, this.lockedBlockID1, 1);
        this.setMetadata(1, 1);
        this.addHollowBox(level, rand, x + 41, y - 2, z + 13, 4, 4, 4);
        x2 = x + 42 + rand.nextInt(2);
        int z2 = z + 14 + rand.nextInt(2);
        BlockTreasureChest.PlaceTreasureChest(level, x2, y - 1, z2, 2);
        level.method_223(x2, y - 1, z2, 2);
        System.out.println("Silver Dungeon: " + x2 + " , " + (y - 1) + " , " + z2);
        return true;
    }

    private void addSapling(final World world, final Random random, final int i, final int j, final int k)
    {
        this.setBlocks(this.lockedBlockID1, this.lockedBlockID1, 1);
        this.setMetadata(1, 1);
        this.addPlaneY(world, random, i, j, k, 3, 3);
        world.method_200(i + 1, j, k + 1, AetherBlocks.AETHER_DIRT.id);
        world.method_200(i + 1, j + 1, k + 1, AetherBlocks.GOLDEN_OAK_SAPLING.id);
        for (int x = i; x < i + 3; x += 2)
        {
            for (int z = k; z < k + 3; z += 2)
            {
                world.method_200(x, j + 1, z, AetherBlocks.AMBROSIUM_TORCH.id);
            }
        }
    }

    private void addChandelier(final World world, final int i, final int j, final int k, final int h)
    {
        for (int y = j + h + 3; y < j + h + 6; ++y)
        {
            world.method_200(i, y, k, Block.FENCE.id);
        }
        for (int x = i - 1; x < i + 2; ++x)
        {
            world.method_200(x, j + h + 1, k, Block.GLOWSTONE.id);
        }
        for (int y = j + h; y < j + h + 3; ++y)
        {
            world.method_200(i, y, k, Block.GLOWSTONE.id);
        }
        for (int z = k - 1; z < k + 2; ++z)
        {
            world.method_200(i, j + h + 1, z, Block.GLOWSTONE.id);
        }
    }

    private void addColumn(final World world, final Random random, final int i, final int j, final int k, final int h)
    {
        this.setBlocks(this.wallBlockID1, this.wallBlockID2, 20);
        this.setMetadata(1, 1);
        this.addPlaneY(world, random, i, j, k, 3, 3);
        this.addPlaneY(world, random, i, j + h, k, 3, 3);
        this.setBlocks(this.columnID, this.columnID, 1);
        this.setMetadata(0, 0);
        this.addLineY(world, random, i + 1, j, k + 1, h - 1);
        world.method_154(i + 1, j + h - 1, k + 1, this.columnID, 1);
    }

    private void addStaircase(final World world, final Random random, final int i, final int j, final int k, final int h)
    {
        this.setBlocks(0, 0, 1);
        this.addSolidBox(world, random, i + 1, j, k + 1, 4, h, 4);
        this.setBlocks(this.lockedBlockID1, this.lockedBlockID2, 5);
        this.setMetadata(1, 1);
        this.addSolidBox(world, random, i + 2, j, k + 2, 2, h + 4, 2);
        world.method_200(i + 1, j + 0, k + 1, Block.SLAB.id);
        world.method_200(i + 2, j + 0, k + 1, Block.DOUBLE_SLAB.id);
        world.method_200(i + 3, j + 1, k + 1, Block.SLAB.id);
        world.method_200(i + 4, j + 1, k + 1, Block.DOUBLE_SLAB.id);
        world.method_200(i + 4, j + 2, k + 2, Block.SLAB.id);
        world.method_200(i + 4, j + 2, k + 3, Block.DOUBLE_SLAB.id);
        world.method_200(i + 4, j + 3, k + 4, Block.SLAB.id);
        world.method_200(i + 3, j + 3, k + 4, Block.DOUBLE_SLAB.id);
        world.method_200(i + 2, j + 4, k + 4, Block.SLAB.id);
        world.method_200(i + 1, j + 4, k + 4, Block.DOUBLE_SLAB.id);
        if (h == 5)
        {
            return;
        }
        world.method_200(i + 1, j + 5, k + 3, Block.SLAB.id);
        world.method_200(i + 1, j + 5, k + 2, Block.DOUBLE_SLAB.id);
        world.method_200(i + 1, j + 6, k + 1, Block.SLAB.id);
        world.method_200(i + 2, j + 6, k + 1, Block.DOUBLE_SLAB.id);
        world.method_200(i + 3, j + 7, k + 1, Block.SLAB.id);
        world.method_200(i + 4, j + 7, k + 1, Block.DOUBLE_SLAB.id);
        world.method_200(i + 4, j + 8, k + 2, Block.SLAB.id);
        world.method_200(i + 4, j + 8, k + 3, Block.DOUBLE_SLAB.id);
        world.method_200(i + 4, j + 9, k + 4, Block.SLAB.id);
        world.method_200(i + 3, j + 9, k + 4, Block.DOUBLE_SLAB.id);
    }
}
