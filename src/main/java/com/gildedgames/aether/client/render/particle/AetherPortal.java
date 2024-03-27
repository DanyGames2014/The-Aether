package com.gildedgames.aether.client.render.particle;

import net.minecraft.client.particle.PortalParticle;
import net.minecraft.world.World;

public class AetherPortal extends PortalParticle
{

    public AetherPortal(World arg, double d, double d1, double d2, double d3, double d4, double d5)
    {
        super(arg, d, d1, d2, d3, d4, d5);
        red = green = blue = random.nextFloat() * 0.6F + 0.4F;
        red *= 0.2F;
        green *= 0.2F;
    }
}
