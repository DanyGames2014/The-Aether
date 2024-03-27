package com.gildedgames.aether.block;

import com.gildedgames.aether.entity.tile.TileEntityIncubator;
import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.gui.container.ContainerIncubator;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

import static com.gildedgames.aether.AetherMod.of;

public class BlockIncubator extends TemplateBlockWithEntity {
    private final Random IncubatorRand;


    public BlockIncubator(final Identifier blockID) {
        super(blockID, Material.STONE);

        this.IncubatorRand = new Random();
    }

    @Override
    public void onPlaced(final World level, final int x, final int y, final int z) {
        super.onPlaced(level, x, y, z);
        this.setDefaultDirection(level, x, y, z);
    }

    private void setDefaultDirection(final World world, final int i, final int j, final int k) {
        if (world.isRemote) {
            return;
        }
        final int l = world.getBlockId(i, j, k - 1);
        final int i2 = world.getBlockId(i, j, k + 1);
        final int j2 = world.getBlockId(i - 1, j, k);
        final int k2 = world.getBlockId(i + 1, j, k);
        byte byte0 = 3;
        if (Block.BLOCKS_OPAQUE[l] && !Block.BLOCKS_OPAQUE[i2]) {
            byte0 = 3;
        }
        if (Block.BLOCKS_OPAQUE[i2] && !Block.BLOCKS_OPAQUE[l]) {
            byte0 = 2;
        }
        if (Block.BLOCKS_OPAQUE[j2] && !Block.BLOCKS_OPAQUE[k2]) {
            byte0 = 5;
        }
        if (Block.BLOCKS_OPAQUE[k2] && !Block.BLOCKS_OPAQUE[j2]) {
            byte0 = 4;
        }
        world.method_215(i, j, k, byte0);
    }

    @Override
    public int getTexture(final int side) {
        if (side == 1) {
            return TextureListener.sprIncubatorTop;
        }
        if (side == 0) {
            return TextureListener.sprIncubatorTop;
        }
        return TextureListener.sprIncubatorSide;
    }

    @Override
    public boolean onUse(final World level, final int x, final int y, final int z, final PlayerEntity player) {
        if (level.isRemote) {
            return true;
        }
        final TileEntityIncubator tileentityIncubator = (TileEntityIncubator) level.method_1777(x, y, z);
        GuiHelper.openGUI(player, of("incubator"), tileentityIncubator, new ContainerIncubator(player.inventory, tileentityIncubator));
        return true;
    }

    public static void updateIncubatorBlockState(final boolean flag, final World world, final int i, final int j, final int k) {
        final int l = world.getBlockMeta(i, j, k);
        final BlockEntity tileentity = world.method_1777(i, j, k);
        world.method_215(i, j, k, l);
        world.method_157(i, j, k, tileentity);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new TileEntityIncubator();
    }

    @Override
    public void onPlaced(final World level, final int x, final int y, final int z, final LivingEntity living) {
        final int l = MathHelper.floor(living.yaw * 4.0f / 360.0f + 0.5) & 0x3;
        if (l == 0) {
            level.method_215(x, y, z, 2);
        }
        if (l == 1) {
            level.method_215(x, y, z, 5);
        }
        if (l == 2) {
            level.method_215(x, y, z, 3);
        }
        if (l == 3) {
            level.method_215(x, y, z, 4);
        }
    }

    @Override
    public void onBreak(final World level, final int x, final int y, final int z) {
        final TileEntityIncubator tileentityIncubator = (TileEntityIncubator) level.method_1777(x, y, z);
        for (int l = 0; l < tileentityIncubator.size(); ++l) {
            final ItemStack itemstack = tileentityIncubator.getStack(l);
            if (itemstack != null) {
                final float f = this.IncubatorRand.nextFloat() * 0.8f + 0.1f;
                final float f2 = this.IncubatorRand.nextFloat() * 0.8f + 0.1f;
                final float f3 = this.IncubatorRand.nextFloat() * 0.8f + 0.1f;
                while (itemstack.count > 0) {
                    int i1 = this.IncubatorRand.nextInt(21) + 10;
                    if (i1 > itemstack.count) {
                        i1 = itemstack.count;
                    }
                    final ItemStack itemInstance = itemstack;
                    itemInstance.count -= i1;
                    final ItemEntity entityitem = new ItemEntity(level, x + f, y + f2, z + f3, new ItemStack(itemstack.itemId, i1, itemstack.getDamage()));
                    final float f4 = 0.05f;
                    entityitem.velocityX = (float) this.IncubatorRand.nextGaussian() * f4;
                    entityitem.velocityY = (float) this.IncubatorRand.nextGaussian() * f4 + 0.2f;
                    entityitem.velocityZ = (float) this.IncubatorRand.nextGaussian() * f4;
                    level.method_210(entityitem);
                }
            }
        }
        super.onBreak(level, x, y, z);
    }
}
