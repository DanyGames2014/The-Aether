package com.gildedgames.aether.registry;

import com.gildedgames.aether.item.accessory.*;
import com.gildedgames.aether.item.misc.*;
import com.gildedgames.aether.item.tool.*;
import com.gildedgames.aether.utils.EnumElement;
import com.matthewperiut.accessoryapi.api.AccessoryRegister;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ItemBase;
import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.template.item.tool.TemplateHatchet;
import net.modificationstation.stationapi.api.template.item.tool.TemplatePickaxe;
import net.modificationstation.stationapi.api.template.item.tool.TemplateShovel;
import net.modificationstation.stationapi.api.template.item.tool.TemplateSword;

import static com.gildedgames.aether.AetherMod.MODID;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class AetherItems
{
    @Entrypoint.ModID
    private static ModID MOD_ID;

    @EventListener
    private static void registerItems(ItemRegistryEvent event)
    {
        AccessoryRegister.add("pendant");
        AccessoryRegister.add("cape");
        AccessoryRegister.add("shield");
        AccessoryRegister.add("misc", 0, 3);
        AccessoryRegister.add("misc", 1, 3);
        AccessoryRegister.add("ring");
        AccessoryRegister.add("ring");
        AccessoryRegister.add("gloves");

        AmbrosiumShard = new ItemAmbrosium(Identifier.of(MOD_ID, "ambrosium_shard"), 1).setTranslationKey(MODID, "AmbrosiumShard");
        Stick = new ItemAether(Identifier.of(MOD_ID, "stick")).setTranslationKey(MODID, "SkyrootStick");
        AetherItems.Key = new ItemAetherKey(Identifier.of(MOD_ID, "key")).setTranslationKey(MODID, "AetherKey");
        AetherItems.VictoryMedal = new ItemAether(Identifier.of(MOD_ID, "victory_medal")).setMaxStackSize(10).setTranslationKey(MODID, "VictoryMedal");
        Bucket = new ItemSkyrootBucket(Identifier.of(MOD_ID, "skyroot_bucket")).setTranslationKey(MODID, "SkyrootBucket");

        AetherItems.LoreBook = new ItemLoreBook(Identifier.of(MOD_ID, "lore_book")).setTexturePosition(59).setTranslationKey(MODID, "LoreBook");
        AetherItems.MoaEgg = new ItemMoaEgg(Identifier.of(MOD_ID, "moa_egg")).setTranslationKey(MODID, "MoaEgg");
        AetherItems.AechorPetal = new ItemAether(Identifier.of(MOD_ID, "aechor_petal")).setTranslationKey(MODID, "AechorPetal");
        AetherItems.GoldenAmber = new ItemAether(Identifier.of(MOD_ID, "golden_amber")).setTranslationKey(MODID, "GoldenAmber");
        AetherItems.Dart = new ItemDart(Identifier.of(MOD_ID, "item_dart")).setHasSubItems(true).setTranslationKey(MODID, "Dart");
        AetherItems.DartShooter = new ItemDartShooter(Identifier.of(MOD_ID, "item_dart_shooter")).setTranslationKey(MODID, "DartShooter");
        AetherItems.HealingStone = new ItemAmbrosium(Identifier.of(MOD_ID, "item_healing_stone"), 4).setTranslationKey(MODID, "HealingStone");
        AetherItems.Zanite = new ItemAether(Identifier.of(MOD_ID, "item_zanite")).setTranslationKey(MODID, "Zanite");
        AetherItems.BlueMusicDisk = new ItemAetherRecord(Identifier.of(MOD_ID, "item_blue_music_disk"), "AetherTune").setTranslationKey(MODID, "BlueMusicDisk");
        ToolMaterial mat = ToolMaterial.field_1688; // (wood)
        AetherItems.PickSkyroot = new TemplatePickaxe(Identifier.of(MOD_ID, "item_skyroot_pickaxe"), mat).setTranslationKey(MODID, "PickSkyroot");
        AetherItems.ShovelSkyroot = new TemplateShovel(Identifier.of(MOD_ID, "item_skyroot_shovel"), mat).setTranslationKey(MODID, "ShovelSkyroot");
        AetherItems.AxeSkyroot = new TemplateHatchet(Identifier.of(MOD_ID, "item_skyroot_axe"), mat).setTranslationKey(MODID, "AxeSkyroot");
        AetherItems.SwordSkyroot = new TemplateSword(Identifier.of(MOD_ID, "item_skyroot_sword"), mat).setTranslationKey(MODID, "SwordSkyroot");
        mat = ToolMaterial.field_1689; // (stone)
        AetherItems.PickHolystone = new ItemHolystonePickaxe(Identifier.of(MOD_ID, "item_holystone_pickaxe"), mat).setTranslationKey(MODID, "PickHolystone");
        AetherItems.ShovelHolystone = new ItemHolystoneSpade(Identifier.of(MOD_ID, "item_holystone_shovel"), mat).setTranslationKey(MODID, "ShovelHolystone");
        AetherItems.AxeHolystone = new ItemHolystoneAxe(Identifier.of(MOD_ID, "item_holystone_axe"), mat).setTranslationKey(MODID, "AxeHolystone");
        AetherItems.SwordHolystone = new ItemSwordHolystone(Identifier.of(MOD_ID, "item_holystone_sword"), mat).setTranslationKey(MODID, "SwordHolystone");
        mat = ToolMaterial.field_1690; // (iron)
        AetherItems.PickZanite = new ItemZanitePickaxe(Identifier.of(MOD_ID, "item_zanite_pickaxe"), mat).setTranslationKey(MODID, "PickZanite");
        AetherItems.ShovelZanite = new ItemZaniteSpade(Identifier.of(MOD_ID, "item_zanite_shovel"), mat).setTranslationKey(MODID, "ShovelZanite");
        AetherItems.AxeZanite = new ItemZaniteAxe(Identifier.of(MOD_ID, "item_zanite_axe"), mat).setTranslationKey(MODID, "AxeZanite");
        AetherItems.SwordZanite = new ItemSwordZanite(Identifier.of(MOD_ID, "item_zanite_sword"), mat).setTranslationKey(MODID, "SwordZanite");
        mat = ToolMaterial.field_1692; // (emerald)
        AetherItems.PickGravitite = new ItemGravititePickaxe(Identifier.of(MOD_ID, "item_gravitite_pickaxe"), mat).setTranslationKey(MODID, "PickGravitite");
        AetherItems.ShovelGravitite = new ItemGravititeSpade(Identifier.of(MOD_ID, "item_gravitite_shovel"), mat).setTranslationKey(MODID, "ShovelGravitite");
        AetherItems.AxeGravitite = new ItemGravititeAxe(Identifier.of(MOD_ID, "item_gravitite_axe"), mat).setTranslationKey(MODID, "AxeGravitite");
        AetherItems.SwordGravitite = new ItemSwordGravitite(Identifier.of(MOD_ID, "item_gravitite_sword"), mat).setTranslationKey(MODID, "SwordGravitite");
        AetherItems.PickValkyrie = new ItemValkyriePickaxe(Identifier.of(MOD_ID, "item_valkyre_pickaxe"), mat).setTranslationKey(MODID, "PickValkyrie");
        AetherItems.ShovelValkyrie = new ItemValkyrieSpade(Identifier.of(MOD_ID, "item_valkyre_shovel"), mat).setTranslationKey(MODID, "ShovelValkyrie");
        AetherItems.AxeValkyrie = new ItemValkyrieAxe(Identifier.of(MOD_ID, "item_valkyre_axe"), mat).setTranslationKey(MODID, "AxeValkyrie");
        AetherItems.CloudParachute = new ItemCloudParachute(Identifier.of(MOD_ID, "item_cloud_parachute"), false).setTranslationKey(MODID, "CloudParachute");
        AetherItems.CloudParachuteGold = new ItemCloudParachute(Identifier.of(MOD_ID, "item_gold_cloud_parachute"), true).setTranslationKey(MODID, "CloudParachuteGold");
        AetherItems.PhoenixHelm = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_helmet"), 3, "Phoenix", 0, 16742144).setTexturePosition(1).setTranslationKey(MODID, "PhoenixHelm");
        AetherItems.PhoenixBody = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_body"), 3, "Phoenix", 1, 16742144).setTexturePosition(17).setTranslationKey(MODID, "PhoenixBody");
        AetherItems.PhoenixLegs = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_legs"), 3, "Phoenix", 2, 16742144).setTexturePosition(33).setTranslationKey(MODID, "PhoenixLegs");
        AetherItems.PhoenixBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_boots"), 3, "Phoenix", 3, 16742144).setTexturePosition(49).setTranslationKey(MODID, "PhoenixBoots");
        AetherItems.ObsidianHelm = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_helmet"), 4, "Obsidian", 0, 1774663).setTexturePosition(2).setTranslationKey(MODID, "ObsidianHelm");
        AetherItems.ObsidianBody = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_body"), 4, "Obsidian", 1, 1774663).setTexturePosition(18).setTranslationKey(MODID, "ObsidianBody");
        AetherItems.ObsidianLegs = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_legs"), 4, "Obsidian", 2, 1774663).setTexturePosition(34).setTranslationKey(MODID, "ObsidianLegs");
        AetherItems.ObsidianBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_boots"), 4, "Obsidian", 3, 1774663).setTexturePosition(50).setTranslationKey(MODID, "ObsidianBoots");
        AetherItems.GravititeHelmet = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_helmet"), 3, "Gravitite", 0, 15160027).setTexturePosition(2).setTranslationKey(MODID, "GravHelm");
        AetherItems.GravititeBodyplate = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_body"), 3, "Gravitite", 1, 15160027).setTexturePosition(18).setTranslationKey(MODID, "GravBody");
        AetherItems.GravititePlatelegs = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_legs"), 3, "Gravitite", 2, 15160027).setTexturePosition(34).setTranslationKey(MODID, "GravLegs");
        AetherItems.GravititeBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_boots"), 3, "Gravitite", 3, 15160027).setTexturePosition(50).setTranslationKey(MODID, "GravBoots");
        AetherItems.ZaniteHelmet = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_helmet"), 2, "Zanite", 0, 7412456).setTexturePosition(2).setTranslationKey(MODID, "ZaniteHelm");
        AetherItems.ZaniteChestplate = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_body"), 2, "Zanite", 1, 7412456).setTexturePosition(18).setTranslationKey(MODID, "ZaniteBody");
        AetherItems.ZaniteLeggings = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_legs"), 2, "Zanite", 2, 7412456).setTexturePosition(34).setTranslationKey(MODID, "ZaniteLegs");
        AetherItems.ZaniteBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_boots"), 2, "Zanite", 3, 7412456).setTexturePosition(50).setTranslationKey(MODID, "ZaniteBoots");
        AetherItems.NeptuneHelmet = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_helmet"), 3, "Neptune", 0, 2512127).setTexturePosition(1).setTranslationKey(MODID, "NeptuneHelm");
        AetherItems.NeptuneChestplate = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_body"), 3, "Neptune", 1, 2512127).setTexturePosition(17).setTranslationKey(MODID, "NeptuneBody");
        AetherItems.NeptuneLeggings = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_legs"), 3, "Neptune", 2, 2512127).setTexturePosition(33).setTranslationKey(MODID, "NeptuneLegs");
        AetherItems.NeptuneBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_boots"), 3, "Neptune", 3, 2512127).setTexturePosition(49).setTranslationKey(MODID, "NeptuneBoots");
        AetherItems.LifeShard = new ItemLifeShard(Identifier.of(MOD_ID, "item_life_shard")).setTranslationKey(MODID, "LifeShard");
        AetherItems.Lance = new ItemLance(Identifier.of(MOD_ID, "item_lance"), mat).setTranslationKey(MODID, "Lance");
        AetherItems.PigSlayer = new ItemPigSlayer(Identifier.of(MOD_ID, "item_pig_slayer")).setTranslationKey(MODID, "PigSlayer");
        AetherItems.VampireBlade = new ItemVampireBlade(Identifier.of(MOD_ID, "item_vampire_blade")).setTranslationKey(MODID, "VampireBlade");
        AetherItems.NatureStaff = new ItemAether(Identifier.of(MOD_ID, "item_nature_shield")).setMaxStackSize(1).setTranslationKey(MODID, "NatureStaff");
        AetherItems.SwordFire = new ItemSwordElemental(Identifier.of(MOD_ID, "item_fire_sword"), EnumElement.Fire, -20609).setTranslationKey(MODID, "SwordFire");
        AetherItems.SwordHoly = new ItemSwordElemental(Identifier.of(MOD_ID, "item_holy_sword"), EnumElement.Holy, -81).setTranslationKey(MODID, "SwordHoly");
        AetherItems.SwordLightning = new ItemSwordElemental(Identifier.of(MOD_ID, "item_lightning_sword"), EnumElement.Lightning, -5242881).setTranslationKey(MODID, "SwordLightning");
        AetherItems.LightningKnife = new ItemLightningKnife(Identifier.of(MOD_ID, "item_lightning_knife")).setTranslationKey(MODID, "LightningKnife");
        AetherItems.GummieSwet = new ItemGummieSwet(Identifier.of(MOD_ID, "item_gummie_swet")).setTranslationKey(MODID, "GummieSwet");
        AetherItems.HammerNotch = new ItemNotchHammer(Identifier.of(MOD_ID, "item_notch_hammer")).setTranslationKey(MODID, "HammerOfNotch");
        AetherItems.CloudStaff = new ItemCloudStaff(Identifier.of(MOD_ID, "item_cloud_staff")).setTranslationKey(MODID, "CloudStaff");
        AetherItems.PhoenixBow = new ItemPhoenixBow(Identifier.of(MOD_ID, "item_phoenix_bow")).setTranslationKey(MODID, "PhoenixBow");

        AetherItems.GoldenFeather = new GoldenFeather(Identifier.of(MOD_ID, "item_golden_feather")).setTranslationKey(MODID, "GoldenFeather");
        AetherItems.RepShield = new EnergyShield(Identifier.of(MOD_ID, "item_rep_shield")).setTranslationKey(MODID, "RepShield").setDurability(512);
        AetherItems.AgilityCape = new AgileCape(Identifier.of(MOD_ID, "item_agility_cape"), "aether:textures/capes/AgilityCape.png").setTranslationKey(MODID, "AgilityCape");
        AetherItems.WhiteCape = new CosmeticCape(Identifier.of(MOD_ID, "item_white_cape"), "aether:textures/capes/WhiteCape.png").setTranslationKey(MODID, "WhiteCape");
        AetherItems.RedCape = new CosmeticCape(Identifier.of(MOD_ID, "item_red_cape"), "aether:textures/capes/RedCape.png", 15208721).setTranslationKey(MODID, "RedCape");
        ;//new ItemMoreArmor(Identifier.of(MOD_ID, "item_red_cape"), 0, "aether:textures/capes/RedCape.png", Accessory.Type.cape, 15208721).setTranslationKey(MODID, "RedCape");
        AetherItems.YellowCape = new CosmeticCape(Identifier.of(MOD_ID, "item_yellow_cape"), "aether:textures/capes/YellowCape.png", 13486862).setTranslationKey(MODID, "YellowCape");
        AetherItems.BlueCape = new CosmeticCape(Identifier.of(MOD_ID, "item_blue_cape"), "aether:textures/capes/BlueCape.png", 1277879).setTranslationKey(MODID, "BlueCape");
        AetherItems.IronBubble = new IronBubble(Identifier.of(MOD_ID, "item_iron_bubble")).setTranslationKey(MODID, "IronBubble");
        AetherItems.AetherCape = new CosmeticCape(Identifier.of(MOD_ID, "item_aether_cape"), "aether:textures/capes/AetherCape.png").setTranslationKey(MODID, "AetherCape");
        AetherItems.InvisibilityCloak = new InvisibilityCloak(Identifier.of(MOD_ID, "item_invisibility_cloack")).setTranslationKey(MODID, "InvisibilityCloak");
        AetherItems.RegenerationStone = new RegenerationStone(Identifier.of(MOD_ID, "item_regeneraation_stone")).setTranslationKey(MODID, "RegenerationStone");

        AetherItems.LeatherGlove = new Glove(Identifier.of(MOD_ID, "item_leather_glove"), 0, "aether:textures/armor/Accessories.png", 12999733).setTranslationKey(MODID, "LeatherGlove");
        AetherItems.IronGlove = new Glove(Identifier.of(MOD_ID, "item_iron_glove"), 2, "aether:textures/armor/Accessories.png", 14540253).setTranslationKey(MODID, "IronGlove");
        AetherItems.GoldGlove = new Glove(Identifier.of(MOD_ID, "item_gold_glove"), 1, "aether:textures/armor/Accessories.png", 15396439).setTranslationKey(MODID, "GoldGlove");
        AetherItems.DiamondGlove = new Glove(Identifier.of(MOD_ID, "item_diamond_glove"), 3, "aether:textures/armor/Accessories.png", 3402699).setTranslationKey(MODID, "DiamondGlove");
        AetherItems.ZaniteGlove = new Glove(Identifier.of(MOD_ID, "item_zanite_glove"), 2, "aether:textures/armor/Accessories.png", 7412456).setTranslationKey(MODID, "ZaniteGlove");
        AetherItems.GravititeGlove = new Glove(Identifier.of(MOD_ID, "item_gravitite_glove"), 3, "aether:textures/armor/Accessories.png", 15160027).setTranslationKey(MODID, "GravititeGlove");
        AetherItems.PhoenixGlove = new PhoenixGlove(Identifier.of(MOD_ID, "item_phoenix_glove"), 3, "aether:textures/armor/Phoenix.png", 16742144).setTranslationKey(MODID, "PhoenixGlove");
        AetherItems.ObsidianGlove = new Glove(Identifier.of(MOD_ID, "item_obsidian_glove"), 4, "aether:textures/armor/Accessories.png", 1774663).setTranslationKey(MODID, "ObsidianGlove");
        AetherItems.NeptuneGlove = new Glove(Identifier.of(MOD_ID, "item_neptune_glove"), 3, "aether:textures/armor/Accessories.png", 2512127).setTranslationKey(MODID, "NeptuneGlove");

        AetherItems.IronPendant = new Pendant(Identifier.of(MOD_ID, "iron_pendant"), "aether:textures/armor/Accessories.png", 16777215).setTranslationKey(MODID, "IronPendant");
        AetherItems.GoldPendant = new Pendant(Identifier.of(MOD_ID, "gold_endant"), "aether:textures/armor/Accessories.png", 16776994).setTranslationKey(MODID, "GoldPendant");
        AetherItems.ZanitePendant = new ZanitePendant(Identifier.of(MOD_ID, "zanite_pendant"), "aether:textures/armor/Accessories.png", 7412456).setTranslationKey(MODID, "ZanitePendant");
        AetherItems.IcePendant = new IcePendant(Identifier.of(MOD_ID, "ice_pendant"), "aether:textures/armor/Accessories.png", 9823975).setTranslationKey(MODID, "IcePendant");

        IronRing = new Ring(Identifier.of(MOD_ID, "iron_ring"), 16777215).setTranslationKey(MODID, "IronRing");
        GoldRing = new Ring(Identifier.of(MOD_ID, "gold_ring"), 16776994).setTranslationKey(MODID, "GoldRing");
        ZaniteRing = new ZaniteRing(Identifier.of(MOD_ID, "zanite_ring"), 7412456).setTranslationKey(MODID, "ZaniteRing");
        IceRing = new IceRing(Identifier.of(MOD_ID, "ice_ring"), 9823975).setTranslationKey(MODID, "IceRing");
    }
    /*
    public static void tick(final Minecraft game) {

        if (true) {


            if (player.inventory.armour[3] != null && player.inventory.armour[3].itemId == AetherItems.PhoenixHelm.id && player.inventory.armour[2] != null && player.inventory.armour[2].itemId == AetherItems.PhoenixBody.id && player.inventory.armour[1] != null && player.inventory.armour[1].itemId == AetherItems.PhoenixLegs.id && player.inventory.armour[0] != null && player.inventory.armour[0].itemId == AetherItems.PhoenixBoots.id && inv.slots[6] != null && inv.slots[6].itemId == AetherItems.PhoenixGlove.id) {
                ((EntityBaseAccessor)player).setImmunityToFire(true);
                player.fire = 0;
                //if (!MainMenu.mmactive) {
                game.level.addParticle("flame", player.x + ((EntityBaseAccessor)player).getRand().nextGaussian() / 5.0, player.y - 0.5 + ((EntityBaseAccessor)player).getRand().nextGaussian() / 5.0, player.z + ((EntityBaseAccessor)player).getRand().nextGaussian() / 3.0, 0.0, 0.0, 0.0);
                //}
            }
            else {
            	((EntityBaseAccessor)player).setImmunityToFire(false);
            }
            if (player.isTouchingWater()) {
                final int playerBlock = game.level.getTileId(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z));
                if (player.inventory.armour[0] != null && player.inventory.armour[0].itemId == AetherItems.PhoenixBoots.id) {
                    player.inventory.armour[0].applyDamage(1, player);
                    if (playerBlock == BlockBase.STILL_WATER.id) {
                        player.inventory.armour[0].applyDamage(4, player);
                        game.level.setTile(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z), 0);
                    }
                    if (player.inventory.armour[0] == null || player.inventory.armour[0].count < 1) {
                        player.inventory.armour[0] = new ItemInstance(AetherItems.ObsidianBoots, 1, 0);
                    }
                }
                if (player.inventory.armour[1] != null && player.inventory.armour[1].itemId == AetherItems.PhoenixLegs.id) {
                    player.inventory.armour[1].applyDamage(1, player);
                    if (playerBlock == BlockBase.STILL_WATER.id) {
                        player.inventory.armour[1].applyDamage(4, player);
                        game.level.setTile(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z), 0);
                    }
                    if (player.inventory.armour[1] == null || player.inventory.armour[1].count < 1) {
                        player.inventory.armour[1] = new ItemInstance(AetherItems.ObsidianLegs, 1, 0);
                    }
                }
                if (player.inventory.armour[2] != null && player.inventory.armour[2].itemId == AetherItems.PhoenixBody.id) {
                    player.inventory.armour[2].applyDamage(1, player);
                    if (playerBlock == BlockBase.STILL_WATER.id) {
                        player.inventory.armour[2].applyDamage(4, player);
                        game.level.setTile(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z), 0);
                    }
                    if (player.inventory.armour[2] == null || player.inventory.armour[2].count < 1) {
                        player.inventory.armour[2] = new ItemInstance(AetherItems.ObsidianBody, 1, 0);
                    }
                }
                if (player.inventory.armour[3] != null && player.inventory.armour[3].itemId == AetherItems.PhoenixHelm.id) {
                    player.inventory.armour[3].applyDamage(1, player);
                    if (playerBlock == BlockBase.STILL_WATER.id) {
                        player.inventory.armour[3].applyDamage(4, player);
                        game.level.setTile(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z), 0);
                    }
                    if (player.inventory.armour[3] == null || player.inventory.armour[3].count < 1) {
                        player.inventory.armour[3] = new ItemInstance(AetherItems.ObsidianHelm, 1, 0);
                    }
                }
                if (inv.slots[6] != null && inv.slots[6].itemId == AetherItems.PhoenixGlove.id) {
                    inv.slots[6].applyDamage(1, player);
                    if (playerBlock == BlockBase.STILL_WATER.id) {
                        inv.slots[6].applyDamage(4, player);
                        game.level.setTile(MathHelper.floor(player.x), MathHelper.floor(player.y), MathHelper.floor(player.z), 0);
                    }
                    if (inv.slots[6] == null || inv.slots[6].count < 1) {
                        inv.slots[6] = new ItemInstance(AetherItems.ObsidianGlove, 1, 0);
                    }
                }
            }
            if (player.inventory.armour[3] != null && player.inventory.armour[3].itemId == AetherItems.GravititeHelmet.id && player.inventory.armour[2] != null && player.inventory.armour[2].itemId == AetherItems.GravititeBodyplate.id && player.inventory.armour[1] != null && player.inventory.armour[1].itemId == AetherItems.GravititePlatelegs.id && player.inventory.armour[0] != null && player.inventory.armour[0].itemId == AetherItems.GravititeBoots.id && inv.slots[6] != null && inv.slots[6].itemId == AetherItems.GravititeGlove.id) {
                if (((LivingAccessor)player).getJumping() && !AetherItems.jumpBoosted) {
                    player.velocityY = 1.0;
                    AetherItems.jumpBoosted = true; //TODO: check/make it work
                }
                ((EntityBaseAccessor)player).setFallDistance(-1.0f);
            }
            if (!((LivingAccessor)player).getJumping() && player.onGround) {
                AetherItems.jumpBoosted = false;
            }
            if ((inv.slots[3] != null && inv.slots[3].itemId == AetherItems.IronBubble.id) || (inv.slots[7] != null && inv.slots[7].itemId == AetherItems.IronBubble.id)) {
                player.air = 20;
            }
            if ((inv.slots[0] != null && inv.slots[0].itemId == AetherItems.IcePendant.id) || (inv.slots[4] != null && inv.slots[4].itemId == AetherItems.IceRing.id) || (inv.slots[5] != null && inv.slots[5].itemId == AetherItems.IceRing.id)) {
                final int i = MathHelper.floor(player.x);
                final int j = MathHelper.floor(player.boundingBox.minY);
                final int k = MathHelper.floor(player.z);
                final double yoff = player.y - j;
                final Material mat0 = game.level.getMaterial(i, j, k);
                final Material mat2 = game.level.getMaterial(i, j - 1, k);
                for (int l = i - 1; l <= i + 1; ++l) {
                    for (int i2 = j - 1; i2 <= j + 1; ++i2) {
                        for (int j2 = k - 1; j2 <= k + 1; ++j2) {
                            if (game.level.getTileId(l, i2, j2) == 8) {
                                game.level.setTile(l, i2, j2, 79);
                            }
                            else if (game.level.getTileId(l, i2, j2) == 9) {
                                game.level.setTile(l, i2, j2, 79);
                            }
                            else if (game.level.getTileId(l, i2, j2) == 10) {
                                game.level.setTile(l, i2, j2, 49);
                            }
                            else if (game.level.getTileId(l, i2, j2) == 11) {
                                game.level.setTile(l, i2, j2, 49);
                            }
                        }
                    }
                }
            }
            if (AetherItems.ticks % 200 == 0 && player.health < Aether.getPlayerHandler(player).maxHealth && ((inv.slots[3] != null && inv.slots[3].itemId == AetherItems.RegenerationStone.id) || (inv.slots[7] != null && inv.slots[7].itemId == AetherItems.RegenerationStone.id))) {
                player.addHealth(1);
            }
            ++AetherItems.ticks;
        }
    }*/


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
