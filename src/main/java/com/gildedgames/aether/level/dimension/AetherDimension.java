package com.gildedgames.aether.level.dimension;

import com.gildedgames.aether.level.gen.AetherBiomeSource;
import com.gildedgames.aether.level.source.AetherLevelSource;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.block.Block;
import net.minecraft.class_51;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.client.world.dimension.TravelMessageProvider;

import static com.gildedgames.aether.AetherMod.of;

@EnvironmentInterface(value = EnvType.CLIENT, itf = TravelMessageProvider.class)
public class AetherDimension extends Dimension implements TravelMessageProvider
{

    public static final String
            ENTERING_MESSAGE = "gui." + of("ascending"),
            LEAVING_MESSAGE = "gui." + of("descending");

    private final float[] colours = new float[4];

    public AetherDimension(int serialId)
    {
        id = serialId;
    }

    @Override
    protected void method_1769()
    {
        field_2174 = new AetherBiomeSource(1);
    }

    @Override
    public class_51 method_1772()
    {
        return new AetherLevelSource(world, world.getSeed());
    }

    @Override
    public float method_1771(final long time, final float delta)
    {
        /* todo: sun pos
        final boolean hasKilledGold = MinecraftClientAccessor.getMCinstance().statFileWriter.isAchievementUnlocked(AetherAchievements.defeatGold);
        if (hasKilledGold) {
            int timeTicks = (int)(time % 80000L);
            float timeFraction = (timeTicks + delta) / 120000.0f - 0.25f;
            if (timeTicks > 60000) {
                timeTicks -= 40000;
                timeFraction = (timeTicks + delta) / 20000.0f - 0.25f;
            }
            if (timeFraction < 0.0f) {
                ++timeFraction;
            }
            if (timeFraction > 1.0f) {
                --timeFraction;
            }
            final float f2 = timeFraction;
            timeFraction = 1.0f - (float)((Math.cos(timeFraction * 3.141592653589793) + 1.0) / 2.0);
            timeFraction = f2 + (timeFraction - f2) / 3.0f;
            return timeFraction;
        }*/
        return 0.0f;
    }

    @Override
    public float[] method_1761(float time, float delta)
    {
        float f2 = 0.4F;
        float f3 = MathHelper.cos(time * 3.141593F * 2.0F) - 0.0F;
        float f4 = -0F;
        if (f3 >= f4 - f2 && f3 <= f4 + f2)
        {
            float f5 = ((f3 - f4) / f2) * 0.5F + 0.5F;
            float f6 = 1.0F - (1.0F - MathHelper.sin(f5 * 3.141593F)) * 0.99F;
            f6 *= f6;
            colours[0] = f5 * 0.3F + 0.1F;
            colours[1] = f5 * f5 * 0.7F + 0.2F;
            colours[2] = f5 * f5 * 0.7F + 0.2F;
            colours[3] = f6;
            return colours;
        }
        else
            return null;
    }

    @Override
    public Vec3d method_1762(float time, float delta)
    {
        int i = 0x8080a0;
        float f2 = MathHelper.cos(time * 3.141593F * 2.0F) * 2.0F + 0.5F;
        if (f2 < 0.0F)
            f2 = 0.0F;
        if (f2 > 1.0F)
            f2 = 1.0F;
        float f3 = (float) (i >> 16 & 0xff) / 255F;
        float f4 = (float) (i >> 8 & 0xff) / 255F;
        float f5 = (float) (i & 0xff) / 255F;
        f3 *= f2 * 0.94F + 0.06F;
        f4 *= f2 * 0.94F + 0.06F;
        f5 *= f2 * 0.91F + 0.09F;
        return Vec3d.createCached(f3, f4, f5);
    }

    @Override
    public boolean method_1763()
    {
        return false;
    }

    @Override
    public float method_1764()
    {
        return 8;
    }

    @Override
    public boolean method_1770(int x, int y)
    {
        int var3 = this.world.method_152(x, y);
        return var3 != 0 && Block.BLOCKS[var3].material.method_907();
    }

    @Override
    public boolean method_1766()
    {
        return false;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public String getEnteringTranslationKey()
    {
        return ENTERING_MESSAGE;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public String getLeavingTranslationKey()
    {
        return LEAVING_MESSAGE;
    }
}
