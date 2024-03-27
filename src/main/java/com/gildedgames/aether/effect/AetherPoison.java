package com.gildedgames.aether.effect;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class AetherPoison
{
    public static long clock;
    public static final float poisonRed = 1.0f;
    public static final float poisonBlue = 1.0f;
    public static final float poisonGreen = 0.0f;
    public static final float cureRed = 0.0f;
    public static final float cureBlue = 0.1f;
    public static final float cureGreen = 1.0f;
    public static int poisonTime;
    public static final int poisonInterval = 50;
    public static final int poisonDmg = 1;
    public static final int poisonHurts = 10;
    public static final int maxPoisonTime = 500;
    public static World world;
    public static double rotDFac;
    public static double rotD;
    public static double rotTaper;
    public static double motTaper;
    public static double motD;
    public static double motDFac;

    public AetherPoison()
    {

    }

    public static boolean canPoison(final Entity entity)
    {
        return false;
    }

    public static void distractEntity(final Entity ent)
    {
    }

    public static boolean afflictPoison()
    {
        return false;
    }

    public static boolean curePoison()
    {
        return false;
    }

    public static float getPoisonAlpha(final float f)
    {
        return f * f / 5.0f + 0.4f;
    }

    public static float getCureAlpha(final float f)
    {
        return f * f / 10.0f + 0.4f;
    }

    public static void displayCureEffect()
    {
    }

    public static void displayPoisonEffect(final int mod)
    {
    }

    public static void flashColor(final String file, final float a)
    {
    }

    static
    {
        AetherPoison.rotDFac = 0.7853981633974483;
        AetherPoison.rotTaper = 0.125;
        AetherPoison.motTaper = 0.2;
        AetherPoison.motDFac = 0.1;
    }
}
