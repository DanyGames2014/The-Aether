package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.client.render.entity.*;
import com.gildedgames.aether.client.render.model.*;
import com.gildedgames.aether.entity.EntityCloudParachute;
import com.gildedgames.aether.entity.animal.*;
import com.gildedgames.aether.entity.boss.EntityFireMonster;
import com.gildedgames.aether.entity.boss.EntitySlider;
import com.gildedgames.aether.entity.boss.EntityValkyrie;
import com.gildedgames.aether.entity.mobs.*;
import com.gildedgames.aether.entity.projectile.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.render.entity.LightningEntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;

public class EntityRendererListener
{
    @EventListener
    private static void registerEntity(EntityRendererRegisterEvent event)
    {
        event.renderers.put(EntityFlyingCow.class, new RenderFlyingCow(new ModelFlyingCow1(), new ModelFlyingCow2(), 0.7f));
        event.renderers.put(EntityZephyr.class, new RenderZephyr());
        event.renderers.put(EntityZephyrSnowball.class, new RenderZephyrSnowball());
        event.renderers.put(EntitySlider.class, new RenderSlider(new ModelSlider(0.0f, 12.0f), 1.5f));
        event.renderers.put(EntityValkyrie.class, new RenderValkyrie(new ModelValkyrie(), 0.3f));
        event.renderers.put(EntityPoisonNeedle.class, new RenderPoisonNeedle());
        event.renderers.put(EntityDartPoison.class, new RenderDartPoison());
        event.renderers.put(EntityDartGolden.class, new RenderDartGolden());
        event.renderers.put(EntityDartEnchanted.class, new RenderDartEnchanted());
        event.renderers.put(EntityFireMonster.class, new SkeletonEntityRenderer(new ModelFireMonster(0.0f, 0.0f), 0.4f));
        event.renderers.put(EntityFireMinion.class, new SkeletonEntityRenderer(new ModelFireMinion(0.0f, 0.0f), 0.4f));
        event.renderers.put(EntityFiroBall.class, new RenderFiroBall(new ModelHomeShot(0.5f, 0.0f), 0.25f));
        event.renderers.put(EntityFloatingBlock.class, new RenderFloatingBlock());
        event.renderers.put(EntityMimic.class, new RenderMimic());
        event.renderers.put(EntityMoa.class, new RenderMoa(new ModelMoa(), 1.0f));
        event.renderers.put(EntityAerwhale.class, new RenderAerwhale());
        event.renderers.put(EntityCockatrice.class, new RenderCockatrice(new ModelCockatrice(), 1.0f));
        event.renderers.put(EntitySheepuff.class, new RenderSheepuff(new ModelSheepuff1(), new ModelSheepuff2(), new ModelSheepuff3(), 0.7f));
        event.renderers.put(EntityPhyg.class, new RenderPhyg(new ModelFlyingPig1(), new ModelFlyingPig2(), 0.7f));
        event.renderers.put(EntitySwet.class, new RenderSwet(new SlimeEntityModel(16), new SlimeEntityModel(0), 0.3f));
        event.renderers.put(EntityAechorPlant.class, new RenderAechorPlant(new ModelAechorPlant(), 0.3f));
        event.renderers.put(EntityAerbunny.class, new RenderAerbunny(new ModelAerbunny(), 0.3f));
        event.renderers.put(EntityCloudParachute.class, new RenderCloudParachute());
        event.renderers.put(EntityFlamingArrow.class, new RenderFlamingArrow());
        event.renderers.put(EntityHomeShot.class, new RenderHomeShot(new ModelHomeShot(0.0f, 0.0f), 0.2f));
        //event.renderers.put(EntityNotchWave.class, new RenderNotchWave());
        event.renderers.put(EntityAetherLightning.class, new LightningEntityRenderer());
        //event.renderers.put(EntityLightningKnife.class, new RenderLightningKnife());
        event.renderers.put(EntitySentry.class, new RenderSentry(new SlimeEntityModel(0), 0.2f));
        event.renderers.put(EntityMiniCloud.class, new LivingEntityRenderer(new ModelMiniCloud(0.0f, 20.0f), 0.35f));
        event.renderers.put(Whirly.class, new RenderWhirly());
    }
}