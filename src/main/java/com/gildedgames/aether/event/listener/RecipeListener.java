package com.gildedgames.aether.event.listener;

import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class RecipeListener
{
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event)
    {
        RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);
        switch (type)
        {
            case CRAFTING_SHAPELESS:
                registerShapelessRecipes();
            case CRAFTING_SHAPED:
                registerShapedRecipes();
        }
    }

    private void registerShapedRecipes()
    {
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.IronRing), " C ", "C C", " C ", 'C', Item.IRON_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.GoldRing), " C ", "C C", " C ", 'C', Item.GOLD_INGOT);
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.Stick, 4), "#", "#", '#', AetherBlocks.SKYROOT_PLANKS);
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.PickHolystone, 1), new Object[]{"ZZZ", " Y ", " Y ", 'Z', AetherBlocks.HOLYSTONE, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.AxeHolystone, 1), new Object[]{"ZZ", "ZY", " Y", 'Z', AetherBlocks.HOLYSTONE, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ShovelHolystone, 1), new Object[]{"Z", "Y", "Y", 'Z', AetherBlocks.HOLYSTONE, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.SwordHolystone, 1), new Object[]{"Z", "Z", "Y", 'Z', AetherBlocks.HOLYSTONE, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.PickSkyroot, 1), new Object[]{"ZZZ", " Y ", " Y ", 'Z', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.AxeSkyroot, 1), new Object[]{"ZZ", "ZY", " Y", 'Z', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ShovelSkyroot, 1), new Object[]{"Z", "Y", "Y", 'Z', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.SwordSkyroot, 1), new Object[]{"Z", "Z", "Y", 'Z', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.Bucket, 1, 0), new Object[]{"Z Z", " Z ", 'Z', AetherBlocks.SKYROOT_PLANKS});
        CraftingRegistry.addShapedRecipe(new ItemStack(Block.CRAFTING_TABLE, 1), new Object[]{"UU", "UU", 'U', AetherBlocks.SKYROOT_PLANKS});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherBlocks.AMBROSIUM_TORCH, 2), new Object[]{" Z", " Y", 'Z', AetherItems.AmbrosiumShard, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.Dart, 1, 0), new Object[]{"X", "Z", "Y", 'X', AetherItems.GoldenAmber, 'Z', AetherItems.Stick, 'Y', Item.FEATHER});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.Dart, 8, 1), new Object[]{"XXX", "XZX", "XXX", 'X', new ItemStack(AetherItems.Dart, 1, 0), 'Z', new ItemStack(AetherItems.Bucket, 1, 2)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherBlocks.INCUBATOR, 1), new Object[]{"XXX", "XZX", "XXX", 'X', AetherBlocks.HOLYSTONE, 'Z', AetherBlocks.AMBROSIUM_TORCH});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherBlocks.FREEZER, 1), new Object[]{"XXX", "XZX", "YYY", 'X', AetherBlocks.HOLYSTONE, 'Z', AetherBlocks.ICESTONE, 'Y', AetherBlocks.SKYROOT_PLANKS});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherBlocks.ZANITE_BLOCK, 1), new Object[]{"XX", "XX", 'X', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.DartShooter, 1), new Object[]{"X", "X", "Y", 'X', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.SADDLE, 1), new Object[]{"XXX", "XZX", 'X', Item.LEATHER, 'Z', Item.STRING});
        CraftingRegistry.addShapedRecipe(new ItemStack(Block.CHEST, 1), new Object[]{"PPP", "P P", "PPP", 'P', AetherBlocks.SKYROOT_PLANKS});
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.WOODEN_DOOR), new Object[]{"PP", "PP", "PP", 'P', AetherBlocks.SKYROOT_PLANKS});
        CraftingRegistry.addShapedRecipe(new ItemStack(Block.FENCE, 2), new Object[]{"SSS", "SSS", 'S', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(Block.LADDER, 4), new Object[]{"S S", "SSS", "S S", 'S', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(Block.JUKEBOX), new Object[]{"PPP", "PGP", "PPP", 'P', AetherBlocks.SKYROOT_PLANKS, 'G', AetherBlocks.ENCHANTED_GRAVITITE});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherBlocks.ENCHANTER), new Object[]{"HHH", "HZH", "HHH", 'H', AetherBlocks.HOLYSTONE, 'Z', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.IronPendant), new Object[]{"SSS", "S S", " C ", 'S', Item.STRING, 'C', Item.IRON_INGOT});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.GoldPendant), new Object[]{"SSS", "S S", " C ", 'S', Item.STRING, 'C', Item.GOLD_INGOT});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ZanitePendant), new Object[]{"SSS", "S S", " C ", 'S', Item.STRING, 'C', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ZaniteRing), new Object[]{" C ", "C C", " C ", 'C', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.PickZanite, 1), new Object[]{"ZZZ", " Y ", " Y ", 'Z', AetherItems.Zanite, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.AxeZanite, 1), new Object[]{"ZZ", "ZY", " Y", 'Z', AetherItems.Zanite, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ShovelZanite, 1), new Object[]{"Z", "Y", "Y", 'Z', AetherItems.Zanite, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.SwordZanite, 1), new Object[]{"Z", "Z", "Y", 'Z', AetherItems.Zanite, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.LeatherGlove), new Object[]{"C C", 'C', Item.LEATHER});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.IronGlove), new Object[]{"C C", 'C', Item.IRON_INGOT});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.GoldGlove), new Object[]{"C C", 'C', Item.GOLD_INGOT});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.DiamondGlove), new Object[]{"C C", 'C', Item.DIAMOND});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ZaniteGlove), new Object[]{"C C", 'C', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.GravititeGlove), new Object[]{"C C", 'C', AetherBlocks.ENCHANTED_GRAVITITE});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.PickGravitite, 1), new Object[]{"ZZZ", " Y ", " Y ", 'Z', AetherBlocks.ENCHANTED_GRAVITITE, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.AxeGravitite, 1), new Object[]{"ZZ", "ZY", " Y", 'Z', AetherBlocks.ENCHANTED_GRAVITITE, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ShovelGravitite, 1), new Object[]{"Z", "Y", "Y", 'Z', AetherBlocks.ENCHANTED_GRAVITITE, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.SwordGravitite, 1), new Object[]{"Z", "Z", "Y", 'Z', AetherBlocks.ENCHANTED_GRAVITITE, 'Y', AetherItems.Stick});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.WhiteCape, 1), new Object[]{"XX", "XX", "XX", 'X', new ItemStack(Block.WOOL, 1, 0)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.RedCape, 1), new Object[]{"XX", "XX", "XX", 'X', new ItemStack(Block.WOOL, 1, 14)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.BlueCape, 1), new Object[]{"XX", "XX", "XX", 'X', new ItemStack(Block.WOOL, 1, 11)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.BlueCape, 1), new Object[]{"XX", "XX", "XX", 'X', new ItemStack(Block.WOOL, 1, 3)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.BlueCape, 1), new Object[]{"XX", "XX", "XX", 'X', new ItemStack(Block.WOOL, 1, 9)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.YellowCape, 1), new Object[]{"XX", "XX", "XX", 'X', new ItemStack(Block.WOOL, 1, 4)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.CloudParachute), new Object[]{"CC", "CC", 'C', new ItemStack(AetherBlocks.AERCLOUD, 1, 0)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.CloudParachuteGold), new Object[]{"CC", "CC", 'C', new ItemStack(AetherBlocks.AERCLOUD, 1, 2)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.DartShooter, 1), new Object[]{"X", "X", "Y", 'X', AetherBlocks.SKYROOT_PLANKS, 'Y', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.CloudParachute, 1), new Object[]{"UU", "UU", 'U', new ItemStack(AetherBlocks.AERCLOUD, 0)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.CloudParachuteGold, 1), new Object[]{"UU", "UU", 'U', new ItemStack(AetherBlocks.AERCLOUD, 2)});
        CraftingRegistry.addShapedRecipe(new ItemStack(Item.SADDLE, 1), new Object[]{"XXX", "XZX", 'X', Item.LEATHER, 'Z', Item.STRING});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.NatureStaff, 1), new Object[]{"Y", "X", 'X', AetherItems.Stick, 'Y', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.GravititeHelmet, 1), new Object[]{"XXX", "X X", 'X', AetherBlocks.ENCHANTED_GRAVITITE});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.GravititeBodyplate, 1), new Object[]{"X X", "XXX", "XXX", 'X', AetherBlocks.ENCHANTED_GRAVITITE});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.GravititePlatelegs, 1), new Object[]{"XXX", "X X", "X X", 'X', AetherBlocks.ENCHANTED_GRAVITITE});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.GravititeBoots, 1), new Object[]{"X X", "X X", 'X', AetherBlocks.ENCHANTED_GRAVITITE});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ZaniteHelmet, 1), new Object[]{"XXX", "X X", 'X', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ZaniteChestplate, 1), new Object[]{"X X", "XXX", "XXX", 'X', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ZaniteLeggings, 1), new Object[]{"XXX", "X X", "X X", 'X', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.ZaniteBoots, 1), new Object[]{"X X", "X X", 'X', AetherItems.Zanite});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.CloudParachute), new Object[]{"CC", "CC", 'C', new ItemStack(AetherBlocks.AERCLOUD, 1, 0)});
        CraftingRegistry.addShapedRecipe(new ItemStack(AetherItems.CloudParachuteGold), new Object[]{"CC", "CC", 'C', new ItemStack(AetherBlocks.AERCLOUD, 1, 2)});
    }

    private void registerShapelessRecipes()
    {
        CraftingRegistry.addShapelessRecipe(new ItemStack(AetherBlocks.SKYROOT_PLANKS, 4), new ItemStack(AetherBlocks.SKYROOT_LOG));
        CraftingRegistry.addShapelessRecipe(new ItemStack(AetherBlocks.SKYROOT_PLANKS, 4), new ItemStack(AetherBlocks.GOLDEN_OAK_LOG));
        CraftingRegistry.addShapelessRecipe(new ItemStack(AetherItems.DartShooter, 1, 1), new Object[]{new ItemStack(AetherItems.DartShooter, 1, 0), AetherItems.AechorPetal});
        CraftingRegistry.addShapelessRecipe(new ItemStack(AetherItems.Zanite, 4), new Object[]{AetherBlocks.ZANITE_BLOCK});
        CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DYE, 2, 5), new Object[]{AetherBlocks.PURPLE_FLOWER});
    }
}
