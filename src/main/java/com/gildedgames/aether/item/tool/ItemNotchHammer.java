package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.item.misc.ItemAether;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemNotchHammer extends ItemAether
{
    private int weaponDamage;

    public ItemNotchHammer(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxCount = 1;
        // field_1690 is IRON
        this.setMaxDamage(ToolMaterial.IRON.getDurability());
        this.weaponDamage = 4 + ToolMaterial.IRON.getAttackDamage() * 2;
    }

    @Override
    public float getMiningSpeedMultiplier(final ItemStack item, final Block tile)
    {
        return 1.5f;
    }

    @Override
    public boolean postHit(final ItemStack itemstack, final LivingEntity damageSource, final LivingEntity damageTarget)
    {
        itemstack.damage(1, damageTarget);
        return true;
    }

    @Override
    public boolean postMine(final ItemStack itemstack, final int i, final int j, final int k, final int l, final LivingEntity damageTarget)
    {
        itemstack.damage(2, damageTarget);
        return true;
    }

    @Override
    public int getAttackDamage(final Entity entity)
    {
        return this.weaponDamage;
    }

    @Override
    public ItemStack use(final ItemStack item, final World level, final PlayerEntity player)
    {
        item.damage(1, player);
        level.playSound((Entity) player, "mob.ghast.fireball", 1.0f, 1.0f / (ItemNotchHammer.random.nextFloat() * 0.4f + 0.8f));
        if (!level.isRemote)
        {
            /*TODO: NotchWave final EntityNotchWave notchwave = new EntityNotchWave(level, player);
            level.spawnEntity(notchwave);*/
        }
        return item;
    }

    @Override
    public boolean isHandheld()
    {
        return true;
    }
}
