package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.registry.AetherBlocks;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class BlockAetherLeaves extends TemplateBlock {

    private final boolean isGold;

    public BlockAetherLeaves(final Identifier id, boolean isGold) {
        super(id, Material.LEAVES);
        this.isGold = isGold;
        this.setTickRandomly(true);
    }

    @Override
    public int getTexture(int side, int meta) {
        return (isGold) ? TextureListener.sprGoldenOak : TextureListener.sprSkyroot;
    }

    @Override
    public int getDroppedItemCount(final Random rand) {
        if (this.id == AetherBlocks.GOLDEN_OAK_LEAVES.id) {
            return (rand.nextInt(60) == 0) ? 1 : 0;
        }
        return (rand.nextInt(40) == 0) ? 1 : 0;
    }

    @Override
    public int getDroppedItemId(final int meta, final Random rand) {
        if (this.id == AetherBlocks.SKYROOT_LEAVES.id) {
            return AetherBlocks.SKYROOT_SAPLING.id;
        }
        if (rand.nextInt(10) == 0) {
            return Item.GOLDEN_APPLE.id;
        }
        return AetherBlocks.GOLDEN_OAK_SAPLING.id;
    }

    @Override
    public void onBreak(final World level, final int x, final int y, final int z) {
        final int l = 1;
        final int i1 = l + 1;
        if (level.method_155(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
            for (int j1 = -l; j1 <= l; ++j1) {
                for (int k1 = -l; k1 <= l; ++k1) {
                    for (int l2 = -l; l2 <= l; ++l2) {
                        final int i2 = level.getBlockId(x + j1, y + k1, z + l2);
                        if (i2 == this.id) {
                            final int j2 = level.getBlockMeta(x + j1, y + k1, z + l2);
                            level.method_223(x + j1, y + k1, z + l2, j2 | 0x8);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onTick(final World level, final int x, final int y, final int z, final Random rand) {
        //if (level.isServerSide) {
        //    return;
        //}
        if (!this.nearTrunk(level, x, y, z)) {
            this.removeLeaves(level, x, y, z);
        }
    }

    private void removeLeaves(final World world, final int px, final int py, final int pz) {
        this.dropStacks(world, px, py, pz, world.getBlockMeta(px, py, pz));
        world.setBlock(px, py, pz, 0);
    }

    private boolean nearTrunk(final World world, final int px, final int py, final int pz) {
        return true;
        /*final Loc startLoc = new Loc(px, py, pz);
        final LinkedList<Loc> toCheck = (LinkedList<Loc>)new LinkedList();
        final ArrayList<Loc> checked = (ArrayList<Loc>)new ArrayList();
        toCheck.offer(new Loc(px, py, pz));
        final int bLeaves = this.id;
        while (!toCheck.isEmpty()) {
            final Loc curLoc = (Loc)toCheck.poll();
            if (checked.contains(curLoc)) {
                continue;
            }
            if (curLoc.distSimple(startLoc) <= 4) {
                final int block = curLoc.getBlock((BlockView)world);
                final int meta = curLoc.getMeta((BlockView)world);
                if (block == AetherBlocks.LOG.id && this.isMyTrunkMeta(meta)) {
                    return true;
                }
                if (block == bLeaves) {
                    toCheck.addAll((Collection)Arrays.asList((Object[])curLoc.adjacent()));
                }
            }
            System.out.println("nya");
            checked.add(curLoc);
        }
        return true;*/
    }

    private boolean isMyTrunkMeta(final int meta) {
        if (this.id == AetherBlocks.SKYROOT_LEAVES.id) {
            return meta <= 1;
        }
        return meta >= 2;
    }

    @Override
    protected int getDroppedItemMeta(final int meta) {
        return meta & 0x3;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public void afterBreak(final World level, final PlayerEntity playerBase, final int x, final int y, final int z, final int meta) {
        if (playerBase.getHand() != null && playerBase.getHand().itemId == Item.SHEARS.id) {
            playerBase.increaseStat(Stats.MINE_BLOCK[this.id], 1);
            this.dropStack(level, x, y, z, new ItemStack(this.id, 1, meta & 0x3));
        } else {
            super.afterBreak(level, playerBase, x, y, z, meta);
        }
    }

    @Override
    public boolean isSideVisible(final BlockView tileView, final int x, final int y, final int z, final int side) {
        final int i1 = tileView.getBlockId(x, y, z);
        return true;
    }
}
