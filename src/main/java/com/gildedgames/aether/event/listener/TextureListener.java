package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.item.tool.ItemSkyrootBucket;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener
{
    @EventListener
    public void registerTextures(TextureRegisterEvent event)
    {
        //Sprites for blocks ^-^
        sprSkyrootLogTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootLogTop")).index;
        sprSkyrootLogSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootLogSide")).index;
        sprGoldenLogSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GoldenOak")).index;
        sprSkyroot = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootLeaves")).index;
        sprGoldenOak = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GoldenOakLeaves")).index;
        sprAercloud = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Aercloud")).index;
        sprAerogel = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Aerogel")).index;
        sprWhiteFlower = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/WhiteFlower")).index;
        sprPurpleFlower = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/PurpleFlower")).index;
        sprGoldenSapling = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GoldenOakSapling")).index;
        sprOakSapling = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/SkyrootSapling")).index;
        sprAmbrosiumOre = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/AmbrosiumOre")).index;
        sprIceStone = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Icestone")).index;
        sprPlank = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Plank")).index;
        sprDirt = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Dirt")).index;
        sprGrassTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GrassTop")).index;
        sprGrassSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GrassSide")).index;
        sprAmbrosiumTorch = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/AmbrosiumTorch")).index;
        sprZaniteOre = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/ZaniteOre")).index;
        sprQuicksoil = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Quicksoil")).index;
        sprBronze = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/CarvedStone")).index;
        sprSilver = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/AngelicStone")).index;
        sprGold = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/HellfireStone")).index;
        sprBronzeLit = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/LightCarvedStone")).index;
        sprSilverLit = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/LightAngelicStone")).index;
        sprGoldLit = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/LightHellfireStone")).index;
        sprChestFront = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/LockedChestFront")).index;
        sprChestSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/LockedChestSide")).index;
        sprPillarTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/PillarTop")).index;
        sprPillarSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/PillarSide")).index;
        sprPillarTopSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/PillarCarved")).index;
        sprHolystone = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Holystone")).index;
        sprMossyHolystone = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/MossyHolystone")).index;
        sprFreezerTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/FreezerTop")).index;
        sprFreezerSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/FreezerSide")).index;
        sprQuicksoilGlass = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/QuicksoilGlass")).index;
        sprGravititeOre = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/GravititeOre")).index;
        sprIncubatorTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/IncubatorTop")).index;
        sprIncubatorSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/IncubatorSide")).index;
        sprEnchanterTop = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/EnchanterTop")).index;
        sprEnchanterSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/EnchanterSide")).index;
        sprPortal = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, "block/Portal")).index;

        //Textures for some items
        sprShooterNormal = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartShooter")).index;
        sprShooterPoison = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartShooterPoison")).index;
        sprShooterEnchanted = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartShooterEnchanted")).index;
        sprDartGolden = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartGolden")).index;
        sprDartEnchanted = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartEnchanted")).index;
        sprDartPoison = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/DartPoison")).index;
        ItemSkyrootBucket.sprEmpty = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/Bucket")).index;
        ItemSkyrootBucket.sprWater = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/BucketWater")).index;
        ItemSkyrootBucket.sprMilk = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/BucketMilk")).index;
        ItemSkyrootBucket.sprPoison = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/BucketPoison")).index;
        ItemSkyrootBucket.sprRemedy = Atlases.getGuiItems().addTexture(Identifier.of(MOD_ID, "items/BucketRemedy")).index;

        //Some textures for blocks
        AetherBlocks.AMBROSIUM_TORCH.textureId = sprAmbrosiumTorch;

        AetherItems.AmbrosiumShard.setTexture(Identifier.of(MOD_ID, "items/AmbrosiumShard"));
        AetherItems.Key.setTexture(Identifier.of(MOD_ID, "items/Key"));
        AetherItems.VictoryMedal.setTexture(Identifier.of(MOD_ID, "items/VictoryMedal"));
        AetherItems.Stick.setTexture(Identifier.of(MOD_ID, "items/Stick"));
        AetherItems.GoldPendant.setTexture(Identifier.of(MOD_ID, "items/Pendant"));
        AetherItems.IronPendant.setTexture(Identifier.of(MOD_ID, "items/Pendant"));
        AetherItems.IcePendant.setTexture(Identifier.of(MOD_ID, "items/Pendant"));
        AetherItems.ZanitePendant.setTexture(Identifier.of(MOD_ID, "items/Pendant"));
        AetherItems.IronRing.setTexture(Identifier.of(MOD_ID, "items/Ring"));
        AetherItems.GoldRing.setTexture(Identifier.of(MOD_ID, "items/Ring"));
        AetherItems.IceRing.setTexture(Identifier.of(MOD_ID, "items/Ring"));
        AetherItems.ZaniteRing.setTexture(Identifier.of(MOD_ID, "items/Ring"));
        AetherItems.MoaEgg.setTexture(Identifier.of(MOD_ID, "items/MoaEgg"));
        AetherItems.AechorPetal.setTexture(Identifier.of(MOD_ID, "items/AechorPetal"));
        AetherItems.GoldenAmber.setTexture(Identifier.of(MOD_ID, "items/GoldenAmber"));
        AetherItems.HealingStone.setTexture(Identifier.of(MOD_ID, "items/HealingStone"));
        AetherItems.Zanite.setTexture(Identifier.of(MOD_ID, "items/Zanite"));
        AetherItems.BlueMusicDisk.setTexture(Identifier.of(MOD_ID, "items/BlueMusicDisk"));
        AetherItems.PickSkyroot.setTexture(Identifier.of(MOD_ID, "items/PickSkyroot"));
        AetherItems.ShovelSkyroot.setTexture(Identifier.of(MOD_ID, "items/ShovelSkyroot"));
        AetherItems.AxeSkyroot.setTexture(Identifier.of(MOD_ID, "items/AxeSkyroot"));
        AetherItems.SwordSkyroot.setTexture(Identifier.of(MOD_ID, "items/SwordSkyroot"));
        AetherItems.PickHolystone.setTexture(Identifier.of(MOD_ID, "items/PickHolystone"));
        AetherItems.ShovelHolystone.setTexture(Identifier.of(MOD_ID, "items/ShovelHolystone"));
        AetherItems.AxeHolystone.setTexture(Identifier.of(MOD_ID, "items/AxeHolystone"));
        AetherItems.SwordHolystone.setTexture(Identifier.of(MOD_ID, "items/SwordHolystone"));
        AetherItems.AxeZanite.setTexture(Identifier.of(MOD_ID, "items/AxeZanite"));
        AetherItems.PickZanite.setTexture(Identifier.of(MOD_ID, "items/PickZanite"));
        AetherItems.ShovelZanite.setTexture(Identifier.of(MOD_ID, "items/ShovelZanite"));
        AetherItems.SwordZanite.setTexture(Identifier.of(MOD_ID, "items/SwordZanite"));
        AetherItems.LeatherGlove.setTexture(Identifier.of(MOD_ID, "items/Glove"));
        AetherItems.IronGlove.setTexture(Identifier.of(MOD_ID, "items/Glove"));
        AetherItems.GoldGlove.setTexture(Identifier.of(MOD_ID, "items/Glove"));
        AetherItems.DiamondGlove.setTexture(Identifier.of(MOD_ID, "items/Glove"));
        AetherItems.ZaniteGlove.setTexture(Identifier.of(MOD_ID, "items/Glove"));
        AetherItems.GravititeGlove.setTexture(Identifier.of(MOD_ID, "items/Glove"));
        AetherItems.ObsidianGlove.setTexture(Identifier.of(MOD_ID, "items/Glove"));
        AetherItems.PhoenixGlove.setTexture(Identifier.of(MOD_ID, "items/GloveChain"));
        AetherItems.NeptuneGlove.setTexture(Identifier.of(MOD_ID, "items/GloveChain"));
        AetherItems.PickGravitite.setTexture(Identifier.of(MOD_ID, "items/PickGravitite"));
        AetherItems.ShovelGravitite.setTexture(Identifier.of(MOD_ID, "items/ShovelGravitite"));
        AetherItems.AxeGravitite.setTexture(Identifier.of(MOD_ID, "items/AxeGravitite"));
        AetherItems.SwordGravitite.setTexture(Identifier.of(MOD_ID, "items/SwordGravitite"));
        AetherItems.PickValkyrie.setTexture(Identifier.of(MOD_ID, "items/ValkyriePickaxe"));
        AetherItems.ShovelValkyrie.setTexture(Identifier.of(MOD_ID, "items/ValkyrieShovel"));
        AetherItems.AxeValkyrie.setTexture(Identifier.of(MOD_ID, "items/ValkyrieAxe"));
        AetherItems.IronBubble.setTexture(Identifier.of(MOD_ID, "items/IronBubble"));
        AetherItems.AetherCape.setTexture(Identifier.of(MOD_ID, "items/AetherCape"));
        AetherItems.RegenerationStone.setTexture(Identifier.of(MOD_ID, "items/RegenerationStone"));
        AetherItems.InvisibilityCloak.setTexture(Identifier.of(MOD_ID, "items/InvisibilityCloak"));
        AetherItems.AgilityCape.setTexture(Identifier.of(MOD_ID, "items/AgilityCape"));
        AetherItems.WhiteCape.setTexture(Identifier.of(MOD_ID, "items/Cape"));
        AetherItems.RedCape.setTexture(Identifier.of(MOD_ID, "items/Cape"));
        AetherItems.YellowCape.setTexture(Identifier.of(MOD_ID, "items/Cape"));
        AetherItems.BlueCape.setTexture(Identifier.of(MOD_ID, "items/Cape"));
        AetherItems.CloudParachute.setTexture(Identifier.of(MOD_ID, "items/CloudParachute"));
        AetherItems.CloudParachuteGold.setTexture(Identifier.of(MOD_ID, "items/CloudParachute"));
        AetherItems.LifeShard.setTexture(Identifier.of(MOD_ID, "items/LifeShard"));
        AetherItems.GoldenFeather.setTexture(Identifier.of(MOD_ID, "items/GoldenFeather"));
        AetherItems.Lance.setTexture(Identifier.of(MOD_ID, "items/Lance"));
        //AetherItems.RepShield.setTexture(Identifier.of(MOD_ID, "items/RepulsionShield"));
        AetherItems.PigSlayer.setTexture(Identifier.of(MOD_ID, "items/PigSlayer"));
        AetherItems.VampireBlade.setTexture(Identifier.of(MOD_ID, "items/VampireBlade"));
        AetherItems.NatureStaff.setTexture(Identifier.of(MOD_ID, "items/NatureStaff"));
        AetherItems.SwordFire.setTexture(Identifier.of(MOD_ID, "items/ElementalSword"));
        AetherItems.SwordHoly.setTexture(Identifier.of(MOD_ID, "items/ElementalSword"));
        AetherItems.SwordLightning.setTexture(Identifier.of(MOD_ID, "items/ElementalSword"));
        AetherItems.LightningKnife.setTexture(Identifier.of(MOD_ID, "items/LightningKnife"));
        AetherItems.GummieSwet.setTexture(Identifier.of(MOD_ID, "items/GummieSwet"));
        AetherItems.HammerNotch.setTexture(Identifier.of(MOD_ID, "items/HammerNotch"));
        AetherItems.CloudStaff.setTexture(Identifier.of(MOD_ID, "items/CloudStaff"));
        AetherItems.PhoenixBow.setTexture(Identifier.of(MOD_ID, "items/PhoenixBow"));
    }

    //this is Alias code but i changed public static booleans to ints and renamed them
    public static int sprIncubatorSide, sprIncubatorTop, sprPortal;
    public static int sprEnchanterSide, sprEnchanterTop;
    public static int sprShooterNormal, sprQuicksoilGlass;
    public static int sprShooterPoison;
    public static int sprShooterEnchanted;
    public static int sprDartGolden;
    public static int sprDartEnchanted;
    public static int sprDartPoison;
    public static int sprFreezerTop;
    public static int sprFreezerSide;
    public static int sprPillarTop, sprHolystone, sprMossyHolystone;
    public static int sprPillarSide;
    public static int sprPillarTopSide;
    public static int sprBronze, sprChestFront, sprChestSide;
    public static int sprSilver;
    public static int sprGold;
    public static int sprBronzeLit;
    public static int sprSilverLit;
    public static int sprGoldLit;
    public static int sprAmbrosiumOre, sprIceStone, sprPlank, sprDirt, sprGrassTop, sprGrassSide, sprAmbrosiumTorch, sprQuicksoil;
    public static int sprGoldenSapling, sprOakSapling;
    public static int sprWhiteFlower, sprPurpleFlower;
    public static int sprAerogel;
    public static int sprAercloud;
    public static int sprSkyroot;
    public static int sprGoldenOak;
    public static int sprSkyrootLogTop;
    public static int sprSkyrootLogSide;
    public static int sprGoldenLogSide;
    public static int sprZaniteOre;
    public static int sprGravititeOre;
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();
}
