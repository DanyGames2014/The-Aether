package com.gildedgames.aether.level.gen;

import com.gildedgames.aether.level.biome.AetherBiomes;
import net.minecraft.class_519;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;

import java.util.Arrays;

public class AetherBiomeSource extends class_519
{

    private final Biome biome;
    private final double temperature;

    public AetherBiomeSource(double temperature)
    {
        biome = AetherBiomes.AETHER;
        this.temperature = temperature;
    }

    @Override
    public Biome method_1789(ChunkPos pos)
    {
        return biome;
    }

    @Override
    public Biome method_1787(int x, int z)
    {
        return biome;
    }

    @Override
    public double method_1786(int x, int z)
    {
        return temperature;
    }

    @Override
    public double[] method_1790(double[] temperatures, int x, int z, int xSize, int zSize)
    {
        if (temperatures == null || temperatures.length < xSize * zSize)
            temperatures = new double[xSize * zSize];
        Arrays.fill(temperatures, 0, xSize * zSize, temperature);
        return temperatures;
    }

    @Override
    public Biome[] method_1791(Biome[] biomes, int x, int z, int xSize, int zSize)
    {
        if (biomes == null || biomes.length < xSize * zSize)
            biomes = new Biome[xSize * zSize];
        if (field_2235 == null || field_2235.length < xSize * zSize)
        {
            field_2235 = new double[xSize * zSize];
            field_2236 = new double[xSize * zSize];
        }
        Arrays.fill(biomes, 0, xSize * zSize, biome);
        return biomes;
    }
}
