package com.gildedgames.aether.generator;

import net.minecraft.level.Level;
import net.minecraft.level.structure.Structure;

import java.util.Random;

public class AetherGenBuildings extends Structure
{
    public int blockID1;
    public int blockID2;
    public int meta1;
    public int meta2;
    public int chance;
    public boolean replaceAir;
    public boolean replaceSolid;

    @Override
    public boolean generate(final Level level, final Random rand, final int x, final int y, final int z)
    {
        return false;
    }

    public void setBlocks(final int i, final int j, final int k)
    {
        this.blockID1 = i;
        this.blockID2 = j;
        this.chance = k;
        if (this.chance < 1)
        {
            this.chance = 1;
        }
    }

    public void setMetadata(final int i, final int j)
    {
        this.meta1 = i;
        this.meta2 = j;
    }

    public void addLineX(final Level world, final Random random, final int i, final int j, final int k, final int length)
    {
        for (int x = i; x < i + length; ++x)
        {
            if ((this.replaceAir || world.getTileId(x, j, k) != 0) && (this.replaceSolid || world.getTileId(x, j, k) == 0))
            {
                if (random.nextInt(this.chance) == 0)
                {
                    world.setTileWithMetadata(x, j, k, this.blockID2, this.meta2);
                }
                else
                {
                    world.setTileWithMetadata(x, j, k, this.blockID1, this.meta1);
                }
            }
        }
    }

    public void addLineY(final Level world, final Random random, final int i, final int j, final int k, final int length)
    {
        for (int y = j; y < j + length; ++y)
        {
            if ((this.replaceAir || world.getTileId(i, y, k) != 0) && (this.replaceSolid || world.getTileId(i, y, k) == 0))
            {
                if (random.nextInt(this.chance) == 0)
                {
                    world.setTileWithMetadata(i, y, k, this.blockID2, this.meta2);
                }
                else
                {
                    world.setTileWithMetadata(i, y, k, this.blockID1, this.meta1);
                }
            }
        }
    }

    public void addLineZ(final Level world, final Random random, final int i, final int j, final int k, final int length)
    {
        for (int z = k; z < k + length; ++z)
        {
            if ((this.replaceAir || world.getTileId(i, j, z) != 0) && (this.replaceSolid || world.getTileId(i, j, z) == 0))
            {
                if (random.nextInt(this.chance) == 0)
                {
                    world.setTileWithMetadata(i, j, z, this.blockID2, this.meta2);
                }
                else
                {
                    world.setTileWithMetadata(i, j, z, this.blockID1, this.meta1);
                }
            }
        }
    }

    public void addPlaneX(final Level world, final Random random, final int i, final int j, final int k, final int dj, final int dk)
    {
        for (int y = j; y < j + dj; ++y)
        {
            for (int z = k; z < k + dk; ++z)
            {
                if ((this.replaceAir || world.getTileId(i, y, z) != 0) && (this.replaceSolid || world.getTileId(i, y, z) == 0))
                {
                    if (random.nextInt(this.chance) == 0)
                    {
                        world.setTileWithMetadata(i, y, z, this.blockID2, this.meta2);
                    }
                    else
                    {
                        world.setTileWithMetadata(i, y, z, this.blockID1, this.meta1);
                    }
                }
            }
        }
    }

    public void addPlaneY(final Level world, final Random random, final int i, final int j, final int k, final int di, final int dk)
    {
        for (int x = i; x < i + di; ++x)
        {
            for (int z = k; z < k + dk; ++z)
            {
                if ((this.replaceAir || world.getTileId(x, j, z) != 0) && (this.replaceSolid || world.getTileId(x, j, z) == 0))
                {
                    if (random.nextInt(this.chance) == 0)
                    {
                        world.setTileWithMetadata(x, j, z, this.blockID2, this.meta2);
                    }
                    else
                    {
                        world.setTileWithMetadata(x, j, z, this.blockID1, this.meta1);
                    }
                }
            }
        }
    }

