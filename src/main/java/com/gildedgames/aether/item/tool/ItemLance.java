package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.hit.HitResultType;
import net.modificationstation.stationapi.api.item.CustomReachProvider;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ItemLance extends TemplateItem implements CustomReachProvider
{
    private int weaponDamage;

    public ItemLance(final @NotNull Identifier identifier, final ToolMaterial enumtoolmaterial)
    {
        super(identifier);
        this.maxCount = 1;
        this.setMaxDamage(enumtoolmaterial.getDurability());
        this.weaponDamage = 4 + enumtoolmaterial.getAttackDamage() * 2;
    }

    @Override
    public float getMiningSpeedMultiplier(final ItemStack item, final Block tile)
    {
        return (tile.id != Block.COBWEB.id) ? 1.5f : 15.0f;
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
    public boolean isHandheld()
    {
        return true;
    }

    @Override
    public boolean isSuitableFor(final Block tile)
    {
        return tile.id == Block.COBWEB.id;
    }

    public boolean reachItemMatches(final ItemStack itemstack)
    {
        return itemstack != null && itemstack.itemId == AetherItems.Lance.id;
    }

    @Override
    public double getReach(ItemStack itemInstance, PlayerEntity player, HitResultType type, double currentReach)
    {
        return 10.f;
    }
}
