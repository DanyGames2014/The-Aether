package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.effect.AetherPoison;
import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResultType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemSkyrootBucket extends TemplateItem
{
    public static int sprEmpty;
    public static int sprWater;
    public static int sprMilk;
    public static int sprPoison;
    public static int sprRemedy;

    public ItemSkyrootBucket(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.setHasSubtypes(true);
        this.maxCount = 1;
    }

    @Override
    public int getTextureId(final int damage)
    {
        if (damage == 3)
        {
            return ItemSkyrootBucket.sprRemedy;
        }
        if (damage == 2)
        {
            return ItemSkyrootBucket.sprPoison;
        }
        if (damage == 1)
        {
            return ItemSkyrootBucket.sprMilk;
        }
        if (damage == Block.FLOWING_WATER.id)
        {
            return ItemSkyrootBucket.sprWater;
        }
        return ItemSkyrootBucket.sprEmpty;
    }

    @Override
    public String getTranslationKey(final ItemStack item)
    {
        int i = item.getDamage();
        if (i > 3 && i != Block.FLOWING_WATER.id)
        {
            i = 0;
        }
        return this.getTranslationKey() + i;
    }

    @Override
    public ItemStack use(final ItemStack item, final World level, final PlayerEntity player)
    {
        final float f = 1.0f;
        final float f2 = player.prevPitch + (player.pitch - player.prevPitch) * f;
        final float f3 = player.prevYaw + (player.yaw - player.prevYaw) * f;
        final double d = player.prevX + (player.x - player.prevX) * f;
        final double d2 = player.prevY + (player.y - player.prevY) * f + 1.62 - player.eyeHeight;
        final double d3 = player.prevZ + (player.z - player.prevZ) * f;
        final Vec3d vec3d = Vec3d.createCached(d, d2, d3);
        final float f4 = MathHelper.cos(-f3 * 0.01745329f - 3.141593f);
        final float f5 = MathHelper.sin(-f3 * 0.01745329f - 3.141593f);
        final float f6 = -MathHelper.cos(-f2 * 0.01745329f);
        final float f7 = MathHelper.sin(-f2 * 0.01745329f);
        final float f8 = f5 * f6;
        final float f9 = f7;
        final float f10 = f4 * f6;
        final double d4 = 5.0;
        final Vec3d vec3d2 = vec3d.add(f8 * d4, f9 * d4, f10 * d4);
        final HitResult movingobjectposition = level.method_161(vec3d, vec3d2, item.getDamage() == 0);
        /* todo: inherit bucket?
        if (item.getDamage() == 2 && (MinecraftClientAccessor.getMCinstance().hitResult == null || MinecraftClientAccessor.getMCinstance().hitResult.field_1989 == null ||
        		!(MinecraftClientAccessor.getMCinstance().hitResult.field_1989 instanceof EntityAechorPlant))) {
            if (AetherPoison.afflictPoison()) {
                item.setDamage(0);
                return item;
            }
        }*/
        if (item.getDamage() == 3 && AetherPoison.curePoison())
        {
            item.setDamage(0);
            return item;
        }
        // (HitType) field_789 = TILE, field_790 = ENTITY
        if (movingobjectposition != null && movingobjectposition.type == HitResultType.BLOCK && (item.getDamage() == 0 || item.getDamage() == Block.FLOWING_WATER.id))
        {
            int i = movingobjectposition.blockX;
            int j = movingobjectposition.blockY;
            int k = movingobjectposition.blockZ;
            if (!level.method_171(player, i, j, k))
            {
                return item;
            }
            if (item.getDamage() == 0)
            {
                if (level.method_1779(i, j, k) == Material.WATER && level.getBlockMeta(i, j, k) == 0)
                {
                    level.setBlock(i, j, k, 0);
                    item.setDamage(Block.FLOWING_WATER.id);
                    return item;
                }
            }
            else
            {
                if (item.getDamage() <= 3 && item.getDamage() != 0)
                {
                    return new ItemStack(AetherItems.Bucket);
                }
                if (movingobjectposition.side == 0)
                {
                    --j;
                }
                if (movingobjectposition.side == 1)
                {
                    ++j;
                }
                if (movingobjectposition.side == 2)
                {
                    --k;
                }
                if (movingobjectposition.side == 3)
                {
                    ++k;
                }
                if (movingobjectposition.side == 4)
                {
                    --i;
                }
                if (movingobjectposition.side == 5)
                {
                    ++i;
                }
                if (level.method_234(i, j, k) || !level.method_1779(i, j, k).method_905())
                {
                    if (level.dimension.field_2176 && item.getDamage() == Block.FLOWING_WATER.id)
                    {
                        level.playSound(d + 0.5, d2 + 0.5, d3 + 0.5, "random.fizz", 0.5f, 2.6f + (level.field_214.nextFloat() - level.field_214.nextFloat()) * 0.8f);
                        for (int l = 0; l < 8; ++l)
                        {
                            level.addParticle("largesmoke", i + Math.random(), j + Math.random(), k + Math.random(), 0.0, 0.0, 0.0);
                        }
                    }
                    else
                    {
                        level.method_201(i, j, k, item.getDamage(), 0);
                    }
                    return new ItemStack(AetherItems.Bucket);
                }
            }
        }
        /* todo: more inherit bucket?
        else if (item.getDamage() == 0 && MinecraftClientAccessor.getMCinstance().hitResult != null && MinecraftClientAccessor.getMCinstance().hitResult.field_1989 != null && (MinecraftClientAccessor.getMCinstance().hitResult.field_1989 instanceof Cow || MinecraftClientAccessor.getMCinstance().hitResult.field_1989 instanceof EntityFlyingCow)) {
            item.setDamage(1);
            return item;
        }*/
        return item;
    }
}
