package com.gildedgames.aether.client.render.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.Tessellator;
import net.minecraft.world.World;

public class EntityCloudSmokeFX extends Particle
{
    float field_671_a;

    public EntityCloudSmokeFX(final World world, final double x, final double y, final double z, final double initialMotionX, final double initialMotionY, final double intialMotionZ, final float size, final float red, final float blue, final float green)
    {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.velocityX *= 0.10000000149011612;
        this.velocityY *= 0.10000000149011612;
        this.velocityZ *= 0.10000000149011612;
        this.velocityX += initialMotionX;
        this.velocityY += initialMotionY;
        this.velocityZ += intialMotionZ;
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.scale *= 0.75f;
        this.scale *= size;
        this.field_671_a = this.scale;
        this.maxAge = (int) (8.0 / (Math.random() * 0.8 + 0.2));
        this.maxAge *= (int) size;
        this.field_1642 = false;
    }

    @Override
    public void method_2002(final Tessellator tessellator, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        float f6 = (this.field_2638 + f) / this.maxAge * 32.0f;
        if (f6 < 0.0f)
        {
            f6 = 0.0f;
        }
        if (f6 > 1.0f)
        {
            f6 = 1.0f;
        }
        this.scale = this.field_671_a * f6;
        super.method_2002(tessellator, f, f1, f2, f3, f4, f5);
    }

    @Override
    public void tick()
    {
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        if (this.field_2638++ >= this.maxAge)
        {
            this.markDead();
        }
        this.field_2635 = 7 - this.field_2638 * 8 / this.maxAge;
        this.velocityY += 0.004;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        if (this.y == this.prevY)
        {
            this.velocityX *= 1.1;
            this.velocityZ *= 1.1;
        }
        this.velocityX *= 0.9599999785423279;
        this.velocityY *= 0.9599999785423279;
        this.velocityZ *= 0.9599999785423279;
        if (this.field_1623)
        {
            this.velocityX *= 0.699999988079071;
            this.velocityZ *= 0.699999988079071;
        }
    }
}
