package com.gildedgames.aether.client.render.entity;

import com.gildedgames.aether.entity.mobs.EntitySentry;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;

public class RenderSentry extends LivingEntityRenderer
{
    public RenderSentry(EntityModelBase arg, float f) {
        super(arg, f);
        this.setModel(arg);
    }

    @Override
    protected void method_823(Living arg, float f) {
        float f1 = 1.75F;
        GL11.glScalef(f1,f1,f1);
        super.method_823(arg,f);
    }

    protected boolean render(EntitySentry sentry, int i, float f) {
        if (i != 0) {
            return false;
        } else if (i != 0) {
            return false;
        } else if (sentry.active) {
            this.bindTexture("aether:textures/entity/SentryEye.png");
            float f1 = (1.0F - sentry.getBrightnessAtEyes(1.0F)) * 0.75F;
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean method_819(Living arg, int i, float f) {
        return this.render((EntitySentry) arg, i, f);
    }
}
