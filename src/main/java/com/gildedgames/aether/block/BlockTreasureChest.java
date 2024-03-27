package com.gildedgames.aether.block;

import com.gildedgames.aether.entity.tile.TileEntityTreasureChest;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.gui.container.ContainerTreasureChest;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.template.block.TemplateChestBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

import static com.gildedgames.aether.AetherMod.of;

public class BlockTreasureChest extends TemplateChestBlock {
    public BlockTreasureChest(Identifier identifier) {
        super(identifier);
    }

    @Override
    public int getTextureId(final BlockView tileView, final int x, final int y, final int z, final int meta) {
        if (meta == 1) {
            return 62;
        }
        if (meta == 0) {
            return 62;
        }
        final int i1 = tileView.getBlockId(x, y, z - 1);
        final int j1 = tileView.getBlockId(x, y, z + 1);
        final int k1 = tileView.getBlockId(x - 1, y, z);
        final int l1 = tileView.getBlockId(x + 1, y, z);
        byte byte0 = 3;
        if (Block.BLOCKS_OPAQUE[i1] && !Block.BLOCKS_OPAQUE[j1]) {
            byte0 = 3;
        }
        if (Block.BLOCKS_OPAQUE[j1] && !Block.BLOCKS_OPAQUE[i1]) {
            byte0 = 2;
        }
        if (Block.BLOCKS_OPAQUE[k1] && !Block.BLOCKS_OPAQUE[l1]) {
            byte0 = 5;
        }
        if (Block.BLOCKS_OPAQUE[l1] && !Block.BLOCKS_OPAQUE[k1]) {
            byte0 = 4;
        }
        return (meta != byte0) ? TextureListener.sprChestSide : TextureListener.sprChestFront;
    }

    @Override
    public int getTexture(final int side) {
        if (side == 1) {
            return 62;
        }
        if (side == 0) {
            return 62;
        }
        if (side == 3) {
            return TextureListener.sprChestFront;
        }
        return TextureListener.sprChestSide;
    }

    @Override
    public void onPlaced(World level, int x, int y, int z, int l) {
        Random rand = new Random();
        super.onPlaced(level, x, y, z, l);
        BlockEntity tileEntity = level.method_1777(x, y, z);
        if (tileEntity instanceof TileEntityTreasureChest treasureChest) {
            if (rand.nextBoolean()) {
                level.method_215(x, y, z, 5);
            } else {
                treasureChest.setRarity(rand.nextInt(4));
                if (treasureChest.rarity == 0)
                    level.method_215(x, y, z, 0);
                else
                    level.method_215(x, y, z, 1);
            }
        }
    }

    @Override
    public void onBreak(World arg, int i, int j, int k) {
        if (arg.getBlockMeta(i, j, k) == 0) super.onBreak(arg, i, j, k);
    }

