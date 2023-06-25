package com.gildedgames.aether.client.render.entity;
import org.lwjgl.opengl.GL11;

import com.gildedgames.aether.entity.boss.EntitySlider;

import net.minecraft.entity.Living;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.client.render.entity.LivingEntityRenderer;

public class RenderSlider extends LivingEntityRenderer {
    public RenderSlider(final EntityModelBase ms, final float f) {
        super(ms, f);
        this.model = ms;
    }
    
    @Override
    protected void method_823(final Living entityliving, final float f) {
        final EntitySlider e1 = (EntitySlider)entityliving;
        if (e1.harvey > 0.01f) {
            GL11.glRotatef(e1.harvey * -30.0f, (float)e1.rennis, 0.0f, (float)e1.dennis);
        }
    }
    
    protected boolean setSliderEyeBrightness(final EntitySlider slider, final int i, final float f) {
        if (i != 0) {
            return false;
        }
        if (slider.awake) {
            if (slider.criticalCondition()) {
                this.bindTexture("aether:textures/entity/sliderAwakeGlow_red.png");
            }
            else {
                this.bindTexture("aether:textures/entity/sliderAwakeGlow.png");
            }
        }
        else {
            this.bindTexture("aether:textures/entity/sliderSleepGlow.png");
        }
        final float f2 = (1.0f - slider.getBrightnessAtEyes(1.0f)) * 0.5f;
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, f2);
        return true;
    }
    
    @Override
    protected boolean render(final Living entityliving, final int i, final float f) {
        return this.setSliderEyeBrightness((EntitySlider)entityliving, i, f);
    }
}
