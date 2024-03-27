package com.gildedgames.aether.block;

import com.gildedgames.aether.event.listener.TextureListener;
import com.gildedgames.aether.level.dimension.AetherTravelAgent;
import com.gildedgames.aether.registry.AetherDimensions;
import net.minecraft.block.Block;
import net.minecraft.class_467;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.CustomPortal;
import net.modificationstation.stationapi.api.template.block.TemplateNetherPortalBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

import static com.gildedgames.aether.AetherMod.of;

public class AetherPortal extends TemplateNetherPortalBlock implements CustomPortal {

    public AetherPortal(Identifier identifier, int j) {
        super(identifier, j);
    }

    @Override
    public int getTexture(int side, int meta) {
        return TextureListener.sprPortal;
    }

    @Override
    public boolean method_736(World arg, int x, int y, int z) {
        byte var5 = 0;
        byte var6 = 0;
        if (arg.getBlockId(x - 1, y, z) == Block.GLOWSTONE.id || arg.getBlockId(x + 1, y, z) == Block.GLOWSTONE.id)
            var5 = 1;

        if (arg.getBlockId(x, y, z - 1) == Block.GLOWSTONE.id || arg.getBlockId(x, y, z + 1) == Block.GLOWSTONE.id)
            var6 = 1;

        if (var5 == var6)
            return false;
        else {
            if (arg.getBlockId(x - var5, y, z - var6) == 0) {
                x -= var5;
                z -= var6;
            }

            for (int var7 = -1; var7 <= 2; ++var7)
                for (int var8 = -1; var8 <= 3; ++var8) {
                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;
                    if (var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3) {
                        int var10 = arg.getBlockId(x + var5 * var7, y + var8, z + var6 * var7);
                        if (var9) {
                            if (var10 != Block.GLOWSTONE.id)
                                return false;
                        } else if (var10 != 0 && var10 != Block.WATER.id && var10 != Block.FLOWING_WATER.id)
                            return false;
                    }
                }

            arg.field_211 = true;

            for (int var11 = 0; var11 < 2; ++var11)
                for (int var12 = 0; var12 < 3; ++var12) {
                    arg.method_154(x + var5 * var11, y + var12, z + var6 * var11, id, (var5 + var6 * 2) - 1);
                    arg.method_243(x + var5 * var11, y + var12, z + var6 * var11);
                }

            arg.field_211 = false;
            return true;
        }
    }

    @Override
    public void neighborUpdate(World level, int x, int y, int z, int id) {
        byte var6 = 0;
        byte var7 = 1;
        if (level.getBlockId(x - 1, y, z) == this.id || level.getBlockId(x + 1, y, z) == this.id) {
            var6 = 1;
            var7 = 0;
        }

        int var8;
        var8 = y;
        while (level.getBlockId(x, var8 - 1, z) == this.id)
            --var8;

        if (level.getBlockId(x, var8 - 1, z) != Block.GLOWSTONE.id)
            level.setBlock(x, y, z, 0);
        else {
            int var9;
            var9 = 1;
            while (var9 < 4 && level.getBlockId(x, var8 + var9, z) == this.id)
                ++var9;

            if (var9 == 3 && level.getBlockId(x, var8 + var9, z) == Block.GLOWSTONE.id) {
                boolean var10 = level.getBlockId(x - 1, y, z) == this.id || level.getBlockId(x + 1, y, z) == this.id;
                boolean var11 = level.getBlockId(x, y, z - 1) == this.id || level.getBlockId(x, y, z + 1) == this.id;
                if (var10 && var11)
                    level.setBlock(x, y, z, 0);
                else if ((level.getBlockId(x + var6, y, z + var7) != Block.GLOWSTONE.id || level.getBlockId(x - var6, y, z - var7) != this.id) && (level.getBlockId(x - var6, y, z - var7) != Block.GLOWSTONE.id || level.getBlockId(x + var6, y, z + var7) != this.id))
                    level.setBlock(x, y, z, 0);
            } else
                level.setBlock(x, y, z, 0);
        }
    }

    @Override
    public void randomDisplayTick(World level, int x, int y, int z, Random rand) {
        if (rand.nextInt(100) == 0)
            level.playSound((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "portal.portal", 1.0F, rand.nextFloat() * 0.4F + 0.8F);

        for (int var6 = 0; var6 < 4; ++var6) {
            double var7 = (float) x + rand.nextFloat();
            double var9 = (float) y + rand.nextFloat();
            double var11 = (float) z + rand.nextFloat();
            double var13;
            double var15;
            double var17;
            int var19 = rand.nextInt(2) * 2 - 1;
            var13 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            var15 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            var17 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            if (level.getBlockId(x - 1, y, z) != this.id && level.getBlockId(x + 1, y, z) != this.id) {
                var7 = (double) x + 0.5D + 0.25D * (double) var19;
                var13 = rand.nextFloat() * 2.0F * (float) var19;
            } else {
                var11 = (double) z + 0.5D + 0.25D * (double) var19;
                var17 = rand.nextFloat() * 2.0F * (float) var19;
            }

            level.addParticle(of("aether_portal").toString(), var7, var9, var11, var13, var15, var17);
        }
    }

    @Override
    public Identifier getDimension(PlayerEntity player) {
        return AetherDimensions.THE_AETHER;
    }

    @Override
    public class_467 getTravelAgent(PlayerEntity player) {
        return new AetherTravelAgent();
    }
}
