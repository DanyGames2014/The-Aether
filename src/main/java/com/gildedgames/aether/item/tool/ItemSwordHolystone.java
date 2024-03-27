package com.gildedgames.aether.item.tool;

import com.gildedgames.aether.registry.AetherItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ItemSwordHolystone extends TemplateSwordItem
{
    public ItemSwordHolystone(final @NotNull Identifier identifier, final ToolMaterial mat)
    {
        super(identifier, mat);
    }

    @Override
    public boolean postHit(final ItemStack itemstack, final LivingEntity damageSource, final LivingEntity damageTarget)
    {
        if (new Random().nextInt(25) == 0 && damageTarget != null && damageTarget instanceof PlayerEntity && (damageSource.hurtTime > 0 || damageSource.field_1041 > 0))
        {
            damageSource.method_1325(AetherItems.AmbrosiumShard.id, 1, 0.0f);
            itemstack.damage(1, damageTarget);
        }
        itemstack.damage(1, damageTarget);
        return true;
    }
}
