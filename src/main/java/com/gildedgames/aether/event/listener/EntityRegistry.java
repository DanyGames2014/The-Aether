package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.entity.EntityCloudParachute;
import com.gildedgames.aether.entity.animal.*;
import com.gildedgames.aether.entity.boss.EntityFireMonster;
import com.gildedgames.aether.entity.boss.EntitySlider;
import com.gildedgames.aether.entity.boss.EntityValkyrie;
import com.gildedgames.aether.entity.mobs.*;
import com.gildedgames.aether.entity.projectile.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.registry.Registry;

import static com.gildedgames.aether.AetherMod.MODID;
import static net.modificationstation.stationapi.api.util.Identifier.of;

public class EntityRegistry
{
    @EventListener
    public void registerMobHandlers(MobHandlerRegistryEvent event)
    {
        Registry.register(event.registry, of(MODID, "FlyingCow"), EntityFlyingCow::new);
        Registry.register(event.registry, of(MODID, "Zephyr"), EntityZephyr::new);
        Registry.register(event.registry, of(MODID, "Slider"), EntitySlider::new);
        Registry.register(event.registry, of(MODID, "Sentry"), EntitySentry::new);
        Registry.register(event.registry, of(MODID, "Mimic"), EntityMimic::new);
        Registry.register(event.registry, of(MODID, "Valkyrie"), EntityValkyrie::new);
        Registry.register(event.registry, of(MODID, "Homeshot"), EntityHomeShot::new);
        Registry.register(event.registry, of(MODID, "SunSpirit"), EntityFireMonster::new);
        Registry.register(event.registry, of(MODID, "FireMinion"), EntityFireMinion::new);
        Registry.register(event.registry, of(MODID, "Moa"), EntityMoa::new);
        Registry.register(event.registry, of(MODID, "Aerwhale"), EntityAerwhale::new);
        Registry.register(event.registry, of(MODID, "AechorPlant"), EntityAechorPlant::new);
        Registry.register(event.registry, of(MODID, "Aerbunny"), EntityAerbunny::new);
        Registry.register(event.registry, of(MODID, "Cockatrice"), EntityCockatrice::new);
        Registry.register(event.registry, of(MODID, "Sheepuff"), EntitySheepuff::new);
        Registry.register(event.registry, of(MODID, "Phyg"), EntityPhyg::new);
        Registry.register(event.registry, of(MODID, "Swet"), EntitySwet::new);
        Registry.register(event.registry, of(MODID, "Minicloud"), EntityMiniCloud::new);
        Registry.register(event.registry, of(MODID, "Whirly"), Whirly::new);
    }

    @EventListener
    public void registerEntities(EntityRegister event)
    {
        event.register(EntityCockatrice.class, "Cockatrice");
        event.register(EntitySheepuff.class, "Sheepuff");
        event.register(EntityPhyg.class, "Phyg");
        event.register(EntitySwet.class, "Swet");
        event.register(EntityFlyingCow.class, "FlyingCow");
        event.register(EntityZephyr.class, "Zephyr");
        event.register(EntityZephyrSnowball.class, "ZephyrSnowball");
        event.register(EntitySentry.class, "Sentry");
        event.register(EntityMimic.class, "Mimic");
        event.register(EntityMoa.class, "Moa");
        event.register(EntitySlider.class, "Slider");
        event.register(EntityValkyrie.class, "Valkyrie");
        event.register(EntityHomeShot.class, "Homeshot");
        event.register(EntityPoisonNeedle.class, "PoisonNeedle");
        event.register(EntityDartPoison.class, "PoisonDart");
        event.register(EntityDartGolden.class, "GoldenDart");
        event.register(EntityDartEnchanted.class, "EnchantedDart");
        event.register(EntityFireMonster.class, "SunSpirit");
        event.register(EntityFireMinion.class, "FireMinion");
        event.register(EntityFiroBall.class, "Fireball");
        event.register(EntityFloatingBlock.class, "FloatingBlock");
        event.register(EntityAerwhale.class, "Aerwhale");
        event.register(EntityAechorPlant.class, "AechorPlant");
        event.register(EntityAerbunny.class, "Aerbunny");
        event.register(EntityCloudParachute.class, "Parachute");
        event.register(EntityMiniCloud.class, "Minicloud");
        event.register(EntityFlamingArrow.class, "FlamingArrow");
        event.register(Whirly.class, "Whirly");
    }
}