    public void addPlaneZ(final Level world, final Random random, final int i, final int j, final int k, final int di, final int dj)
    {
        for (int x = i; x < i + di; ++x)
        {
            for (int y = j; y < j + dj; ++y)
            {
                if ((this.replaceAir || world.getTileId(x, y, k) != 0) && (this.replaceSolid || world.getTileId(x, y, k) == 0))
                {
                    if (random.nextInt(this.chance) == 0)
                    {
                        world.setTileWithMetadata(x, y, k, this.blockID2, this.meta2);
                    }
                    else
                    {
                        world.setTileWithMetadata(x, y, k, this.blockID1, this.meta1);
                    }
                }
            }
        }
    }

    public void addHollowBox(final Level world, final Random random, final int i, final int j, final int k, final int di, final int dj, final int dk)
    {
        final int temp1 = this.blockID1;
        final int temp2 = this.blockID2;
        this.setBlocks(0, 0, this.chance);
        this.addSolidBox(world, random, i, j, k, di, dj, dk);
        this.setBlocks(temp1, temp2, this.chance);
        this.addPlaneY(world, random, i, j, k, di, dk);
        this.addPlaneY(world, random, i, j + dj - 1, k, di, dk);
        this.addPlaneX(world, random, i, j, k, dj, dk);
        this.addPlaneX(world, random, i + di - 1, j, k, dj, dk);
        this.addPlaneZ(world, random, i, j, k, di, dj);
        this.addPlaneZ(world, random, i, j, k + dk - 1, di, dj);
    }

    public void addSquareTube(final Level world, final Random random, final int i, final int j, final int k, final int di, final int dj, final int dk, final int dir)
    {
        final int temp1 = this.blockID1;
        final int temp2 = this.blockID2;
        this.setBlocks(0, 0, this.chance);
        this.addSolidBox(world, random, i, j, k, di, dj, dk);
        this.setBlocks(temp1, temp2, this.chance);
        if (dir == 0 || dir == 2)
        {
            this.addPlaneY(world, random, i, j, k, di, dk);
            this.addPlaneY(world, random, i, j + dj - 1, k, di, dk);
        }
        if (dir == 1 || dir == 2)
        {
            this.addPlaneX(world, random, i, j, k, dj, dk);
            this.addPlaneX(world, random, i + di - 1, j, k, dj, dk);
        }
        if (dir == 0 || dir == 1)
        {
            this.addPlaneZ(world, random, i, j, k, di, dj);
            this.addPlaneZ(world, random, i, j, k + dk - 1, di, dj);
        }
    }

    public void addSolidBox(final Level world, final Random random, final int i, final int j, final int k, final int di, final int dj, final int dk)
    {
        for (int x = i; x < i + di; ++x)
        {
            for (int y = j; y < j + dj; ++y)
            {
                for (int z = k; z < k + dk; ++z)
                {
                    if ((this.replaceAir || world.getTileId(x, y, z) != 0) && (this.replaceSolid || world.getTileId(x, y, z) == 0))
                    {
                        if (random.nextInt(this.chance) == 0)
                        {
                            world.setTileWithMetadata(x, y, z, this.blockID2, this.meta2);
                        }
                        else
                        {
                            world.setTileWithMetadata(x, y, z, this.blockID1, this.meta1);
                        }
                    }
                }
            }
        }
    }

    public boolean isBoxSolid(final Level world, final int i, final int j, final int k, final int di, final int dj, final int dk)
    {
        boolean flag = true;
        for (int x = i; x < i + di; ++x)
        {
            for (int y = j; y < j + dj; ++y)
            {
                for (int z = k; z < k + dk; ++z)
                {
                    if (world.getTileId(x, y, z) == 0)
                    {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public boolean isBoxEmpty(final Level world, final int i, final int j, final int k, final int di, final int dj, final int dk)
    {
        boolean flag = true;
        for (int x = i; x < i + di; ++x)
        {
            for (int y = j; y < j + dj; ++y)
            {
                for (int z = k; z < k + dk; ++z)
                {
                    if (world.getTileId(x, y, z) != 0)
                    {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }
}
