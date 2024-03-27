package com.gildedgames.aether.level.biome;

import com.gildedgames.aether.entity.animal.*;
import com.gildedgames.aether.entity.mobs.EntityAerwhale;
import com.gildedgames.aether.entity.mobs.EntityCockatrice;
import com.gildedgames.aether.entity.mobs.EntityZephyr;
import com.gildedgames.aether.generator.AetherGenGoldenOak;
import com.gildedgames.aether.generator.AetherGenSkyroot;
import net.minecraft.class_288;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.worldgen.biome.BiomeColorProvider;

import java.util.Random;

public class AetherBiome extends Biome
{

    public static int raritySwet;
    public static int rarityAechorPlant;
    public static int rarityCockatrice;
    public static int rarityAerwhale;
    public static int rarityZephyr;
    public static int raritySheepuff;
    public static int rarityPhyg;
    public static int rarityMoa;
    public static int rarityFlyingCow;
    public static int rarityWhirlwind;
    public static int rarityAerbunny;

    static
    {
        raritySwet = 8;
        rarityAechorPlant = 8;
        rarityCockatrice = 3;
        rarityAerwhale = 8;
        rarityZephyr = 5;
        raritySheepuff = 10;
        rarityPhyg = 12;
        rarityMoa = 10;
        rarityFlyingCow = 10;
        rarityWhirlwind = 8;
        rarityAerbunny = 11;
    }

    public AetherBiome()
    {
        spawnableMonsters.clear();
        spawnablePassive.clear();
        spawnableWaterCreatures.clear();
        if (raritySwet != 0)
        {
            this.spawnablePassive.add(new class_288(EntitySwet.class, raritySwet));
        }
        if (rarityAechorPlant != 0)
        {
            this.spawnablePassive.add(new class_288(EntityAechorPlant.class, rarityAechorPlant));
        }
        if (rarityCockatrice != 0)
        {
            this.spawnableMonsters.add(new class_288(EntityCockatrice.class, rarityCockatrice));
        }
        if (rarityAerwhale != 0)
        {
            this.spawnableMonsters.add(new class_288(EntityAerwhale.class, rarityAerwhale));
        }
        if (rarityZephyr != 0)
        {
            this.spawnableMonsters.add(new class_288(EntityZephyr.class, rarityZephyr));
        }
        if (raritySheepuff != 0)
        {
            this.spawnablePassive.add(new class_288(EntitySheepuff.class, raritySheepuff));
        }
        if (rarityPhyg != 0)
        {
            this.spawnablePassive.add(new class_288(EntityPhyg.class, rarityPhyg));
        }
        if (rarityMoa != 0)
        {
            this.spawnablePassive.add(new class_288(EntityMoa.class, rarityMoa));
        }
        if (rarityFlyingCow != 0)
        {
            this.spawnablePassive.add(new class_288(EntityFlyingCow.class, rarityFlyingCow));
        }
        /* todo: particles fix
        if (rarityWhirlwind != 0) {
            this.creatures.add(new EntityEntry(Whirly.class, rarityWhirlwind));
        }*/
        if (rarityAerbunny != 0)
        {
            this.spawnablePassive.add(new class_288(EntityAerbunny.class, rarityAerbunny));
        }
    }

    @Override
    public Feature getRandomTreeFeature(Random rand)
    {
        if (rand.nextInt(100) == 0)
        {
            return new AetherGenGoldenOak();
        }
        return new AetherGenSkyroot();
    }

    @Override
    public int method_796(float temperature)
    {
        return 0xc0c0ff;
    }


}
