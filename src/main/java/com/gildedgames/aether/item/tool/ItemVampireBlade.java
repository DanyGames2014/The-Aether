package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.item.misc.ItemAether;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ItemVampireBlade extends ItemAether
{
    private int weaponDamage;
    private static Random random;

    public ItemVampireBlade(final @NotNull Identifier identifier)
    {
        super(identifier);
        this.maxCount = 1;
        this.setMaxDamage(ToolMaterial.DIAMOND.getDurability()); // (ToolMaterial) field_1691 = emerald
        this.weaponDamage = 4 + ToolMaterial.DIAMOND.getAttackDamage() * 2;
    }

    @Override
    public float getMiningSpeedMultiplier(final ItemStack item, final Block tile)
    {
        return 1.5f;
    }

    @Override
    public boolean postHit(final ItemStack itemstack, final LivingEntity damageSource, final LivingEntity damageTarget)
    {
        final PlayerEntity player = (PlayerEntity) damageTarget;
        if (player.health < AetherMod.getPlayerHandler(player).maxHealth)
        {
            final PlayerEntity playerBase = player;
            ++playerBase.health;
        }
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
    public boolean isHandheld()
    {
        return true;
    }

    static
    {
        ItemVampireBlade.random = new Random();
    }
}
