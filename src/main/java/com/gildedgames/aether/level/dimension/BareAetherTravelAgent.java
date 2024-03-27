package com.gildedgames.aether.level.dimension;

import net.minecraft.class_467;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import java.util.Random;

public class BareAetherTravelAgent extends class_467
{

    private final Random random = new Random();

    @Override
    public boolean method_1531(World level, Entity entity)
    {
        return true;
    }

    @Override
    public boolean method_1532(World level, Entity entity)
    {
        return true;
    }

    public boolean blockIsGood(int block, int meta)
    {
        return true;
    }
}