    private ItemStack getGoldLoot(final Random random) {
        final int item = random.nextInt(8);
        switch (item) {
            case 0: {
                return new ItemStack(AetherItems.IronBubble);
            }
            case 1: {
                return new ItemStack(AetherItems.VampireBlade);
            }
            case 2: {
                return new ItemStack(AetherItems.PigSlayer);
            }
            case 3: {
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.PhoenixHelm);
                }
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.PhoenixLegs);
                }
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.PhoenixBody);
                }
                break;
            }
            case 4: {
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.PhoenixBoots);
                }
                return new ItemStack(AetherItems.PhoenixGlove);
            }
            case 5: {
                return new ItemStack(AetherItems.LifeShard);
            }
            case 6: {
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.GravititeHelmet);
                }
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.GravititePlatelegs);
                }
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.GravititeBodyplate);
                }
                break;
            }
            case 7: {
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.GravititeBoots);
                }
                return new ItemStack(AetherItems.GravititeGlove);
            }
        }
        //return null;
        return new ItemStack(AetherItems.ObsidianBody);
    }

    private ItemStack getSilverLoot(final Random random) {
        final int item = random.nextInt(9);
        switch (item) {
            case 0: {
                return new ItemStack(AetherItems.GummieSwet, random.nextInt(16));
            }
            case 1: {
                return new ItemStack(AetherItems.SwordLightning);
            }
            case 2: {
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.AxeValkyrie);
                }
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.ShovelValkyrie);
                }
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.PickValkyrie);
                }
                break;
            }
            case 3: {
                return new ItemStack(AetherItems.SwordHoly);
            }
            case 4: {
                return new ItemStack(AetherItems.GoldenFeather);
            }
            case 5: {
                return new ItemStack(AetherItems.RegenerationStone);
            }
            case 6: {
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.NeptuneHelmet);
                }
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.NeptuneLeggings);
                }
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.NeptuneChestplate);
                }
                break;
            }
            case 7: {
                if (random.nextBoolean()) {
                    return new ItemStack(AetherItems.NeptuneBoots);
                }
                return new ItemStack(AetherItems.NeptuneGlove);
            }
            case 8: {
                return new ItemStack(AetherItems.InvisibilityCloak);
            }
        }
        return new ItemStack(AetherItems.ZanitePendant);
    }

    private ItemStack getBronzeLoot(final Random random) {
        final int item = random.nextInt(7);
        switch (item) {
            case 0: {
                return new ItemStack(AetherItems.GummieSwet, random.nextInt(8), random.nextInt(2));
            }
            case 1: {
                return new ItemStack(AetherItems.PhoenixBow);
            }
            case 2: {
                return new ItemStack(AetherItems.SwordFire);
            }
            case 3: {
                return new ItemStack(AetherItems.HammerNotch);
            }
            case 4: {
                return new ItemStack(AetherItems.LightningKnife, random.nextInt(16));
            }
            case 5: {
                return new ItemStack(AetherItems.Lance);
            }
            case 6: {
                return new ItemStack(AetherItems.AgilityCape);
            }
            default: {
                return new ItemStack(AetherItems.Stick);
            }
        }
    }


    @Override
    public boolean onUse(final World level, final int x, final int y, final int z, final PlayerEntity player) {
        final int meta = level.getBlockMeta(x, y, z);
        BlockEntity tileEntity = level.method_1777(x, y, z);
        if (tileEntity instanceof TileEntityTreasureChest treasureChest) {
            if (meta == 5) {
                GuiHelper.openGUI(player, of("treasure_chest"), treasureChest, new ContainerTreasureChest(player.inventory, treasureChest));
                return true;
            }
            if (meta > 1) {
                ((TileEntityTreasureChest) tileEntity).setRarity(meta - 1);
                level.method_215(x, y, z, 1);
            }
            if (meta == 0) {
                GuiHelper.openGUI(player, of("treasure_chest"), treasureChest, new ContainerTreasureChest(player.inventory, treasureChest));
                return true;
            }
            if (level.isRemote) {
                return true;
            }
            final ItemStack itemstack = player.inventory.getSelectedItem();
            if (itemstack != null && meta == 1) {
                if (itemstack.itemId == AetherItems.Key.id && itemstack.getDamage() + 1 == treasureChest.rarity) {
                    player.inventory.removeStack(player.inventory.selectedSlot, 1);
                    level.method_215(x, y, z, 0);
                    Random rand = new Random();
                    switch (treasureChest.rarity) {
                        case 0:
                            break;
                        case 1:
                            for (int p = 0; p < 3 + rand.nextInt(3); ++p) {
                                final ItemStack item = this.getBronzeLoot(rand);
                                treasureChest.setStack(rand.nextInt(treasureChest.size()), item);
                            }
                            break;
                        case 2:
                            for (int p = 0; p < 3 + rand.nextInt(3); ++p) {
                                final ItemStack item = this.getSilverLoot(rand);
                                treasureChest.setStack(rand.nextInt(treasureChest.size()), item);
                            }
                            break;
                        case 3:
                            for (int p = 0; p < 3 + rand.nextInt(3); ++p) {
                                final ItemStack item = this.getGoldLoot(rand);
                                treasureChest.setStack(rand.nextInt(treasureChest.size()), item);
                            }
                            break;
                    }
                }
            }
        }
        return false;
    }

    public static void PlaceTreasureChest(World level, int x, int y, int z, int rarity) {
        // todo: make sure this works
        level.method_154(x, y, z, AetherBlocks.TREASURE_CHEST.id, 1 + rarity);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new TileEntityTreasureChest();
    }
}
