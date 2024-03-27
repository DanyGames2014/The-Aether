package com.gildedgames.aether.registry;

import com.gildedgames.aether.achievement.AetherACPage;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;

public class AetherAchievements
{
    public static final int acOff = 800;
    public static Achievement enterAether;
    public static Achievement defeatBronze;
    public static Achievement defeatSilver;
    public static Achievement defeatGold;
    public static Achievement enchanter;
    public static Achievement incubator;
    public static Achievement gravTools;
    public static Achievement blueCloud;
    public static Achievement flyingPig;
    public static Achievement lore;
    public static Achievement loreception;

    @EventListener
    private void registerAchievements(AchievementRegisterEvent event)
    {
        AetherAchievements.enterAether = new Achievement(800, "aether:enterAether", 0, 0, Block.GLOWSTONE, (Achievement) null);
        AetherAchievements.defeatBronze = new Achievement(801, "aether:defeatBronze", -2, 3, new ItemStack(AetherItems.Key, 1, 0), AetherAchievements.enterAether);
        AetherAchievements.defeatSilver = new Achievement(802, "aether:defeatSilver", 0, 4, new ItemStack(AetherItems.Key, 1, 1), AetherAchievements.enterAether);
        AetherAchievements.defeatGold = new Achievement(803, "aether:defeatGold", 2, 3, new ItemStack(AetherItems.Key, 1, 2), AetherAchievements.enterAether);
        AetherAchievements.enchanter = new Achievement(804, "aether:enchanter", 2, 1, AetherBlocks.ENCHANTER, AetherAchievements.enterAether);
        AetherAchievements.incubator = new Achievement(805, "aether:incubator", 2, -1, AetherBlocks.INCUBATOR, AetherAchievements.enterAether);
        AetherAchievements.blueCloud = new Achievement(806, "aether:blueCloud", -2, -1, new ItemStack(AetherBlocks.AERCLOUD, 1, 1), AetherAchievements.enterAether);
        AetherAchievements.flyingPig = new Achievement(807, "aether:flyingPig", -2, 1, Item.SADDLE, AetherAchievements.enterAether);
        AetherAchievements.gravTools = new Achievement(808, "aether:gravTools", -1, -3, AetherItems.PickGravitite, AetherAchievements.enterAether);
        AetherAchievements.lore = new Achievement(809, "aether:lore", 1, -3, Item.BOOK, AetherAchievements.enterAether);
        AetherAchievements.loreception = new Achievement(810, "aether:loreception", 1, -5, Item.BOOK, AetherAchievements.lore);
        event.achievements.add(AetherAchievements.enterAether);/*, "Hostile Paradise", "Ascend to the Aether");*/
        event.achievements.add(AetherAchievements.defeatBronze);/*, "Like a Bossaru!", "Defeat the bronze boss");*/
        event.achievements.add(AetherAchievements.defeatSilver);/*, "Dethroned", "Defeat the silver boss");*/
        event.achievements.add(AetherAchievements.defeatGold);/*, "Extinguished", "Defeat the gold boss");*/
        event.achievements.add(AetherAchievements.enchanter);/*, "Do you believe in magic?", "Craft an enchanter");*/
        event.achievements.add(AetherAchievements.incubator);/*, "Now you're family", "Incubate a moa");*/
        event.achievements.add(AetherAchievements.blueCloud);/*, "To infinity and beyond!", "Bounce on a blue cloud");*/
        event.achievements.add(AetherAchievements.flyingPig);/*, "When phygs fly!", "Fly on a phyg");*/
        event.achievements.add(AetherAchievements.gravTools);/*, "Pink is the new blue", "Craft a gravitite tool");*/
        event.achievements.add(AetherAchievements.lore);/*, "The more you know!", "Read a book of lore");*/
        event.achievements.add(AetherAchievements.loreception);/*, "Lore-ception", "It's a book in a book in a book in...");*/
        final AchievementPage page = new AetherACPage();
        page.addAchievements(AetherAchievements.enterAether, AetherAchievements.defeatBronze, AetherAchievements.defeatSilver, AetherAchievements.defeatGold, AetherAchievements.enchanter, AetherAchievements.incubator, AetherAchievements.gravTools, AetherAchievements.blueCloud, AetherAchievements.flyingPig, AetherAchievements.lore, AetherAchievements.loreception);
    }

}
