package com.gildedgames.aether.client.render;

import com.gildedgames.aether.client.render.particle.AetherPortal;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.class_59;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import static com.gildedgames.aether.AetherMod.of;

//@RequiredArgsConstructor
public class AetherWorldRenderer implements class_59
{

    private final Minecraft client;
    public World level;

    public AetherWorldRenderer(Minecraft gameInstance)
    {
        client = gameInstance;
    }

    public void updateLevel(World level)
    {
        if (this.level != null)
            this.level.method_281(this);
        this.level = level;
        if (level != null)
            level.method_183(this);
    }

    @Override
    public void method_1149(int i, int j, int k)
    {

    }

    @Override
    public void method_1150(int i, int j, int k, int i1, int j1, int k1)
    {

    }

    @Override
    public void method_1154(String string, double d, double d1, double d2, float f, float f1)
    {

    }

    @Override
    public void method_1153(String particleId, double x, double y, double z, double xTo, double yTo, double zTo)
    {
        if (client != null && client.field_2807 != null && client.field_2808 != null)
        {
            double var14 = client.field_2807.x - x;
            double var16 = client.field_2807.y - y;
            double var18 = client.field_2807.z - z;
            double var20 = 16;
            if (!(var14 * var14 + var16 * var16 + var18 * var18 > var20 * var20))
            {
                if (particleId.equals(of("aether_portal").toString()))
                {
                    client.field_2808.method_325(new AetherPortal(level, x, y, z, xTo, yTo, zTo));
                }
            }
        }
    }

    @Override
    public void loadEntitySkin(Entity arg)
    {

    }

    @Override
    public void unloadEntitySkin(Entity arg)
    {

    }

    @Override
    public void method_1148()
    {

    }

    @Override
    public void method_1155(String string, int i, int j, int k)
    {

    }

    @Override
    public void method_1151(int i, int j, int k, BlockEntity arg)
    {

    }

    @Override
    public void method_1152(PlayerEntity arg, int i, int j, int k, int i1, int j1)
    {

    }
}
