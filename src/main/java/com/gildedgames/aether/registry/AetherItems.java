package com.gildedgames.aether.registry;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ItemBase;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;

import com.gildedgames.aether.item.ItemAmbrosium;
@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class AetherItems {
    @Entrypoint.ModID
    private static ModID MOD_ID;
    @EventListener
    private static void registerItems(ItemRegistryEvent event) {
    	AmbrosiumShard = new ItemAmbrosium(Identifier.of(MOD_ID, "ambrosium_shard"), 1).setTranslationKey("AmbrosiumShard");
    }
    
    
    public static double motionOffset;
    public static double ybuff;
    public static ItemBase VictoryMedal;
    public static ItemBase Key;
    public static ItemBase LoreBook;
    public static ItemBase MoaEgg;
    public static ItemBase AechorPetal;
    public static ItemBase GoldenAmber;
    public static ItemBase Stick;
    public static ItemBase Dart;
    public static ItemBase DartShooter;
    public static ItemBase AmbrosiumShard;
    public static ItemBase Zanite;
    public static ItemBase BlueMusicDisk;
    public static ItemBase Bucket;
    public static ItemBase PickSkyroot;
    public static ItemBase PickHolystone;
    public static ItemBase PickZanite;
    public static ItemBase PickGravitite;
    public static ItemBase ShovelSkyroot;
    public static ItemBase ShovelHolystone;
    public static ItemBase ShovelZanite;
    public static ItemBase ShovelGravitite;
    public static ItemBase AxeSkyroot;
    public static ItemBase AxeHolystone;
    public static ItemBase AxeZanite;
    public static ItemBase AxeGravitite;
    public static ItemBase SwordSkyroot;
    public static ItemBase SwordHolystone;
    public static ItemBase SwordZanite;
    public static ItemBase SwordGravitite;
    public static ItemBase IronBubble;
    public static ItemBase PigSlayer;
    public static ItemBase VampireBlade;
    public static ItemBase NatureStaff;
    public static ItemBase SwordFire;
    public static ItemBase SwordLightning;
    public static ItemBase SwordHoly;
    public static ItemBase LightningKnife;
    public static ItemBase GummieSwet;
    public static ItemBase HammerNotch;
    public static ItemBase PhoenixBow;
    public static ItemBase PhoenixHelm;
    public static ItemBase PhoenixBody;
    public static ItemBase PhoenixLegs;
    public static ItemBase PhoenixBoots;
    public static ItemBase ObsidianHelm;
    public static ItemBase ObsidianBody;
    public static ItemBase ObsidianLegs;
    public static ItemBase ObsidianBoots;
    public static ItemBase CloudStaff;
    public static ItemBase CloudParachute;
    public static ItemBase CloudParachuteGold;
    public static ItemBase GravititeHelmet;
    public static ItemBase GravititeBodyplate;
    public static ItemBase GravititePlatelegs;
    public static ItemBase GravititeBoots;
    public static ItemBase ZaniteHelmet;
    public static ItemBase ZaniteChestplate;
    public static ItemBase ZaniteLeggings;
    public static ItemBase ZaniteBoots;
    public static ItemBase LifeShard;
    public static ItemBase GoldenFeather;
    public static ItemBase Lance;
    public static ItemBase RepShield;
    public static ItemBase AetherCape;
    public static ItemBase IronRing;
    public static ItemBase GoldRing;
    public static ItemBase ZaniteRing;
    public static ItemBase IronPendant;
    public static ItemBase GoldPendant;
    public static ItemBase ZanitePendant;
    public static ItemBase LeatherGlove;
    public static ItemBase IronGlove;
    public static ItemBase GoldGlove;
    public static ItemBase DiamondGlove;
    public static ItemBase ZaniteGlove;
    public static ItemBase GravititeGlove;
    public static ItemBase PhoenixGlove;
    public static ItemBase ObsidianGlove;
    public static ItemBase NeptuneGlove;
    public static ItemBase NeptuneHelmet;
    public static ItemBase NeptuneChestplate;
    public static ItemBase NeptuneLeggings;
    public static ItemBase NeptuneBoots;
    public static ItemBase RegenerationStone;
    public static ItemBase InvisibilityCloak;
    public static ItemBase AxeValkyrie;
    public static ItemBase PickValkyrie;
    public static ItemBase ShovelValkyrie;
    public static ItemBase HealingStone;
    public static ItemBase AgilityCape;
    public static ItemBase WhiteCape;
    public static ItemBase RedCape;
    public static ItemBase YellowCape;
    public static ItemBase BlueCape;
    public static ItemBase IceRing;
    public static ItemBase IcePendant;
    private static int ticks;
    private static boolean jumpBoosted;
    public static int ElementalSwordIcon;
    public static int gravArmour;
    public static int zaniteArmour;
    public static int neptuneArmour;
    private static boolean debug;
    
}
