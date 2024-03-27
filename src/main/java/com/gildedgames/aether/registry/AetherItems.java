package com.gildedgames.aether.registry;

import com.gildedgames.aether.item.accessory.*;
import com.gildedgames.aether.item.misc.*;
import com.gildedgames.aether.item.tool.*;
import com.gildedgames.aether.utils.EnumElement;
import com.matthewperiut.accessoryapi.api.AccessoryRegister;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.template.item.TemplateAxeItem;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.template.item.TemplateShovelItem;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;

import static com.gildedgames.aether.AetherMod.MODID;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class AetherItems
{
    @Entrypoint.Namespace
    private static Namespace MOD_ID;

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
        Key = new ItemAetherKey(Identifier.of(MOD_ID, "key")).setTranslationKey(MODID, "AetherKey");
        VictoryMedal = new ItemAether(Identifier.of(MOD_ID, "victory_medal")).setMaxCount(10).setTranslationKey(MODID, "VictoryMedal");
        Bucket = new ItemSkyrootBucket(Identifier.of(MOD_ID, "skyroot_bucket")).setTranslationKey(MODID, "SkyrootBucket");

        LoreBook = new ItemLoreBook(Identifier.of(MOD_ID, "lore_book")).setTextureId(59).setTranslationKey(MODID, "LoreBook");
        MoaEgg = new ItemMoaEgg(Identifier.of(MOD_ID, "moa_egg")).setTranslationKey(MODID, "MoaEgg");
        AechorPetal = new ItemAether(Identifier.of(MOD_ID, "aechor_petal")).setTranslationKey(MODID, "AechorPetal");
        GoldenAmber = new ItemAether(Identifier.of(MOD_ID, "golden_amber")).setTranslationKey(MODID, "GoldenAmber");
        Dart = new ItemDart(Identifier.of(MOD_ID, "item_dart")).setHasSubtypes(true).setTranslationKey(MODID, "Dart");
        DartShooter = new ItemDartShooter(Identifier.of(MOD_ID, "item_dart_shooter")).setTranslationKey(MODID, "DartShooter");
        HealingStone = new ItemAmbrosium(Identifier.of(MOD_ID, "item_healing_stone"), 4).setTranslationKey(MODID, "HealingStone");
        Zanite = new ItemAether(Identifier.of(MOD_ID, "item_zanite")).setTranslationKey(MODID, "Zanite");
        BlueMusicDisk = new ItemAetherRecord(Identifier.of(MOD_ID, "item_blue_music_disk"), "AetherTune").setTranslationKey(MODID, "BlueMusicDisk");
        ToolMaterial mat = ToolMaterial.WOOD; // (wood)
        PickSkyroot = new TemplatePickaxeItem(Identifier.of(MOD_ID, "item_skyroot_pickaxe"), mat).setTranslationKey(MODID, "PickSkyroot");
        ShovelSkyroot = new TemplateShovelItem(Identifier.of(MOD_ID, "item_skyroot_shovel"), mat).setTranslationKey(MODID, "ShovelSkyroot");
        AxeSkyroot = new TemplateAxeItem(Identifier.of(MOD_ID, "item_skyroot_axe"), mat).setTranslationKey(MODID, "AxeSkyroot");
        SwordSkyroot = new TemplateSwordItem(Identifier.of(MOD_ID, "item_skyroot_sword"), mat).setTranslationKey(MODID, "SwordSkyroot");
        mat = ToolMaterial.STONE; // (stone)
        PickHolystone = new ItemHolystonePickaxe(Identifier.of(MOD_ID, "item_holystone_pickaxe"), mat).setTranslationKey(MODID, "PickHolystone");
        ShovelHolystone = new ItemHolystoneSpade(Identifier.of(MOD_ID, "item_holystone_shovel"), mat).setTranslationKey(MODID, "ShovelHolystone");
        AxeHolystone = new ItemHolystoneAxe(Identifier.of(MOD_ID, "item_holystone_axe"), mat).setTranslationKey(MODID, "AxeHolystone");
        SwordHolystone = new ItemSwordHolystone(Identifier.of(MOD_ID, "item_holystone_sword"), mat).setTranslationKey(MODID, "SwordHolystone");
        mat = ToolMaterial.IRON; // (iron)
        PickZanite = new ItemZanitePickaxe(Identifier.of(MOD_ID, "item_zanite_pickaxe"), mat).setTranslationKey(MODID, "PickZanite");
        ShovelZanite = new ItemZaniteSpade(Identifier.of(MOD_ID, "item_zanite_shovel"), mat).setTranslationKey(MODID, "ShovelZanite");
        AxeZanite = new ItemZaniteAxe(Identifier.of(MOD_ID, "item_zanite_axe"), mat).setTranslationKey(MODID, "AxeZanite");
        SwordZanite = new ItemSwordZanite(Identifier.of(MOD_ID, "item_zanite_sword"), mat).setTranslationKey(MODID, "SwordZanite");
        mat = ToolMaterial.GOLD; // (emerald)
        PickGravitite = new ItemGravititePickaxe(Identifier.of(MOD_ID, "item_gravitite_pickaxe"), mat).setTranslationKey(MODID, "PickGravitite");
        ShovelGravitite = new ItemGravititeSpade(Identifier.of(MOD_ID, "item_gravitite_shovel"), mat).setTranslationKey(MODID, "ShovelGravitite");
        AxeGravitite = new ItemGravititeAxe(Identifier.of(MOD_ID, "item_gravitite_axe"), mat).setTranslationKey(MODID, "AxeGravitite");
        SwordGravitite = new ItemSwordGravitite(Identifier.of(MOD_ID, "item_gravitite_sword"), mat).setTranslationKey(MODID, "SwordGravitite");
        PickValkyrie = new ItemValkyriePickaxe(Identifier.of(MOD_ID, "item_valkyre_pickaxe"), mat).setTranslationKey(MODID, "PickValkyrie");
        ShovelValkyrie = new ItemValkyrieSpade(Identifier.of(MOD_ID, "item_valkyre_shovel"), mat).setTranslationKey(MODID, "ShovelValkyrie");
        AxeValkyrie = new ItemValkyrieAxe(Identifier.of(MOD_ID, "item_valkyre_axe"), mat).setTranslationKey(MODID, "AxeValkyrie");
        CloudParachute = new ItemCloudParachute(Identifier.of(MOD_ID, "item_cloud_parachute"), false).setTranslationKey(MODID, "CloudParachute");
        CloudParachuteGold = new ItemCloudParachute(Identifier.of(MOD_ID, "item_gold_cloud_parachute"), true).setTranslationKey(MODID, "CloudParachuteGold");
        PhoenixHelm = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_helmet"), 3, "Phoenix", 0, 16742144).setTextureId(1).setTranslationKey(MODID, "PhoenixHelm");
        PhoenixBody = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_body"), 3, "Phoenix", 1, 16742144).setTextureId(17).setTranslationKey(MODID, "PhoenixBody");
        PhoenixLegs = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_legs"), 3, "Phoenix", 2, 16742144).setTextureId(33).setTranslationKey(MODID, "PhoenixLegs");
        PhoenixBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_phoenix_boots"), 3, "Phoenix", 3, 16742144).setTextureId(49).setTranslationKey(MODID, "PhoenixBoots");
        ObsidianHelm = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_helmet"), 4, "Obsidian", 0, 1774663).setTextureId(2).setTranslationKey(MODID, "ObsidianHelm");
        ObsidianBody = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_body"), 4, "Obsidian", 1, 1774663).setTextureId(18).setTranslationKey(MODID, "ObsidianBody");
        ObsidianLegs = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_legs"), 4, "Obsidian", 2, 1774663).setTextureId(34).setTranslationKey(MODID, "ObsidianLegs");
        ObsidianBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_obsidian_boots"), 4, "Obsidian", 3, 1774663).setTextureId(50).setTranslationKey(MODID, "ObsidianBoots");
        GravititeHelmet = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_helmet"), 3, "Gravitite", 0, 15160027).setTextureId(2).setTranslationKey(MODID, "GravHelm");
        GravititeBodyplate = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_body"), 3, "Gravitite", 1, 15160027).setTextureId(18).setTranslationKey(MODID, "GravBody");
        GravititePlatelegs = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_legs"), 3, "Gravitite", 2, 15160027).setTextureId(34).setTranslationKey(MODID, "GravLegs");
        GravititeBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_gravitite_boots"), 3, "Gravitite", 3, 15160027).setTextureId(50).setTranslationKey(MODID, "GravBoots");
        ZaniteHelmet = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_helmet"), 2, "Zanite", 0, 7412456).setTextureId(2).setTranslationKey(MODID, "ZaniteHelm");
        ZaniteChestplate = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_body"), 2, "Zanite", 1, 7412456).setTextureId(18).setTranslationKey(MODID, "ZaniteBody");
        ZaniteLeggings = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_legs"), 2, "Zanite", 2, 7412456).setTextureId(34).setTranslationKey(MODID, "ZaniteLegs");
        ZaniteBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_zanite_boots"), 2, "Zanite", 3, 7412456).setTextureId(50).setTranslationKey(MODID, "ZaniteBoots");
        NeptuneHelmet = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_helmet"), 3, "Neptune", 0, 2512127).setTextureId(1).setTranslationKey(MODID, "NeptuneHelm");
        NeptuneChestplate = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_body"), 3, "Neptune", 1, 2512127).setTextureId(17).setTranslationKey(MODID, "NeptuneBody");
        NeptuneLeggings = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_legs"), 3, "Neptune", 2, 2512127).setTextureId(33).setTranslationKey(MODID, "NeptuneLegs");
        NeptuneBoots = new ItemColouredArmor(Identifier.of(MOD_ID, "item_neptune_boots"), 3, "Neptune", 3, 2512127).setTextureId(49).setTranslationKey(MODID, "NeptuneBoots");
        LifeShard = new ItemLifeShard(Identifier.of(MOD_ID, "item_life_shard")).setTranslationKey(MODID, "LifeShard");
        Lance = new ItemLance(Identifier.of(MOD_ID, "item_lance"), mat).setTranslationKey(MODID, "Lance");
        PigSlayer = new ItemPigSlayer(Identifier.of(MOD_ID, "item_pig_slayer")).setTranslationKey(MODID, "PigSlayer");
        VampireBlade = new ItemVampireBlade(Identifier.of(MOD_ID, "item_vampire_blade")).setTranslationKey(MODID, "VampireBlade");
        NatureStaff = new ItemAether(Identifier.of(MOD_ID, "item_nature_shield")).setMaxCount(1).setTranslationKey(MODID, "NatureStaff");
        SwordFire = new ItemSwordElemental(Identifier.of(MOD_ID, "item_fire_sword"), EnumElement.Fire, -20609).setTranslationKey(MODID, "SwordFire");
        SwordHoly = new ItemSwordElemental(Identifier.of(MOD_ID, "item_holy_sword"), EnumElement.Holy, -81).setTranslationKey(MODID, "SwordHoly");
        SwordLightning = new ItemSwordElemental(Identifier.of(MOD_ID, "item_lightning_sword"), EnumElement.Lightning, -5242881).setTranslationKey(MODID, "SwordLightning");
        LightningKnife = new ItemLightningKnife(Identifier.of(MOD_ID, "item_lightning_knife")).setTranslationKey(MODID, "LightningKnife");
        GummieSwet = new ItemGummieSwet(Identifier.of(MOD_ID, "item_gummie_swet")).setTranslationKey(MODID, "GummieSwet");
        HammerNotch = new ItemNotchHammer(Identifier.of(MOD_ID, "item_notch_hammer")).setTranslationKey(MODID, "HammerOfNotch");
        CloudStaff = new ItemCloudStaff(Identifier.of(MOD_ID, "item_cloud_staff")).setTranslationKey(MODID, "CloudStaff");
        PhoenixBow = new ItemPhoenixBow(Identifier.of(MOD_ID, "item_phoenix_bow")).setTranslationKey(MODID, "PhoenixBow");

        GoldenFeather = new GoldenFeather(Identifier.of(MOD_ID, "item_golden_feather")).setTranslationKey(MODID, "GoldenFeather");
        //RepShield = new EnergyShield(Identifier.of(MOD_ID, "item_rep_shield")).setTranslationKey(MODID, "RepShield").setDurability(512);
        AgilityCape = new AgileCape(Identifier.of(MOD_ID, "item_agility_cape"), "aether:textures/capes/AgilityCape.png").setTranslationKey(MODID, "AgilityCape");
        WhiteCape = new CosmeticCape(Identifier.of(MOD_ID, "item_white_cape"), "aether:textures/capes/WhiteCape.png").setTranslationKey(MODID, "WhiteCape");
        RedCape = new CosmeticCape(Identifier.of(MOD_ID, "item_red_cape"), "aether:textures/capes/RedCape.png", 15208721).setTranslationKey(MODID, "RedCape");
        YellowCape = new CosmeticCape(Identifier.of(MOD_ID, "item_yellow_cape"), "aether:textures/capes/YellowCape.png", 13486862).setTranslationKey(MODID, "YellowCape");
        BlueCape = new CosmeticCape(Identifier.of(MOD_ID, "item_blue_cape"), "aether:textures/capes/BlueCape.png", 1277879).setTranslationKey(MODID, "BlueCape");
        IronBubble = new IronBubble(Identifier.of(MOD_ID, "item_iron_bubble")).setTranslationKey(MODID, "IronBubble");
        AetherCape = new CosmeticCape(Identifier.of(MOD_ID, "item_aether_cape"), "aether:textures/capes/AetherCape.png").setTranslationKey(MODID, "AetherCape");
        InvisibilityCloak = new InvisibilityCloak(Identifier.of(MOD_ID, "item_invisibility_cloak")).setTranslationKey(MODID, "InvisibilityCloak");
        RegenerationStone = new RegenerationStone(Identifier.of(MOD_ID, "item_regeneraation_stone")).setTranslationKey(MODID, "RegenerationStone");

        LeatherGlove = new Glove(Identifier.of(MOD_ID, "item_leather_glove"), 0, "aether:textures/armor/Accessories.png", 12999733).setTranslationKey(MODID, "LeatherGlove");
        IronGlove = new Glove(Identifier.of(MOD_ID, "item_iron_glove"), 2, "aether:textures/armor/Accessories.png", 14540253).setTranslationKey(MODID, "IronGlove");
        GoldGlove = new Glove(Identifier.of(MOD_ID, "item_gold_glove"), 1, "aether:textures/armor/Accessories.png", 15396439).setTranslationKey(MODID, "GoldGlove");
        DiamondGlove = new Glove(Identifier.of(MOD_ID, "item_diamond_glove"), 3, "aether:textures/armor/Accessories.png", 3402699).setTranslationKey(MODID, "DiamondGlove");
        ZaniteGlove = new Glove(Identifier.of(MOD_ID, "item_zanite_glove"), 2, "aether:textures/armor/Accessories.png", 7412456).setTranslationKey(MODID, "ZaniteGlove");
        GravititeGlove = new Glove(Identifier.of(MOD_ID, "item_gravitite_glove"), 3, "aether:textures/armor/Accessories.png", 15160027).setTranslationKey(MODID, "GravititeGlove");
        PhoenixGlove = new PhoenixGlove(Identifier.of(MOD_ID, "item_phoenix_glove"), 3, "aether:textures/armor/Phoenix.png", 16742144).setTranslationKey(MODID, "PhoenixGlove");
        ObsidianGlove = new Glove(Identifier.of(MOD_ID, "item_obsidian_glove"), 4, "aether:textures/armor/Accessories.png", 1774663).setTranslationKey(MODID, "ObsidianGlove");
        NeptuneGlove = new Glove(Identifier.of(MOD_ID, "item_neptune_glove"), 3, "aether:textures/armor/Accessories.png", 2512127).setTranslationKey(MODID, "NeptuneGlove");

        IronPendant = new Pendant(Identifier.of(MOD_ID, "iron_pendant"), "aether:textures/armor/Accessories.png", 16777215).setTranslationKey(MODID, "IronPendant");
        GoldPendant = new Pendant(Identifier.of(MOD_ID, "gold_endant"), "aether:textures/armor/Accessories.png", 16776994).setTranslationKey(MODID, "GoldPendant");
        ZanitePendant = new ZanitePendant(Identifier.of(MOD_ID, "zanite_pendant"), "aether:textures/armor/Accessories.png", 7412456).setTranslationKey(MODID, "ZanitePendant");
        IcePendant = new IcePendant(Identifier.of(MOD_ID, "ice_pendant"), "aether:textures/armor/Accessories.png", 9823975).setTranslationKey(MODID, "IcePendant");

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
    public static Item VictoryMedal;
    public static Item Key;
    public static Item LoreBook;
    public static Item MoaEgg;
    public static Item AechorPetal;
    public static Item GoldenAmber;
    public static Item Stick;
    public static Item Dart;
    public static Item DartShooter;
    public static Item AmbrosiumShard;
    public static Item Zanite;
    public static Item BlueMusicDisk;
    public static Item Bucket;
    public static Item PickSkyroot;
    public static Item PickHolystone;
    public static Item PickZanite;
    public static Item PickGravitite;
    public static Item ShovelSkyroot;
    public static Item ShovelHolystone;
    public static Item ShovelZanite;
    public static Item ShovelGravitite;
    public static Item AxeSkyroot;
    public static Item AxeHolystone;
    public static Item AxeZanite;
    public static Item AxeGravitite;
    public static Item SwordSkyroot;
    public static Item SwordHolystone;
    public static Item SwordZanite;
    public static Item SwordGravitite;
    public static Item IronBubble;
    public static Item PigSlayer;
    public static Item VampireBlade;
    public static Item NatureStaff;
    public static Item SwordFire;
    public static Item SwordLightning;
    public static Item SwordHoly;
    public static Item LightningKnife;
    public static Item GummieSwet;
    public static Item HammerNotch;
    public static Item PhoenixBow;
    public static Item PhoenixHelm;
    public static Item PhoenixBody;
    public static Item PhoenixLegs;
    public static Item PhoenixBoots;
    public static Item ObsidianHelm;
    public static Item ObsidianBody;
    public static Item ObsidianLegs;
    public static Item ObsidianBoots;
    public static Item CloudStaff;
    public static Item CloudParachute;
    public static Item CloudParachuteGold;
    public static Item GravititeHelmet;
    public static Item GravititeBodyplate;
    public static Item GravititePlatelegs;
    public static Item GravititeBoots;
    public static Item ZaniteHelmet;
    public static Item ZaniteChestplate;
    public static Item ZaniteLeggings;
    public static Item ZaniteBoots;
    public static Item LifeShard;
    public static Item GoldenFeather;
    public static Item Lance;
    public static Item RepShield;
    public static Item AetherCape;
    public static Item IronRing;
    public static Item GoldRing;
    public static Item ZaniteRing;
    public static Item IronPendant;
    public static Item GoldPendant;
    public static Item ZanitePendant;
    public static Item LeatherGlove;
    public static Item IronGlove;
    public static Item GoldGlove;
    public static Item DiamondGlove;
    public static Item ZaniteGlove;
    public static Item GravititeGlove;
    public static Item PhoenixGlove;
    public static Item ObsidianGlove;
    public static Item NeptuneGlove;
    public static Item NeptuneHelmet;
    public static Item NeptuneChestplate;
    public static Item NeptuneLeggings;
    public static Item NeptuneBoots;
    public static Item RegenerationStone;
    public static Item InvisibilityCloak;
    public static Item AxeValkyrie;
    public static Item PickValkyrie;
    public static Item ShovelValkyrie;
    public static Item HealingStone;
    public static Item AgilityCape;
    public static Item WhiteCape;
    public static Item RedCape;
    public static Item YellowCape;
    public static Item BlueCape;
    public static Item IceRing;
    public static Item IcePendant;
    private static int ticks;
    private static boolean jumpBoosted;
    public static int ElementalSwordIcon;
    public static int gravArmour;
    public static int zaniteArmour;
    public static int neptuneArmour;
    private static boolean debug;

}
