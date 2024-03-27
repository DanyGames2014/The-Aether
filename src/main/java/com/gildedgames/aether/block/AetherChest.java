package com.gildedgames.aether.block;

import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Block;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateChestBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class AetherChest extends TemplateChestBlock {
    Random rand;

    public AetherChest(Identifier identifier) {
        super(identifier);
        rand = new Random();
    }

    private ItemStack getNormalLoot(final Random random) {
        final int item = random.nextInt(15);
        switch (item) {
            case 0: {
                return new ItemStack(AetherItems.PickZanite);
            }
            case 1: {
                return new ItemStack(AetherItems.Bucket, 1, 2);
            }
            case 2: {
                return new ItemStack(AetherItems.DartShooter);
            }
            case 3: {
                return new ItemStack(AetherItems.MoaEgg, 1, 0);
            }
            case 4: {
                return new ItemStack(AetherItems.AmbrosiumShard, random.nextInt(10) + 1);
            }
            case 5: {
                return new ItemStack(AetherItems.Dart, random.nextInt(5) + 1, 0);
            }
            case 6: {
                return new ItemStack(AetherItems.Dart, random.nextInt(3) + 1, 1);
            }
            case 7: {
                return new ItemStack(AetherItems.Dart, random.nextInt(3) + 1, 2);
            }
            case 8: {
                if (random.nextInt(20) == 0) {
                    return new ItemStack(AetherItems.BlueMusicDisk);
                }
                break;
            }
            case 9: {
                return new ItemStack(AetherItems.Bucket);
            }
            case 10: {
                if (random.nextInt(10) == 0) {
                    return new ItemStack(Item.ITEMS[Item.RECORD_THIRTEEN.id + random.nextInt(2)]);
                }
                break;
            }
            case 11: {
                if (random.nextInt(2) == 0) {
                    return new ItemStack(AetherItems.ZaniteBoots);
                }
                if (random.nextInt(2) == 0) {
                    return new ItemStack(AetherItems.ZaniteHelmet);
                }
                if (random.nextInt(2) == 0) {
                    return new ItemStack(AetherItems.ZaniteLeggings);
                }
                if (random.nextInt(2) == 0) {
                    return new ItemStack(AetherItems.ZaniteChestplate);
                }
                break;
            }
            case 12: {
                if (random.nextInt(4) == 0) {
                    return new ItemStack(AetherItems.IronPendant);
                }
            }
            case 13: {
                if (random.nextInt(10) == 0) {
                    return new ItemStack(AetherItems.GoldPendant);
                }
            }
            case 14: {
                if (random.nextInt(15) == 0) {
                    return new ItemStack(AetherItems.ZaniteRing);
                }
                break;
            }
        }
        return new ItemStack(AetherBlocks.AMBROSIUM_TORCH, random.nextInt(5));
    }

    @Override
    public boolean onUse(World level, int x, int y, int z, PlayerEntity player) {
        level.setBlock(x, y, z, Block.CHEST.id);

        final ChestBlockEntity chest = (ChestBlockEntity) level.method_1777(x, y, z);
        for (int i = 0; i < 3 + rand.nextInt(3); ++i) {
            final ItemStack item = this.getNormalLoot(rand);
            chest.setStack(rand.nextInt(chest.size()), item);
        }

        return Block.CHEST.onUse(level, x, y, z, player);
    }

    @Override
    public int getDroppedItemId(int i, Random random) {
        return CHEST.id;
    }

    @Override
    public void onPlaced(World arg, int i, int j, int k) {

    }
}
